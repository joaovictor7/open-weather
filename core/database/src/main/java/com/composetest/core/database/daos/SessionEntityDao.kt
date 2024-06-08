package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.composetest.core.database.domain.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionEntityDao {
    @Insert
    suspend fun insert(sessionEntity: SessionEntity)

    @Query(
        "SELECT * FROM session " +
            "WHERE endDate IS NULL " +
            "ORDER BY sessionId DESC " +
            "LIMIT 1"
    )
    fun getCurrentSession(): Flow<SessionEntity>
}
