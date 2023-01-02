package com.example.houseopscaretakers.di

import com.example.houseopscaretakers.core.data.repository.CorerepositoryImpl
import com.example.houseopscaretakers.core.domain.repository.CoreRepository
import com.example.houseopscaretakers.core.domain.use_cases.CoreUseCases
import com.example.houseopscaretakers.core.domain.use_cases.CurrentUser
import com.example.houseopscaretakers.core.domain.use_cases.GetCaretaker
import com.example.houseopscaretakers.core.domain.use_cases.IsCaretakerLoggedIn
import com.example.houseopscaretakers.feature_authentication.login.data.repository.LoginRepositoryImpl
import com.example.houseopscaretakers.feature_authentication.login.domain.repository.LoginRepository
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.LoginUseCases
import com.example.houseopscaretakers.feature_authentication.login.domain.use_cases.LoginUser
import com.example.houseopscaretakers.feature_authentication.sign_up.data.respository.SignUpRepositoryImpl
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.CreateCaretakerCollection
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.CreateCaretakerInFirebase
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.SignUpUseCases
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.use_cases.UploadCaretakerImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    //  -----------------CORE REPOSITORY-------------------
    @Provides
    @Singleton
    fun provideCoreRepository(
        db: FirebaseFirestore,
        auth: FirebaseAuth
    ) : CoreRepository = CorerepositoryImpl(db, auth)

    @Provides
    @Singleton
    fun provideCoreUseCases(
        repository: CoreRepository
    ) = CoreUseCases(
        currentUser = CurrentUser(repository),
        getCaretaker = GetCaretaker(repository),
        isCaretakerLoggedIn = IsCaretakerLoggedIn(repository)
    )

    //  -----------------LOGIN REPOSITORY-------------------
    //  Login repository
    @Provides
    @Singleton
    fun provideLoginRepository(
        auth: FirebaseAuth
    ) : LoginRepository = LoginRepositoryImpl(auth)

    //  login use case
    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: LoginRepository
    ) = LoginUseCases(
        loginUser = LoginUser(repository)
    )

    //  -----------------SIGN UP REPOSITORY-------------------
    //  Repository injection
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
        createCaretakerInFirebase = CreateCaretakerInFirebase(repo),
        createCaretakerCollection = CreateCaretakerCollection(repo),
        uploadCaretakerImage = UploadCaretakerImage(repo)
    )

}





























