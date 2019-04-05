package com.louvre2489.fp.repository.repositoryInterface.base

trait ListItemRepositoryInterface[A, ID] extends BaseRepositoryInterface[A, ID] {

  /**
    *  Get all items
    * @return
    */
  def getAll: List[A]

}
