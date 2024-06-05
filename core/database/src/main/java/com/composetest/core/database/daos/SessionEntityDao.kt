package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.database.domain.entities.onetoone.UserEntityAndSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionEntityDao {
    @Insert
    suspend fun insert(sessionEntity: SessionEntity)

    @Transaction
    @Query(
        "SELECT * FROM user " +
            "JOIN session ON user.userId = session.userId " +
            "WHERE session.endDate IS NULL " +
            "ORDER BY session.sessionId DESC " +
            "LIMIT 1"
    )
    fun getCurrentSessionAndUser(): Flow<UserEntityAndSessionEntity>
}
