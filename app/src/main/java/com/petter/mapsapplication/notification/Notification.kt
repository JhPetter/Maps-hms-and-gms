package com.petter.mapsapplication.notification

data class Notification(
    val title: String,
    val body: String,
    val icon: String = "",
    val tag: String = "",
    val color: String = "",
    val image: String = ""
)