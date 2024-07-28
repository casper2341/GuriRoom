package com.guri.guriroom.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM `user-table`")
    fun fetchAllUsers(): List<User>

    @Query("SELECT * FROM `user-table` where id=:id")
    fun fetchUsers(id:Int): User
}