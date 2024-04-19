package com.carlostorres.habitsapp.home.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlostorres.habitsapp.home.data.local.HabitDao
import com.carlostorres.habitsapp.home.data.local.HabitDataBase
import com.carlostorres.habitsapp.home.data.local.typeconverter.HomeTypeConverter
import com.carlostorres.habitsapp.home.data.remote.HomeAPI
import com.carlostorres.habitsapp.home.data.repository.HomeRepoImpl
import com.carlostorres.habitsapp.home.domain.detail.usecase.DetailUseCases
import com.carlostorres.habitsapp.home.domain.detail.usecase.GetHabitByIdUseCase
import com.carlostorres.habitsapp.home.domain.detail.usecase.InsertHabitUseCase
import com.carlostorres.habitsapp.home.domain.home.usecase.CompleteHabitUseCase
import com.carlostorres.habitsapp.home.domain.home.usecase.GetHabitsForDateUseCase
import com.carlostorres.habitsapp.home.domain.home.usecase.HomeUseCases
import com.carlostorres.habitsapp.home.domain.repository.HomeRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun providesHomeUseCases(
        repository: HomeRepository
    ): HomeUseCases{
        
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getHabitsForDateUseCase = GetHabitsForDateUseCase(repository)
        )
        
    }

    @Provides
    @Singleton
    fun providesDetailUseCases(
        repository: HomeRepository
    ): DetailUseCases{

        return DetailUseCases(
            getHabitByIdUseCase = GetHabitByIdUseCase(repository),
            insertHabitUseCase = InsertHabitUseCase(repository)
        )

    }

    @Provides
    @Singleton
    fun provideHabitDao(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): HabitDao{

        return Room.databaseBuilder(
            context,
            HabitDataBase::class.java,
            "habits_db"
        ).addTypeConverter(HomeTypeConverter(moshi)).build().dao

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        
    ): OkHttpClient{
        
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
        
    }

    @Provides
    @Singleton
    fun provideHomeApi(
        okHttpClient: OkHttpClient
    ): HomeAPI {
        
        return Retrofit.Builder()
            .baseUrl(HomeAPI.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(HomeAPI::class.java)
        
    }
    
    @Provides
    @Singleton
    fun providesHomeRepository(
        dao: HabitDao,
        api: HomeAPI
    ): HomeRepository = HomeRepoImpl(dao, api)
    
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()


}