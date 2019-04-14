package com.louvre2489.fp.rdb.db

import scalikejdbc.{ ConnectionPool, DB }

trait BaseDao {

  private val conn = ConnectionPool(DBSettings.poolName).borrow()

  val db: DB = DB(conn)
}
