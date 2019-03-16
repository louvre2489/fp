package com.louvre2489.fp.domain

import com.louvre2489.fp.domain.datafunction.{ DataFunction, DataFunctionComplexity, FileType }
import com.louvre2489.fp.domain.transactionalfunction.{ IOType, TransactionalFunction, TransactionalFunctionComplexity }
import com.louvre2489.fp.domain.value._

trait DevelopmentType
object ADD  extends DevelopmentType
object CHGA extends DevelopmentType
object DEL  extends DevelopmentType

/**
  *
  * @param functionId 機能ID
  * @param developmentType 開発種類
  *                        ADD/CHGA/DEL
  * @param fileType ファイル種類
  *                 ILE/EIF
  * @param ioType 入出力種類
  *               EI/EO/EQ
  * @param det DET
  * @param ret RET
  * @param ftr FTR
  */
case class Function(functionId: FunctionId,
                    developmentType: DevelopmentType,
                    fileType: FileType,
                    ioType: IOType,
                    det: DET,
                    ret: RET,
                    ftr: FTR) {

  /**
    *  UFP = FunctionPoint of ILF/EIF + FunctionPoint ofEI/EO/EQ
    * @return UTP
    */
  def UFP: UnadjustedFunctionPoint = {

    val dataFunctionPoint = DataFunction.calculate(fileType, DataFunctionComplexity.complexity(det, ret))
    val transactionalFunctionPoint =
      TransactionalFunction.calculate(ioType, TransactionalFunctionComplexity.complexity(det, ftr))

    dataFunctionPoint + transactionalFunctionPoint
  }

}

case class Functions(values: List[Function]) {

  /**
    * add function
    * this method creates a new functions collection
    * @param function function to add
    * @return new function collection
    */
  def addFunction(function: Function): Functions = Functions(function :: values)

  /**
    * remove function
    * this method remove a passed function
    * @param function function to remove
    * @return
    */
  def removeFunction(function: Function): Functions =
    Functions(values.filterNot(f => f.functionId == function.functionId))
}
