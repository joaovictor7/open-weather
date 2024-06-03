package com.composetest.core.database.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val name: String?
)
