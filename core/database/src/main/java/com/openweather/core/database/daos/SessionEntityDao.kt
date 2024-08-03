package com.openweather.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.openweather.core.database.entities.SessionEntity
import com.openweather.core.database.entities.partialupdate.FinishedSessionEntityUpdate

@Dao
interface SessionEntityDao {
    @Insert
    suspend fun insert(sessionEntity: SessionEntity)

    @Update(entity = SessionEntity::class)
    suspend fun update(finishedSessionEntityUpdate: FinishedSessionEntityUpdate)

    @Query(
        "SELECT * FROM session " +
            "WHERE isFinished = 0 " +
            "ORDER BY sessionId DESC " +
            "LIMIT 1"
    )
    suspend fun getCurrentSession(): SessionEntity?
}
