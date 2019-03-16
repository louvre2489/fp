package com.louvre2489.fp.domain.datafunction

import com.louvre2489.fp.domain._
import com.louvre2489.fp.domain.value.{ DET, RET }

object DataFunctionComplexity {

  trait Pattern {
    val MIN_DET_POINT: DET
    val MAX_DET_POINT: DET
    val MIN_RET_POINT: RET
    val MAX_RET_POINT: RET
  }

  object FewDET_FewRET extends Pattern {
    val MIN_DET_POINT: DET = DET(1)
    val MAX_DET_POINT: DET = DET(19)
    val MIN_RET_POINT: RET = RET(1)
    val MAX_RET_POINT: RET = RET(1)
  }

  object FewDET_MiddleRET extends Pattern {
    val MIN_DET_POINT: DET = DET(1)
    val MAX_DET_POINT: DET = DET(19)
    val MIN_RET_POINT: RET = RET(2)
    val MAX_RET_POINT: RET = RET(5)
  }

  object FewDET_ManyRET extends Pattern {
    val MIN_DET_POINT: DET = DET(1)
    val MAX_DET_POINT: DET = DET(19)
    val MIN_RET_POINT: RET = RET(6)
    val MAX_RET_POINT: RET = RET(Int.MaxValue)
  }

  object MiddleDET_FewRET extends Pattern {
    val MIN_DET_POINT: DET = DET(20)
    val MAX_DET_POINT: DET = DET(50)
    val MIN_RET_POINT: RET = RET(1)
    val MAX_RET_POINT: RET = RET(1)
  }

  object MiddleDET_MiddleRET extends Pattern {
    val MIN_DET_POINT: DET = DET(20)
    val MAX_DET_POINT: DET = DET(50)
    val MIN_RET_POINT: RET = RET(2)
    val MAX_RET_POINT: RET = RET(5)
  }

  object MiddleDET_ManyRET extends Pattern {
    val MIN_DET_POINT: DET = DET(20)
    val MAX_DET_POINT: DET = DET(50)
    val MIN_RET_POINT: RET = RET(6)
    val MAX_RET_POINT: RET = RET(Int.MaxValue)
  }

  object ManyDET_FewRET extends Pattern {
    val MIN_DET_POINT: DET = DET(51)
    val MAX_DET_POINT: DET = DET(Int.MaxValue)
    val MIN_RET_POINT: RET = RET(1)
    val MAX_RET_POINT: RET = RET(1)
  }

  object ManyDET_MiddleRET extends Pattern {
    val MIN_DET_POINT: DET = DET(51)
    val MAX_DET_POINT: DET = DET(Int.MaxValue)
    val MIN_RET_POINT: RET = RET(2)
    val MAX_RET_POINT: RET = RET(5)
  }

  object ManyDET_ManyRET extends Pattern {
    val MIN_DET_POINT: DET = DET(51)
    val MAX_DET_POINT: DET = DET(Int.MaxValue)
    val MIN_RET_POINT: RET = RET(6)
    val MAX_RET_POINT: RET = RET(Int.MaxValue)
  }

  def complexity(det: DET, ret: RET): Complexity = {

    def evaluate(pattern: Pattern, det: DET, ret: RET): Boolean = {
      // DET value and RET value contained in the target range
      pattern.MIN_DET_POINT.value <= det.value && det.value <= pattern.MAX_DET_POINT.value && pattern.MIN_RET_POINT.value <= ret.value && ret.value <= pattern.MAX_RET_POINT.value
    }

    (det, ret) match {
      // few DET and few RET?
      case (d, r) if evaluate(FewDET_FewRET, d, r) => Low
      // few DET and middle RET?
      case (d, r) if evaluate(FewDET_MiddleRET, d, r) => Low
      // few DET and many RET?
      case (d, r) if evaluate(FewDET_ManyRET, d, r) => Average
      // middle DET and few RET?
      case (d, r) if evaluate(MiddleDET_FewRET, d, r) => Low
      // middle DET and middle RET?
      case (d, r) if evaluate(MiddleDET_MiddleRET, d, r) => Average
      // middle DET and many RET?
      case (d, r) if evaluate(MiddleDET_ManyRET, d, r) => High
      // many DET and few RET?
      case (d, r) if evaluate(ManyDET_FewRET, d, r) => Average
      // many DET and middle RET?
      case (d, r) if evaluate(ManyDET_MiddleRET, d, r) => High
      // many DET and many RET?
      case (_, _) => High
    }
  }
}
