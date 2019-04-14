package com.louvre2489.fp.rdb.models

import scalikejdbc._

case class System(systemId: Long, systemName: String) {

  def save()(implicit session: DBSession = System.autoSession): System = System.save(this)(session)

  def destroy()(implicit session: DBSession = System.autoSession): Int = System.destroy(this)(session)

}

object System extends SQLSyntaxSupport[System] {

  override val tableName = "M_SYSTEM"

  override val columns = Seq("SYSTEM_ID", "SYSTEM_NAME")

  def apply(ms: SyntaxProvider[System])(rs: WrappedResultSet): System = apply(ms.resultName)(rs)
  def apply(ms: ResultName[System])(rs: WrappedResultSet): System = new System(
    systemId = rs.get(ms.systemId),
    systemName = rs.get(ms.systemName)
  )

  val ms = System.syntax("ms")

  override val autoSession = AutoSession

  def find(systemId: Long)(implicit session: DBSession = autoSession): Option[System] = {
    withSQL {
      select.from(System as ms).where.eq(ms.systemId, systemId)
    }.map(System(ms.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[System] = {
    withSQL(select.from(System as ms)).map(System(ms.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(System as ms)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[System] = {
    withSQL {
      select.from(System as ms).where.append(where)
    }.map(System(ms.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[System] = {
    withSQL {
      select.from(System as ms).where.append(where)
    }.map(System(ms.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(System as ms).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(systemId: Long, systemName: String)(implicit session: DBSession = autoSession): System = {
    withSQL {
      insert
        .into(System).namedValues(
          column.systemId   -> systemId,
          column.systemName -> systemName
        )
    }.update.apply()

    System(systemId = systemId, systemName = systemName)
  }

  def batchInsert(entities: collection.Seq[System])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] =
      entities.map(entity => Seq('systemId -> entity.systemId, 'systemName -> entity.systemName))
    SQL("""insert into M_SYSTEM(
      SYSTEM_ID,
      SYSTEM_NAME
    ) values (
      {systemId},
      {systemName}
    )""").batchByName(params: _*).apply[List]()
  }

  def save(entity: System)(implicit session: DBSession = autoSession): System = {
    withSQL {
      update(System)
        .set(
          column.systemId   -> entity.systemId,
          column.systemName -> entity.systemName
        ).where.eq(column.systemId, entity.systemId)
    }.update.apply()
    entity
  }

  def destroy(entity: System)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(System).where.eq(column.systemId, entity.systemId) }.update.apply()
  }

}
