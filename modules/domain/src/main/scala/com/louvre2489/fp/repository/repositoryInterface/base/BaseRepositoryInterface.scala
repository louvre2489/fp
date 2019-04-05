package com.louvre2489.fp.repository.repositoryInterface.base

trait BaseRepositoryInterface[A, ID] {

  /**
    * Find the item that designated by ID
    * @param id
    * @return
    */
  def findById(id: ID): Option[A]

  /**
    * Save the item
    * @param entity
    * @return
    */
  def save(entity: A): Either[Exception, Unit]
}
