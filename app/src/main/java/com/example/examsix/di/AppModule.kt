package com.example.examsix.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Provides
    fun provideStaticDataProvider(): StaticDataProvider {
        return StaticDataProvider()
    }
}