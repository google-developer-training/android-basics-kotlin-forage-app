/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stargrazer.forage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stargrazer.forage.model.Forageable

/**
 * Room database to persist data for the Forage app.
 * This database stores a [Forageable] entity
 */
// TODOx: create the database with all necessary annotations, methods, variables, etc.
@Database(entities = [Forageable::class], version = 2, exportSchema = false)
abstract class ForageDatabase : RoomDatabase() {
    abstract fun forageableDao(): ForageableDao
    companion object {
        @Volatile
        private var INSTANCE: ForageDatabase? = null

        fun getDatabase(context: Context): ForageDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ForageDatabase::class.java,
                    "forageable_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
