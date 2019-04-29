package com.louvre2489.fp.http.marshaller

import com.louvre2489.fp.application.entity._
import com.louvre2489.fp.domain.value.{ SubSystemId, SystemId }
import SystemJsonProtocol.SYSTEM_ID
import spray.json._

/**
  * SubSystem関連のマーシャラー
  */
object SubSystemJsonProtocol extends DefaultJsonProtocol {

  val TARGET_NAME = "__SUBSYSTEM__"

  val ID_VALUE = "value"

  val SUB_SYSTEM_ID = "subSystemId"

  val SUB_SYSTEM_NAME = "subSystemName"

  private implicit val systemIdFormat: RootJsonFormat[SystemId] = SystemJsonProtocol.systemIdFormat

  /**
    * SubSystemId用マーシャラー
    */
  private val convertLongToSubSystemId: Long => SubSystemId   = SubSystemId.apply
  implicit val subSystemIdFormat: RootJsonFormat[SubSystemId] = jsonFormat(convertLongToSubSystemId, ID_VALUE)

  /**
    * SubSystem用マーシャラー
    */
  implicit val subSystemFormat: RootJsonFormat[SubSystem] = new RootJsonFormat[SubSystem] {

    override def read(json: JsValue): SubSystem =
      json.asJsObject.getFields(SUB_SYSTEM_ID, SUB_SYSTEM_NAME, SYSTEM_ID) match {
        case Seq(JsNumber(subSystemId), JsString(subSystemName), JsNumber(systemId)) =>
          SubSystem(SubSystemId(subSystemId.toLong), subSystemName, SystemId(systemId.toLong))
        case _ => throw DeserializationException(TARGET_NAME)
      }

    override def write(obj: SubSystem): JsObject = JsObject(
      SUB_SYSTEM_ID   -> JsNumber(obj.subSystemId.value),
      SUB_SYSTEM_NAME -> JsString(obj.subSystemName),
      SYSTEM_ID       -> JsNumber(obj.systemId.value)
    )
  }

  /**
    * List[SubSystem]用マーシャラー
    */
  implicit val subSystemListFormat: RootJsonFormat[List[SubSystem]] = new RootJsonFormat[List[SubSystem]] {

    override def read(json: JsValue): List[SubSystem] = List(subSystemFormat.read(json))

    override def write(obj: List[SubSystem]): JsArray = JsArray(obj.map(_.toJson).toVector)
  }
}
