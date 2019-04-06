package com.louvre2489.fp.domain

/**
  * 計測の種類
  */
sealed trait Measurement

/**
  * 新規開発計測
  */
object Anew extends Measurement

/**
  * 機能拡張計測
  */
object Extension extends Measurement

/**
  * アプリケーション計測
  */
object Application extends Measurement
