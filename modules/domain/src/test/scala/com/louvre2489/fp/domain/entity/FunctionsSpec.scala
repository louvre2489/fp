package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.domain.datafunction._
import com.louvre2489.fp.domain.entity
import com.louvre2489.fp.domain.transactionalfunction._
import com.louvre2489.fp.domain.value._
import org.scalatest.FlatSpec

class FunctionsSpec extends FlatSpec {

  val system    = SystemInfo(SystemId(789), "SYS", GSC())
  val subSystem = Some(SubSystemInfo(SubSystemId(123), "SUB", SystemId(456)))

  behavior of "Functions"

  "Functions has no function" can "add another function" in {

    val functions = new Functions

    val function = entity.Function(FunctionId("A01"),
                                   "TEST_FUNC",
                                   system,
                                   subSystem,
                                   ADD,
                                   DataMigrationFunction(false),
                                   EIF,
                                   EO,
                                   DET(1),
                                   RET(5),
                                   FTR(7))

    val newFunctions = functions.addFunction(function)

    assert(functions.values.size === 0)
    assert(newFunctions.values.size === 1)

    val extractFunctions = newFunctions.values
    val extractFunction  = extractFunctions.headOption

    extractFunction match {
      case None             => fail()
      case Some(functionID) => assert(functionID.functionId.value === "A01")
    }
  }

  "Functions has functions" can "add another function" in {

    val functions = new Functions

    val a01 = entity.Function(FunctionId("A01"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a02 = entity.Function(FunctionId("A02"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a03 = entity.Function(FunctionId("A03"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a04 = entity.Function(FunctionId("A04"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))

    val newFunctions = functions.addFunction(a01).addFunction(a02).addFunction(a03).addFunction(a04)

    assert(functions.values.size === 0)
    assert(newFunctions.values.size === 4)

    val extractFunctions      = newFunctions.values
    val extractA01 :: tailA01 = extractFunctions
    val extractA02 :: tailA02 = tailA01
    val extractA03 :: tailA03 = tailA02
    val extractA04 :: _       = tailA03

    assert(extractA01.functionId.value === "A01")
    assert(extractA02.functionId.value === "A02")
    assert(extractA03.functionId.value === "A03")
    assert(extractA04.functionId.value === "A04")
  }

  "Functions has functions" can "remove function" in {

    val functions = new Functions

    val a01 = entity.Function(FunctionId("A01"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a02 = entity.Function(FunctionId("A02"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a03 = entity.Function(FunctionId("A03"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a04 = entity.Function(FunctionId("A04"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))

    val newFunctions = functions.addFunction(a01).addFunction(a02).addFunction(a03).addFunction(a04)

    assert(functions.values.size === 0)
    assert(newFunctions.values.size === 4)

    val removedFunction =
      entity.Function(FunctionId("A03"),
                      "TEST_FUNC",
                      system,
                      subSystem,
                      CHGA,
                      DataMigrationFunction(false),
                      ILF,
                      EQ,
                      DET(1),
                      RET(5),
                      FTR(7))

    val removedFunctions = newFunctions.removeFunction(removedFunction)

    assert(functions.values.size === 0)
    assert(newFunctions.values.size === 4)
    assert(removedFunctions.values.size === 3)

    val extractFunctions      = removedFunctions.values
    val extractA01 :: tailA01 = extractFunctions
    val extractA02 :: tailA02 = tailA01
    val extractA04 :: _       = tailA02

    assert(extractA01.functionId.value === "A01")
    assert(extractA02.functionId.value === "A02")
    assert(extractA04.functionId.value === "A04")
  }

  "Functions has no function" should "create empty functions" in {

    val functions = new Functions

    assert(functions.values.size === 0)

    val removedFunction =
      entity.Function(FunctionId("A03"),
                      "TEST_FUNC",
                      system,
                      subSystem,
                      CHGA,
                      DataMigrationFunction(false),
                      ILF,
                      EQ,
                      DET(1),
                      RET(5),
                      FTR(7))

    val removedFunctions = functions.removeFunction(removedFunction)

    assert(functions.values.size === 0)
    assert(removedFunctions.values.size === 0)
  }

  "Functions that does not have target function" must "create same functions" in {

    val functions = new Functions

    val a01 = entity.Function(FunctionId("A01"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a02 = entity.Function(FunctionId("A02"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a03 = entity.Function(FunctionId("A03"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))
    val a04 = entity.Function(FunctionId("A04"),
                              "TEST_FUNC",
                              system,
                              subSystem,
                              ADD,
                              DataMigrationFunction(false),
                              EIF,
                              EO,
                              DET(1),
                              RET(5),
                              FTR(7))

    val newFunctions = functions.addFunction(a01).addFunction(a02).addFunction(a03).addFunction(a04)

    assert(functions.values.size === 0)
    assert(newFunctions.values.size === 4)

    val removedFunction =
      entity.Function(FunctionId("A05"),
                      "TEST_FUNC",
                      system,
                      subSystem,
                      CHGA,
                      DataMigrationFunction(false),
                      ILF,
                      EQ,
                      DET(1),
                      RET(5),
                      FTR(7))

    val removedFunctions = newFunctions.removeFunction(removedFunction)

    assert(functions.values.size === 0)
    assert(newFunctions.values.size === 4)
    assert(removedFunctions.values.size === 4)

    val extractFunctions      = removedFunctions.values
    val extractA01 :: tailA01 = extractFunctions
    val extractA02 :: tailA02 = tailA01
    val extractA03 :: tailA03 = tailA02
    val extractA04 :: _       = tailA03

    assert(extractA01.functionId.value === "A01")
    assert(extractA02.functionId.value === "A02")
    assert(extractA03.functionId.value === "A03")
    assert(extractA04.functionId.value === "A04")
  }

}
