package com.louvre2489.fp.rdb.models

import scalikejdbc._
import java.time.{ LocalDate }

case class MesurementHistory(systemId: Long, version: Short, mesureDate: LocalDate, note: Option[String] = None) {

  def save()(implicit session: DBSession = MesurementHistory.autoSession): MesurementHistory =
    MesurementHistory.save(this)(session)

  def destroy()(implicit session: DBSession = MesurementHistory.autoSession): Int =
    MesurementHistory.destroy(this)(session)

}

object MesurementHistory extends SQLSyntaxSupport[MesurementHistory] {

  override val tableName = "T_MESUREMENT_HISTORY"

  override val columns = Seq("SYSTEM_ID", "VERSION", "MESURE_DATE", "NOTE")

  def apply(tmh: SyntaxProvider[MesurementHistory])(rs: WrappedResultSet): MesurementHistory = apply(tmh.resultName)(rs)
  def apply(tmh: ResultName[MesurementHistory])(rs: WrappedResultSet): MesurementHistory = new MesurementHistory(
    systemId = rs.get(tmh.systemId),
    version = rs.get(tmh.version),
    mesureDate = rs.get(tmh.mesureDate),
    note = rs.get(tmh.note)
  )

  val tmh = MesurementHistory.syntax("tmh")

  override val autoSession = AutoSession

  def find(systemId: Long, version: Short)(implicit session: DBSession = autoSession): Option[MesurementHistory] = {
    withSQL {
      select.from(MesurementHistory as tmh).where.eq(tmh.systemId, systemId).and.eq(tmh.version, version)
    }.map(MesurementHistory(tmh.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[MesurementHistory] = {
    withSQL(select.from(MesurementHistory as tmh)).map(MesurementHistory(tmh.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(MesurementHistory as tmh)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[MesurementHistory] = {
    withSQL {
      select.from(MesurementHistory as tmh).where.append(where)
    }.map(MesurementHistory(tmh.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[MesurementHistory] = {
    withSQL {
      select.from(MesurementHistory as tmh).where.append(where)
    }.map(MesurementHistory(tmh.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(MesurementHistory as tmh).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(systemId: Long, version: Short, mesureDate: LocalDate, note: Option[String] = None)(
      implicit session: DBSession = autoSession
  ): MesurementHistory = {
    withSQL {
      insert
        .into(MesurementHistory).namedValues(
          column.systemId   -> systemId,
          column.version    -> version,
          column.mesureDate -> mesureDate,
          column.note       -> note
        )
    }.update.apply()

    MesurementHistory(systemId = systemId, version = version, mesureDate = mesureDate, note = note)
  }

  def batchInsert(entities: collection.Seq[MesurementHistory])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(
      entity =>
        Seq('systemId   -> entity.systemId,
            'version    -> entity.version,
            'mesureDate -> entity.mesureDate,
            'note       -> entity.note)
    )
    SQL("""insert into T_MESUREMENT_HISTORY(
      SYSTEM_ID,
      VERSION,
      MESURE_DATE,
      NOTE
    ) values (
      {systemId},
      {version},
      {mesureDate},
      {note}
    )""").batchByName(params: _*).apply[List]()
  }

  def save(entity: MesurementHistory)(implicit session: DBSession = autoSession): MesurementHistory = {
    withSQL {
      update(MesurementHistory)
        .set(
          column.systemId   -> entity.systemId,
          column.version    -> entity.version,
          column.mesureDate -> entity.mesureDate,
          column.note       -> entity.note
        ).where.eq(column.systemId, entity.systemId).and.eq(column.version, entity.version)
    }.update.apply()
    entity
  }

  def destroy(entity: MesurementHistory)(implicit session: DBSession = autoSession): Int = {
    withSQL {
      delete.from(MesurementHistory).where.eq(column.systemId, entity.systemId).and.eq(column.version, entity.version)
    }.update.apply()
  }

}
