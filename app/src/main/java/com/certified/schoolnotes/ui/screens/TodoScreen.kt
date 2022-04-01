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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.certified.schoolnotes.R
import com.certified.schoolnotes.data.model.Todo
import com.certified.schoolnotes.ui.theme.SpaceGrotesk
import com.certified.schoolnotes.util.Extensions.showToast
import com.certified.schoolnotes.util.formatReminderDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoScreen() {

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var filterExpanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("All") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_primary_accent))
    ) {

        val (title, deleteIcon, deleteMenu, filterIcon, filterTextField, filterSurface, todoList, addButton) = createRefs()

        Text(
            text = "To-Dos",
            fontSize = 20.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(anchor = parent.top, margin = 20.dp)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_delete_icon_24dp),
            contentDescription = "Delete icon",
            modifier = Modifier
                .clickable { expanded = true }
                .constrainAs(deleteIcon) {
                    top.linkTo(anchor = title.top)
                    bottom.linkTo(anchor = title.bottom)
                    end.linkTo(anchor = parent.end, margin = 20.dp)
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.constrainAs(deleteMenu) {
                top.linkTo(anchor = deleteIcon.bottom)
                end.linkTo(anchor = deleteIcon.end)
            }
        ) {
            DropdownMenuItem(onClick = {
                showToast(context, "Deleted all to-dos")
                expanded = false
            }) {
                Text("Delete all to-dos")
            }
            DropdownMenuItem(onClick = {
                showToast(context, "Deleted all completed to-dos")
                expanded = false
            }) {
                Text("Delete all completed to-dos")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = colorResource(id = R.color.color_primary_accent_2))
                .constrainAs(filterSurface) {
                    top.linkTo(anchor = deleteIcon.bottom, margin = 20.dp)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                }
        )

        TextButton(
            onClick = { filterExpanded = !filterExpanded },
            modifier = Modifier.constrainAs(filterTextField) {
                top.linkTo(anchor = filterSurface.top)
                bottom.linkTo(anchor = filterSurface.bottom)
                end.linkTo(anchor = filterSurface.end, margin = 20.dp)
            }) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(end = 4.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Filter Button",
                tint = colorResource(id = R.color.black)
            )
        }

        DropdownMenu(
            expanded = filterExpanded,
            onDismissRequest = { filterExpanded = false },
            modifier = Modifier.constrainAs(deleteMenu) {
                top.linkTo(anchor = deleteIcon.bottom)
                end.linkTo(anchor = deleteIcon.end)
            }
        ) {
            DropdownMenuItem(onClick = {
                showToast(context, "Showing all to-dos")
                filterExpanded = false
                text = "All"
            }) {
                Text("All")
            }
            DropdownMenuItem(onClick = {
                showToast(context, "Showing incomplete to-dos")
                filterExpanded = false
                text = "Incomplete"
            }) {
                Text("Incomplete")
            }
            DropdownMenuItem(onClick = {
                showToast(context, "Showing completed to-dos")
                filterExpanded = false
                text = "Completed"
            }) {
                Text("Completed")
            }
        }
//            OutlinedTextField(
//                value = text,
//                onValueChange = { text = it },
//                modifier = Modifier.constrainAs(filterTextField) {
//                    top.linkTo(anchor = filterSurface.top)
//                    bottom.linkTo(anchor = filterSurface.bottom)
//                    end.linkTo(anchor = filterSurface.end, margin = 20.dp)
//                }
//            )

        Image(
            painter = painterResource(id = R.drawable.ic_sort_black_24dp),
            contentDescription = "Filter Icon",
            modifier = Modifier
                .constrainAs(filterIcon) {
                    top.linkTo(anchor = filterSurface.top)
                    bottom.linkTo(anchor = filterSurface.bottom)
                    end.linkTo(anchor = filterTextField.start, margin = 16.dp)
                }
        )

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
            ), modifier = Modifier
                .fillMaxWidth()
                .constrainAs(todoList) {
                    top.linkTo(anchor = filterSurface.bottom)
                    end.linkTo(anchor = parent.end)
                    start.linkTo(anchor = parent.start)
                }
        )

        FloatingActionButton(
            onClick = { showToast(context, "You'll be able to add to-dos soon") },
            backgroundColor = colorResource(id = R.color.color_primary_dark),
            modifier = Modifier.constrainAs(addButton) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(anchor = parent.end, margin = 20.dp)
            }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add to-do button",
                tint = colorResource(id = R.color.black)
            )
        }
    }
}

@Composable
fun ListTodos(todos: List<Todo>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = todos, key = { todo ->
            todo.id
        }) { todo ->
            TodoItem(todo = todo)
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {

    var isChecked by remember { mutableStateOf(todo.isDone) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .clickable { isChecked = !isChecked }
    ) {

        val (todoIcon, todoTitle, notificationIcon, todoReminder, checkBox) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_todo_eclipse_10dp),
            contentDescription = "Todo icon",
            modifier = Modifier
                .constrainAs(todoIcon) {
                    top.linkTo(anchor = parent.top, margin = 8.dp)
                    start.linkTo(anchor = notificationIcon.start)
                    end.linkTo(anchor = notificationIcon.end)
                }
        )

        Text(
            text = todo.todo,
            fontSize = 14.sp,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            modifier = Modifier
                .constrainAs(todoTitle) {
                    start.linkTo(anchor = notificationIcon.end, margin = 20.dp)
                    top.linkTo(anchor = parent.top, margin = 4.dp)
//                    end.linkTo(anchor = checkBox.start, margin = 4.dp)
                }
        )

        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier
                .constrainAs(checkBox) {
                    top.linkTo(anchor = parent.top, margin = 4.dp)
                    bottom.linkTo(anchor = parent.bottom, margin = 4.dp)
                    end.linkTo(anchor = parent.end, margin = 24.dp)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_notification_icon_16dp),
            contentDescription = "Notification icon",
            modifier = Modifier
                .alpha(.5f)
                .constrainAs(notificationIcon) {
                    top.linkTo(anchor = todoIcon.bottom, margin = 8.dp)
                    start.linkTo(anchor = parent.start, margin = 20.dp)
                    bottom.linkTo(anchor = parent.bottom, margin = 8.dp)
                }
        )

        Text(
            text = if (todo.reminder == null) "No reminder set" else formatReminderDate(todo.reminder!!),
            fontSize = 14.sp,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Light,
            maxLines = 1,
            modifier = Modifier
                .alpha(.7f)
                .constrainAs(todoReminder) {
                    top.linkTo(anchor = notificationIcon.top)
                    bottom.linkTo(anchor = notificationIcon.bottom)
                    start.linkTo(anchor = notificationIcon.end, margin = 20.dp)
                }
        )
    }
}
