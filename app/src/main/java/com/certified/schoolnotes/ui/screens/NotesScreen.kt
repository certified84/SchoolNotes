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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.certified.schoolnotes.R
import com.certified.schoolnotes.model.Note
import com.certified.schoolnotes.ui.theme.SpaceGrotesk
import com.certified.schoolnotes.util.Extensions

@Composable
fun NotesScreen() {

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.color_primary_accent))
    ) {

        val (title, deleteIcon, deleteMenu, todoList, addButton) = createRefs()

        Text(
            text = "Your Notes",
            fontSize = 20.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(anchor = parent.top, margin = 20.dp)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_delete_icon_24dp),
            contentDescription = "Delete icon",
            modifier = Modifier
                .clickable { expanded = true }
                .constrainAs(deleteIcon) {
                    top.linkTo(anchor = title.top)
                    bottom.linkTo(anchor = title.bottom)
                    end.linkTo(anchor = parent.end, margin = 20.dp)
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.constrainAs(deleteMenu) {
                top.linkTo(anchor = deleteIcon.bottom)
                end.linkTo(anchor = deleteIcon.end)
            }
        ) {
            DropdownMenuItem(onClick = {
                Extensions.showToast(context, "Deleted all notes")
                expanded = false
            }) {
                Text("Delete all notes")
            }
        }

        ListNotes(notes = listOf(
            Note(
                id = 0,
                courseCode = "MTS 415",
                title = "Engineering Maths III - Lecture 1",
            ),
            Note(
                id = 0,
                courseCode = "MTS 415",
                title = "Engineering Maths III - Lecture 1",
            ),
            Note(
                id = 0,
                courseCode = "MTS 415",
                title = "Engineering Maths III - Lecture 1",
            ),
            Note(
                id = 0,
                courseCode = "MTS 415",
                title = "Engineering Maths III - Lecture 1",
            ),
            Note(
                id = 0,
                courseCode = "MTS 415",
                title = "Engineering Maths III - Lecture 1",
            )
        ), modifier = Modifier
            .constrainAs(todoList) {
                top.linkTo(anchor = deleteIcon.bottom, margin = 16.dp)
                end.linkTo(anchor = parent.end, margin = 20.dp)
                start.linkTo(anchor = parent.start, margin = 20.dp)
            })

        FloatingActionButton(
            onClick = { Extensions.showToast(context, "You'll be able to add notes soon") },
            backgroundColor = colorResource(id = R.color.color_primary_dark),
            modifier = Modifier.constrainAs(addButton) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(anchor = parent.end, margin = 20.dp)
            }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note button",
                tint = colorResource(id = R.color.black)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNotes(notes: List<Note>, modifier: Modifier) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(notes) { note ->
            NoteItem(note = note)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteItem(note: Note) {
    Card(
        onClick = { },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (background, noteIcon, noteTitle) = createRefs()

            Box(
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
                    .alpha(.3f)
                    .background(color = colorResource(id = R.color.color_primary))
                    .constrainAs(background) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_note_black_24dp),
                colorFilter = ColorFilter.tint(color = colorResource(id = R.color.black)),
                contentDescription = "note icon",
                modifier = Modifier.constrainAs(noteIcon) {
                    top.linkTo(anchor = parent.top, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = note.title,
                fontSize = 14.sp,
                color = colorResource(id = R.color.black),
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(noteTitle) {
                    top.linkTo(noteIcon.bottom)
                    bottom.linkTo(anchor = parent.bottom, margin = 20.dp)
                    start.linkTo(anchor = parent.start, margin = 8.dp)
                    end.linkTo(anchor = parent.end, margin = 8.dp)
                }
            )
        }
    }
}