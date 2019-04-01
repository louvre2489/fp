package com.louvre2489.fp.domain.entity

trait Entity[ID] {

  def findById(id: ID): Option[Entity[_]]

  def save: Either[Exception, Unit]
}
