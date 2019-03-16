package com.louvre2489.fp.domain.datafunction

import com.louvre2489.fp.domain.value.UnadjustedFunctionPoint
import com.louvre2489.fp.domain.{ Average, High, Low }
import org.scalatest.FlatSpec

class DataFunctionSpec extends FlatSpec {

  behavior of "EIF"

  "EIF and Low Complexity" should "be 5 FP" in {
    assert(DataFunction.calculate(EIF, Low) === UnadjustedFunctionPoint(5))
  }

  "EIF and Average Complexity" should "be 7 FP" in {
    assert(DataFunction.calculate(EIF, Average) === UnadjustedFunctionPoint(7))
  }

  "EIF and High Complexity" should "be 10 FP" in {
    assert(DataFunction.calculate(EIF, High) === UnadjustedFunctionPoint(10))
  }

  behavior of "ILF"

  "ILF and Low Complexity" should "be 7 FP" in {
    assert(DataFunction.calculate(ILF, Low) === UnadjustedFunctionPoint(7))
  }

  "ILF and Average Complexity" should "be 10 FP" in {
    assert(DataFunction.calculate(ILF, Average) === UnadjustedFunctionPoint(10))
  }

  "ILF and High Complexity" should "be 15 FP" in {
    assert(DataFunction.calculate(ILF, High) === UnadjustedFunctionPoint(15))
  }
}
