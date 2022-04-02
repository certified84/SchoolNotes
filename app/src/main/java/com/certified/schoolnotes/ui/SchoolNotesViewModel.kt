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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.certified.schoolnotes.data.model.Todo
import com.certified.schoolnotes.data.repository.SchoolNotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SchoolNotesViewModel @Inject constructor(private val repository: SchoolNotesRepository) :
    ViewModel() {

    private val _todos by mutableStateOf(MutableLiveData<List<Todo>?>())
    val todos: LiveData<List<Todo>?> get() = _todos

    init {
        getTodos()
    }

    private fun getTodos() {
        _todos.value = repository.allTodos.asLiveData().value
    }
}