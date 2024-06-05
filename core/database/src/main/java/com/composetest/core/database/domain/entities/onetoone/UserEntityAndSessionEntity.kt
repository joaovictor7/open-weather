package com.composetest.core.database.domain.entities.onetoone

import androidx.room.Embedded
import androidx.room.Relation
import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.database.domain.entities.UserEntity

data class UserEntityAndSessionEntity(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val session: SessionEntity
)
