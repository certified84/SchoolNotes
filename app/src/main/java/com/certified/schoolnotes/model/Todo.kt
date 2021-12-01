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

/**
 * NoteEntry class represent the domain model i.e the
 * object visible to the app user.
 *
 * @param id        id of the todo
 * @param todo     what to do
 * @param isDone      boolean to check if the task has been done
 * @param reminder  date set for a reminder in the todo
 * @param started   whether or not the reminder is active
 *
 */

//@Entity(tableName = "todo_table")
data class Todo(
//    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val todo: String = "",
    val isDone: Boolean = false,
    var reminder: Long? = null,
    var started: Boolean = false,
)