package com.louvre2489.fp.application.query

import com.louvre2489.fp.domain.entity.SystemInfo
import com.louvre2489.fp.domain.value.SystemId
import com.louvre2489.fp.repository.SystemRepository

class QuerySystem(systemRepository: SystemRepository[SystemInfo, SystemId]) {

  /**
    * 登録されているシステムの一覧を取得する
    * @return システム一覧
    */
  def search: List[SystemInfo] = systemRepository.getAll
}
