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

import androidx.room.*
import com.certified.schoolnotes.data.model.Course
import com.certified.schoolnotes.data.model.Note
import com.certified.schoolnotes.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolNotesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertBookMark(bookMark: BookMark)

    @Update
    suspend fun updateNote(note: Note)

    @Update
    suspend fun updateCourse(course: Course)

    @Update
    suspend fun updateTodo(todo: Todo)

//    @Update
//    suspend fun updateBookMark(bookMark: BookMark)

    @Delete
    suspend fun deleteNote(note: Note)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Delete
    suspend fun deleteTodo(todo: Todo)

//    @Delete
//    suspend fun deleteBookMark(bookMark: BookMark)

    @Query("DELETE FROM note_table ")
    suspend fun deleteAllNotes()

    @Query("DELETE FROM course_table ")
    suspend fun deleteAllCourses()

    @Query("DELETE FROM todo_table ")
    suspend fun deleteAllTodos()

//    @Query("DELETE FROM bookmark_table ")
//    suspend fun deleteAllBookMarks()

    @Query("SELECT * FROM note_table ORDER BY note_title ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM course_table ORDER BY course_code ASC")
    fun getAllCourses(): Flow<List<Course>>

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodos(): Flow<List<Todo>>

//    @Query("SELECT * FROM bookmark_table ORDER BY id ASC")
//    suspend fun getAllBookMarks(): Flow<List<BookMark>>

//    @Query("DELETE FROM bookmark_table WHERE note_id = :noteId")
//    suspend fun deleteBookMarkedNote(noteId: Int)

    @Query("DELETE FROM todo_table WHERE isDone IN (1)")
    suspend fun deleteCompletedTodos()

//    @Query("SELECT * FROM bookmark_table WHERE note_id = :noteId")
//    suspend fun getBookMarkAt(noteId: Int): LiveData<List<BookMark>>

    @Query("SELECT * FROM note_table WHERE course_code IN (:courseCode)")
    fun getNotesAt(courseCode: String): Flow<List<Note>>

    @Query("SELECT course_code FROM course_table WHERE course_title = :courseTitle")
    suspend fun getCourseCode(courseTitle: String): String?

    @Query("SELECT course_title FROM course_table WHERE course_code = :courseCode")
    suspend fun getCourseTitle(courseCode: String): String?

//    @Query("SELECT note_id FROM bookmark_table")
//    fun getNoteIds(): Flow<List<Int>>

    @Query("SELECT course_unit FROM course_table")
    fun getCourseUnits(): Flow<List<Int>>

    @Query("SELECT course_credit_point FROM course_table")
    fun getCourseCreditPoints(): Flow<List<Int>>

    @Query("SELECT * FROM note_table WHERE course_code != :noCourse")
    fun getDeletableNotes(noCourse: String): Flow<List<Note>>

//    @Query("SELECT * FROM bookmark_table WHERE course_code != :noCourse")
//    fun getDeletableBookmarks(noCourse: String): LiveData<List<BookMark>>

    @Query("SELECT * FROM note_table WHERE note_title LIKE :searchQuery OR note_content LIKE :searchQuery OR course_code LIKE :searchQuery ORDER BY note_title ASC")
    fun searchNotes(searchQuery: String): Flow<List<Note?>?>?

    @Query("SELECT * FROM course_table WHERE course_code LIKE :searchQuery OR course_title LIKE :searchQuery ORDER BY course_code ASC")
    fun searchCourses(searchQuery: String?): Flow<List<Course?>?>?

//    @Query("SELECT * FROM bookmark_table WHERE note_title LIKE :searchQuery OR note_content LIKE :searchQuery OR course_code LIKE :searchQuery ORDER BY id ASC")
//    fun searchBookmarks(searchQuery: String?): Flow<List<BookMark?>?>?
}