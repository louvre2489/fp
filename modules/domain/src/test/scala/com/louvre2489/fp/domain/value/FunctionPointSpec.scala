package com.louvre2489.fp.domain.value

import org.scalatest.FlatSpec

class FunctionPointSpec extends FlatSpec {

  "UnadjustedFunctionPoint" can "plus UnadjustedFunctionPoint" in {

    val plusValue = UnadjustedFunctionPoint(1) + UnadjustedFunctionPoint(2)
    assert(plusValue.value === 3)
  }

  it can "minus UnadjustedFunctionPoint" in {

    val minusValue = UnadjustedFunctionPoint(5) - UnadjustedFunctionPoint(4)
    assert(minusValue.value === 1)
  }

  it can "multiple VAF, then the result is adjusted function point" in {

    val adjustedFunctionPoint = UnadjustedFunctionPoint(5) * ValueAdjustmentFactor(1.2)
    assert(adjustedFunctionPoint.value === BigDecimal(6.0))
  }

  "AdjustedFunctionPoint" can "plus AdjustedFunctionPoint" in {

    val plusValue = AdjustedFunctionPoint(1) + AdjustedFunctionPoint(2)
    assert(plusValue.value === 3)
  }
}
