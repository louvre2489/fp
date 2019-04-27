package com.louvre2489.fp.rdb.models

import scalikejdbc._

case class GSCRec(systemId: Long,
                  version: Short,
                  dataCommunications: Short,
                  distributedDataProcessing: Short,
                  performance: Short,
                  heavilyUsedConfiguration: Short,
                  transactionRate: Short,
                  onlineDataEntry: Short,
                  endUserEffeciency: Short,
                  onlineUpdate: Short,
                  complexProcessing: Short,
                  reUsability: Short,
                  installationEase: Short,
                  operationalEase: Short,
                  multipleSites: Short,
                  facilitateChange: Short) {

  def save()(implicit session: DBSession = GSCRec.autoSession): GSCRec = GSCRec.save(this)(session)

  def destroy()(implicit session: DBSession = GSCRec.autoSession): Int = GSCRec.destroy(this)(session)

}

object GSCRec extends SQLSyntaxSupport[GSCRec] {

  override val tableName = "T_GSC_REC"

  override val columns = Seq(
    "SYSTEM_ID",
    "VERSION",
    "DATA_COMMUNICATIONS",
    "DISTRIBUTED_DATA_PROCESSING",
    "PERFORMANCE",
    "HEAVILY_USED_CONFIGURATION",
    "TRANSACTION_RATE",
    "ONLINE_DATA_ENTRY",
    "END_USER_EFFECIENCY",
    "ONLINE_UPDATE",
    "COMPLEX_PROCESSING",
    "RE_USABILITY",
    "INSTALLATION_EASE",
    "OPERATIONAL_EASE",
    "MULTIPLE_SITES",
    "FACILITATE_CHANGE"
  )

  def apply(tgr: SyntaxProvider[GSCRec])(rs: WrappedResultSet): GSCRec = apply(tgr.resultName)(rs)
  def apply(tgr: ResultName[GSCRec])(rs: WrappedResultSet): GSCRec = new GSCRec(
    systemId = rs.get(tgr.systemId),
    version = rs.get(tgr.version),
    dataCommunications = rs.get(tgr.dataCommunications),
    distributedDataProcessing = rs.get(tgr.distributedDataProcessing),
    performance = rs.get(tgr.performance),
    heavilyUsedConfiguration = rs.get(tgr.heavilyUsedConfiguration),
    transactionRate = rs.get(tgr.transactionRate),
    onlineDataEntry = rs.get(tgr.onlineDataEntry),
    endUserEffeciency = rs.get(tgr.endUserEffeciency),
    onlineUpdate = rs.get(tgr.onlineUpdate),
    complexProcessing = rs.get(tgr.complexProcessing),
    reUsability = rs.get(tgr.reUsability),
    installationEase = rs.get(tgr.installationEase),
    operationalEase = rs.get(tgr.operationalEase),
    multipleSites = rs.get(tgr.multipleSites),
    facilitateChange = rs.get(tgr.facilitateChange)
  )

  val tgr = GSCRec.syntax("tgr")

  override val autoSession = AutoSession

  def find(systemId: Long, version: Short)(implicit session: DBSession = autoSession): Option[GSCRec] = {
    withSQL {
      select.from(GSCRec as tgr).where.eq(tgr.systemId, systemId).and.eq(tgr.version, version)
    }.map(GSCRec(tgr.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[GSCRec] = {
    withSQL(select.from(GSCRec as tgr)).map(GSCRec(tgr.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(GSCRec as tgr)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[GSCRec] = {
    withSQL {
      select.from(GSCRec as tgr).where.append(where)
    }.map(GSCRec(tgr.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[GSCRec] = {
    withSQL {
      select.from(GSCRec as tgr).where.append(where)
    }.map(GSCRec(tgr.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(GSCRec as tgr).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(systemId: Long,
             version: Short,
             dataCommunications: Short,
             distributedDataProcessing: Short,
             performance: Short,
             heavilyUsedConfiguration: Short,
             transactionRate: Short,
             onlineDataEntry: Short,
             endUserEffeciency: Short,
             onlineUpdate: Short,
             complexProcessing: Short,
             reUsability: Short,
             installationEase: Short,
             operationalEase: Short,
             multipleSites: Short,
             facilitateChange: Short)(implicit session: DBSession = autoSession): GSCRec = {
    withSQL {
      insert
        .into(GSCRec).namedValues(
          column.systemId                  -> systemId,
          column.version                   -> version,
          column.dataCommunications        -> dataCommunications,
          column.distributedDataProcessing -> distributedDataProcessing,
          column.performance               -> performance,
          column.heavilyUsedConfiguration  -> heavilyUsedConfiguration,
          column.transactionRate           -> transactionRate,
          column.onlineDataEntry           -> onlineDataEntry,
          column.endUserEffeciency         -> endUserEffeciency,
          column.onlineUpdate              -> onlineUpdate,
          column.complexProcessing         -> complexProcessing,
          column.reUsability               -> reUsability,
          column.installationEase          -> installationEase,
          column.operationalEase           -> operationalEase,
          column.multipleSites             -> multipleSites,
          column.facilitateChange          -> facilitateChange
        )
    }.update.apply()

    GSCRec(
      systemId = systemId,
      version = version,
      dataCommunications = dataCommunications,
      distributedDataProcessing = distributedDataProcessing,
      performance = performance,
      heavilyUsedConfiguration = heavilyUsedConfiguration,
      transactionRate = transactionRate,
      onlineDataEntry = onlineDataEntry,
      endUserEffeciency = endUserEffeciency,
      onlineUpdate = onlineUpdate,
      complexProcessing = complexProcessing,
      reUsability = reUsability,
      installationEase = installationEase,
      operationalEase = operationalEase,
      multipleSites = multipleSites,
      facilitateChange = facilitateChange
    )
  }

  def batchInsert(entities: collection.Seq[GSCRec])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(
      entity =>
        Seq(
          'systemId                  -> entity.systemId,
          'version                   -> entity.version,
          'dataCommunications        -> entity.dataCommunications,
          'distributedDataProcessing -> entity.distributedDataProcessing,
          'performance               -> entity.performance,
          'heavilyUsedConfiguration  -> entity.heavilyUsedConfiguration,
          'transactionRate           -> entity.transactionRate,
          'onlineDataEntry           -> entity.onlineDataEntry,
          'endUserEffeciency         -> entity.endUserEffeciency,
          'onlineUpdate              -> entity.onlineUpdate,
          'complexProcessing         -> entity.complexProcessing,
          'reUsability               -> entity.reUsability,
          'installationEase          -> entity.installationEase,
          'operationalEase           -> entity.operationalEase,
          'multipleSites             -> entity.multipleSites,
          'facilitateChange          -> entity.facilitateChange
      )
    )
    SQL("""insert into T_GSC_REC(
      SYSTEM_ID,
      VERSION,
      DATA_COMMUNICATIONS,
      DISTRIBUTED_DATA_PROCESSING,
      PERFORMANCE,
      HEAVILY_USED_CONFIGURATION,
      TRANSACTION_RATE,
      ONLINE_DATA_ENTRY,
      END_USER_EFFECIENCY,
      ONLINE_UPDATE,
      COMPLEX_PROCESSING,
      RE_USABILITY,
      INSTALLATION_EASE,
      OPERATIONAL_EASE,
      MULTIPLE_SITES,
      FACILITATE_CHANGE
    ) values (
      {systemId},
      {version},
      {dataCommunications},
      {distributedDataProcessing},
      {performance},
      {heavilyUsedConfiguration},
      {transactionRate},
      {onlineDataEntry},
      {endUserEffeciency},
      {onlineUpdate},
      {complexProcessing},
      {reUsability},
      {installationEase},
      {operationalEase},
      {multipleSites},
      {facilitateChange}
    )""").batchByName(params: _*).apply[List]()
  }

  def save(entity: GSCRec)(implicit session: DBSession = autoSession): GSCRec = {
    withSQL {
      update(GSCRec)
        .set(
          column.systemId                  -> entity.systemId,
          column.version                   -> entity.version,
          column.dataCommunications        -> entity.dataCommunications,
          column.distributedDataProcessing -> entity.distributedDataProcessing,
          column.performance               -> entity.performance,
          column.heavilyUsedConfiguration  -> entity.heavilyUsedConfiguration,
          column.transactionRate           -> entity.transactionRate,
          column.onlineDataEntry           -> entity.onlineDataEntry,
          column.endUserEffeciency         -> entity.endUserEffeciency,
          column.onlineUpdate              -> entity.onlineUpdate,
          column.complexProcessing         -> entity.complexProcessing,
          column.reUsability               -> entity.reUsability,
          column.installationEase          -> entity.installationEase,
          column.operationalEase           -> entity.operationalEase,
          column.multipleSites             -> entity.multipleSites,
          column.facilitateChange          -> entity.facilitateChange
        ).where.eq(column.systemId, entity.systemId).and.eq(column.version, entity.version)
    }.update.apply()
    entity
  }

  def destroy(entity: GSCRec)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(GSCRec).where.eq(column.systemId, entity.systemId).and.eq(column.version, entity.version) }.update
      .apply()
  }

}
