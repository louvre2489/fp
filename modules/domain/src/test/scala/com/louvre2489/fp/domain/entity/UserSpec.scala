package com.louvre2489.fp.domain.entity

import com.louvre2489.fp.common.{ DomainException, MessageFactory }
import com.louvre2489.fp.domain.value._
import org.scalatest.FlatSpec

class UserSpec extends FlatSpec {

  val messageConf = MessageFactory.load

  behavior of "User"

  "User" can "パスワードハッシュ化" in {

    val userInfo = UserInfo(UserId("USE_ID"), "password", "USER_NAME", false)

    val pwHashedUserInfo = userInfo.hashPassword

    assert(pwHashedUserInfo.isPasswordHashed === true)
    assert(pwHashedUserInfo.password !== userInfo.password)
  }

  it should "ハッシュ化済であればパスワードは変化しない" in {

    val userInfo = UserInfo(UserId("USE_ID"), "password", "USER_NAME", true)

    val pwHashedUserInfo = userInfo.hashPassword

    assert(pwHashedUserInfo.isPasswordHashed === true)
    assert(pwHashedUserInfo.password === userInfo.password)
  }

  it should "ハッシュ化済でなければ保存時に例外が発生する" in {

    val userInfo = UserInfo(UserId("USE_ID"), "password", "USER_NAME", false)

    val result = userInfo.save

    assert(result.isInstanceOf[Left[DomainException, Unit]])

    result match {
      case Right(_) => fail()
      case Left(e) => {
        assert(e.getMessage === "DE002:" + messageConf.getString("DE002"))
      }
    }
  }
}
