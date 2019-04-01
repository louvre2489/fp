package com.louvre2489.fp.infrastructure.rdb.repository

import com.louvre2489.fp.domain.datafunction.EIF
import com.louvre2489.fp.domain.entity.{ ADD, Function, GSC, SubSystemInfo, SystemInfo }
import com.louvre2489.fp.domain.transactionalfunction.EO
import com.louvre2489.fp.domain.value.{ DET, DataMigrationFunction, FTR, FunctionId, RET, SubSystemId, SystemId }
import com.louvre2489.fp.repository.FunctionRepository

object FunctionDao extends FunctionRepository[Function, FunctionId] {

  def findById(id: FunctionId): Function = {

    val system    = SystemInfo(SystemId(789), "SYS", GSC())(SystemDao)
    val subSystem = Some(SubSystemInfo(SubSystemId(123), "SUB")(SubSystemDao))
    val function = Function(FunctionId("ID"),
                            "TEST_FUNC",
                            system,
                            subSystem,
                            ADD,
                            DataMigrationFunction(false),
                            EIF,
                            EO,
                            DET(1),
                            RET(5),
                            FTR(7))(this)
    Some(function)
  }
}
