package me.k1mb.education.users

import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import io.restassured.response.Response
import me.k1mb.education.utils.IntegrationTestBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

/**
 * Test class for the {@link me.k1mb.education.users.UserController}
 */
class UserControllerTest : IntegrationTestBase() {
    @Test
    fun `get all users`() {
        val response: Response =
            given()
                .contentType(JSON)
                .`when`()
                .get("/users")
                .then()
                .extract()
                .response()

        assertThat(response)
            .extracting(Response::statusCode, Response::contentType)
            .containsExactly(200, "application/json")

        val users = response.jsonPath().getList<UserDto>("$")

        assertThat(users)
            .isNotEmpty()
            .hasSize(3)
    }

    @Test
    fun `get user`() {
        val uuid = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")

        val response: Response =
            given()
                .contentType(JSON)
                .`when`()
                .get("/users/{id}", uuid)
                .then()
                .extract()
                .response()

        assertThat(response)
            .extracting(Response::statusCode, Response::contentType)
            .containsExactly(200, "application/json")

        val user = response.`as`(UserDto::class.java)

        assertThat(user)
            .isNotNull()
            .extracting(UserDto::id, UserDto::username, UserDto::email)
            .containsExactly(uuid, "user1", "user1@example.com")
    }

    @Test
    fun `create user`() {
        val userRequest =
            UserDto(
                username = "test",
                email = "test",
            )

        val response: Response =
            given()
                .contentType(JSON)
                .body(userRequest)
                .`when`()
                .post("/users")
                .then()
                .extract()
                .response()

        assertThat(response)
            .extracting(Response::statusCode, Response::contentType)
            .containsExactly(200, "application/json")

        val userResponse = response.`as`(UserDto::class.java)

        assertThat(userResponse)
            .isNotNull()
            .satisfies({ user ->
                assertThat(user.username).isEqualTo(userResponse.username)
                assertThat(user.email).isEqualTo(userResponse.email)
                assertThat(user.createdAt).isNotNull()
                assertThat(user.updatedAt).isNotNull()
            })
    }

    @Test
    fun `delete user`() {
        val uuid = UUID.fromString("c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a13")
        val response: Response =
            given()
                .contentType(JSON)
                .`when`()
                .delete("/users/{id}", uuid)
                .then()
                .extract()
                .response()

        assertThat(response)
            .extracting(Response::statusCode)
            .isEqualTo(200)
    }
}
