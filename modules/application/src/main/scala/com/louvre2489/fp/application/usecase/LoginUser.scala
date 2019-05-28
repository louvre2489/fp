package com.louvre2489.fp.application.usecase

import com.louvre2489.fp.application.entity.{ User, UserCertification }
import com.louvre2489.fp.domain.entity.UserInfo
import com.louvre2489.fp.domain.value.UserId
import com.louvre2489.fp.repository.UserRepository

class LoginUser(implicit userRepository: UserRepository[UserInfo, UserId]) {

  def login(user: UserCertification): LoginUserInfo = {

    val userId = user.userId

    userRepository.findById(userId) match {
      case None =>
        // ユーザー取得失敗
        LoginUserInfo(loginFail(userId), None)
      case Some(loginUser) => {

        val password = user.password.getOrElse("")

        if (loginUser.isCorrectPassword(password))
          LoginUserInfo(loginFail(userId), None)
        else
          // ログイン成功時は、JWT用にハッシュ化されたユーザーIDを返す
          LoginUserInfo(loginSuccess(userId), loginUser.hashedUserId)
      }
    }
  }

  private def loginFail(userId: UserId) =
    UserCertification(userId, None, false)

  private def loginSuccess(userId: UserId) =
    UserCertification(userId, None, true)

  case class LoginUserInfo(userCert: UserCertification, hashedUserId: Option[String])
}
