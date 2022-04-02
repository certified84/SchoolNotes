package com.certified.schoolnotes.ui

import android.os.Bundle
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
import com.certified.schoolnotes.ui.screens.MainScreen
import com.certified.schoolnotes.ui.screens.todo.TodoViewModel
import com.certified.schoolnotes.ui.theme.SchoolNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: TodoViewModel by viewModels()
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
                    MainScreen(viewModel)
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