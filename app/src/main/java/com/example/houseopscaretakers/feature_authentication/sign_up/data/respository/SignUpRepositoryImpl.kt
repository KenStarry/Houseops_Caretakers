package com.example.houseopscaretakers.feature_authentication.sign_up.data.respository

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Landlord
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(

    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage

) : SignupRepository {

    //  create our caretaker
    override suspend fun createUserWithEmailAndPass(
        email: String,
        password: String,
        response: (response: Response<*>) -> Unit
    ) {

        try {

            auth.createUserWithEmailAndPassword(email, password)
            response(Response.Success(true))

        } catch (e: Exception) {
            response(Response.Failure(e))
        }
    }

    //  add caretaker to collection
    override suspend fun addCaretakerToCollection(
        caretaker: Caretaker,
        response: (response: Response<*>) -> Unit
    ) {
        try {
            db.collection(com.example.houseopscaretakers.core.Constants.CARETAKER_COLLECTION)
                .document(caretaker.caretakerEmail!!)
                .set(caretaker)
                .addOnSuccessListener {
                    response(Response.Success(true))
                }
                .addOnFailureListener { e ->
                    response(Response.Failure(e))
                }

        } catch (e: Exception) {
            response(Response.Failure(e))
        }
    }

    override suspend fun addLandlordToCollection(
        landlord: Landlord,
        response: (response: Response<*>) -> Unit
    ) {
        try {
            db.collection(com.example.houseopscaretakers.core.Constants.LANDLORD_COLLECTION)
                .document(landlord.landlordEmail!!)
                .set(landlord)
                .addOnSuccessListener {
                    response(Response.Success(true))
                }
                .addOnFailureListener { e ->
                    response(Response.Failure(e))
                }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    //  upload caretaker image to firebase storage
    override suspend fun uploadCaretakerImageToCloudStorage(
        caretaker: Caretaker,
        imageUri: Uri?,
        context: Context,
        response: (response: Response<*>) -> Unit
    ) {

        val storageRef = FirebaseStorage.getInstance()
            .getReference(com.example.houseopscaretakers.core.Constants.CARETAKER_IMAGES)

        imageUri?.let {

            val fileRef = storageRef.child(
                "${System.currentTimeMillis()}.${getFileExtension(imageUri, context)}"
            )

            try {
                fileRef.putFile(imageUri)
                    .addOnSuccessListener {

                        //  Grab the download url
                        try {
                            fileRef.downloadUrl.addOnSuccessListener { url ->
                                //  add url to the caretaker collection
                                val caretakerRef =
                                    db.collection(com.example.houseopscaretakers.core.Constants.CARETAKER_COLLECTION)
                                        .document(caretaker.caretakerEmail!!)

                                caretakerRef.update("caretakerImage", url)
                            }

                            //  return success
                            response(Response.Success(true))

                        } catch (e: Exception) {
                            //  Return Failure
                            response(Response.Failure(e))
                        }
                    }

                response(Response.Success(true))

            } catch (e: Exception) {
                response(Response.Failure(e))
            }
        }

    }

    override suspend fun uploadLandlordImageToCloudStorage(
        landlord: Landlord,
        imageUri: Uri?,
        context: Context,
        response: (response: Response<*>) -> Unit
    ) {
        val storageRef = FirebaseStorage.getInstance()
            .getReference(com.example.houseopscaretakers.core.Constants.LANDLORD_IMAGES)

        imageUri?.let {

            val fileRef = storageRef.child(
                "${System.currentTimeMillis()}.${getFileExtension(imageUri, context)}"
            )

            try {
                fileRef.putFile(imageUri)
                    .addOnSuccessListener {

                        //  Grab the download url
                        try {
                            fileRef.downloadUrl.addOnSuccessListener { url ->
                                //  add url to the caretaker collection
                                val caretakerRef =
                                    db.collection(com.example.houseopscaretakers.core.Constants.LANDLORD_COLLECTION)
                                        .document(landlord.landlordEmail!!)

                                caretakerRef.update("caretakerImage", url)
                            }

                            //  return success
                            response(Response.Success(true))

                        } catch (e: Exception) {
                            //  Return Failure
                            response(Response.Failure(e))
                        }
                    }

            } catch (e: Exception) {
                response(Response.Failure(e))
            }
        }

    }

    //  get file extension
    private fun getFileExtension(uri: Uri, context: Context): String? {

        val cr = context.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()

        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
    }
}


































