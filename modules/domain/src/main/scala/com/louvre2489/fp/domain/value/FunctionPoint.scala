package com.louvre2489.fp.domain.value

/**
  * 未調整ファンクションポイント値を保持する
  * また、未調整ファンクションポイントを用いた計算を行う
  * @param value 未調整ファンクションポイント
  */
case class UnadjustedFunctionPoint(value: Int) extends AnyVal with Value[Int] {

  /**
    * 未調整ファンクションポイント同士を足し算する
    * @param fp 未調整ファンクションポイント
    * @return 計算後の未調整ファンクションポイント
    */
  def +(fp: UnadjustedFunctionPoint): UnadjustedFunctionPoint = UnadjustedFunctionPoint(this.value + fp.value)

  /**
    * 指定された未調整ファンクションポイントを引き算する
    * @param fp 未調整ファンクションポイント
    * @return 計算後の未調整ファンクションポイント
    */
  def -(fp: UnadjustedFunctionPoint): UnadjustedFunctionPoint = UnadjustedFunctionPoint(this.value - fp.value)

  /**
    * 未調整ファンクションポイントにVAFの掛率を適用して調整後ファンクションポイントを計算する
    * 未調整ファンクションポイント　×　VAF　＝　調整後ファンクションポイント
    * @param vaf VAF
    * @return 計算後の未調整ファンクションポイント
    */
  def *(vaf: ValueAdjustmentFactor): AdjustedFunctionPoint = AdjustedFunctionPoint(this.value * vaf.value)
}

/**
  * 調整後ファンクションポイント値を保持する
  * また、調整後ファンクションポイントを用いた計算を行う
  * @param value 調整後ファンクションポイント
  */
case class AdjustedFunctionPoint(value: BigDecimal) extends AnyVal with Value[BigDecimal] {

  /**
    * 調整後ファンクションポイント同士を足し算する
    * @param fp 調整後ファンクションポイント
    * @return 計算後の調整ファンクションポイント
    */
  def +(fp: AdjustedFunctionPoint): AdjustedFunctionPoint = AdjustedFunctionPoint(this.value + fp.value)
}
