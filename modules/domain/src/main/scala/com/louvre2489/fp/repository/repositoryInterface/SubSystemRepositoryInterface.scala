package com.louvre2489.fp.repository.repositoryInterface

import com.louvre2489.fp.repository.repositoryInterface.base.ListItemRepositoryInterface

trait SubSystemRepositoryInterface[A, ID] extends ListItemRepositoryInterface[A, ID] {}
