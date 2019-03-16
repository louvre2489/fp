package com.louvre2489.fp.domain.transactionalfunction

sealed trait IOType

object EI extends IOType
object EO extends IOType
object EQ extends IOType
