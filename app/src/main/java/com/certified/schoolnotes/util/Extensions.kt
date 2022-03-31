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

package com.certified.schoolnotes.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.*
import androidx.core.content.res.ResourcesCompat
import com.certified.schoolnotes.R
import com.certified.schoolnotes.util.Config.CUSTOM_PACKAGE_NAME

object Extensions {

    fun showToast(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    fun Context.openBrowser(url: String) {
        try {
            val packageManager = this.packageManager
            packageManager.getPackageInfo(CUSTOM_PACKAGE_NAME, 0)
            showChromeCustomTabView(url, this)
        } catch (e: PackageManager.NameNotFoundException) {
            this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    private fun showChromeCustomTabView(url: String, context: Context) {
        var customTabsClient: CustomTabsClient?
        var customTabsSession: CustomTabsSession? = null
        val customTabsServiceConnection: CustomTabsServiceConnection =
            object : CustomTabsServiceConnection() {
                override fun onServiceDisconnected(name: ComponentName?) {
                    customTabsClient = null
                }

                override fun onCustomTabsServiceConnected(
                    name: ComponentName,
                    client: CustomTabsClient
                ) {
                    customTabsClient = client
                    customTabsClient!!.warmup(0L)
                    customTabsSession = customTabsClient!!.newSession(null)
                }
            }
        CustomTabsClient.bindCustomTabsService(
            context,
            CUSTOM_PACKAGE_NAME,
            customTabsServiceConnection
        )
        val customTabsIntent = CustomTabsIntent.Builder(customTabsSession)
            .setShowTitle(true)
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder().setToolbarColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.color_primary_dark,
                        null
                    )
                ).build()
            ).build()

        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}