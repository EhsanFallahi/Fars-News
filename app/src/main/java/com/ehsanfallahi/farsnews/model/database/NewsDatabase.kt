package com.ehsanfallahi.farsnews.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ehsanfallahi.farsnews.model.models.Root
import com.ehsanfallahi.farsnews.util.Converter

@Database(entities = [Root::class],version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract class NewsDatabase:RoomDatabase() {

    abstract val newsDao:NewsDao
    companion object{
        @Volatile
        private var INSTANCE:NewsDatabase?=null

        fun getInstance(context: Context):NewsDatabase{
            synchronized(this){
                var instance= INSTANCE

                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        "news_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}