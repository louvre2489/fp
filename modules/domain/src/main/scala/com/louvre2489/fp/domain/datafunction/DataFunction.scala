package com.louvre2489.fp.domain.datafunction

import com.louvre2489.fp.domain._
import com.louvre2489.fp.domain.value.UnadjustedFunctionPoint

object DataFunction {

  def calculate(fileType: FileType, complexity: Complexity): UnadjustedFunctionPoint = (fileType, complexity) match {
    case (EIF, Low)     => UnadjustedFunctionPoint(5)
    case (ILF, Low)     => UnadjustedFunctionPoint(7)
    case (EIF, Average) => UnadjustedFunctionPoint(7)
    case (ILF, Average) => UnadjustedFunctionPoint(10)
    case (EIF, High)    => UnadjustedFunctionPoint(10)
    case (ILF, High)    => UnadjustedFunctionPoint(15)
  }
}
