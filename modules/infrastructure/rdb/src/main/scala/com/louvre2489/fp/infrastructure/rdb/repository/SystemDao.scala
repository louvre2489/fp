package com.louvre2489.fp.infrastructure.rdb.repository

import com.louvre2489.fp.domain.entity.{ GSC, SystemInfo }
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.repository.SystemRepository

object SystemDao extends SystemRepository[SystemInfo, SystemId] {

  def findById(id: SystemId): Some[SystemInfo] = {

    val systemInfo = SystemInfo(SystemId(789), "SYS", GSC())(this)
    Some(systemInfo)
  }

  @Override
  def save(entity: SystemInfo): Either[Exception, Unit] = {
    Left(new Exception)
  }
}
