package com.louvre2489.fp.domain.value

sealed trait Reference extends Any

case class FTR(value: Int) extends AnyVal with Value[Int] with Reference
