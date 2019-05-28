package com.louvre2489.fp.application.entity

import com.louvre2489.fp.domain.value.UserId

case class User(userId: UserId, userName: String) {}

case class UserCertification(userId: UserId, password: Option[String], isLoginSuccess: Boolean) {}
