package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.domain.value.{ SubSystemId, SystemId }
import com.louvre2489.fp.repository.{ SubSystemRepository, SystemRepository }
import com.louvre2489.fp.repository.SystemRepository

/**
  * Management attributes of the system
  * @param systemId ID
  * @param systemName name of the system
  * @param gsc global system characteristics
  * @param repository system repository
  */
case class SystemInfo(systemId: SystemId, systemName: String, gsc: GSC)(
    implicit repository: SystemRepository[SystemInfo, SystemId]
) extends Entity[SystemId] {

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
    implicit repository: SubSystemRepository[SubSystemInfo, SubSystemId]
) extends Entity[SubSystemId] {

  @Override
  def save: Either[Exception, Unit] =
    repository.save(this)
}
