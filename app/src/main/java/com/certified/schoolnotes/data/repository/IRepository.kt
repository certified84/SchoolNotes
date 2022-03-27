package com.certified.schoolnotes.data.repository

import com.certified.schoolnotes.data.model.TestNote
import retrofit2.Response

interface IRepository {
    suspend fun insert(note: TestNote): Response<String>?
    suspend fun getNotes(): Response<List<TestNote>>?
}