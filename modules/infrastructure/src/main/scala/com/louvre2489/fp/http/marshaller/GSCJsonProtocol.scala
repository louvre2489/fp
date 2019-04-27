package com.louvre2489.fp.http.marshaller

import com.louvre2489.fp.application.entity._
import com.louvre2489.fp.domain.characteristics._
import com.louvre2489.fp.domain.value._
import spray.json._

/**
  * GSC関連のマーシャラー
  */
object GSCJsonProtocol extends DefaultJsonProtocol {

  val TARGET_NAME = "__GSC__"

  val SYSTEM_ID = "systemId"

  val VERSION = "version"

  val DATA_COMMUNICATIONS = "dataCommunications"

  val DISTRIBUTED_DATA_PROCESSING = "distributedDataProcessing"

  val PERFORMANCE = "performance"

  val HEAVILY_USED_CONFIGURATION = "heavilyUsedConfiguration"

  val TRANSACTION_RATE = "transactionRate"

  val ONLINE_DATA_ENTRY = "onlineDataEntry"

  val END_USER_EFFECIENCY = "endUserEffeciency"

  val ONLINE_UPDATE = "onlineUpdate"

  val COMPLEX_PROCESSING = "complexProcessing"

  val RE_USABILITY = "reUsability"

  val INSTALLATION_EASE = "installationEase"

  val OPERATIONAL_EASE = "operationalEase"

  val MULTIPLE_SITES = "multipleSites"

  val FACILITATE_CHANGE = "facilitateChange"

  private implicit val systemIdFormat: RootJsonFormat[SystemId] = SystemJsonProtocol.systemIdFormat

  /**
    * GSC用マーシャラー
    */
  implicit val gscFormat: RootJsonFormat[GSC] = new RootJsonFormat[GSC] {

    override def read(json: JsValue): GSC =
      json.asJsObject.getFields(
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
      ) match {
        case Seq(JsNumber(systemId),
                 JsNumber(version),
                 JsNumber(dataCommunications),
                 JsNumber(distributedDataProcessing),
                 JsNumber(performance),
                 JsNumber(heavilyUsedConfiguration),
                 JsNumber(transactionRate),
                 JsNumber(onlineDataEntry),
                 JsNumber(endUserEffeciency),
                 JsNumber(onlineUpdate),
                 JsNumber(complexProcessing),
                 JsNumber(reUsability),
                 JsNumber(installationEase),
                 JsNumber(operationalEase),
                 JsNumber(multipleSites),
                 JsNumber(facilitateChange)) =>
          GSC(
            SystemId(systemId.toLong),
            Version(version.toShort),
            DataCommunications(DI(dataCommunications.toShort)),
            DistributedDataProcessing(DI(distributedDataProcessing.toShort)),
            Performance(DI(performance.toShort)),
            HeavilyUsedConfiguration(DI(heavilyUsedConfiguration.toShort)),
            TransactionRate(DI(transactionRate.toShort)),
            OnlineDataEntry(DI(onlineDataEntry.toShort)),
            EndUserEffeciency(DI(endUserEffeciency.toShort)),
            OnlineUpdate(DI(onlineUpdate.toShort)),
            ComplexProcessing(DI(complexProcessing.toShort)),
            ReUsability(DI(reUsability.toShort)),
            InstallationEase(DI(installationEase.toShort)),
            OperationalEase(DI(operationalEase.toShort)),
            MultipleSites(DI(multipleSites.toShort)),
            FacilitateChange(DI(facilitateChange.toShort))
          )
        case _ => throw DeserializationException(TARGET_NAME)
      }

    override def write(obj: GSC): JsObject = JsObject(
      SYSTEM_ID                   -> JsNumber(obj.systemId.value),
      VERSION                     -> JsNumber(obj.version.value),
      DATA_COMMUNICATIONS         -> JsNumber(obj.dataCommunications.di.value),
      DISTRIBUTED_DATA_PROCESSING -> JsNumber(obj.distributedDataProcessing.di.value),
      PERFORMANCE                 -> JsNumber(obj.performance.di.value),
      HEAVILY_USED_CONFIGURATION  -> JsNumber(obj.heavilyUsedConfiguration.di.value),
      TRANSACTION_RATE            -> JsNumber(obj.transactionRate.di.value),
      ONLINE_DATA_ENTRY           -> JsNumber(obj.onlineDataEntry.di.value),
      END_USER_EFFECIENCY         -> JsNumber(obj.endUserEffeciency.di.value),
      ONLINE_UPDATE               -> JsNumber(obj.onlineUpdate.di.value),
      COMPLEX_PROCESSING          -> JsNumber(obj.complexProcessing.di.value),
      RE_USABILITY                -> JsNumber(obj.reUsability.di.value),
      INSTALLATION_EASE           -> JsNumber(obj.installationEase.di.value),
      OPERATIONAL_EASE            -> JsNumber(obj.operationalEase.di.value),
      MULTIPLE_SITES              -> JsNumber(obj.multipleSites.di.value),
      FACILITATE_CHANGE           -> JsNumber(obj.facilitateChange.di.value),
    )
  }
}
