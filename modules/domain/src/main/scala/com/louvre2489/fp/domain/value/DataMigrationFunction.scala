package com.louvre2489.fp.domain.value

/**
  *  データ移行機能であるか否かを保有する
  * @param value true: データ移行機能 false:アプリケーション機能
  */
case class DataMigrationFunction(value: Boolean) extends AnyVal with Value[Boolean]
