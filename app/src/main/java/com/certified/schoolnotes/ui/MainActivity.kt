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

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.certified.schoolnotes.data.model.Todo
import com.certified.schoolnotes.ui.screens.MainScreen
import com.certified.schoolnotes.ui.theme.SchoolNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SchoolNotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            SchoolNotesTheme(
                darkTheme = false
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val todos: List<Todo> by viewModel.todos.collectAsState(initial = listOf())
                    Log.d("TAG", "TodoScreen: $todos")
                    MainScreen(viewModel, todos)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, number: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        for (i in 0..number)
            Surface {
                Text(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
                    text = "Hello $name!",
                    modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._16sdp))
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchoolNotesTheme {
        Greeting("Android", 5)
    }
}