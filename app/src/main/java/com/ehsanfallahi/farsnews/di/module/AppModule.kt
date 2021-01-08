package com.ehsanfallahi.farsnews.di.module

import android.content.Context
import com.ehsanfallahi.farsnews.model.database.NewsDao
import com.ehsanfallahi.farsnews.model.database.NewsDatabase
import com.ehsanfallahi.farsnews.model.datasource.NewsDataSource
import com.ehsanfallahi.farsnews.model.repository.NewsRepository
import com.ehsanfallahi.farsnews.model.service.NewsService
import com.ehsanfallahi.farsnews.util.ConnectivityInterceptor
import com.ehsanfallahi.farsnews.util.Constant.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

//    @Provides
//    fun providesOkhttpInterceptor() : Interceptor {
//        return  Interceptor { chain: Interceptor.Chain ->
//            chain.proceed(chain.request())
//        }
//    }
//    @Provides
//    fun  provideOkHttpClient(connectivityInterceptor: ConnectivityInterceptor):OkHttpClient{
//        return OkHttpClient.Builder()
//            .addInterceptor(connectivityInterceptor)
//            .build()
//    }

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor(context))
            .build() }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient)=Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
    .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Singleton
    @Provides
    fun provideNewsDataSource(newsService: NewsService) = NewsDataSource(newsService)

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext appContext: Context) = NewsDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) = db.newsDao

    @Singleton
    @Provides
    fun provideRepository(newsDataSource:NewsDataSource,
                          newsDao: NewsDao) =NewsRepository(newsDataSource,newsDao)

}