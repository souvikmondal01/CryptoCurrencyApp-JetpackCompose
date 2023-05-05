package com.kivous.cryptocurrencyapp_jetpackcompose.di

import com.kivous.cryptocurrencyapp_jetpackcompose.common.Constants.BASE_URL
import com.kivous.cryptocurrencyapp_jetpackcompose.data.remote.CoinPaprikaApi
import com.kivous.cryptocurrencyapp_jetpackcompose.data.repository.CoinRepositoryImp
import com.kivous.cryptocurrencyapp_jetpackcompose.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImp(api)
    }

}