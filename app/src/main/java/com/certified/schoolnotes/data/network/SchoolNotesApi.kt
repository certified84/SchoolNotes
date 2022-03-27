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

package com.certified.schoolnotes.data.network

import com.certified.schoolnotes.data.model.TestNote
import retrofit2.Response
import retrofit2.http.*

interface SchoolNotesApi {

    @Headers(
        "Content-Type: application/json"
    )
    @POST("/default")
    @FormUrlEncoded
    suspend fun postMessage(
        @Field("id") id: String,
        @Field("courseCode") courseCode: String,
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("color") color: String,
        @Field("date") date: String
    ): Response<String>?

    @GET("/default")
    suspend fun getNotes(): Response<List<TestNote>>?
}