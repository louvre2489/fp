package com.louvre2489.fp.domain.entity

import java.math.BigInteger
import java.security.MessageDigest

import com.louvre2489.fp.common.DomainException
import com.louvre2489.fp.domain.value.{ MessageId, UserId }
import com.louvre2489.fp.repository.UserRepository

object UserInfo {

  def apply(userId: UserId, password: String, userName: String)(
      implicit repository: UserRepository[UserInfo, UserId]
  ): UserInfo = {

    val notHashedUserInfo = new UserInfo(userId, password, userName, false, None, None)

    // パスワードのハッシュ化
    val passwordHashedUserInfo = notHashedUserInfo.setHashedPassword

    // ハッシュ化済UserIdの格納
    passwordHashedUserInfo.setHashedUserId
  }

  /**
    * SHA256ハッシュ化
    * @param target ハッシュ化対象文字列
    * @return SHA256ハッシュ文字列
    */
  def sha256(target: String): String = {

    val digest =
      MessageDigest.getInstance("SHA-256").digest(target.getBytes("UTF-8"))

    String.format("%032x", new BigInteger(1, digest))
  }
}

/**
  *  ユーザー情報を扱う
  * @param userId ユーザーID
  * @param password パスワード
  * @param userName ユーザー名
  * @param isPasswordHashed ハッシュ済の場合はtrue
  * @param hashedUserId ハッシュ済ユーザーID
  *                     未設定時はnullを設定するが、nullのままでは保存不可
  * @param salt ソルト
  * @param repository userRepository
  */
case class UserInfo(userId: UserId,
                    password: String,
                    userName: String,
                    isPasswordHashed: Boolean,
                    hashedUserId: Option[String],
                    salt: Option[String])(
    implicit repository: UserRepository[UserInfo, UserId]
) extends Entity[UserId] {

  require(userId != null)

  require(password != null)

  require(userName != null)

  @Override
  def save: Either[Exception, Unit] = {

    if (isPasswordHashed && hashedUserId.isInstanceOf[Some[_]] && salt.isInstanceOf[Some[_]])
      repository.save(this)
    else
      // ハッシュ化未実施
      Left(DomainException(MessageId("DE002")))
  }

  def changePassword(newPassword: String): UserInfo = {

    val newUserInfo = this.copy(password = newPassword, isPasswordHashed = false)

    newUserInfo.setHashedPassword
  }

  /**
    * セットされているパスワードをハッシュ化した`UserInfo`を返す。
    * ハッシュ化済の場合は値の変更は発生しない
    * @return `UserInfo`
    */
  def setHashedPassword: UserInfo = {

    /**
      * `USerInfo`に設定されている`UserId`から`salt`を生成し、
      * それを元にパスワードをハッシュ化する
      * 既にハッシュ化済の場合は、設定済の値を返す
      * @return `(ハッシュ化済パスワード, salt)`
      */
    def sha256Password: (String, Option[String]) = {

      if (this.isPasswordHashed)
        (this.password, this.salt)
      else {

        val salt = this.createSalt

        (UserInfo.sha256(password.concat(salt)), Some(salt))
      }
    }

    val (hashedPassword, salt) = sha256Password

    this.copy(password = hashedPassword, isPasswordHashed = true, salt = salt)
  }

  /**
    * パスワードを元にソルトを生成する
    * @return ソルト
    */
  private def createSalt: String = UserInfo.sha256(this.password)

  /**
    * セットされているパスワードをハッシュ化した`UserInfo`を返す。
    * ハッシュ化済の場合は値の変更は発生しない
    * @return `UserInfo`
    */
  private def setHashedUserId: UserInfo = {

    val hashedUserId = UserInfo.sha256(this.userId.value.concat(this.salt.getOrElse("")))

    this.copy(hashedUserId = Some(hashedUserId))
  }
}
