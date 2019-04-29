package com.louvre2489.fp.domain.value

sealed trait Identify extends Any

case class FunctionId(value: String) extends AnyVal with Value[String] with Identify

case class SystemId(value: Long) extends AnyVal with Value[Long] with Identify

case class SubSystemId(value: Long) extends AnyVal with Value[Long] with Identify

case class ID[V, E](value: V) extends AnyVal with Value[V] with Identify
