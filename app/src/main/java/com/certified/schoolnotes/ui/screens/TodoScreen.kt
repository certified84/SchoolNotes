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

package com.certified.schoolnotes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.certified.schoolnotes.R
import com.certified.schoolnotes.model.Todo
import com.certified.schoolnotes.ui.theme.SpaceGrotesk

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_primary_accent))
    ) {

        Text(
            text = "To-Dos",
            fontSize = 18.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black_day_white_night),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Surface(
            color = colorResource(id = R.color.color_primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sort_black_24dp),
                    contentDescription = "Filter Icon",
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .align(Alignment.CenterVertically)
                )
                var text by remember { mutableStateOf("All") }
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_delete_icon_24dp),
                    contentDescription = "Delete icon",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 24.dp)
                        .clickable {

                        }
                )
            }
        }

        ListTodos(
            todos = listOf(
                Todo(
                    id = 0,
                    todo = "Complete these mf designs",
                    isDone = false
                ),
                Todo(
                    id = 1,
                    todo = "Complete these mf designs",
                    isDone = false
                ),
                Todo(
                    id = 2,
                    todo = "Complete these mf designs",
                    isDone = false
                ),
                Todo(
                    id = 3,
                    todo = "Complete these mf designs",
                    isDone = false
                ),
                Todo(
                    id = 4,
                    todo = "Complete these mf designs",
                    isDone = false
                )
            )
        )
    }
}

@Composable
fun ListTodos(todos: List<Todo>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = todos, key = {
            it.id
        }) { it ->
            TodoItem(todo = it)
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    Column(modifier = Modifier.background(color = colorResource(id = R.color.white))) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_todo_eclipse_10dp),
                contentDescription = "Todo icon",
                modifier = Modifier
                    .padding(start = 24.dp, top = 4.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = todo.todo,
                fontSize = 14.sp,
                fontFamily = SpaceGrotesk,
                color = colorResource(id = R.color.black_day_white_night),
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 20.dp, top = 4.dp)
                    .align(alignment = Alignment.CenterVertically),
                maxLines = 1
            )
            var isChecked by remember { mutableStateOf(todo.isDone) }
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Row {

        }
    }
}