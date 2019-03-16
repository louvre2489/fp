package com.louvre2489.fp.domain.value

case class UnadjustedFunctionPoint(value: Int) extends AnyVal with Value[Int] {

  /**
    *  Add unadjusted function points
    * @param fp unadjusted function point
    * @return
    */
  def +(fp: UnadjustedFunctionPoint): UnadjustedFunctionPoint = UnadjustedFunctionPoint(this.value + fp.value)

  /**
    *  Adjust function point.
    *  (unadjusted function point) * (value Adjusted factor) = (Adjusted function point)
    * @param vaf value adjustment factor
    * @return
    */
  def *(vaf: ValueAdjustmentFactor): AdjustedFunctionPoint = AdjustedFunctionPoint(this.value * vaf.value)
}

case class AdjustedFunctionPoint(value: BigDecimal) extends AnyVal with Value[BigDecimal]
