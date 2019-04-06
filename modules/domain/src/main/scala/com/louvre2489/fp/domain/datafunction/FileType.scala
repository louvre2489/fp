package com.louvre2489.fp.domain.datafunction

sealed trait FileType

/**
  * Internal Logical File
  * 追加、更新、削除などの操作の対象となるファイル
  */
object ILF extends FileType

/**
  * External Interface File
  * 参照のみ発生するファイル
  */
object EIF extends FileType
