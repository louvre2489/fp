package com.louvre2489.fp.domain

import com.louvre2489.fp.domain.value.{ FunctionId, SubSystemId, SystemId }
import com.louvre2489.fp.repository.repositoryInterface.{
  FunctionRepositoryInterface,
  GSCRepositoryInterface,
  SubSystemRepositoryInterface,
  SystemRepositoryInterface
}

package object entity {

  implicit val functionRepo: FunctionRepositoryInterface[Function, FunctionId] =
    new FunctionRepositoryInterface[Function, FunctionId] {

      @Override
      override def getAll: List[Function] = Nil

      @Override
      def findById(id: FunctionId): Option[Function] = None

      @Override
      def save(f: Function) = Right(Unit)
    }

  implicit val systemRepo: SystemRepositoryInterface[SystemInfo, SystemId] =
    new SystemRepositoryInterface[SystemInfo, SystemId] {

      @Override
      override def getAll: List[SystemInfo] = Nil

      @Override
      def findById(id: SystemId): Option[SystemInfo] = None

      @Override
      def save(f: SystemInfo) = Right(Unit)
    }

  implicit val subSystemRepo: SubSystemRepositoryInterface[SubSystemInfo, SubSystemId] =
    new SubSystemRepositoryInterface[SubSystemInfo, SubSystemId] {

      @Override
      override def getAll: List[SubSystemInfo] = Nil

      @Override
      def findById(id: SubSystemId): Option[SubSystemInfo] = None

      @Override
      def save(f: SubSystemInfo) = Right(Unit)
    }

  implicit val gscRepo: GSCRepositoryInterface[GSC, SystemId] = new GSCRepositoryInterface[GSC, SystemId] {

    def findById(id: SystemId): Option[GSC] = None

    @Override
    def save(f: GSC) = Right(Unit)
  }
}
