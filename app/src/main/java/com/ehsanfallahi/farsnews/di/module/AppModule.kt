package com.ehsanfallahi.farsnews.di.module

import android.content.Context
import com.ehsanfallahi.farsnews.model.database.NewsDao
import com.ehsanfallahi.farsnews.model.database.NewsDatabase
import com.ehsanfallahi.farsnews.model.datasource.NewsDataSource
import com.ehsanfallahi.farsnews.model.repository.NewsRepository
import com.ehsanfallahi.farsnews.model.service.NewsService
import com.ehsanfallahi.farsnews.util.Constant.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson)=Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
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