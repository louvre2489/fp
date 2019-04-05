package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.domain.value.{ SubSystemId, SystemId }
import com.louvre2489.fp.repository.repositoryInterface.{ SubSystemRepositoryInterface, SystemRepositoryInterface }

/**
  * Management attributes of the system
  * @param systemId ID
  * @param systemName name of the system
  * @param gsc global system characteristics
  * @param repository system repository
  */
case class SystemInfo(systemId: SystemId, systemName: String, gsc: GSC)(
    implicit repository: SystemRepositoryInterface[SystemInfo, SystemId]
) extends Entity[SystemId] {

  @Override
  def getAll: List[SystemInfo] = repository.getAll

  @Override
  def findById(id: SystemId): Option[SystemInfo] = repository.findById(id)

  @Override
  def save: Either[Exception, Unit] =
    repository.save(this)
}

/**
  * Management attributes of the sub system
  * @param subSystemId ID
  * @param subSystemName name of the sub system
  * @param systemInfo SystemInfo object
  * @param repository sub system repository
  */
case class SubSystemInfo(subSystemId: SubSystemId, subSystemName: String, systemInfo: SystemInfo)(
    implicit repository: SubSystemRepositoryInterface[SubSystemInfo, SubSystemId]
) extends Entity[SubSystemId] {

  @Override
  def getAll: List[SubSystemInfo] = repository.getAll

  @Override
  def findById(id: SubSystemId): Option[SubSystemInfo] = repository.findById(id)

  @Override
  def save: Either[Exception, Unit] =
    repository.save(this)
}
