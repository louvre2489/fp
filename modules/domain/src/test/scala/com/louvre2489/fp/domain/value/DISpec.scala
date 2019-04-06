package com.louvre2489.fp.domain.value
import org.scalatest.FlatSpec

class DISpec extends FlatSpec {

  "DI" can "plus DI" in {
    val plusValue = DI(1) + DI(2)
    assert(plusValue.value === 3)
  }

}
