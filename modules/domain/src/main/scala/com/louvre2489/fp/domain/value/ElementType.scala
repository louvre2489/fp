package com.louvre2489.fp.domain.value

sealed trait ElementType extends Any

/**
  * データエレメントタイプ
  * 繰り返しを含まないデータ項目
  * @param value データ項目数
  */
case class DET(value: Int) extends AnyVal with Value[Int] with ElementType

/**
  * レコードエレメントタイプ
  * 繰り返しのあるデータ項目
  * @param value データ項目数
  */
case class RET(value: Int) extends AnyVal with Value[Int] with ElementType
