package Azamat.dictionary.app

import Azamat.dictionary.WordDB
import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        WordDB.init(this)
    }
}