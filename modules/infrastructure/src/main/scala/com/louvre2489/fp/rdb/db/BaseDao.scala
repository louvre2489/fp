package com.louvre2489.fp.rdb.db

import scalikejdbc.DB

trait BaseDaoObject {

  def apply()(implicit db: DB): BaseDao
}

abstract class BaseDao()(implicit db: DB) {

//  private val conn = Conne//ctionPool(DBSettings.poolName).borrow()
//
//  val db: DB = DB(conn)
}
