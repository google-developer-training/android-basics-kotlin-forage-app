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
package com.example.forage.data

import androidx.room.*
import com.example.forage.model.Forageable
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for database interaction.
 */
@Dao
interface ForageableDao {

    @Query("SELECT * from forageable")
    fun getForageables() : Flow<List<Forageable>>

    @Query("SELECT * from forageable WHERE id = :id")
    fun getForageable(id: Long) : Flow<Forageable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forageable: Forageable)

    @Update
    fun update(forageable: Forageable)

    @Delete
    fun delete(forageable: Forageable)
}
