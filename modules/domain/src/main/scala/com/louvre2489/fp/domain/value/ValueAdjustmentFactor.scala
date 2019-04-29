package com.louvre2489.fp.domain.value

/**
  * 調整係数
  * システムの複雑度などを加味して設定する
  *
  * @param value 調整係数
  */
case class ValueAdjustmentFactor(value: BigDecimal) extends AnyVal with Value[BigDecimal]

/**
  * GSCの評価点
  * @param value 評価点
  */
case class DI(value: Short) extends AnyVal with Value[Short] {
  def +(di: DI): DI = DI((this.value + di.value).toShort)
}
