package com.louvre2489.fp.application.query

import com.louvre2489.fp.domain.entity.SubSystemInfo
import com.louvre2489.fp.domain.value.{ SubSystemId, SystemId }
import com.louvre2489.fp.repository.SubSystemRepository

class QuerySubSystem(subSystemRepository: SubSystemRepository[SubSystemInfo, SubSystemId]) {

  def findAll: List[SubSystemInfo] = subSystemRepository.findAll

  def findBySystemId(systemId: SystemId): List[SubSystemInfo] = subSystemRepository.findBySystemId(systemId)
}
