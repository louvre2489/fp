package com.louvre2489.fp.rdb.repository

import com.louvre2489.fp.domain.datafunction.EIF
import com.louvre2489.fp.domain.entity._
import com.louvre2489.fp.domain.transactionalfunction.EO
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.rdb.db.{ BaseDao, BaseDaoObject }
import com.louvre2489.fp.repository.FunctionRepository
import scalikejdbc.DB

object FunctionDao extends BaseDaoObject {

  def apply()(implicit db: DB) = new FunctionDao
}

class FunctionDao(implicit db: DB) extends BaseDao with FunctionRepository[Function, FunctionId] {

  @Override
  def findById(id: FunctionId): Option[Function] = {

    val system    = SystemInfo(SystemId(789), "SYS", GSC(SystemId(999), Version(1))(GSCDao()))(SystemDao())
    val subSystem = Some(SubSystemInfo(SubSystemId(123), "SUB", SystemId(456))(SubSystemDao()))
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
                            FTR(7))(this)
    Some(function)
  }

  @Override
  override def findAll: List[Function] = Nil

  @Override
  def save(entity: Function): Either[Exception, Unit] = {
    Left(new Exception)
  }
}
