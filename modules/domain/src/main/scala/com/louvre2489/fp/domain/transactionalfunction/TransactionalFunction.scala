package com.louvre2489.fp.domain.transactionalfunction

import com.louvre2489.fp.domain._
import com.louvre2489.fp.domain.value.UnadjustedFunctionPoint

object TransactionalFunction {

  /**
    * 入出力タイプと複雑さから未調整ファンクションポイントを算出する
    * @param ioType 入出力タイプ
    *               EI/EO/EQ
    * @param complexity 複雑さ
    *                   Low/Average/High
    * @return 未調整ファンクションポイント
    */
  def calculate(ioType: IOType, complexity: Complexity): UnadjustedFunctionPoint = (ioType, complexity) match {
    case (EI, Low)     => UnadjustedFunctionPoint(3)
    case (EO, Low)     => UnadjustedFunctionPoint(4)
    case (EQ, Low)     => UnadjustedFunctionPoint(3)
    case (EI, Average) => UnadjustedFunctionPoint(4)
    case (EO, Average) => UnadjustedFunctionPoint(5)
    case (EQ, Average) => UnadjustedFunctionPoint(4)
    case (EI, High)    => UnadjustedFunctionPoint(6)
    case (EO, High)    => UnadjustedFunctionPoint(7)
    case (EQ, High)    => UnadjustedFunctionPoint(6)
  }
}
