package com.louvre2489.fp.domain.systemcharacteristics

import com.louvre2489.fp.domain.value.DI
import org.scalatest.FlatSpec

class GSCSpec extends FlatSpec {

  behavior of "Constructor"

  "Constructor" should "create 0 VAF" in {

    val gsc = GSC()

    assert(gsc.dataCommunications.di.value === 0)
    assert(gsc.distributedDataProcessing.di.value === 0)
    assert(gsc.performance.di.value === 0)
    assert(gsc.heavilyUsedConfiguration.di.value === 0)
    assert(gsc.transactionRate.di.value === 0)
    assert(gsc.onlineDataEntry.di.value === 0)
    assert(gsc.endUserEffeciency.di.value === 0)
    assert(gsc.onlineUpdate.di.value === 0)
    assert(gsc.complexProcessing.di.value === 0)
    assert(gsc.reUsability.di.value === 0)
    assert(gsc.installationEase.di.value === 0)
    assert(gsc.operationalEase.di.value === 0)
    assert(gsc.multipleSites.di.value === 0)
    assert(gsc.facilitateChange.di.value === 0)
  }

  it should "create 1 VAF when give DI(1)" in {

    val gsc = GSC(DataCommunications(DI(1)))

    assert(gsc.dataCommunications.di.value === 1)
    assert(gsc.distributedDataProcessing.di.value === 0)
    assert(gsc.performance.di.value === 0)
    assert(gsc.heavilyUsedConfiguration.di.value === 0)
    assert(gsc.transactionRate.di.value === 0)
    assert(gsc.onlineDataEntry.di.value === 0)
    assert(gsc.endUserEffeciency.di.value === 0)
    assert(gsc.onlineUpdate.di.value === 0)
    assert(gsc.complexProcessing.di.value === 0)
    assert(gsc.reUsability.di.value === 0)
    assert(gsc.installationEase.di.value === 0)
    assert(gsc.operationalEase.di.value === 0)
    assert(gsc.multipleSites.di.value === 0)
    assert(gsc.facilitateChange.di.value === 0)
  }

  it should "create VAFs" in {

    val gsc = GSC(
      DataCommunications(DI(1)),
      DistributedDataProcessing(DI(2)),
      Performance(DI(3)),
      HeavilyUsedConfiguration(DI(4)),
      TransactionRate(DI(5)),
      OnlineDataEntry(DI(1)),
      EndUserEffeciency(DI(2)),
      OnlineUpdate(DI(3)),
      ComplexProcessing(DI(4)),
      ReUsability(DI(5)),
      InstallationEase(DI(1)),
      OperationalEase(DI(2)),
      MultipleSites(DI(3)),
      FacilitateChange(DI(4))
    )

    assert(gsc.dataCommunications.di.value === 1)
    assert(gsc.distributedDataProcessing.di.value === 2)
    assert(gsc.performance.di.value === 3)
    assert(gsc.heavilyUsedConfiguration.di.value === 4)
    assert(gsc.transactionRate.di.value === 5)
    assert(gsc.onlineDataEntry.di.value === 1)
    assert(gsc.endUserEffeciency.di.value === 2)
    assert(gsc.onlineUpdate.di.value === 3)
    assert(gsc.complexProcessing.di.value === 4)
    assert(gsc.reUsability.di.value === 5)
    assert(gsc.installationEase.di.value === 1)
    assert(gsc.operationalEase.di.value === 2)
    assert(gsc.multipleSites.di.value === 3)
    assert(gsc.facilitateChange.di.value === 4)
  }

  behavior of "methods"

  "TDI" should "Summary DIs" in {

    val gsc = GSC(
      DataCommunications(DI(1)),
      DistributedDataProcessing(DI(2)),
      Performance(DI(3)),
      HeavilyUsedConfiguration(DI(4)),
      TransactionRate(DI(5)),
      OnlineDataEntry(DI(1)),
      EndUserEffeciency(DI(2)),
      OnlineUpdate(DI(3)),
      ComplexProcessing(DI(4)),
      ReUsability(DI(5)),
      InstallationEase(DI(1)),
      OperationalEase(DI(2)),
      MultipleSites(DI(3)),
      FacilitateChange(DI(4))
    )

    assert(gsc.TDI.value === 40)
  }

  it should "0 TDI when GSC has no General System Characteristics" in {

    val gsc = GSC()
    assert(gsc.TDI.value === 0)
  }

  "VAF" should "0.65 VAF when GSC has no General System Characteristics" in {
    val gsc = GSC()
    assert(gsc.VAF.value === 0.65)
  }

  it should "VAF when GSC has no General System Characteristics" in {

    val gsc = GSC(
      DataCommunications(DI(1)),
      DistributedDataProcessing(DI(2)),
      Performance(DI(3)),
      HeavilyUsedConfiguration(DI(4)),
      TransactionRate(DI(5)),
      OnlineDataEntry(DI(1)),
      EndUserEffeciency(DI(2)),
      OnlineUpdate(DI(3)),
      ComplexProcessing(DI(4)),
      ReUsability(DI(5)),
      InstallationEase(DI(1)),
      OperationalEase(DI(2)),
      MultipleSites(DI(3)),
      FacilitateChange(DI(4))
    )

    assert(gsc.VAF.value === 1.05)
  }
}

class GeneralSystemCharacteristicsSpec extends FlatSpec {

  behavior of "Range Check"

  "DataCommunications" can "have minimum characteristics, 0" in {
    val characteristics = DataCommunications(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = DataCommunications(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      DataCommunications(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      DataCommunications(DI(6))
    }
  }

  "DistributedDataProcessing" can "have minimum characteristics, 0" in {
    val characteristics = DistributedDataProcessing(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = DistributedDataProcessing(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      DistributedDataProcessing(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      DistributedDataProcessing(DI(6))
    }
  }

  "Performance" can "have minimum characteristics, 0" in {
    val characteristics = Performance(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = Performance(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      Performance(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      Performance(DI(6))
    }
  }

  "HeavilyUsedConfiguration" can "have minimum characteristics, 0" in {
    val characteristics = HeavilyUsedConfiguration(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = HeavilyUsedConfiguration(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      HeavilyUsedConfiguration(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      HeavilyUsedConfiguration(DI(6))
    }
  }

  "TransactionRate" can "have minimum characteristics, 0" in {
    val characteristics = TransactionRate(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = TransactionRate(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      TransactionRate(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      TransactionRate(DI(6))
    }
  }

  "OnlineDataEntry" can "have minimum characteristics, 0" in {
    val characteristics = OnlineDataEntry(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = OnlineDataEntry(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      OnlineDataEntry(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      OnlineDataEntry(DI(6))
    }
  }

  "EndUserEffeciency" can "have minimum characteristics, 0" in {
    val characteristics = EndUserEffeciency(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = EndUserEffeciency(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      EndUserEffeciency(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      EndUserEffeciency(DI(6))
    }
  }

  "OnlineUpdate" can "have minimum characteristics, 0" in {
    val characteristics = OnlineUpdate(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = OnlineUpdate(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      OnlineUpdate(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      OnlineUpdate(DI(6))
    }
  }

  "ComplexProcessing" can "have minimum characteristics, 0" in {
    val characteristics = ComplexProcessing(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = ComplexProcessing(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      ComplexProcessing(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      ComplexProcessing(DI(6))
    }
  }

  "ReUsability" can "have minimum characteristics, 0" in {
    val characteristics = ReUsability(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = ReUsability(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      ReUsability(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      ReUsability(DI(6))
    }
  }

  "InstallationEase" can "have minimum characteristics, 0" in {
    val characteristics = InstallationEase(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = InstallationEase(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      InstallationEase(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      InstallationEase(DI(6))
    }
  }

  "OperationalEase" can "have minimum characteristics, 0" in {
    val characteristics = OperationalEase(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = OperationalEase(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      OperationalEase(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      OperationalEase(DI(6))
    }
  }

  "MultipleSites" can "have minimum characteristics, 0" in {
    val characteristics = MultipleSites(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = MultipleSites(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      MultipleSites(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      MultipleSites(DI(6))
    }
  }

  "FacilitateChange" can "have minimum characteristics, 0" in {
    val characteristics = FacilitateChange(DI(0))
    assert(characteristics.di.value === characteristics.MIN_POINT)
  }

  it can "have maximum characteristics, 5" in {
    val characteristics = FacilitateChange(DI(5))
    assert(characteristics.di.value === characteristics.MAX_POINT)
  }

  it can "not have less than 0" in {
    assertThrows[IllegalArgumentException] {
      FacilitateChange(DI(-1))
    }
  }

  it can "not have more than 5" in {
    assertThrows[IllegalArgumentException] {
      FacilitateChange(DI(6))
    }
  }
}
