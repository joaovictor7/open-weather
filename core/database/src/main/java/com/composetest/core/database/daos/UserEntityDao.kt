package com.composetest.core.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.composetest.core.database.entities.UserEntity

@Dao
interface UserEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): List<UserEntity>

    @Delete
    fun delete(userEntity: UserEntity)
}
