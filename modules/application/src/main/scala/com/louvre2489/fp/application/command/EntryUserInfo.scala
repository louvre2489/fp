package com.louvre2489.fp.application.command

import com.louvre2489.fp.domain.entity.UserInfo
import com.louvre2489.fp.domain.value.UserId
import com.louvre2489.fp.repository.UserRepository

class EntryUserInfo(userRepository: UserRepository[UserInfo, UserId]) {}
