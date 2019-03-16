package com.louvre2489.fp.domain.systemcharacteristics

sealed trait Measurement

object Anew        extends Measurement
object Extension   extends Measurement
object Application extends Measurement

object Measurement
