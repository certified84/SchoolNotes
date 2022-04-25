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

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.certified.schoolnotes.data.model.Todo
import com.certified.schoolnotes.data.model.User
import com.certified.schoolnotes.ui.screens.*
import com.certified.schoolnotes.ui.screens.TodoScreen

@Composable
fun BottomNavGraph(navController: NavHostController, viewModel: SchoolNotesViewModel, todos: List<Todo>) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Todo.route) {
            TodoScreen(viewModel, todos)
        }
        composable(route = BottomBarScreen.Result.route) {
            ResultScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(User(id = "", name = "Samson Achiaga", profileImage = null))
        }
    }
}