package com.example.cronoapp.di

import android.content.Context
import androidx.room.Room
import com.example.cronoapp.room.CronosDatabase
import com.example.cronoapp.room.CronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providesCronosDao(cronoDatabase: CronosDatabase) : CronosDatabaseDao{
        return cronoDatabase.cronosDao()
    }

    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context: Context): CronosDatabase{
        return Room.databaseBuilder(
            context,
            CronosDatabase::class.java, name = "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}