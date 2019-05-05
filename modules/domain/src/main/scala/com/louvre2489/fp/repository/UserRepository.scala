package com.louvre2489.fp.repository

import com.louvre2489.fp.repository.base._

trait UserRepository[A, ID] extends ListItemRepository[A, ID] with HasIDRepository[A, ID]
