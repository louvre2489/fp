package com.louvre2489.fp.domain
import com.louvre2489.fp.domain.datafunction._
import com.louvre2489.fp.domain.transactionalfunction._
import com.louvre2489.fp.domain.value.{ DET, FTR, FunctionId, RET }
import org.scalatest.FlatSpec

class FunctionSpec extends FlatSpec {

  behavior of "Function"

  "Function" should "has arguments" in {

    val function = Function(FunctionId("ID"), ADD, EIF, EO, DET(1), RET(5), FTR(7))

    assert(function.functionId.value === "ID")
    assert(function.developmentType === ADD)
    assert(function.fileType === EIF)
    assert(function.ioType === EO)
    assert(function.det.value === 1)
    assert(function.ret.value === 5)
    assert(function.ftr.value === 7)
  }

}
