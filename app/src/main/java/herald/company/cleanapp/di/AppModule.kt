package herald.company.cleanapp.di

import herald.company.cleanapp.BuildConfig
import herald.company.cleanapp.common.AsyncCompletableTransformer
import herald.company.cleanapp.common.AsyncSingleTransformer
import herald.company.cleanapp.mappers.AuthMapper
import herald.company.cleanapp.modules.login.AuthViewModel
import herald.company.data.repositories.AuthRepositoryImpl
import herald.company.data.source.local.AuthCache
import herald.company.data.source.local.RxSharedPreferencesUtil
import herald.company.data.source.local.impl.AuthCacheImpl
import herald.company.data.source.remote.AuthRemote
import herald.company.data.source.remote.client.AuthApiClient
import herald.company.data.source.remote.impl.AuthRemoteImpl
import herald.company.domain.interactor.usecases.AuthUseCase
import herald.company.domain.interactor.usecases.CheckAuthUseCase
import herald.company.domain.interactor.usecases.GetUserDetailsUseCase
import herald.company.domain.interactor.usecases.LogoutUserUseCase
import herald.company.domain.interactor.usecases.impl.AuthUseCaseImpl
import herald.company.domain.interactor.usecases.impl.CheckAuthUseCaseImpl
import herald.company.domain.interactor.usecases.impl.GetUserDetailsUseCaseImpl
import herald.company.domain.interactor.usecases.impl.LogoutUserUseCaseImpl
import herald.company.domain.repositories.AuthRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by herald25santos on 2019-07-08
 */


val mRepositoryModules = module {
    single { AuthRemoteImpl(androidApplication(), api = get()) as AuthRemote }
    single { AuthCacheImpl(rxSharedPreferencesUtil = get()) as AuthCache }
    single {
        AuthRepositoryImpl(
            remote = get(),
            cache = get(),
            mapper = herald.company.data.mapper.AuthMapper()
        ) as AuthRepository
    }
}

val mAppModules = module {
    single { RxSharedPreferencesUtil(androidApplication()) }
}

val mUseCaseModules = module {
    factory {
        AuthUseCaseImpl(
            transformer = AsyncSingleTransformer(),
            repository = get()
        ) as AuthUseCase
    }
    factory {
        CheckAuthUseCaseImpl(
            transformer = AsyncSingleTransformer(),
            repository = get()
        ) as CheckAuthUseCase
    }
    factory {
        LogoutUserUseCaseImpl(
            transformer = AsyncCompletableTransformer(),
            repository = get()
        ) as LogoutUserUseCase
    }
    factory {
        GetUserDetailsUseCaseImpl(
            transformer = AsyncSingleTransformer(),
            repository = get()
        ) as GetUserDetailsUseCase
    }
}

val mNetworkModules = module {
    single { createHttpClient(androidApplication()) }
    single { createRetrofitClient<AuthApiClient>(BuildConfig.HOST, get()) }
}

val mViewModels = module {
    viewModel {
        AuthViewModel(
            authUseCase = get(),
            checkAuthUseCase = get(),
            logoutUserUseCase = get(),
            getUserDetailsUseCase = get(),
            mapperFromCallable = AuthMapper()
        )
    }
}