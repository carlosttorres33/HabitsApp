package com.carlostorres.habitsapp.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.carlostorres.habitsapp.home.data.local.entity.HabitEntity
import com.carlostorres.habitsapp.home.data.local.typeconverter.HomeTypeConverter

@Database(entities = [HabitEntity::class], version = 1)
@TypeConverters(
    HomeTypeConverter::class
)
abstract class HabitDataBase() : RoomDatabase() {

    abstract val dao: HabitDao

}