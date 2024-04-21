package com.golgeciarif.newsapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseCommon @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
///??????????
    private val cartCollection =
        firestore.collection("users").document(auth.uid!!).collection("news")


}