package com.louvre2489.fp.rdb.repository

import com.louvre2489.fp.domain.entity.{ GSC, SystemInfo, UserInfo }
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
      User
        .find(id.value)
        .map { data =>
          UserInfo(UserId(data.userId), data.password, data.userName, true, Some(data.hashedUserId), Some(data.salt))(
            this
          )
        }
    }
  }

  @Override
  override def findAll: List[UserInfo] = {

    db readOnly { implicit session =>
      User
        .findAll()
        .map { data =>
          UserInfo(UserId(data.userId), data.password, data.userName, true, Some(data.hashedUserId), Some(data.salt))(
            this
          )
        }
    }
  }

  @Override
  def save(entity: UserInfo): Either[Exception, Unit] = {
    try {
      User(entity.userId.value, entity.password, entity.userName, entity.hashedUserId.get, entity.salt.get).save()
      Right(Unit)
    } catch {
      case e: Exception =>
        Left(e)
    }
  }
}
