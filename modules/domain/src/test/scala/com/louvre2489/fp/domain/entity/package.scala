package com.louvre2489.fp.domain

import com.louvre2489.fp.domain.value.{ FunctionId, SubSystemId, SystemId, UserId, Version }
import com.louvre2489.fp.repository.{
  FunctionRepository,
  GSCRepository,
  SubSystemRepository,
  SystemRepository,
  UserRepository
}

package object entity {

  implicit val functionRepo: FunctionRepository[Function, FunctionId] =
    new FunctionRepository[Function, FunctionId] {

      @Override
      override def findAll: List[Function] = Nil

      @Override
      override def findById(id: FunctionId): Option[Function] = None

      @Override
      override def save(f: Function) = Right(Unit)
    }

  implicit val systemRepo: SystemRepository[SystemInfo, SystemId] =
    new SystemRepository[SystemInfo, SystemId] {

      @Override
      override def findAll: List[SystemInfo] = Nil

      @Override
      override def findById(id: SystemId): Option[SystemInfo] = None

      @Override
      override def save(f: SystemInfo) = Right(Unit)
    }

  implicit val subSystemRepo: SubSystemRepository[SubSystemInfo, SubSystemId] =
    new SubSystemRepository[SubSystemInfo, SubSystemId] {

      @Override
      override def findAll: List[SubSystemInfo] = Nil

      @Override
      override def findById(id: SubSystemId): Option[SubSystemInfo] = None

      @Override
      override def findBySystemId(systemId: SystemId): List[SubSystemInfo] = Nil

      @Override
      override def save(f: SubSystemInfo) = Right(Unit)
    }

  implicit val gscRepo: GSCRepository[GSC, SystemId] = new GSCRepository[GSC, SystemId] {

    @Override
    override def find(id: SystemId, version: Version): Option[GSC] = None

    @Override
    override def save(f: GSC) = Right(Unit)
  }

  implicit val userRepo: UserRepository[UserInfo, UserId] = new UserRepository[UserInfo, UserId] {

    @Override
    override def findAll(): List[UserInfo] = Nil

    @Override
    override def findById(id: UserId): Option[UserInfo] = None

    @Override
    override def save(f: UserInfo) = Right(Unit)

  }
}
