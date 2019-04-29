package com.louvre2489.fp.domain.transactionalfunction

import com.louvre2489.fp.domain._
import com.louvre2489.fp.domain.value.{ DET, FTR }
import org.scalatest.FlatSpec

class TransactionalFunctionComplexitySpec extends FlatSpec {

  behavior of "Few FTR"

  behavior of "Few DET"
  "1 DET and 0 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(1), FTR(0)) === Low)
  }

  "1 DET and 1 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(1), FTR(1)) === Low)
  }

  "4 DET and 0 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(4), FTR(0)) === Low)
  }

  "4 DET and 1 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(4), FTR(1)) === Low)
  }

  behavior of "Middle DET"
  "5 DET and 0 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(5), FTR(0)) === Low)
  }

  "5 DET and 1 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(5), FTR(1)) === Low)
  }

  "15 DET and 0 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(15), FTR(0)) === Low)
  }

  "15 DET and 1 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(15), FTR(1)) === Low)
  }

  behavior of "Many DET"
  "16 DET and 0 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(16), FTR(0)) === Average)
  }

  "16 DET and 1 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(16), FTR(1)) === Average)
  }

  "Max DET and 0 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(Int.MaxValue), FTR(0)) === Average)
  }

  "Max DET and 1 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(Int.MaxValue), FTR(1)) === Average)
  }

  behavior of "Middle FTR"

  behavior of "Few DET"
  "1 DET and 2 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(1), FTR(2)) === Low)
  }

  "4 DET and 2 FTR" should "be Low Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(4), FTR(2)) === Low)
  }

  behavior of "Middle DET"
  "5 DET and 2 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(5), FTR(2)) === Average)
  }

  "15 DET and 2 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(15), FTR(2)) === Average)
  }

  behavior of "Many DET"
  "16 DET and 2 FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(16), FTR(2)) === High)
  }

  "Max DET and 2 FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(Int.MaxValue), FTR(2)) === High)
  }

  behavior of "Many FTR"

  behavior of "Few DET"
  "1 DET and 3 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(1), FTR(3)) === Average)
  }

  "1 DET and Max FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(1), FTR(Int.MaxValue)) === Average)
  }

  "4 DET and 3 FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(4), FTR(3)) === Average)
  }

  "4 DET and Max FTR" should "be Average Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(4), FTR(Int.MaxValue)) === Average)
  }

  behavior of "Middle DET"
  "5 DET and 3 FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(5), FTR(3)) === High)
  }

  "5 DET and Max FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(5), FTR(Int.MaxValue)) === High)
  }

  "15 DET and 3 FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(15), FTR(3)) === High)
  }

  "15 DET and Max FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(15), FTR(Int.MaxValue)) === High)
  }

  behavior of "Many DET"
  "16 DET and 3 FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(16), FTR(3)) === High)
  }

  "16 DET and Max FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(16), FTR(3)) === High)
  }

  "Max DET and 3 FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(Int.MaxValue), FTR(3)) === High)
  }

  "Max DET and Max FTR" should "be High Complexity" in {
    assert(TransactionalFunctionComplexity.complexity(DET(Int.MaxValue), FTR(Int.MaxValue)) === High)
  }
}
