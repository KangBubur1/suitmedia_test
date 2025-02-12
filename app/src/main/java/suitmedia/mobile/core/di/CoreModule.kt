package suitmedia.mobile.core.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import suitmedia.mobile.core.data.remote.RemoteDataSource
import suitmedia.mobile.core.data.remote.UserRepository
import suitmedia.mobile.core.data.remote.network.ApiService
import suitmedia.mobile.core.data.remote.paging.UserPagingSource
import suitmedia.mobile.core.domain.repository.IUserRepository
import suitmedia.mobile.core.utils.AppExecutors
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IUserRepository> { UserRepository(get(),get()) }
}

val pagingModule = module {
    factory { UserPagingSource(get()) }
}