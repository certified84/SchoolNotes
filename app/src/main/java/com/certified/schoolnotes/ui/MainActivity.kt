package com.certified.schoolnotes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.certified.schoolnotes.ui.screens.TodoScreen
import com.certified.schoolnotes.ui.theme.SchoolNotesTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            SchoolNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TodoScreen()
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