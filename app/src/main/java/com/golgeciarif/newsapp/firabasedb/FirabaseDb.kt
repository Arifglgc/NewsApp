package com.golgeciarif.newsapp.firabasedb

import com.golgeciarif.newsapp.model.User
import com.golgeciarif.newsapp.util.Constants.Companion.USER_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirabaseDb {

    private val usersCollectionRef = Firebase.firestore.collection(USER_COLLECTION)
    val userUid = FirebaseAuth.getInstance().currentUser?.uid

    private val firebaseAuth = Firebase.auth
    fun loginUser(
        email: String,
        password: String
    ) = firebaseAuth.signInWithEmailAndPassword(email, password)

    fun createNewUser(
        email: String, password: String
    ) = firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun saveUserInformation(
        userUid: String,
        user: User
    ) = usersCollectionRef.document(userUid).set(user)

    fun getUser() = usersCollectionRef
        .document(FirebaseAuth.getInstance().currentUser!!.uid)

    fun logout() = Firebase.auth.signOut()

}