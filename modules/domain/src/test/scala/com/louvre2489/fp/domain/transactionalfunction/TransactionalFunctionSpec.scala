package com.louvre2489.fp.domain.transactionalfunction

import com.louvre2489.fp.domain.value.UnadjustedFunctionPoint
import com.louvre2489.fp.domain.{ Average, High, Low }
import org.scalatest.FlatSpec

class TransactionalFunctionSpec extends FlatSpec {

  behavior of "EI"

  "EI and Low Complexity" should "be 3 FP" in {
    assert(TransactionalFunction.calculate(EI, Low) === UnadjustedFunctionPoint(3))
  }

  "EI and Average Complexity" should "be 4 FP" in {
    assert(TransactionalFunction.calculate(EI, Average) === UnadjustedFunctionPoint(4))
  }

  "EI and High Complexity" should "be 6 FP" in {
    assert(TransactionalFunction.calculate(EI, High) === UnadjustedFunctionPoint(6))
  }

  behavior of "EO"

  "EO and Low Complexity" should "be 4 FP" in {
    assert(TransactionalFunction.calculate(EO, Low) === UnadjustedFunctionPoint(4))
  }

  "EO and Average Complexity" should "be 5 FP" in {
    assert(TransactionalFunction.calculate(EO, Average) === UnadjustedFunctionPoint(5))
  }

  "EO and High Complexity" should "be 7 FP" in {
    assert(TransactionalFunction.calculate(EO, High) === UnadjustedFunctionPoint(7))
  }

  behavior of "EQ"

  "EQ and Low Complexity" should "be 3 FP" in {
    assert(TransactionalFunction.calculate(EQ, Low) === UnadjustedFunctionPoint(3))
  }

  "EQ and Average Complexity" should "be 4 FP" in {
    assert(TransactionalFunction.calculate(EQ, Average) === UnadjustedFunctionPoint(4))
  }

  "EQ and High Complexity" should "be 6 FP" in {
    assert(TransactionalFunction.calculate(EQ, High) === UnadjustedFunctionPoint(6))
  }
}
