package com.example.houseopscaretakers.core.di

import android.content.Context
import com.example.houseopscaretakers.feature_caretaker.feature_settings.data.datastore.AccentPreference
import com.example.houseopscaretakers.core.data.repository.CorerepositoryImpl
import com.example.houseopscaretakers.core.domain.repository.ConnectivityObserver
import com.example.houseopscaretakers.core.domain.repository.CoreRepository
import com.example.houseopscaretakers.core.domain.use_cases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideCoreRepository(
        db: FirebaseFirestore,
        auth: FirebaseAuth,
        storage: FirebaseStorage
    ): CoreRepository = CorerepositoryImpl(db, auth, storage)

    @Provides
    @Singleton
    fun provideCoreUseCases(
        repository: CoreRepository,
        connectivityObserver: ConnectivityObserver
    ) = CoreUseCases(
        connection = Connection(connectivityObserver),
        currentUser = CurrentUser(repository),
        getCaretaker = GetCaretaker(repository),
        isCaretakerLoggedIn = IsCaretakerLoggedIn(repository),
        coreUploadImages = CoreUploadImages(repository),
        getHouse = GetHouse(repository)
    )

    //  provide datastore accent preference
    @Provides
    @Singleton
    fun provideAccentPreference(
        @ApplicationContext context: Context
    ) = AccentPreference(context)

}