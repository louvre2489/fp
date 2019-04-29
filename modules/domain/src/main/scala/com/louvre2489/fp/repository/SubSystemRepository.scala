package com.louvre2489.fp.repository

import com.louvre2489.fp.domain.value.SystemId
import com.louvre2489.fp.repository.base._

trait SubSystemRepository[A, ID] extends ListItemRepository[A, ID] with HasIDRepository[A, ID] {

  def findBySystemId(systemId: SystemId): List[A]
}
