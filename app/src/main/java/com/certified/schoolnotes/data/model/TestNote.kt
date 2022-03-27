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

package com.certified.schoolnotes.data.model

import com.certified.schoolnotes.util.colors
import java.util.*

/**
 * Note class represent the domain model i.e the
 * note object visible to the app user.
 *
 * @param id        id of the note
 * @param title     title of the note
 * @param courseCode     courseCode of the course which the note is associated with.
 *                      If the note isn't associated with any course, it will be "NIL"
 * @param content      content of the note
 * @param date      date the note was created/modified
 * @param color     color of the note
 *
 */

data class TestNote(
//    var id: String?,
//    val courseCode: String,
//    val title: String,
//    val content: String,
//    var color: String,
//    var date: String

    var id: String = "0",
    val courseCode: String = "NIL",
    val title: String = "",
    val content: String = "",
    var color: String = colors.random().toString(),
    var date: String = Calendar.getInstance().timeInMillis.toString()
)