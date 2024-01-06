package com.codingambitions.jetpackcomposeloginapp.data

import kotlinx.coroutines.delay

class LoginRepository {


    suspend fun login(): User {

        delay(500) //add mock delay for retrofit api call

        return User(
            id = "123",
            name = "CodingAmbitions",
            email = "codingambitions@gmail.com",
            profilePicture = ""
        )

    }
}