package com.louvre2489.fp.repository

import com.louvre2489.fp.domain.entity.GSC
import com.louvre2489.fp.domain.value.{ SystemId, Version }
import com.louvre2489.fp.repository.base.BaseRepository

trait GSCRepository[A, ID] extends BaseRepository[A, ID] {

  def find(id: SystemId, version: Version): Option[GSC]
}
