package com.louvre2489.fp.http.marshaller

import com.louvre2489.fp.application.entity._
import com.louvre2489.fp.domain.value.SystemId
import spray.json._

/**
  * System関連のマーシャラー
  */
object SystemJsonProtocol extends DefaultJsonProtocol {

  val TARGET_NAME = "__SYSTEM__"

  val ID_VALUE = "value"

  val SYSTEM_ID = "systemId"

  val SYSTEM_NAME = "systemName"

  /**
    * SystemId用マーシャラー
    */
  private val convertLongToSystemId: Long => SystemId   = SystemId.apply
  implicit val systemIdFormat: RootJsonFormat[SystemId] = jsonFormat(convertLongToSystemId, ID_VALUE)

  /**
    * System用マーシャラー
    */
  implicit val systemFormat: RootJsonFormat[System] = new RootJsonFormat[System] {

    override def read(json: JsValue): System =
      json.asJsObject.getFields(SYSTEM_ID, SYSTEM_NAME) match {
        case Seq(JsNumber(systemId), JsString(systemName)) =>
          System(SystemId(systemId.toLong), systemName)
        case _ => throw DeserializationException(TARGET_NAME)
      }

    override def write(obj: System): JsObject = JsObject(
      SYSTEM_ID   -> JsNumber(obj.systemId.value),
      SYSTEM_NAME -> JsString(obj.systemName)
    )
  }

  /**
    * List[System]用マーシャラー
    */
  implicit val systemListFormat: RootJsonFormat[List[System]] = new RootJsonFormat[List[System]] {

    override def read(json: JsValue): List[System] = List(systemFormat.read(json))

    override def write(obj: List[System]): JsArray = JsArray(obj.map(_.toJson).toVector)
  }
}
