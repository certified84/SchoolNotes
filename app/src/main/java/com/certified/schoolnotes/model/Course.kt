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

package com.certified.schoolnotes.model

import com.certified.schoolnotes.util.colors

/**
 * Note class represent the domain model i.e the
 * note object visible to the app user.
 *
 * @param id        id of the course
 * @param code     courseCode of the course
 * @param title      title of the course
 * @param unit      no of units for the course
 * @param score     mark obtained for the course
 * @param grade     grade obtained for the course
 * @param gradePoint     grade point of the course
 * @param creditPoint     credit point of the course
 * @param color     random color associated with the course
 *
 */

//@Entity(tableName = "course_table")
data class Course(
//    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
//    @ColumnInfo(name = "course_code")
    val code: String = "",
//    @ColumnInfo(name = "course_title")
    val title: String = "",
//    @ColumnInfo(name = "course_unit")
    val unit: Int = 0,
//    @ColumnInfo(name = "course_mark")
    val score: Int = 0,
//    @ColumnInfo(name = "course_grade_point")
    val gradePoint: Int = 0,
    val color: Int = colors.random()
) {
    //    @ColumnInfo(name = "course_credit_point")
    val creditPoint: Int = gradePoint * unit
    //    @ColumnInfo(name = "course_grade")
    val grade: String = when (score) {
        in 0..44 -> "F"
        in 45..49 -> "D"
        in 50..59 -> "C"
        in 60..69 -> "B"
        else -> "A"
    }
}