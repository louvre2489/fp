package com.louvre2489.fp.domain

sealed trait Complexity

object Low     extends Complexity
object Average extends Complexity
object High    extends Complexity
