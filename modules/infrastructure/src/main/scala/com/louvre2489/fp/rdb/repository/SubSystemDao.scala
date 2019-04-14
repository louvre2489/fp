package com.louvre2489.fp.rdb.repository

import com.louvre2489.fp.domain.entity.SubSystemInfo
import com.louvre2489.fp.domain.value.{ SubSystemId, SystemId }
import com.louvre2489.fp.rdb.db.BaseDao
import com.louvre2489.fp.repository.SubSystemRepository
import com.louvre2489.fp.rdb.models._

object SubSystemDao extends BaseDao with SubSystemRepository[SubSystemInfo, SubSystemId] {

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
