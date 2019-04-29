package com.louvre2489.fp.domain.transactionalfunction
import com.louvre2489.fp.domain._
import com.louvre2489.fp.domain.value.{ DET, FTR }

object TransactionalFunctionComplexity {

  trait Pattern {
    val MIN_DET_POINT: DET
    val MAX_DET_POINT: DET
    val MIN_FTR_POINT: FTR
    val MAX_FTR_POINT: FTR
  }

  object FewDET_FewFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(1)
    val MAX_DET_POINT: DET = DET(4)
    val MIN_FTR_POINT: FTR = FTR(0)
    val MAX_FTR_POINT: FTR = FTR(1)
  }

  object FewDET_MiddleFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(1)
    val MAX_DET_POINT: DET = DET(4)
    val MIN_FTR_POINT: FTR = FTR(2)
    val MAX_FTR_POINT: FTR = FTR(2)
  }

  object FewDET_ManyFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(1)
    val MAX_DET_POINT: DET = DET(4)
    val MIN_FTR_POINT: FTR = FTR(3)
    val MAX_FTR_POINT: FTR = FTR(Int.MaxValue)
  }

  object MiddleDET_FewFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(5)
    val MAX_DET_POINT: DET = DET(15)
    val MIN_FTR_POINT: FTR = FTR(0)
    val MAX_FTR_POINT: FTR = FTR(1)
  }

  object MiddleDET_MiddleFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(5)
    val MAX_DET_POINT: DET = DET(15)
    val MIN_FTR_POINT: FTR = FTR(2)
    val MAX_FTR_POINT: FTR = FTR(2)
  }

  object MiddleDET_ManyFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(5)
    val MAX_DET_POINT: DET = DET(15)
    val MIN_FTR_POINT: FTR = FTR(3)
    val MAX_FTR_POINT: FTR = FTR(Int.MaxValue)
  }

  object ManyDET_FewFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(16)
    val MAX_DET_POINT: DET = DET(Int.MaxValue)
    val MIN_FTR_POINT: FTR = FTR(0)
    val MAX_FTR_POINT: FTR = FTR(1)
  }

  object ManyDET_MiddleFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(16)
    val MAX_DET_POINT: DET = DET(Int.MaxValue)
    val MIN_FTR_POINT: FTR = FTR(2)
    val MAX_FTR_POINT: FTR = FTR(2)
  }

  object ManyDET_ManyFTR extends Pattern {
    val MIN_DET_POINT: DET = DET(16)
    val MAX_DET_POINT: DET = DET(Int.MaxValue)
    val MIN_FTR_POINT: FTR = FTR(3)
    val MAX_FTR_POINT: FTR = FTR(Int.MaxValue)
  }

  def complexity(det: DET, ftr: FTR): Complexity = {

    def evaluate(pattern: Pattern, det: DET, ftr: FTR): Boolean = {
      // DET value and FTR value contained in the target range
      pattern.MIN_DET_POINT.value <= det.value && det.value <= pattern.MAX_DET_POINT.value && pattern.MIN_FTR_POINT.value <= ftr.value && ftr.value <= pattern.MAX_FTR_POINT.value
    }

    (det, ftr) match {
      // few DET and few FTR?
      case (d, f) if evaluate(FewDET_FewFTR, d, f) => Low
      // few DET and middle FTR?
      case (d, f) if evaluate(FewDET_MiddleFTR, d, f) => Low
      // few DET and many FTR?
      case (d, f) if evaluate(FewDET_ManyFTR, d, f) => Average
      // middle DET and few FTR?
      case (d, f) if evaluate(MiddleDET_FewFTR, d, f) => Low
      // middle DET and middle FTR?
      case (d, f) if evaluate(MiddleDET_MiddleFTR, d, f) => Average
      // middle DET and many FTR?
      case (d, f) if evaluate(MiddleDET_ManyFTR, d, f) => High
      // many DET and few FTR?
      case (d, f) if evaluate(ManyDET_FewFTR, d, f) => Average
      // many DET and middle FTR?
      case (d, f) if evaluate(ManyDET_MiddleFTR, d, f) => High
      // many DET and many FTR?
      case (_, _) => High
    }
  }
}
