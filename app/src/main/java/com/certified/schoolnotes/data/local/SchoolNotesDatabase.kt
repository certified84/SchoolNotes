/*
 * Copyright (c) 2021 Samson Achiaga
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

package com.certified.schoolnotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.certified.schoolnotes.data.model.Course
import com.certified.schoolnotes.data.model.Note
import com.certified.schoolnotes.data.model.Todo
import com.certified.schoolnotes.util.Converters

@Database(entities = [Note::class, Course::class, Todo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SchoolNotesDatabase : RoomDatabase() {

    abstract fun schoolNotesDao(): SchoolNotesDAO
}