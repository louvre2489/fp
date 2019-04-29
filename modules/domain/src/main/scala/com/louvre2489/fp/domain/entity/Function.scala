package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.domain.datafunction.{ DataFunction, DataFunctionComplexity, FileType }
import com.louvre2489.fp.domain.transactionalfunction.{ IOType, TransactionalFunction, TransactionalFunctionComplexity }
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.repository.FunctionRepository

/**
  * 開発種類
  */
trait DevelopmentType

/**
  * 新規機能
  */
object ADD extends DevelopmentType

/**
  * 変更機能
  */
object CHGA extends DevelopmentType

/**
  * 削除機能
  */
object DEL extends DevelopmentType

/**
  * @param functionId 機能ID
  * @param functionName 機能名
  * @param systemInfo システム情報
  * @param version バージョン
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
                    version: Version,
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
  def save: Either[Exception, Unit] =
    repository.save(this)

  /**
    * UFP = ILF/EIFの未調整ファンクションポイント + EI/EO/EQのみ調整ファンクションポイント
    * @return UTP 未調整ファンクションポイント
    */
  def UFP: UnadjustedFunctionPoint = {

    val dataFunctionPoint = DataFunction.calculate(fileType, DataFunctionComplexity.complexity(det, ret))
    val transactionalFunctionPoint =
      TransactionalFunction.calculate(ioType, TransactionalFunctionComplexity.complexity(det, ftr))

    dataFunctionPoint + transactionalFunctionPoint
  }
}

/**
  * Functionのコレクションオブジェクト
  * @param functions Functionのリスト
  */
case class Functions(private val functions: List[Function]) {

  /**
    * 空のコレクションオブジェクトを生成する
    */
  def this() = this(Nil)

  /**
    * このクラスのオブジェクトが保持しているFunctionの一覧
    * @return functions
    */
  def values: List[Function] = functions

  /**
    * 機能の追加
    * パラメーターの機能を追加した新しいコレクションオブジェクトを生成して返す
    * @param function 追加機能
    * @return 新しいコレクションオブジェクト
    */
  def addFunction(function: Function): Functions = Functions((function :: functions.reverse).reverse)

  /**
    * 機能の削除
    * パラメーターの機能を変更した新しいコレクションオブジェクトを生成して返す
    * @param function 削除機能
    * @return 新しいコレクションオブジェクト
    */
  def removeFunction(function: Function): Functions =
    Functions(functions.filter(f => f.functionId.value != function.functionId.value))

  /**
    * Functionを全て保存する
    * @return
    */
  def save: Either[Exception, Unit] = {
    Left(new Exception())
  }
}
