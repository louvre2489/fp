package com.louvre2489.fp.infrastructure.rdb.repository

import com.louvre2489.fp.domain.entity.{ GSC, SubSystemInfo, SystemInfo }
import com.louvre2489.fp.domain.value.{ SubSystemId, SystemId }
import com.louvre2489.fp.repository.SubSystemRepository

object SubSystemDao extends SubSystemRepository[SubSystemInfo, SubSystemId] {

  @Override
  def findById(id: SubSystemId): Option[SubSystemInfo] = {

    val systemInfo = SystemInfo(SystemId(789), "SYS", GSC()(GSCDao))(SystemDao)
    val subSystem  = SubSystemInfo(SubSystemId(123), "SUB", systemInfo)(this)
    Some(subSystem)
  }

  @Override
  override def getAll: List[SubSystemInfo] = Nil

  @Override
  def save(entity: SubSystemInfo): Either[Exception, Unit] = {
    Left(new Exception)
  }
}
