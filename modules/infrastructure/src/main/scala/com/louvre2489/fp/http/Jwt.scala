package com.louvre2489.fp.http

import java.time.Instant

import spray.json._
import pdi.jwt.{ JwtAlgorithm, JwtSprayJson }

object Jwt {

  /**
    * 本来であれば公開鍵だけど、適当な文字列で代用
    */
  val key = "abcdefg1234xyz"

  /**
    * アルゴリズム
    */
  val algorithm = JwtAlgorithm.HS256

  def createToken(hashedUserId: Option[String]): String = {

    val claimJson = s"""{"expires":${Instant.now.getEpochSecond}}""".parseJson.asJsObject
    JwtSprayJson.encode(claimJson, key, algorithm)
  }
}
