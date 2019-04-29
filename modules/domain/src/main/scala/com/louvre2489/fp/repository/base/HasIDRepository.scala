package com.louvre2489.fp.repository.base

trait HasIDRepository[A, ID] {

  /**
    * Find the item that designated by ID
    * @param id データのキーとなるID
    * @return
    */
  def findById(id: ID): Option[A]
}
