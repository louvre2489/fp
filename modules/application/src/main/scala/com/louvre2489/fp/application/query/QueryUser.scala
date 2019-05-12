package com.louvre2489.fp.application.query

import com.louvre2489.fp.domain.entity.UserInfo
import com.louvre2489.fp.domain.value.UserId
import com.louvre2489.fp.repository.UserRepository

class QueryUser(userRepository: UserRepository[UserInfo, UserId]) {

  /**
    * 登録されているユーザーの一覧を取得する
    * @return システム一覧
    */
  def findAll: List[UserInfo] = userRepository.findAll

}
