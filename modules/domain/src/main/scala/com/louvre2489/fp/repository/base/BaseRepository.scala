package com.louvre2489.fp.repository.base

trait BaseRepository[A, ID] {

  /**
    * Find the item that designated by ID
    * @param id データのキーとなるID
    * @return
    */
  def findById(id: ID): Option[A]

  /**
    * Save the item
    * @param entity エンティティ
    * @return
    */
  def save(entity: A): Either[Exception, Unit]
}
