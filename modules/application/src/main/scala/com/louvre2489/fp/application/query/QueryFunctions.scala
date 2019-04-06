package com.louvre2489.fp.application.query

import com.louvre2489.fp.domain.entity.{ Function, Functions }
import com.louvre2489.fp.domain.value.FunctionId
import com.louvre2489.fp.repository.FunctionRepository

class QueryFunctions(functionRepository: FunctionRepository[Function, FunctionId]) {

  /**
    * 登録されている機能の一覧を取得する
    * @return 機能一覧
    */
  def search: Functions = Functions(functionRepository.getAll)
}
