package com.example.houseopscaretakers.core.data.repository

import androidx.compose.runtime.snapshotFlow
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.repository.CoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CorerepositoryImpl(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CoreRepository {

    //  check if the user is currently logged in
    override suspend fun isUserLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun getCurrentCaretaker(): Caretaker? {

        //  get the current user
        val currentUser = auth.currentUser
        var caretaker: Caretaker? = null

        //  get user details from the database
        db.collection(Constants.CARETAKER_COLLECTION)
            .document(currentUser?.email!!)
            .addSnapshotListener { snapshot, error ->

                if (error != null)
                    return@addSnapshotListener

                snapshot?.let {
                    caretaker = it.toObject(Caretaker::class.java)
                }
            }

        return caretaker
    }
}























