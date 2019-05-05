package com.louvre2489.fp.common

import com.typesafe.config.{ Config, ConfigFactory }

import com.louvre2489.fp.domain.value.MessageId

object MessageFactory {

  def load: Config = {

    val conf = ConfigFactory.load()
    ConfigFactory.load(conf.getString(MESSAGE_FILE))
  }

  def getMessage(messageId: MessageId): String = {

    val messageConf = this.load
    messageConf.getString(messageId.value)
  }

  def getMessage(messageId: MessageId, params: Seq[String]): String = {

    var message = MessageFactory.getMessage(messageId)

    params.zipWithIndex.foreach {
      case (param: String, i: Int) => {
        message = message.replace("{" + i.toString() + "}", param)
      }
    }

    message
  }
}
