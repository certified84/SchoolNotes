package com.certified.schoolnotes.data.repository

import com.certified.schoolnotes.data.model.TestNote
import com.certified.schoolnotes.data.network.SchoolNotesApi
import retrofit2.Response

class TestRepository(private val api: SchoolNotesApi) : IRepository {
    override suspend fun insert(note: TestNote): Response<String>? {
        return try {
            api.postMessage(
                note.id,
                note.courseCode,
                note.title,
                note.content,
                note.color,
                note.date
            )
        } catch (t: Throwable) {
            null
        }
    }

    override suspend fun getNotes(): Response<List<TestNote>>? {
        return try {
            api.getNotes()
        } catch (t: Throwable) {
            null
        }
    }
}