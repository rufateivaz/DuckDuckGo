package com.sample.data.injection

import com.sample.data.character.injection.characterDataModule
import com.sample.data.database.injection.databaseModule
import com.sample.data.network.injection.networkModule

val dataModule = listOf (
    networkModule,
    characterDataModule,
    databaseModule
)