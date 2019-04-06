package com.louvre2489.fp.domain.entity

trait Entity[ID] {

  def save: Either[Exception, Unit]
}
