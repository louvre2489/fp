package com.louvre2489.fp.http.marshaller

import com.louvre2489.fp.application.entity._
import com.louvre2489.fp.domain.value.UserId
import spray.json._

object UserIdJsonProtocol extends DefaultJsonProtocol {

  val ID_VALUE = "value"

  /**
    * UserId用マーシャラー
    */
  private val convertLongToUserId: String => UserId = UserId.apply
  implicit val userIdFormat: RootJsonFormat[UserId] = jsonFormat(convertLongToUserId, ID_VALUE)
}

/**
  * User関連のマーシャラー
  */
object UserJsonProtocol extends DefaultJsonProtocol {

  val TARGET_NAME = "__USER__"

  val USER_ID = "userId"

  val USER_NAME = "userName"

  /**
    * User用マーシャラー
    */
  implicit val userFormat: RootJsonFormat[User] = new RootJsonFormat[User] {

    override def read(json: JsValue): User =
      json.asJsObject.getFields(USER_ID, USER_NAME) match {
        case Seq(JsString(userId), JsString(userName)) =>
          User(UserId(userId), userName)
        case _ => throw DeserializationException(TARGET_NAME)
      }

    override def write(obj: User): JsObject = JsObject(
      USER_ID   -> JsString(obj.userId.value),
      USER_NAME -> JsString(obj.userName)
    )
  }

  /**
    * List[User]用マーシャラー
    */
  implicit val userListFormat: RootJsonFormat[List[User]] = new RootJsonFormat[List[User]] {

    override def read(json: JsValue): List[User] = List(userFormat.read(json))

    override def write(obj: List[User]): JsArray = JsArray(obj.map(_.toJson).toVector)
  }
}

/**
  * ユーザー認証用マーシャラー
  */
object UserCertificationJsonProtocol extends DefaultJsonProtocol {

  val TARGET_NAME = "__USER_CERT__"

  val USER_ID = "userId"

  val PASS = "password"

  val RESULT = "isLoginSuccess"

  val TOKEN = "token"

  /**
    * ユーザー認証用マーシャラー
    */
  implicit val userCertificationFormat: RootJsonFormat[UserCertification] = new RootJsonFormat[UserCertification] {

    private val isLoginFail = false

    override def read(json: JsValue): UserCertification =
      json.asJsObject.getFields(USER_ID, PASS, RESULT) match {
        case Seq(JsString(userId), JsString(password), JsBoolean(_)) =>
          if (password == null || password.isEmpty)
            // パスワード未設定時は`None`
            UserCertification(UserId(userId), None, isLoginFail, None)
          else
            // パスワード入力時は`Some`で包む
            UserCertification(UserId(userId), Some(password), isLoginFail, None)
        case _ => throw DeserializationException(TARGET_NAME)
      }

    override def write(obj: UserCertification): JsObject = JsObject(
      USER_ID -> JsString(obj.userId.value),
      PASS    -> JsString(obj.password.getOrElse("")),
      RESULT  -> JsBoolean(obj.isLoginSuccess),
      TOKEN   -> JsString(obj.token.getOrElse(""))
    )
  }
}
