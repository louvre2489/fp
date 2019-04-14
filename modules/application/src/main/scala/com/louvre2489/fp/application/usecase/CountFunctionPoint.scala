package com.louvre2489.fp.application.usecase

import com.louvre2489.fp.domain.entity.Function
import com.louvre2489.fp.domain.value.FunctionId
import com.louvre2489.fp.repository.FunctionRepository

trait CountFunctionPointInterface {}

class CountFunctionPoint(functionRepository: FunctionRepository[Function, FunctionId])
    extends CountFunctionPointInterface {}
