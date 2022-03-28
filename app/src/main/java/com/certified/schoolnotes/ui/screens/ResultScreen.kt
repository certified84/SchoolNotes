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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.certified.schoolnotes.R
import com.certified.schoolnotes.ui.theme.SpaceGrotesk

@Composable
fun ResultScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_primary_accent))
    ) {

        Text(
            text = "GPA Calculator",
            fontSize = 20.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black_day_white_night),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        ConstraintLayout {
            val (courses, units, scores) = createRefs()
            Text(text = "Courses", modifier = Modifier.constrainAs(courses) {
                top.linkTo(anchor = parent.top, margin = 24.dp)
                start.linkTo(anchor = parent.start, margin = 24.dp)
            })
            Text(text = "Units", modifier = Modifier.constrainAs(units) {
                top.linkTo(anchor = parent.top, margin = 24.dp)
                start.linkTo(anchor = courses.end)
                end.linkTo(anchor = scores.start)
            })
            Text(text = "Scores", modifier = Modifier.constrainAs(scores) {
                top.linkTo(anchor = parent.top, margin = 24.dp)
                end.linkTo(anchor = parent.end, margin = 24.dp)
            })
        }
    }
}