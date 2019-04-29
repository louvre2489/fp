package com.louvre2489.fp.rdb.repository

import com.louvre2489.fp.domain.characteristics._
import com.louvre2489.fp.domain.entity.GSC
import com.louvre2489.fp.domain.value._
import com.louvre2489.fp.rdb.db.{ BaseDao, BaseDaoObject }
import com.louvre2489.fp.repository.GSCRepository
import com.louvre2489.fp.rdb.models._
import scalikejdbc.DB

object GSCDao extends BaseDaoObject {

  def apply()(implicit db: DB) = new GSCDao
}

class GSCDao(implicit db: DB) extends BaseDao with GSCRepository[GSC, SystemId] {

  @Override
  def find(id: SystemId, version: Version): Option[GSC] = {
    db readOnly { implicit session =>
      GSCRec
        .find(id.value, version.value) // TODO
        .map { data =>
          GSC(
            SystemId(data.systemId),
            Version(data.version),
            DataCommunications(DI(data.dataCommunications)),
            DistributedDataProcessing(DI(data.distributedDataProcessing)),
            Performance(DI(data.performance)),
            HeavilyUsedConfiguration(DI(data.heavilyUsedConfiguration)),
            TransactionRate(DI(data.transactionRate)),
            OnlineDataEntry(DI(data.onlineDataEntry)),
            EndUserEffeciency(DI(data.endUserEffeciency)),
            OnlineUpdate(DI(data.onlineUpdate)),
            ComplexProcessing(DI(data.complexProcessing)),
            ReUsability(DI(data.reUsability)),
            InstallationEase(DI(data.installationEase)),
            OperationalEase(DI(data.operationalEase)),
            MultipleSites(DI(data.multipleSites)),
            FacilitateChange(DI(data.facilitateChange))
          )(this)
        }
    }
  }

  @Override
  def save(entity: GSC): Either[Exception, Unit] = {
    try {
      GSCRec(
        entity.systemId.value,
        entity.version.value,
        entity.dataCommunications.di.value,
        entity.distributedDataProcessing.di.value,
        entity.performance.di.value,
        entity.heavilyUsedConfiguration.di.value,
        entity.transactionRate.di.value,
        entity.onlineDataEntry.di.value,
        entity.endUserEffeciency.di.value,
        entity.onlineUpdate.di.value,
        entity.complexProcessing.di.value,
        entity.reUsability.di.value,
        entity.installationEase.di.value,
        entity.operationalEase.di.value,
        entity.multipleSites.di.value,
        entity.facilitateChange.di.value
      ).save()

      Right(Unit)
    } catch {
      case e: Exception =>
        Left(e)
    }
  }
}
