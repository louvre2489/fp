package com.louvre2489.fp.rdb.repository

import com.louvre2489.fp.domain.entity.GSC
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.repository.GSCRepository

object GSCDao extends GSCRepository[GSC, SystemId] {

  @Override
  def findById(id: SystemId): Option[GSC] = {

    val gscInfo = GSC()(this)
    Some(gscInfo)
  }

  @Override
  def save(entity: GSC): Either[Exception, Unit] = {
    Left(new Exception)
  }
}
