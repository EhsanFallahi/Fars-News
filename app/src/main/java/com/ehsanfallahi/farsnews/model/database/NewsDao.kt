package com.ehsanfallahi.farsnews.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ehsanfallahi.farsnews.model.models.Root

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(root: Root)

    @Query("SELECT * FROM news_table")
    fun getAllNews():LiveData<Root>








}