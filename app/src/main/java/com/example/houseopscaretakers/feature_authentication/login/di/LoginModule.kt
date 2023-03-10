package com.example.houseopscaretakers.feature_authentication.login.di

import com.example.houseopscaretakers.feature_authentication.login.data.repository.LoginRepositoryImpl
import com.example.houseopscaretakers.feature_authentication.login.domain.repository.LoginRepository
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.LoginUseCases
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.LoginUser
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.validation.LoginValidateEmail
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.validation.LoginValidatePassword
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.validation.LoginValidateUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        auth: FirebaseAuth
    ): LoginRepository = LoginRepositoryImpl(auth)

    //  login use case
    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: LoginRepository
    ) = LoginUseCases(
        loginUser = LoginUser(repository)
    )

    //  login validation usecases
    @Provides
    @Singleton
    fun provideLoginValidateUseCases() = LoginValidateUseCases(
        loginValidateEmail = LoginValidateEmail(),
        loginValidatePassword = LoginValidatePassword()
    )
}