package com.louvre2489.fp.infrastructure.rdb.repository

import com.louvre2489.fp.domain.entity.SubSystemInfo
import com.louvre2489.fp.domain.value.SubSystemId
import com.louvre2489.fp.repository.SubSystemRepository

object SubSystemDao extends SubSystemRepository[SubSystemInfo, SubSystemId] {

  def findById(id: SubSystemId): Option[SubSystemInfo] = {

    val subSystem = SubSystemInfo(SubSystemId(123), "SUB")(this)
    Some(subSystem)
  }

  def save(entity: SubSystemInfo): Either[Exception, Unit] = {
    Left(new Exception)
  }
}
