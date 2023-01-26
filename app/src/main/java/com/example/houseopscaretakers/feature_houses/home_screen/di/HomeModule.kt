package com.example.houseopscaretakers.feature_houses.home_screen.di

import com.example.houseopscaretakers.feature_houses.home_screen.data.repository.HouseRepositoryImpl
import com.example.houseopscaretakers.feature_houses.home_screen.domain.repository.HouseRepository
import com.example.houseopscaretakers.feature_houses.home_screen.domain.use_cases.AddHouse
import com.example.houseopscaretakers.feature_houses.home_screen.domain.use_cases.DeleteHouse
import com.example.houseopscaretakers.feature_houses.home_screen.domain.use_cases.GetHouses
import com.example.houseopscaretakers.feature_houses.home_screen.domain.use_cases.HouseUseCases
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHouseRepository(
        db: FirebaseFirestore
    ): HouseRepository = HouseRepositoryImpl(db)

    //  house use case
    @Provides
    @Singleton
    fun provideHouseUseCases(
        repository: HouseRepository
    ) = HouseUseCases(
        addHouse = AddHouse(repository),
        getHouses = GetHouses(repository),
        deleteHouse = DeleteHouse(repository)
    )
}