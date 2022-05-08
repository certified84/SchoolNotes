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

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.certified.schoolnotes.R
import com.certified.schoolnotes.data.model.Course
import com.certified.schoolnotes.ui.SchoolNotesViewModel
import com.certified.schoolnotes.ui.theme.SpaceGrotesk
import com.certified.schoolnotes.util.Extensions.showToast

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultScreen(viewModel: SchoolNotesViewModel, courseList: List<Course>) {

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var filterExpanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("All") }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val course by remember { mutableStateOf(Course()) }

//    BottomSheetScaffold(
//        backgroundColor = colorResource(R.color.color_primary_accent),
//        scaffoldState = bottomSheetScaffoldState,
//        sheetContent = { TodoDialogContent(todo = todo, bottomSheetScaffoldState, viewModel) },
//        sheetPeekHeight = 0.dp,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(bottom = 58.dp)
//    ) {
//
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_primary_accent))
    ) {

        Text(
            text = "GPA Calculator",
            fontSize = 20.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        val visible by remember { mutableStateOf(courseList.isEmpty()) }
        Log.d("TAG", "TodoScreen: $visible")

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val (emptyText, emptyTextAuthor, courses, units, scores, coursesConstraint, otherConstraint) = createRefs()

            if (visible) {
                Text(
                    text = "\"If you focus on results, you'll never change. If you focus on change, you'll get results.\"",
                    fontSize = 20.sp,
                    fontFamily = SpaceGrotesk,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .constrainAs(emptyText) {
                            top.linkTo(anchor = parent.top)
                            bottom.linkTo(anchor = parent.bottom, 8.dp)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = parent.end)
                        }
                        .padding(end = 20.dp, start = 20.dp)
                )

                Text(
                    text = "- Jack Dixon",
                    fontSize = 20.sp,
                    fontFamily = SpaceGrotesk,
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .constrainAs(emptyTextAuthor) {
                            top.linkTo(anchor = emptyText.bottom)
//                        bottom.linkTo(anchor = parent.bottom)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = parent.end)
//                        centerAround(anchor = emptyText.bottom)
                        }
                )
            }

            if (!visible) {
                Text(text = "Courses",
                    fontSize = 18.sp,
                    fontFamily = SpaceGrotesk,
                    color = colorResource(id = R.color.color_primary_darker),
                    fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(courses) {
                        top.linkTo(anchor = parent.top, margin = 24.dp)
                        start.linkTo(anchor = parent.start, margin = 24.dp)
                    })

                Text(
                    text = "Units",
                    fontSize = 18.sp,
                    fontFamily = SpaceGrotesk,
                    color = colorResource(id = R.color.color_primary_darker),
                    fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(units) {
                        top.linkTo(anchor = parent.top, margin = 24.dp)
                        start.linkTo(anchor = courses.end)
                        end.linkTo(anchor = scores.start)
                    })

                Text(
                    text = "Scores",
                    fontSize = 18.sp,
                    fontFamily = SpaceGrotesk,
                    color = colorResource(id = R.color.color_primary_darker),
                    fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(scores) {
                        top.linkTo(anchor = parent.top, margin = 24.dp)
                        end.linkTo(anchor = parent.end, margin = 24.dp)
                    })

                Spacer(modifier = Modifier.height(4.dp))

                ListResultCourses(courses = courseList, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(coursesConstraint) {
                        top.linkTo(courses.bottom, 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(300.dp)
                    .defaultMinSize(minHeight = 200.dp))

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(otherConstraint) {
                            top.linkTo(coursesConstraint.bottom, 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {

                    val (gsText, gsValue, tluText, tluValue, gpaText, gpaValue, btnCalc) = createRefs()

                    Text(
                        text = "Grade scale",
                        fontSize = 16.sp,
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(gsText) {
                            top.linkTo(anchor = parent.top, margin = 24.dp)
                            start.linkTo(anchor = parent.start, margin = 24.dp)
                        })

                    Text(
                        text = "5.0",
                        fontSize = 16.sp,
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(gsValue) {
                            top.linkTo(anchor = parent.top, margin = 24.dp)
                            end.linkTo(anchor = parent.end, margin = 24.dp)
                        })

                    Text(
                        text = "Total Load Unit (TLU)",
                        fontSize = 16.sp,
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(tluText) {
                            top.linkTo(anchor = gsText.bottom, margin = 12.dp)
                            start.linkTo(anchor = parent.start, margin = 24.dp)
                        })

                    Text(
                        text = "24",
                        fontSize = 16.sp,
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(tluValue) {
                            top.linkTo(anchor = gsValue.bottom, margin = 12.dp)
                            end.linkTo(anchor = parent.end, margin = 24.dp)
                        })

                    Text(
                        text = "Grade Point Average (GPA)",
                        fontSize = 16.sp,
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(gpaText) {
                            top.linkTo(anchor = tluText.bottom, margin = 12.dp)
                            start.linkTo(anchor = parent.start, margin = 24.dp)
                        })

                    Text(
                        text = "5.0",
                        fontSize = 16.sp,
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Medium, modifier = Modifier.constrainAs(gpaValue) {
                            top.linkTo(anchor = tluValue.bottom, margin = 12.dp)
                            end.linkTo(anchor = parent.end, margin = 24.dp)
                        })

                    Button(onClick = {
                        showToast(
                            context,
                            "You'll be able to calculate GPA shortly"
                        )
                    },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_primary_dark)),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .height(40.dp)
                            .defaultMinSize(minWidth = 300.dp)
                            .constrainAs(btnCalc) {
                                top.linkTo(anchor = gpaText.bottom, margin = 24.dp)
                                start.linkTo(anchor = parent.start, margin = 42.dp)
                                end.linkTo(anchor = parent.end, margin = 42.dp)
                            }) {

                        Text(
                            text = "Calculate GPA",
                            fontSize = 16.sp,
                            fontFamily = SpaceGrotesk,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListResultCourses(courses: List<Course>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = courses, key = {
            it.id
        }) { it ->
            ResultItem(course = it)
        }
    }
}

@Composable
fun ResultItem(course: Course) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .clickable { }
    ) {
        val (courseCode, unit, score, grade) = createRefs()

        Text(
            text = course.code,
            fontSize = 16.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Normal, modifier = Modifier.constrainAs(courseCode) {
                top.linkTo(anchor = parent.top, margin = 8.dp)
                bottom.linkTo(anchor = parent.bottom, margin = 8.dp)
                start.linkTo(anchor = parent.start, margin = 24.dp)
            })

        Text(
            text = course.unit.toString(),
            fontSize = 16.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Normal, modifier = Modifier.constrainAs(unit) {
                top.linkTo(anchor = parent.top, margin = 8.dp)
                bottom.linkTo(anchor = parent.bottom, margin = 8.dp)
                start.linkTo(anchor = courseCode.end)
                end.linkTo(anchor = score.start)
            })

        Text(
            text = course.score.toString(),
            fontSize = 16.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(score) {
                top.linkTo(anchor = parent.top, margin = 8.dp)
                bottom.linkTo(anchor = parent.bottom, margin = 8.dp)
                end.linkTo(anchor = grade.start)
            })

        Text(
            text = course.grade,
            fontSize = 16.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Normal, modifier = Modifier.constrainAs(grade) {
                top.linkTo(anchor = parent.top, margin = 8.dp)
                bottom.linkTo(anchor = parent.bottom, margin = 8.dp)
                end.linkTo(anchor = parent.end, margin = 24.dp)
            })

        Divider()
    }
}