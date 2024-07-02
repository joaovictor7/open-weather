package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.composetest.core.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)

    @Query(
        "SELECT user.* FROM user " +
            "JOIN session ON user.userId = session.userId " +
            "WHERE session.endDate IS NULL " +
            "ORDER BY session.sessionId DESC " +
            "LIMIT 1"
    )
    fun getCurrentUser(): Flow<UserEntity?>
}
