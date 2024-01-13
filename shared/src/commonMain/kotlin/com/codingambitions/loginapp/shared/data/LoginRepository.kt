package com.codingambitions.loginapp.shared.data

import kotlinx.coroutines.delay

class LoginRepository {

    //Retrfit-> Ktor for KMP
    //Shared Preferences- > Multiplatform Settings
    //Roomdb -> SqlDelight
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