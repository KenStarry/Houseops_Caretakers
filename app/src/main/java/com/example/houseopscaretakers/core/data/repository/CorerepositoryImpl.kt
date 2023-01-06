package com.example.houseopscaretakers.core.data.repository

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.data.utils.getFileExtension
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.core.domain.repository.CoreRepository
import com.example.houseopscaretakers.feature_houses.home_screen.domain.model.HouseModel
import com.google.common.io.Files.getFileExtension
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
        db.collection(Constants.CARETAKER_COLLECTION)
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

        val storageRef = FirebaseStorage.getInstance().getReference("${houseModel.houseCategory}/${Constants.HOUSE_IMAGES}")

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
                                val apartmentCollection = db.collection(Constants.APARTMENTS_COLLECTION)
                                    .document(apartmentName).collection(Constants.HOUSES_SUB_COLLECTION)
                                    .document(houseModel.houseCategory)

                                apartmentCollection.update("houseImageUris", FieldValue.arrayUnion(url))
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
}























