package com.composetest.core.database.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import java.time.LocalDateTime

@Entity(
    tableName = "Session",
    indices = [Index(value = ["userId"])],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("userId")
    )]
)
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("sessionId") val id: Long = 0,
    val initialDate: LocalDateTime,
    val endDate: LocalDateTime? = null,
    val userId: String
)
