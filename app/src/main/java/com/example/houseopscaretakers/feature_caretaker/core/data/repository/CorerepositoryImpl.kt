package com.example.houseopscaretakers.feature_caretaker.core.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.houseopscaretakers.feature_caretaker.core.data.utils.getFileExtension
import com.example.houseopscaretakers.feature_caretaker.core.domain.model.Caretaker
import com.example.houseopscaretakers.feature_caretaker.core.domain.model.Response
import com.example.houseopscaretakers.feature_caretaker.core.domain.repository.CoreRepository
import com.example.houseopscaretakers.feature_caretaker.feature_houses.home_screen.domain.model.HouseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class CorerepositoryImpl(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) : CoreRepository {

    override suspend fun currentUser(): FirebaseUser? {
        return auth.currentUser
    }

    //  check if the user is currently logged in
    override suspend fun isUserLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun getCurrentCaretaker(
        email: String,
        currentCaretaker: (caretaker: Caretaker) -> Unit
    ) {

        //  get user details from the database
        db.collection(com.example.houseopscaretakers.feature_caretaker.core.Constants.CARETAKER_COLLECTION)
            .document(email)
            .addSnapshotListener { snapshot, error ->

                if (error != null)
                    return@addSnapshotListener

                snapshot?.toObject(Caretaker::class.java)?.let { currentCaretaker(it) }
            }
    }

    override suspend fun uploadImagesToStorage(
        imageUriList: List<Uri>,
        context: Context,
        houseModel: HouseModel,
        apartmentName: String
    ) {

        val storageRef = FirebaseStorage.getInstance()
            .getReference("${houseModel.houseCategory}/${com.example.houseopscaretakers.feature_caretaker.core.Constants.HOUSE_IMAGES}")

        imageUriList.forEach {

            val fileRef = storageRef.child(
                "${System.currentTimeMillis()}.${getFileExtension(it, context)}"
            )

            try {
                fileRef.putFile(it)
                    .addOnSuccessListener {

                        //  Grab the download url
                        try {
                            fileRef.downloadUrl.addOnSuccessListener { url ->
                                //  add url to the caretaker collection
                                val apartmentCollection =
                                    db.collection(com.example.houseopscaretakers.feature_caretaker.core.Constants.APARTMENTS_COLLECTION)
                                        .document(apartmentName)
                                        .collection(com.example.houseopscaretakers.feature_caretaker.core.Constants.HOUSES_SUB_COLLECTION)
                                        .document(houseModel.houseCategory)

                                apartmentCollection.update(
                                    "houseImageUris",
                                    FieldValue.arrayUnion(url)
                                )

                                Log.d("Storage", url.toString())
                            }

                        } catch (e: Exception) {
                            //  Return Failure
                            Response.Failure(e)
                        }
                    }

            } catch (e: Exception) {
                Response.Failure(e)
            }

        }

    }

    override suspend fun getCurrentHouse(
        category: String,
        apartmentName: String,
        currentHouse: (house: HouseModel) -> Unit
    ) {

        db.collection(com.example.houseopscaretakers.feature_caretaker.core.Constants.APARTMENTS_COLLECTION).document(apartmentName)
            .collection(com.example.houseopscaretakers.feature_caretaker.core.Constants.HOUSES_SUB_COLLECTION).document(category)
            .addSnapshotListener { snapshot, error ->

                if (error != null)
                    return@addSnapshotListener

                snapshot?.toObject(HouseModel::class.java)?.let {
                    currentHouse(it)
                }
            }
    }
}























