package suitmedia.mobile

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import suitmedia.mobile.core.di.networkModule
import suitmedia.mobile.core.di.pagingModule
import suitmedia.mobile.core.di.repositoryModule
import suitmedia.mobile.di.useCaseModule
import suitmedia.mobile.di.viewModelModule

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    pagingModule
                )
            )
        }
    }
}