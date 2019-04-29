package com.louvre2489.fp.domain.value

sealed trait Reference extends Any

/**
  * ファイルタイプリファレンス
  * 参照更新などアクセスするファイル数
  * @param value
  */
case class FTR(value: Int) extends AnyVal with Value[Int] with Reference
