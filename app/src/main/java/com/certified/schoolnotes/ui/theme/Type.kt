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

package com.certified.schoolnotes.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.certified.schoolnotes.R

// Set of Material typography styles to start with
private val light = Font(R.font.space_grotesk_light, FontWeight.W300)
private val regular = Font(R.font.space_grotesk_regular, FontWeight.W400)
private val medium = Font(R.font.space_grotesk_medium, FontWeight.W500)
private val semibold = Font(R.font.space_grotesk_semi_bold, FontWeight.W600)
private val bold = Font(R.font.space_grotesk_bold, FontWeight.W700)

val SpaceGrotesk = FontFamily(light, regular, medium, semibold, bold)

val Typography = Typography(
    defaultFontFamily = SpaceGrotesk,
//    body1 = TextStyle(
//        fontFamily = SpaceGrotesk,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    ),
//    h1 = TextStyle(
//        fontFamily = SpaceGrotesk,
//        fontWeight = FontWeight.Bold,
//        fontSize = 24.sp
//    ),
//    h2 = TextStyle(
//        fontFamily = SpaceGrotesk,
//        fontWeight = FontWeight.Bold,
//        fontSize = 20.sp
//    ),
//    h3 = TextStyle(
//        fontFamily = SpaceGrotesk,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 20.sp
//    ),
//    h4 = TextStyle(
//        fontFamily = SpaceGrotesk,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 18.sp
//    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)