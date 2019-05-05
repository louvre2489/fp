package com.louvre2489.fp.domain.entity

import java.math.BigInteger
import java.security.MessageDigest

import com.louvre2489.fp.common.DomainException
import com.typesafe.config._
import com.louvre2489.fp.domain.value.{ MessageId, UserId }
import com.louvre2489.fp.repository.UserRepository

/**
  *  ユーザー情報を扱う
  * @param userId ユーザーID
  * @param password パスワード
  * @param userName ユーザー名
  * @param isPasswordHashed ハッシュ済の場合はtrue
  * @param repository userRepository
  */
case class UserInfo(userId: UserId, password: String, userName: String, isPasswordHashed: Boolean = false)(
    implicit repository: UserRepository[UserInfo, UserId]
) extends Entity[UserId] {

  @Override
  def save: Either[Exception, Unit] = {

    if (isPasswordHashed)
      repository.save(this)
    else
      // ハッシュ化未実施
      Left(DomainException(MessageId("DE002")))
  }

  /**
    * セットされているパスワードをハッシュ化した`UserInfo`を返す。
    * ハッシュ化済の場合は値の変更は発生しない
    * @return `UserInfo`
    */
  def hashPassword: UserInfo = {

    def sha256Password: String = {

      if (isPasswordHashed)
        this.password
      else {

        val conf = ConfigFactory.load

        val digest =
          MessageDigest.getInstance("SHA-256").digest((password.concat(conf.getString(SALT)).getBytes("UTF-8")))
        String.format("%032x", new BigInteger(1, digest))
      }
    }

    this.copy(password = sha256Password, isPasswordHashed = true)
  }
}
