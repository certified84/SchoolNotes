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

package com.certified.schoolnotes.data.repository

import com.certified.schoolnotes.data.local.SchoolNotesDAO
import com.certified.schoolnotes.data.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolNotesRepository @Inject constructor(private val schoolNotesDAO: SchoolNotesDAO) {

    val allTodos: Flow<List<Todo>> = schoolNotesDAO.getAllTodos()

    suspend fun insertTodo(todo: Todo) {
        schoolNotesDAO.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        schoolNotesDAO.updateTodo(todo)
    }
}