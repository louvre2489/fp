package com.louvre2489.fp.domain

import com.louvre2489.fp.domain.value.{ FunctionId, SubSystemId, SystemId }
import com.louvre2489.fp.repository.{ FunctionRepository, GSCRepository, SubSystemRepository, SystemRepository }

package object entity {

  implicit val functionRepo: FunctionRepository[Function, FunctionId] = new FunctionRepository[Function, FunctionId] {

    @Override
    override def getAll: List[Function] = Nil

    @Override
    def findById(id: FunctionId): Option[Function] = None

    @Override
    def save(f: Function) = Right(Unit)
  }

  implicit val systemRepo: SystemRepository[SystemInfo, SystemId] = new SystemRepository[SystemInfo, SystemId] {

    @Override
    override def getAll: List[SystemInfo] = Nil

    @Override
    def findById(id: SystemId): Option[SystemInfo] = None

    @Override
    def save(f: SystemInfo) = Right(Unit)
  }

  implicit val subSystemRepo: SubSystemRepository[SubSystemInfo, SubSystemId] =
    new SubSystemRepository[SubSystemInfo, SubSystemId] {

      @Override
      override def getAll: List[SubSystemInfo] = Nil

      @Override
      def findById(id: SubSystemId): Option[SubSystemInfo] = None

      @Override
      def save(f: SubSystemInfo) = Right(Unit)
    }

  implicit val gscRepo: GSCRepository[GSC, SystemId] = new GSCRepository[GSC, SystemId] {

    def findById(id: SystemId): Option[GSC] = None

    @Override
    def save(f: GSC) = Right(Unit)
  }
}
