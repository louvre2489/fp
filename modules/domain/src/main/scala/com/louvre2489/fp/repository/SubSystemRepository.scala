package com.louvre2489.fp.repository

import com.louvre2489.fp.repository.base.ListItemRepository

trait SubSystemRepository[A, ID] extends ListItemRepository[A, ID] {}
