package com.louvre2489.fp.infrastructure.rdb.repository

import com.louvre2489.fp.domain.entity.GSC
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.repository.repositoryInterface.GSCRepositoryInterface

object GSCDao extends GSCRepositoryInterface[GSC, SystemId] {

  @Override
  def findById(id: SystemId): Some[GSC] = {

    val gscInfo = GSC()(this)
    Some(gscInfo)
  }

  @Override
  def save(entity: GSC): Either[Exception, Unit] = {
    Left(new Exception)
  }
}
