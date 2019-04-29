package com.louvre2489.fp.application.command

import com.louvre2489.fp.domain.entity.SystemInfo
import com.louvre2489.fp.domain.value.SystemId
import com.louvre2489.fp.repository.SystemRepository

class EntrySystemInfo(systemRepository: SystemRepository[SystemInfo, SystemId]) {}
