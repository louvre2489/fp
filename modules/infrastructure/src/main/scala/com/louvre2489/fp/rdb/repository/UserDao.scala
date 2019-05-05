package com.louvre2489.fp.rdb.repository

import com.louvre2489.fp.domain.entity.{ GSC, SystemInfo }
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.rdb.db.{ BaseDao, BaseDaoObject }
import com.louvre2489.fp.repository.{ SystemRepository, UserRepository }
import com.louvre2489.fp.rdb.models._
import scalikejdbc.DB

object UserDao extends BaseDaoObject {

  def apply()(implicit db: DB) = new UserDao
}

class UserDao(implicit db: DB) extends BaseDao with UserRepository[UserInfo, UserId] {

  @Override
  def findById(id: UserId): Option[UserInfo] = {

    db readOnly { implicit session =>
      System
        .find(id.value)
        .map { data =>
          SystemInfo(SystemId(data.systemId), data.systemName, GSC(SystemId(data.systemId), Version(1))(GSCDao()))(this)
        }
    }
  }

  @Override
  override def findAll: List[SystemInfo] = {

    db readOnly { implicit session =>
      System
        .findAll()
        .map { data =>
          SystemInfo(SystemId(data.systemId), data.systemName, GSC(SystemId(data.systemId), Version(1))(GSCDao()))(this)
        }
    }
  }

  @Override
  def save(entity: SystemInfo): Either[Exception, Unit] = {
    try {
      System(entity.systemId.value, entity.systemName).save()
      Right(Unit)
    } catch {
      case e: Exception =>
        Left(e)
    }
  }
}

object SystemAttr {
  val col_system_id: String   = "system_id"
  val col_system_name: String = "system_name"
}
