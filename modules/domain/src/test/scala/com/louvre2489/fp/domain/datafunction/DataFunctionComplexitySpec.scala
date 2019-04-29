package com.louvre2489.fp.domain.datafunction

import com.louvre2489.fp.domain._
import com.louvre2489.fp.domain.value.{ DET, RET }
import org.scalatest.FlatSpec

class DataFunctionComplexitySpec extends FlatSpec {

  behavior of "Few RET"

  behavior of "Few DET"
  "1 DET and 1 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(1), RET(1)) === Low)
  }

  "19 DET and 1 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(19), RET(1)) === Low)
  }

  behavior of "Middle DET"
  "20 DET and 1 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(20), RET(1)) === Low)
  }

  "50 DET and 1 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(50), RET(1)) === Low)
  }

  behavior of "Many DET"
  "51 DET and 1 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(51), RET(1)) === Average)
  }

  "Max DET and 1 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(Int.MaxValue), RET(1)) === Average)
  }

  behavior of "Middle RET"

  behavior of "Few DET"
  "1 DET and 2 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(1), RET(2)) === Low)
  }

  "1 DET and 5 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(1), RET(5)) === Low)
  }

  "19 DET and 2 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(19), RET(2)) === Low)
  }

  "19 DET and 5 RET" should "be Low Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(19), RET(5)) === Low)
  }

  behavior of "Middle DET"
  "20 DET and 2 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(20), RET(2)) === Average)
  }

  "20 DET and 5 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(20), RET(5)) === Average)
  }

  "50 DET and 2 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(50), RET(2)) === Average)
  }

  "50 DET and 5 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(50), RET(5)) === Average)
  }

  behavior of "Many DET"
  "51 DET and 2 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(51), RET(2)) === High)
  }

  "51 DET and 5 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(51), RET(5)) === High)
  }

  "Max DET and 2 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(Int.MaxValue), RET(2)) === High)
  }

  "Max DET and 5 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(Int.MaxValue), RET(5)) === High)
  }

  behavior of "Many RET"

  behavior of "Few DET"
  "1 DET and 6 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(1), RET(6)) === Average)
  }

  "1 DET and Max RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(1), RET(Int.MaxValue)) === Average)
  }

  "19 DET and 6 RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(19), RET(6)) === Average)
  }

  "19 DET and Max RET" should "be Average Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(19), RET(Int.MaxValue)) === Average)
  }

  behavior of "Middle DET"
  "20 DET and 6 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(20), RET(6)) === High)
  }

  "20 DET and Max RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(20), RET(Int.MaxValue)) === High)
  }

  "50 DET and 6 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(50), RET(6)) === High)
  }

  "50 DET and Max RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(50), RET(Int.MaxValue)) === High)
  }

  behavior of "Many DET"
  "51 DET and 6 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(51), RET(6)) === High)
  }

  "51 DET and Max RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(51), RET(Int.MaxValue)) === High)
  }

  "Max DET and 6 RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(Int.MaxValue), RET(6)) === High)
  }

  "Max DET and Max RET" should "be High Complexity" in {
    assert(DataFunctionComplexity.complexity(DET(Int.MaxValue), RET(Int.MaxValue)) === High)
  }
}
