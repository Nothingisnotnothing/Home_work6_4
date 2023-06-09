package kg.vohkysan.home_work6_4

import android.app.Application
import kg.vohkysan.home_work6_4.repository.Repository

class App : Application() {

    companion object {
        val repository = Repository()
    }
}