package com.louvre2489.fp.rdb.db

import java.util.ResourceBundle

import scalikejdbc.{ ConnectionPool, ConnectionPoolSettings }

object DBSettings {

  private val rb = ResourceBundle.getBundle("scalikejdbc")

  val poolName         = 'fp
  private val url      = rb.getString("jdbc.url")
  private val username = rb.getString("jdbc.username")
  private val password = rb.getString("jdbc.password")

  private val settings = ConnectionPoolSettings(initialSize = 10,
                                                maxSize = 20,
                                                connectionTimeoutMillis = 3000L,
                                                validationQuery = "select 1 from dual")

  // initialize JDBC driver & connection pool
  Class.forName(rb.getString("jdbc.driver"))

  // after loading JDBC drivers
  ConnectionPool.singleton(url, username, password)
  ConnectionPool.add(poolName, url, username, password)

  // all the connections are released, old connection pool will be abandoned
  ConnectionPool.add(poolName, url, username, password, settings)
}
