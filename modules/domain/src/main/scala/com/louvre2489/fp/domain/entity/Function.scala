package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.domain.datafunction.{ DataFunction, DataFunctionComplexity, FileType }
import com.louvre2489.fp.domain.transactionalfunction.{ IOType, TransactionalFunction, TransactionalFunctionComplexity }
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.repository.FunctionRepository

trait DevelopmentType
object ADD  extends DevelopmentType
object CHGA extends DevelopmentType
object DEL  extends DevelopmentType

/**
  * @param functionId 機能ID
  * @param functionName 機能名
  * @param systemInfo システム情報
  * @param subSystemInfo サブシステム情報(Optional)
  * @param developmentType 開発種類
  *                        ADD/CHGA/DEL
  * @param isDataMigrationFunction データ移行機能
  *                              true: データ移行機能 false: それ以外の機能
  * @param fileType ファイル種類
  *                 ILE/EIF
  * @param ioType 入出力種類
  *               EI/EO/EQ
  * @param det DET
  * @param ret RET
  * @param ftr FTR
  */
case class Function(functionId: FunctionId,
                    functionName: String,
                    systemInfo: SystemInfo,
                    subSystemInfo: Option[SubSystemInfo],
                    developmentType: DevelopmentType,
                    isDataMigrationFunction: DataMigrationFunction,
                    fileType: FileType,
                    ioType: IOType,
                    det: DET,
                    ret: RET,
                    ftr: FTR)(implicit repository: FunctionRepository[Function, FunctionId])
    extends Entity[FunctionId] {

  @Override
  def getAll: List[Function] = repository.getAll

  @Override
  def findById(id: FunctionId): Option[Function] = repository.findById(id)

  @Override
  def save: Either[Exception, Unit] =
    repository.save(this)

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

case class Functions(private val functions: List[Function]) {

  /**
    * Initialise with no function
    */
  def this() = this(Nil)

  /**
    *  functions that this object has
    * @return functions
    */
  def values: List[Function] = functions

  /**
    * add function
    * this method creates a new functions collection
    * @param function function to add
    * @return new function collection
    */
  def addFunction(function: Function): Functions = Functions((function :: functions.reverse).reverse)

  /**
    * remove function
    * this method remove a passed function
    * @param function function to remove
    * @return
    */
  def removeFunction(function: Function): Functions =
    Functions(functions.filter(f => f.functionId.value != function.functionId.value))

  def save: Either[Exception, Unit] = {
    Left(new Exception())
  }
}
