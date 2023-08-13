package com.southafrica.roadguide.di

import com.google.gson.Gson
import com.southafrica.roadguide.data.json.AssetsRepository
import com.southafrica.roadguide.data.json.AssetsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideAssetsRepository(
        assetsRepositoryImpl: AssetsRepositoryImpl,
    ): AssetsRepository

    companion object {
        @Provides
        fun provideGson() = Gson()

        @Provides
        @Named("driverFaqs")
        fun provideDriverFaqsFilename() = "driver-faqs.json"

        @Provides
        @Named("learnerFaqs")
        fun provideLearnerFaqsFilename() = "learner-faqs.json"
    }
}