package com.louvre2489.fp.rdb.models

import scalikejdbc._

case class SubSystem(subSystemId: Long, subSystemName: String, systemId: Long) {

  def save()(implicit session: DBSession = SubSystem.autoSession): SubSystem = SubSystem.save(this)(session)

  def destroy()(implicit session: DBSession = SubSystem.autoSession): Int = SubSystem.destroy(this)(session)

}

object SubSystem extends SQLSyntaxSupport[SubSystem] {

  override val tableName = "M_SUB_SYSTEM"

  override val columns = Seq("SUB_SYSTEM_ID", "SUB_SYSTEM_NAME", "SYSTEM_ID")

  def apply(mss: SyntaxProvider[SubSystem])(rs: WrappedResultSet): SubSystem = apply(mss.resultName)(rs)
  def apply(mss: ResultName[SubSystem])(rs: WrappedResultSet): SubSystem = new SubSystem(
    subSystemId = rs.get(mss.subSystemId),
    subSystemName = rs.get(mss.subSystemName),
    systemId = rs.get(mss.systemId)
  )

  val mss = SubSystem.syntax("mss")

  override val autoSession = AutoSession

  def find(subSystemId: Long)(implicit session: DBSession = autoSession): Option[SubSystem] = {
    withSQL {
      select.from(SubSystem as mss).where.eq(mss.subSystemId, subSystemId)
    }.map(SubSystem(mss.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[SubSystem] = {
    withSQL(select.from(SubSystem as mss)).map(SubSystem(mss.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(SubSystem as mss)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[SubSystem] = {
    withSQL {
      select.from(SubSystem as mss).where.append(where)
    }.map(SubSystem(mss.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[SubSystem] = {
    withSQL {
      select.from(SubSystem as mss).where.append(where)
    }.map(SubSystem(mss.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(SubSystem as mss).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(subSystemId: Long, subSystemName: String, systemId: Long)(
      implicit session: DBSession = autoSession
  ): SubSystem = {
    withSQL {
      insert
        .into(SubSystem).namedValues(
          column.subSystemId   -> subSystemId,
          column.subSystemName -> subSystemName,
          column.systemId      -> systemId
        )
    }.update.apply()

    SubSystem(subSystemId = subSystemId, subSystemName = subSystemName, systemId = systemId)
  }

  def batchInsert(entities: collection.Seq[SubSystem])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(
      entity =>
        Seq('subSystemId -> entity.subSystemId, 'subSystemName -> entity.subSystemName, 'systemId -> entity.systemId)
    )
    SQL("""insert into M_SUB_SYSTEM(
      SUB_SYSTEM_ID,
      SUB_SYSTEM_NAME,
      SYSTEM_ID
    ) values (
      {subSystemId},
      {subSystemName},
      {systemId}
    )""").batchByName(params: _*).apply[List]()
  }

  def save(entity: SubSystem)(implicit session: DBSession = autoSession): SubSystem = {
    withSQL {
      update(SubSystem)
        .set(
          column.subSystemId   -> entity.subSystemId,
          column.subSystemName -> entity.subSystemName,
          column.systemId      -> entity.systemId
        ).where.eq(column.subSystemId, entity.subSystemId)
    }.update.apply()
    entity
  }

  def destroy(entity: SubSystem)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(SubSystem).where.eq(column.subSystemId, entity.subSystemId) }.update.apply()
  }

}
