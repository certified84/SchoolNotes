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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.certified.schoolnotes.R
import com.certified.schoolnotes.ui.BottomNavGraph
import com.certified.schoolnotes.ui.screens.todo.TodoViewModel

@Composable
fun MainScreen(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomBar(navController = navController)
    }) {
        BottomNavGraph(navController = navController, viewModel = viewModel)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Todo,
        BottomBarScreen.Result,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(elevation = 0.dp) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = { navController.navigate(screen.route) },
        label = { Text(text = screen.title, color = colorResource(id = R.color.black)) },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "${screen.title} icon",
                tint = colorResource(id = R.color.black)
            )
        },
        modifier = Modifier.background(color = colorResource(id = R.color.color_primary_accent))
    )
}