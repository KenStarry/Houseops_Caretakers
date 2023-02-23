package com.example.houseopscaretakers.feature_authentication.sign_up.di

import com.example.houseopscaretakers.feature_authentication.sign_up.data.respository.SignUpRepositoryImpl
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.CreateCaretakerCollection
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.CreateUserInFirebase
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.SignUpUseCases
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.UploadCaretakerImage
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.validation.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {

    @Provides
    @Singleton
    fun provideSignUpRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore,
        storage: FirebaseStorage
    ): SignupRepository = SignUpRepositoryImpl(
        auth, db, storage
    )

    //  Use cases
    @Provides
    @Singleton
    fun provideSignUpUseCases(
        repo: SignupRepository
    ) = SignUpUseCases(
        createUserInFirebase = CreateUserInFirebase(repo),
        createCaretakerCollection = CreateCaretakerCollection(repo),
        uploadCaretakerImage = UploadCaretakerImage(repo)
    )

    @Provides
    @Singleton
    fun provideSignUpValidateUseCases() = SignUpValidateUseCases(
        validateEmail = ValidateEmail(),
        validatePassword = ValidatePassword(),
        validateRepeatedPassword = ValidateRepeatedPassword(),
        validateUserName = ValidateUserName()
    )
}













