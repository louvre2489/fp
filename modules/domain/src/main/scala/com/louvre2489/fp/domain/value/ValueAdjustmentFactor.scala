package com.louvre2489.fp.domain.value

case class ValueAdjustmentFactor(value: BigDecimal) extends AnyVal with Value[BigDecimal]

case class DI(value: Int) extends AnyVal with Value[Int] {
  def +(di: DI): DI = DI(this.value + di.value)
}
