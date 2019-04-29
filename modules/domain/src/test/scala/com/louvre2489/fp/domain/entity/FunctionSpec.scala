package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.domain.datafunction._
import com.louvre2489.fp.domain.entity
import com.louvre2489.fp.domain.transactionalfunction._
import com.louvre2489.fp.domain.value._
import org.scalatest.FlatSpec

class FunctionSpec extends FlatSpec {

  val system    = SystemInfo(SystemId(789), "SYS", GSC(SystemId(999), Version(1)))
  val subSystem = Some(SubSystemInfo(SubSystemId(123), "SUB", SystemId(456)))

  behavior of "Function"

  "Function" should "has arguments" in {

    val function = Function(FunctionId("ID"),
                            "TEST_FUNC",
                            system,
                            Version(1),
                            subSystem,
                            ADD,
                            DataMigrationFunction(false),
                            EIF,
                            EO,
                            DET(1),
                            RET(5),
                            FTR(7))

    assert(function.functionId.value === "ID")
    assert(function.functionName === "TEST_FUNC")
    assert(function.developmentType === ADD)
    assert(function.isDataMigrationFunction.value === false)
    assert(function.fileType === EIF)
    assert(function.ioType === EO)
    assert(function.det.value === 1)
    assert(function.ret.value === 5)
    assert(function.ftr.value === 7)

    val systemValue = function.systemInfo
    assert(systemValue.systemId.value === 789)
    assert(systemValue.systemName === "SYS")
    assert(systemValue.gsc === GSC(SystemId(999), Version(1)))

    val subSystemValue = function.subSystemInfo.get
    assert(subSystemValue.subSystemId.value === 123)
    assert(subSystemValue.subSystemName === "SUB")
  }

  behavior of "UFP"

  "Low ILF Data Function and Low EI Transactional Function" must "10 function point" in {

    val function = entity.Function(FunctionId("ID"),
                                   "TEST_FUNC",
                                   system,
                                   Version(1),
                                   subSystem,
                                   ADD,
                                   DataMigrationFunction(false),
                                   ILF,
                                   EI,
                                   DET(1),
                                   RET(5),
                                   FTR(0))
    assert(function.UFP.value === 10)
  }

  "Low EIF Data Function and Low EO Transactional Function" must "9 function point" in {

    val function = entity.Function(FunctionId("ID"),
                                   "TEST_FUNC",
                                   system,
                                   Version(1),
                                   subSystem,
                                   ADD,
                                   DataMigrationFunction(false),
                                   EIF,
                                   EO,
                                   DET(1),
                                   RET(5),
                                   FTR(0))
    assert(function.UFP.value === 9)
  }

  "High ILF Data Function and High EI Transactional Function" must "21 function point" in {

    val function = entity.Function(
      FunctionId("ID"),
      "TEST_FUNC",
      system,
      Version(1),
      subSystem,
      ADD,
      DataMigrationFunction(false),
      ILF,
      EI,
      DET(Int.MaxValue),
      RET(Int.MaxValue),
      FTR(Int.MaxValue)
    )
    assert(function.UFP.value === 21)
  }

  "High EIF Data Function and High EO Transactional Function" must "17 function point" in {

    val function = entity.Function(
      FunctionId("ID"),
      "TEST_FUNC",
      system,
      Version(1),
      subSystem,
      ADD,
      DataMigrationFunction(false),
      EIF,
      EO,
      DET(Int.MaxValue),
      RET(Int.MaxValue),
      FTR(Int.MaxValue)
    )
    assert(function.UFP.value === 17)
  }
}
