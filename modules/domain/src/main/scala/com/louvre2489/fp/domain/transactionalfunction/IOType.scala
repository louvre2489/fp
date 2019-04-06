package com.louvre2489.fp.domain.transactionalfunction

sealed trait IOType

/**
  * External Input
  * データを受け取る処理(ILFに対してデータの追加・変更・削除を行う)
  */
object EI extends IOType

/**
  * External Output
  * データを外部へ出力する処理(ILF、EIFから参照したデータを加工してから出力)
  */
object EO extends IOType

/**
  * External inQuery
  * データを外部へ出力する処理(ILF、EIFから参照したデータを加工しないで出力)
  */
object EQ extends IOType
