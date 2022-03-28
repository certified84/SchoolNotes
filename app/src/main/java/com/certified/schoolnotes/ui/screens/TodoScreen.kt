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

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.certified.schoolnotes.R
import com.certified.schoolnotes.model.Todo
import com.certified.schoolnotes.ui.theme.SpaceGrotesk

//@Preview(showBackground = true)
@Composable
fun TodoScreen() {

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 8.dp)
            ) {
                Row {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_todo_circle),
//                        contentDescription = "Todo circle"
//                    )
                    var text by remember { mutableStateOf("All") }
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier.padding(start = 8.dp)
                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_delete_icon_24dp),
//                        contentDescription = "Delete icon"
//                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoItem(todo: Todo, onClick: () -> Unit) {
    Surface(onClick = onClick) {
        Column {

        }
    }
}