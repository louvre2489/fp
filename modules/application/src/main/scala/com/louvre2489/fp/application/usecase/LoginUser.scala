package com.louvre2489.fp.application.usecase

import com.louvre2489.fp.application.entity.{ User, UserCertification }
import com.louvre2489.fp.domain.entity.UserInfo
import com.louvre2489.fp.domain.value.UserId
import com.louvre2489.fp.repository.UserRepository
import org.slf4j.LoggerFactory

class LoginUser(implicit userRepository: UserRepository[UserInfo, UserId]) {

  private val logger = LoggerFactory.getLogger(this.getClass)

  private val isLoginSuccess = true
  private val isLoginFail    = false

  def login(user: UserCertification): LoginUserInfo = {

    val userId = user.userId

    userRepository.findById(userId) match {
      case None =>
        // ユーザー取得失敗
        LoginUserInfo(loginFail(userId), None)
      case Some(loginUser) => {

        val password = user.password.getOrElse("")

        logger.debug(userId.value)
        logger.debug(password)

        if (loginUser.isCorrectPassword(password)) {
          // ログイン成功時は、JWT用にハッシュ化されたユーザーIDを返す
          logger.debug("ログイン成功：" + userId.value)
          LoginUserInfo(loginSuccess(userId, loginUser.hashedUserId), loginUser.hashedUserId)
        } else
          LoginUserInfo(loginFail(userId), None)
      }
    }
  }

  private def loginFail(userId: UserId) =
    UserCertification(userId, None, isLoginFail, None)

  private def loginSuccess(userId: UserId, hashedUserId: Option[String]) =
    UserCertification(userId, None, isLoginSuccess, Some(Jwt.createToken(hashedUserId)))

  case class LoginUserInfo(userCert: UserCertification, hashedUserId: Option[String])
}

import java.time.Instant

import spray.json._
import pdi.jwt.{ JwtAlgorithm, JwtSprayJson }

object Jwt {

  /**
    * 本来であれば公開鍵だけど、適当な文字列で代用
    */
  private val key = "abcdefg1234xyz"

  /**
    * アルゴリズム
    */
  private val algorithm = JwtAlgorithm.HS256

  def createToken(hashedUserId: Option[String]): String = {

    val claimJson = s"""{"expires":${Instant.now.getEpochSecond}}""".parseJson.asJsObject
    JwtSprayJson.encode(claimJson, key, algorithm)
  }
}
