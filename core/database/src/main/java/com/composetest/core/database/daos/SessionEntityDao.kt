package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.database.domain.entities.onetoone.UserEntityAndSessionEntity

@Dao
interface SessionEntityDao {
    @Insert
    fun insert(sessionEntity: SessionEntity)

    @Transaction
    @Query(
        "SELECT * FROM user " +
            "JOIN session ON user.id = session.userId " +
            "WHERE endDate is null " +
            "LIMIT 1"
    )
    fun getCurrentSessionAndUser(): UserEntityAndSessionEntity
}
