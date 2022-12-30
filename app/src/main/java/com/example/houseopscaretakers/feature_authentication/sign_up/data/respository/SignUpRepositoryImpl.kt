package com.example.houseopscaretakers.feature_authentication.sign_up.data.respository

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import com.example.houseopscaretakers.core.Constants
import com.example.houseopscaretakers.core.domain.model.Caretaker
import com.example.houseopscaretakers.core.domain.model.Response
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.CreateUserResponse
import com.example.houseopscaretakers.feature_authentication.sign_up.domain.repository.SignupRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(

    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage

) : SignupRepository {

    //  create our caretaker
    override suspend fun createCaretakerWithEmailAndPass(
        email: String,
        password: String
    ): CreateUserResponse {

        return try {

            auth.createUserWithEmailAndPassword(email, password)
            Response.Success(true)

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    //  add caretaker to collection
    override suspend fun addCaretakerToCollection(caretaker: Caretaker): CreateUserResponse {

        var response = Response.Success(false)

        return try {
            db.collection(Constants.CARETAKER_COLLECTION).document(caretaker.caretakerEmail!!)
                .set(caretaker)
                .addOnSuccessListener {
                    response = Response.Success(true)
                }
                .addOnFailureListener { e ->
                    response = Response.Success(false)
                }

            response

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    //  upload caretaker image to firebase storage
    override suspend fun uploadCaretakerImageToCloudStorage(
        caretaker: Caretaker,
        context: Context
    ): CreateUserResponse {

        val storageRef = FirebaseStorage.getInstance().getReference(Constants.CARETAKER_IMAGES)
        var response = Response.Success(false)

        caretaker.caretakerImage?.let {

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
                                val caretakerRef = db.collection(Constants.CARETAKER_COLLECTION)
                                    .document(caretaker.caretakerEmail!!)

                                caretakerRef.update("caretakerImage", url)
                            }

                            //  return success
                            response = Response.Success(true)

                        } catch (e: Exception) {
                            //  Return Failure
                            Response.Failure(e)
                        }
                    }

                response = Response.Success(true)

            } catch (e: Exception) {
                Response.Failure(e)
            }
        }

        return response
    }

    //  get file extension
    private fun getFileExtension(uri: Uri, context: Context): String? {

        val cr = context.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()

        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
    }
}


































