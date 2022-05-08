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

package com.certified.schoolnotes.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.certified.schoolnotes.data.model.Course
import com.certified.schoolnotes.data.model.Todo
import com.certified.schoolnotes.data.repository.SchoolNotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolNotesViewModel @Inject constructor(private val repository: SchoolNotesRepository) :
    ViewModel() {

    private val _todos by mutableStateOf(MutableLiveData<List<Todo>?>())
    val todos: Flow<List<Todo>> = repository.allTodos

    private val _courses by mutableStateOf(MutableLiveData<List<Course>?>())
    val courses: Flow<List<Course>> = repository.allCourses

    init {
        getTodos()
        getCourses()
    }

    private fun getTodos() {
        viewModelScope.launch {
            _todos.value = repository.allTodos.asLiveData().value
            repository.allTodos.collect { Log.d("TAG", "getTodos: $it") }
        }
    }

    private fun getCourses() {
        viewModelScope.launch {
            _courses.value = repository.allCourses.asLiveData().value
            repository.allTodos.collect { Log.d("TAG", "getTodos: $it") }
        }
    }

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun deleteCompletedTodos() {
        viewModelScope.launch {
            repository.deleteCompletedTodos()
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch {
            repository.deleteAllTodos()
        }
    }
}