package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.domain.characteristics._
import com.louvre2489.fp.domain.value.{ DI, SystemId, ValueAdjustmentFactor }
import com.louvre2489.fp.repository.GSCRepository

case class GSC(
    dataCommunications: DataCommunications = DataCommunications(DI(0)),
    distributedDataProcessing: DistributedDataProcessing = DistributedDataProcessing(DI(0)),
    performance: Performance = Performance(DI(0)),
    heavilyUsedConfiguration: HeavilyUsedConfiguration = HeavilyUsedConfiguration(DI(0)),
    transactionRate: TransactionRate = TransactionRate(DI(0)),
    onlineDataEntry: OnlineDataEntry = OnlineDataEntry(DI(0)),
    endUserEffeciency: EndUserEffeciency = EndUserEffeciency(DI(0)),
    onlineUpdate: OnlineUpdate = OnlineUpdate(DI(0)),
    complexProcessing: ComplexProcessing = ComplexProcessing(DI(0)),
    reUsability: ReUsability = ReUsability(DI(0)),
    installationEase: InstallationEase = InstallationEase(DI(0)),
    operationalEase: OperationalEase = OperationalEase(DI(0)),
    multipleSites: MultipleSites = MultipleSites(DI(0)),
    facilitateChange: FacilitateChange = FacilitateChange(DI(0))
)(implicit repository: GSCRepository[GSC, SystemId])
    extends Entity[SystemId] {

  @Override
  def save: Either[Exception, Unit] =
    repository.save(this)

  /**
    * Create new GSC object.
    * Set new General System Characteristic,
    * then this method returns new GSC object.
    * @param gc General System Characteristic that you want to change
    * @return new GSC
    */
  def changeDI(gc: GeneralSystemCharacteristic): GSC = {

    var dataCommunications        = this.dataCommunications
    var distributedDataProcessing = this.distributedDataProcessing
    var performance               = this.performance
    var heavilyUsedConfiguration  = this.heavilyUsedConfiguration
    var transactionRate           = this.transactionRate
    var onlineDataEntry           = this.onlineDataEntry
    var endUserEffeciency         = this.endUserEffeciency
    var onlineUpdate              = this.onlineUpdate
    var complexProcessing         = this.complexProcessing
    var reUsability               = this.reUsability
    var installationEase          = this.installationEase
    var operationalEase           = this.operationalEase
    var multipleSites             = this.multipleSites
    var facilitateChange          = this.facilitateChange

    gc match {
      case gc: DataCommunications => dataCommunications = gc

      case gc: DistributedDataProcessing => distributedDataProcessing = gc

      case gc: Performance => performance = gc

      case gc: HeavilyUsedConfiguration => heavilyUsedConfiguration = gc

      case gc: TransactionRate => transactionRate = gc

      case gc: OnlineDataEntry => onlineDataEntry = gc

      case gc: EndUserEffeciency => endUserEffeciency = gc

      case gc: OnlineUpdate => onlineUpdate = gc

      case gc: ComplexProcessing => complexProcessing = gc

      case gc: ReUsability => reUsability = gc

      case gc: InstallationEase => installationEase = gc

      case gc: OperationalEase => operationalEase = gc

      case gc: MultipleSites => multipleSites = gc

      case gc: FacilitateChange => facilitateChange = gc
    }

    GSC(
      dataCommunications,
      distributedDataProcessing,
      performance,
      heavilyUsedConfiguration,
      transactionRate,
      onlineDataEntry,
      endUserEffeciency,
      onlineUpdate,
      complexProcessing,
      reUsability,
      installationEase,
      operationalEase,
      multipleSites,
      facilitateChange
    )
  }

  def TDI: DI = {
    dataCommunications.di +
    distributedDataProcessing.di +
    performance.di +
    heavilyUsedConfiguration.di +
    transactionRate.di +
    onlineDataEntry.di +
    endUserEffeciency.di +
    onlineUpdate.di +
    complexProcessing.di +
    reUsability.di +
    installationEase.di +
    operationalEase.di +
    multipleSites.di +
    facilitateChange.di
  }

  def VAF: ValueAdjustmentFactor = ValueAdjustmentFactor((TDI.value * 0.01) + 0.65)
}
