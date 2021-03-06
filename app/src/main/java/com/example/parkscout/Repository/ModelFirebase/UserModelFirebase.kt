package com.example.parkscout.Repository.ModelFirebase

import com.example.parkscout.Repository.ChatMessage
import com.example.parkscout.Repository.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import java.sql.Timestamp
import java.util.*

class UserModelFirebase {

    companion object {
        val COLLECTION_NAME: String = "users";
    }

    // Methods
    fun getUser(listener: (User) -> Unit) {
        var uid: String = FirebaseAuth.getInstance().currentUser?.uid!!;
        var db: FirebaseFirestore = FirebaseFirestore.getInstance();

        db.collection(COLLECTION_NAME)
//            .document(uid)
            .whereEqualTo("uid", uid)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                var user: User? = querySnapshot.documents[0].toObject(User::class.java);

                if (user != null) {
                    listener(user);
                }
            };
    }

    fun setUser(user: User, listener: () -> Unit) {
        var uid: String = FirebaseAuth.getInstance().currentUser?.uid!!;
        var db: FirebaseFirestore = FirebaseFirestore.getInstance();

        db.collection(COLLECTION_NAME)
            .whereEqualTo("uid", uid)
            .get().addOnSuccessListener { it: QuerySnapshot ->
                it.forEach { doc ->
                    doc.reference.set(user.toMap()).addOnSuccessListener { listener(); }
                }
            }
    }

    fun addUser(user: User, listener: () -> Unit) {
        var uid: String = user.uId;
        var db: FirebaseFirestore = FirebaseFirestore.getInstance();

        db.collection(COLLECTION_NAME).document(user.uId).set(user)
            .addOnSuccessListener { listener(); }
    }

    fun getUserByID(id: String, listener: (User) -> Unit) {
        var db: FirebaseFirestore = FirebaseFirestore.getInstance();

        db.collection(COLLECTION_NAME)
//            .document(uid)
            .whereEqualTo("uid", id)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                var user: User? = querySnapshot.documents[0].toObject(User::class.java);

                if (user != null) {
                    listener(user);
                }
            };
    }
}