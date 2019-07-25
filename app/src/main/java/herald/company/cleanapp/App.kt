package herald.company.cleanapp

import android.app.Application
import herald.company.cleanapp.common.platform.ErrorReportingTree
import herald.company.cleanapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ErrorReportingTree())
        }
    }

    private fun loadKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                mutableListOf(
                    mNetworkModules,
                    mViewModels,
                    mRepositoryModules,
                    mUseCaseModules,
                    mAppModules
                )
            )
        }
    }
}