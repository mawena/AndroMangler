package com.example.andromangler.di

import com.example.andromangler.data.remote.chapter.ChapterApi
import com.example.andromangler.data.remote.manga.MangaApi
import com.example.andromangler.data.remote.website.WebsiteApi
import com.example.andromangler.utils.Constants.Companion.BASE_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideWebsiteApi(retrofit: Retrofit): WebsiteApi {
        return retrofit.create(WebsiteApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMangaApi(retrofit: Retrofit): MangaApi {
        return retrofit.create(MangaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideChapterApi(retrofit: Retrofit): ChapterApi {
        return retrofit.create(ChapterApi::class.java)
    }
}