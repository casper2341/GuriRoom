package com.guri.guriroom

import android.app.Application
import com.guri.guriroom.room.UserDatabase

class GuriRoom : Application() {

    val db by lazy{
        UserDatabase.getInstance(this)
    }
}