package com.louvre2489.fp.common

import com.louvre2489.fp.domain.value.MessageId

case class DomainException(message: String) extends Exception(message)

object DomainException {

  def apply(messageId: MessageId, params: Seq[String] = Seq()): DomainException = {

    MessageFactory.getMessage(messageId, params)

    var message = MessageFactory.getMessage(messageId)

    params.zipWithIndex.foreach {
      case (param: String, i: Int) => {
        message = message.replace("{" + i.toString() + "}", param)
      }
    }

    DomainException(messageId.value + ":" + message)
  }
}
