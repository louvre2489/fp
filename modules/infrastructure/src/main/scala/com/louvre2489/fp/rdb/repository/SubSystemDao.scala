package com.louvre2489.fp.rdb.repository

import com.louvre2489.fp.domain.entity.SubSystemInfo
import com.louvre2489.fp.domain.value.{ SubSystemId, SystemId }
import com.louvre2489.fp.rdb.db.{ BaseDao, BaseDaoObject }
import com.louvre2489.fp.repository.SubSystemRepository
import com.louvre2489.fp.rdb.models._
import scalikejdbc._

object SubSystemDao extends BaseDaoObject {

  def apply()(implicit db: DB) = new SubSystemDao

}

class SubSystemDao(implicit db: DB) extends BaseDao with SubSystemRepository[SubSystemInfo, SubSystemId] {

  /**
    * Query DSLでカスタム型を使用するためのパラメーター
    */
  private implicit val systemIdBinder = ParameterBinderFactory[SystemId] { value => (stmt, idx) =>
    stmt.setLong(idx, value.value)
  }

  @Override
  def findById(id: SubSystemId): Option[SubSystemInfo] = {

    db readOnly { implicit session =>
      SubSystem
        .find(id.value)
        .map { data =>
          SubSystemInfo(SubSystemId(data.subSystemId), data.subSystemName, SystemId(data.systemId))(this)
        }
    }
  }

  @Override
  def findBySystemId(systemId: SystemId): List[SubSystemInfo] = {

    import SubSystem._

    db readOnly { implicit session =>
      withSQL {
        select
          .from(SubSystem as mss).where.eq(mss.systemId, systemId)
      }.map(SubSystem(mss.resultName)).list.apply().map { data =>
        SubSystemInfo(SubSystemId(data.subSystemId), data.subSystemName, SystemId(data.systemId))(this)
      }
    }
  }

  @Override
  override def findAll: List[SubSystemInfo] = {

    db readOnly { implicit session =>
      SubSystem
        .findAll()
        .map { data =>
          SubSystemInfo(SubSystemId(data.subSystemId), data.subSystemName, SystemId(data.systemId))(this)
        }
    }
  }

  @Override
  def save(entity: SubSystemInfo): Either[Exception, Unit] = {
    try {
      SubSystem(entity.subSystemId.value, entity.subSystemName, entity.systemId.value).save()
      Right(Unit)
    } catch {
      case e: Exception =>
        Left(e)
    }
  }
}
