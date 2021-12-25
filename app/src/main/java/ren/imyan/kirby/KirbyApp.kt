package ren.imyan.kirby

import android.app.Application
import com.biubiu.eventbus.EventBusInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ren.imyan.kirby.common.utils.DataStoreUtil
import ren.imyan.kirby.di.kirbyModule
import ren.imyan.kirby.di.networkModule
import ren.imyan.kirby.di.repositoryModule
import timber.log.Timber

class KirbyApp : Application() {

    private val moduleList = listOf(
        networkModule,
        repositoryModule,
        kirbyModule
    )

    override fun onCreate() {
        super.onCreate()
        EventBusInitializer.init(this)
        DataStoreUtil.init(this)
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@KirbyApp)
            modules(moduleList)
        }
    }
}