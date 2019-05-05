package com.louvre2489.fp.rdb.models

import scalikejdbc._

case class User(
  userId: String,
  password: String) {

  def save()(implicit session: DBSession = User.autoSession): User = User.save(this)(session)

  def destroy()(implicit session: DBSession = User.autoSession): Int = User.destroy(this)(session)

}


object User extends SQLSyntaxSupport[User] {

  override val tableName = "M_USER"

  override val columns = Seq("USER_ID", "PASSWORD")

  def apply(mu: SyntaxProvider[User])(rs: WrappedResultSet): User = apply(mu.resultName)(rs)
  def apply(mu: ResultName[User])(rs: WrappedResultSet): User = new User(
    userId = rs.get(mu.userId),
    password = rs.get(mu.password)
  )

  val mu = User.syntax("mu")

  override val autoSession = AutoSession

  def find(userId: String)(implicit session: DBSession = autoSession): Option[User] = {
    withSQL {
      select.from(User as mu).where.eq(mu.userId, userId)
    }.map(User(mu.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[User] = {
    withSQL(select.from(User as mu)).map(User(mu.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(User as mu)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[User] = {
    withSQL {
      select.from(User as mu).where.append(where)
    }.map(User(mu.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[User] = {
    withSQL {
      select.from(User as mu).where.append(where)
    }.map(User(mu.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(User as mu).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    userId: String,
    password: String)(implicit session: DBSession = autoSession): User = {
    withSQL {
      insert.into(User).namedValues(
        column.userId -> userId,
        column.password -> password
      )
    }.update.apply()

    User(
      userId = userId,
      password = password)
  }

  def batchInsert(entities: collection.Seq[User])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        'userId -> entity.userId,
        'password -> entity.password))
    SQL("""insert into M_USER(
      USER_ID,
      PASSWORD
    ) values (
      {userId},
      {password}
    )""").batchByName(params: _*).apply[List]()
  }

  def save(entity: User)(implicit session: DBSession = autoSession): User = {
    withSQL {
      update(User).set(
        column.userId -> entity.userId,
        column.password -> entity.password
      ).where.eq(column.userId, entity.userId)
    }.update.apply()
    entity
  }

  def destroy(entity: User)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(User).where.eq(column.userId, entity.userId) }.update.apply()
  }

}
