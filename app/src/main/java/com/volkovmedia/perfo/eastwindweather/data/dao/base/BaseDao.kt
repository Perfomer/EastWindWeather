package com.volkovmedia.perfo.eastwindweather.data.dao.base

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update

interface BaseDao<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Entity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Collection<Entity>): LongArray

    @Update
    fun update(entity: Entity)

    @Delete
    fun delete(entity: Entity)

}