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

package com.certified.schoolnotes.model

import com.certified.schoolnotes.util.colors

/**
 * Note class represent the domain model i.e the
 * note object visible to the app user.
 *
 * @param id        id of the note
 * @param noteTitle     title of the note
 * @param courseCode     courseCode of the note
 * @param noteContent      content of the note
 * @param noteDate      date the note was created/modified
 * @param noteColor     color of the note
 *
 */

//@Entity(tableName = "bookmark_table")
data class BookMark(
//    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
//    @ColumnInfo(name = "course_code")
    val courseCode: String = "NIL",
//    @ColumnInfo(name = "note_title")
    val noteTitle: String = "",
//    @ColumnInfo(name = "note_content")
    val noteContent: String = "",
//    @ColumnInfo(name = "note_color")
    var noteColor: Int = colors.random(),
//    @ColumnInfo(name = "date")
    var noteDate: Long? = null
)