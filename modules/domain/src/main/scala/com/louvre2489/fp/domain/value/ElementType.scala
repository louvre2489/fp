package com.louvre2489.fp.domain.value

sealed trait ElementType extends Any

case class DET(value: Int) extends AnyVal with Value[Int] with ElementType
case class RET(value: Int) extends AnyVal with Value[Int] with ElementType
