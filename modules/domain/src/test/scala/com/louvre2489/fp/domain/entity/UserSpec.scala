package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.common.{ DomainException, MessageFactory }
import com.louvre2489.fp.domain.value._
import org.scalatest.FlatSpec

class UserSpec extends FlatSpec {

  val messageConf = MessageFactory.load

  behavior of "User"

  "User" can "新規ユーザーの作成" in {

    val password = "password"
    val userInfo = UserInfo(UserId("USE_ID"), password, "USER_NAME")

    assert(userInfo.isPasswordHashed === true)
    assert(userInfo.password !== password)
    assert(userInfo.hashedUserId.get !== userInfo.userId.value)

    assert(userInfo.password === UserInfo.sha256(password + userInfo.salt.get))
    assert(
      userInfo.hashedUserId.get === UserInfo.sha256(userInfo.salt.map(x => userInfo.userId.value + x).getOrElse(""))
    )
  }

  it should "ハッシュ化済であればパスワードは変化しない" in {

    val userInfo = UserInfo(UserId("USE_ID"), "password", "USER_NAME", true, Some("hashedUserId"), Some("salt"))

    val pwHashedUserInfo = userInfo.setHashedPassword

    assert(pwHashedUserInfo.isPasswordHashed === true)
    assert(pwHashedUserInfo.password === userInfo.password)
  }

  it should "ハッシュ化済でなければ保存時に例外が発生する" in {

    val userInfo = UserInfo(UserId("USE_ID"), "password", "USER_NAME", false, None, None)

    val result = userInfo.save

    assert(result.isInstanceOf[Left[DomainException, Unit]])

    result match {
      case Right(_) => fail()
      case Left(e) => {
        assert(e.getMessage === "DE002:" + messageConf.getString("DE002"))
      }
    }
  }

  it should "パスワード変更" in {

    val oldPass = "old"
    val newPass = "new"

    val userInfo = UserInfo(UserId("USE_ID"), oldPass, "USER_NAME")

    val newUserInfo = userInfo.changePassword(newPass)

    assert(userInfo.userId.value === newUserInfo.userId.value)
    assert(userInfo.password !== newUserInfo.password)
    assert(userInfo.salt !== newUserInfo.salt)
    assert(newUserInfo.isPasswordHashed === true)

  }
}
