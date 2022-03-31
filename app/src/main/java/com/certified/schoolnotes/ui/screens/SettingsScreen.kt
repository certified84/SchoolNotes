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

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.certified.schoolnotes.R
import com.certified.schoolnotes.model.User
import com.certified.schoolnotes.ui.theme.SpaceGrotesk
import com.certified.schoolnotes.util.Extensions.openBrowser
import com.certified.schoolnotes.util.Extensions.showToast

@Composable
fun SettingsScreen(user: User) {

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Settings",
            fontSize = 20.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        ConstraintLayout(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {

            val (profileImage, btnUpdateProfileImage) = createRefs()

            val image: Any = user.profileImage ?: R.drawable.avatar

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.avatar),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .constrainAs(profileImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            FloatingActionButton(onClick = {
                showToast(
                    context,
                    "You'll be able to change your profile picture soon"
                )
            },
                backgroundColor = colorResource(id = R.color.color_primary_dark),
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .constrainAs(btnUpdateProfileImage) {
                        end.linkTo(profileImage.end)
                        bottom.linkTo(profileImage.bottom)
                    }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_24dp),
                    contentDescription = "Change profile image button",
                    tint = colorResource(id = R.color.black)
                )
            }
        }

        Text(
            text = user.name,
            fontSize = 16.sp,
            fontFamily = SpaceGrotesk,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { showToast(context, "You'll be able to edit your profile shortly") },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_primary)),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .height(40.dp)
                .defaultMinSize(minWidth = 320.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Edit profile",
                fontSize = 16.sp,
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Medium
            )
        }

        var isChecked by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .fillMaxWidth()
                .clickable { isChecked = !isChecked }
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_notification_icon_16dp),
                contentDescription = "Notification icon",
                tint = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Text(
                text = "Push Notifications",
                fontSize = 16.sp,
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )

            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = !isChecked },
                modifier = Modifier.align(Alignment.CenterVertically),
                colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.color_primary_dark))
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .fillMaxWidth()
                .clickable {
                    context.openBrowser("https://github.com/certified84/SchoolNotes/tree/compose-branch")
                }
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_notification_icon_16dp),
                contentDescription = "Privacy icon",
                tint = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Text(
                text = "Privacy",
                fontSize = 16.sp,
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_24dp),
                contentDescription = "right arrow",
                tint = colorResource(id = R.color.black),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .alpha(.5f)
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:Sammie_kt@pm.me?subject=Feedback")
                    context.startActivity(intent)
                }
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_support_24dp),
                contentDescription = "Support icon",
                tint = colorResource(id = R.color.black),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Text(
                text = "Help & Support",
                fontSize = 16.sp,
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_24dp),
                contentDescription = "right arrow",
                tint = colorResource(id = R.color.black),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .alpha(.5f)
            )
        }

        TextButton(
            onClick = { showToast(context, "You'll be able to logout shortly") },
            shape = RoundedCornerShape(25.dp),
            border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.black)),
            modifier = Modifier
                .padding(top = 40.dp)
                .height(40.dp)
                .defaultMinSize(minWidth = 320.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Logout",
                fontSize = 16.sp,
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.red)
            )
        }
    }
}