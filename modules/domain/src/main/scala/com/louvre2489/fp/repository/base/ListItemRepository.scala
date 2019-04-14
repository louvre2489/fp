package com.louvre2489.fp.repository.base

trait ListItemRepository[A, ID] extends BaseRepository[A, ID] {

  /**
    *  Get all items
    * @return
    */
  def findAll: List[A]

}
