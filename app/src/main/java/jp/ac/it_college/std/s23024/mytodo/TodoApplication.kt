package jp.ac.it_college.std.s23024.mytodo

import android.app.Application
import jp.ac.it_college.std.s23024.mytodo.deta.AppContainer
import jp.ac.it_college.std.s23024.mytodo.deta.AppDataContainer

class TodoApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}