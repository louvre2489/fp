package com.louvre2489.fp.application.entity

import com.louvre2489.fp.domain.characteristics._
import com.louvre2489.fp.domain.value.{ SystemId, Version }

case class GSC(systemId: SystemId,
               version: Version,
               dataCommunications: DataCommunications,
               distributedDataProcessing: DistributedDataProcessing,
               performance: Performance,
               heavilyUsedConfiguration: HeavilyUsedConfiguration,
               transactionRate: TransactionRate,
               onlineDataEntry: OnlineDataEntry,
               endUserEffeciency: EndUserEffeciency,
               onlineUpdate: OnlineUpdate,
               complexProcessing: ComplexProcessing,
               reUsability: ReUsability,
               installationEase: InstallationEase,
               operationalEase: OperationalEase,
               multipleSites: MultipleSites,
               facilitateChange: FacilitateChange) {}
