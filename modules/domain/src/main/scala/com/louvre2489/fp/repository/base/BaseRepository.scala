package com.louvre2489.fp.repository.base

trait BaseRepository[A, ID] {

  /**
    * Save the item
    * @param entity エンティティ
    * @return
    */
  def save(entity: A): Either[Exception, Unit]
}
