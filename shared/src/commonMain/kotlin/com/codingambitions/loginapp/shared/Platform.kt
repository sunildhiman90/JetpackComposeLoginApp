package com.codingambitions.loginapp.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform