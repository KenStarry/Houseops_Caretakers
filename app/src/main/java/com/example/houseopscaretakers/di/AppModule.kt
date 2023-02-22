package com.example.houseopscaretakers.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.houseopscaretakers.feature_caretaker.core.data.repository.ConnectivityObserverImpl
import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.ConnectivityObserver
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //  connectivity manager to monitor our internet connection
    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //  connectivity observer repository injection
    @Provides
    @Singleton
    fun provideConnectivityObserver(
        connectivityManager: ConnectivityManager
    ) : ConnectivityObserver = ConnectivityObserverImpl(connectivityManager)

    //  Firestore instance
    @Provides
    @Singleton
    fun provideFirebaseFiresore() = Firebase.firestore

    //  Authentication instance
    @Provides
    @Singleton
    fun provideFirebaseAuthentication() = Firebase.auth

    //  Sorage instance
    @Provides
    @Singleton
    fun provideFirebaseStorage() = Firebase.storage

}





























