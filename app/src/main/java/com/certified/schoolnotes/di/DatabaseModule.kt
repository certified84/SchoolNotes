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

package com.certified.schoolnotes.di

import android.content.Context
import androidx.room.Room
import com.certified.schoolnotes.data.local.SchoolNotesDAO
import com.certified.schoolnotes.data.local.SchoolNotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAudioNotesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SchoolNotesDatabase::class.java, "school_notes_database.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideNoteDao(database: SchoolNotesDatabase): SchoolNotesDAO {
        return database.schoolNotesDao()
    }
}