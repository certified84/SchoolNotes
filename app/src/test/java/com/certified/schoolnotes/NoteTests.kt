package com.certified.schoolnotes

import com.certified.schoolnotes.data.model.APIError
import com.certified.schoolnotes.data.model.ErrorResponse
import com.certified.schoolnotes.data.model.TestNote
import com.certified.schoolnotes.data.network.SchoolNotesApi
import com.certified.schoolnotes.data.repository.TestRepository
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class NoteTests {

    private val mockWebServer = MockWebServer()
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private lateinit var api: SchoolNotesApi
    private lateinit var repository: TestRepository

    @Before
    fun getRetrofit() {
        mockWebServer.url("http://localhost:8080")
        api = retrofit.create(SchoolNotesApi::class.java)
        repository = TestRepository(api)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should get all notes`() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("it.readString(StandardCharsets.UTF_8)")
        )
        runBlocking {
            val actual = repository.getNotes()?.body()
            val expected = listOf(
                TestNote(
                    id = "0",
                    courseCode = "CPE 401",
                    title = "Lecture 1",
                    content = "Not your fucking business bitch",
                    color = "-123234",
                    date = "12345"
                ),
                TestNote(
                    id = "0",
                    courseCode = "CPE 401",
                    title = "Lecture 1",
                    content = "Not your fucking business bitch",
                    color = "-123234",
                    date = "12345"
                ),
                TestNote(
                    id = "0",
                    courseCode = "CPE 401",
                    title = "Lecture 1",
                    content = "Not your fucking business bitch",
                    color = "-123234",
                    date = "12345"
                )
            )
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should insert a note successfully`() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("it.readString(StandardCharsets.UTF_8)")
        )
        runBlocking {
//            repository.insert(
//                TestNote(
//                    id = "1",
//                    courseCode = "CPE 407",
//                    title = "Lecture 1",
//                    content = "Nothing for now bitch"
//                )
//            )
            val response = repository.insert(
                TestNote(
                    id = "1",
                    courseCode = "CPE 407",
                    title = "Lecture 1",
                    content = "Nothing for now bitch"
                )
            )
            val error = parseError(retrofit, response!!)
            val actual = error?.error?.let { ErrorResponse(it) }
            val expected =
                ErrorResponse("Estate ID and password don't match, try again. NOTE!! They are case sensitive.")
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should fail when trying to insert a note`() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody("it.readString(StandardCharsets.UTF_8)")
        )
        runBlocking {
            val response = repository.insert(
                TestNote(
                    id = "2",
                    courseCode = "CPE 401",
                    title = "Lecture 1",
                    content = "Nothing for now bitch"
                )
            )
            val error = parseError(retrofit, response!!)
            val actual = error?.error?.let { ErrorResponse(it) }
            val expected = ErrorResponse("Internal Server Error")
            assertEquals(expected, actual)
        }
    }

    @Throws(IOException::class)
    fun parseError(retrofit: Retrofit, response: Response<*>): APIError? {
        val converter = retrofit.responseBodyConverter<APIError>(
            APIError::class.java, arrayOfNulls(0)
        )
        val error: APIError? = if (response.errorBody() != null) {
            converter.convert(response.errorBody()!!)
        } else {
            APIError(400, "Unknown error")
        }
        return error
    }
}