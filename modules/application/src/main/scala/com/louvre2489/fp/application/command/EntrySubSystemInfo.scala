package com.louvre2489.fp.application.command

import com.louvre2489.fp.domain.entity.SubSystemInfo
import com.louvre2489.fp.domain.value.SubSystemId
import com.louvre2489.fp.repository.SubSystemRepository

class EntrySubSystemInfo(subSystemRepository: SubSystemRepository[SubSystemInfo, SubSystemId]) {}
