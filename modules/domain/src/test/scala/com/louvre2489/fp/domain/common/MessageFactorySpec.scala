package com.louvre2489.fp.domain.common

import com.louvre2489.fp.common.MessageFactory
import com.louvre2489.fp.domain.value.MessageId
import org.scalatest.FlatSpec

class MessageFactorySpec extends FlatSpec {

  behavior of "MessageFactory"

  "パラメーターの無いメッセージ" should "message.confを読み込む" in {

    val messageConf = MessageFactory.load
    assert(messageConf.getString("SAMPLE_MESSAGE_NO_PARAM") === "No parameter massage")
  }

  it should "メッセージを返す" in {

    assert(MessageFactory.getMessage(MessageId("SAMPLE_MESSAGE_NO_PARAM")) === "No parameter massage")
  }

  "パラメーターの有るメッセージ" should "メッセージを返す" in {

    assert(
      MessageFactory
        .getMessage(MessageId("SAMPLE_MESSAGE_WITH_PARAM"), Seq("P1", "P2", "P3")) === "Message with parameter:P1-P2-P3"
    )
  }
}
