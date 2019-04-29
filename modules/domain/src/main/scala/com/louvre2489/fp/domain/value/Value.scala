package com.louvre2489.fp.domain.value

trait Value[T] extends Any {
  def value: T
}
