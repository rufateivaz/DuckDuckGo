package com.sample.data.database.injection

import androidx.room.Room
import com.sample.data.database.ViewerDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), ViewerDatabase::class.java, "viewer_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<ViewerDatabase>().characterDao }
}