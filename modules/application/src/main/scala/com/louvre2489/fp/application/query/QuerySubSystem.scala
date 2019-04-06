package com.louvre2489.fp.application.query

import com.louvre2489.fp.domain.entity.SubSystemInfo
import com.louvre2489.fp.domain.value.SubSystemId
import com.louvre2489.fp.repository.SubSystemRepository

class QuerySubSystem(subSystemRepository: SubSystemRepository[SubSystemInfo, SubSystemId]) {

  def search: List[SubSystemInfo] = subSystemRepository.getAll
}
