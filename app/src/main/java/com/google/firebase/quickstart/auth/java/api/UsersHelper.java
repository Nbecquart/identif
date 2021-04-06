package com.google.firebase.quickstart.auth.java.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.quickstart.auth.java.models.users;
import android.util.Log;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnSuccessListener;

public class UsersHelper{

    private static final String COLLECTION_NAME = "users";

    // --- COLLECTION REFERENCE ---
    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---
    public static Task <Void> createUsers(String surnom, String password,int admin, int nb_echec_solo, int nb_success_solo, int nb_echec_multi, int nb_success_multi, String Ui) {
        users usersToCreate = new users(password, surnom, admin, nb_echec_solo, nb_success_solo, nb_echec_multi, nb_success_multi);
        //Log.d("teste", "creationobject");
        return UsersHelper.getUsersCollection().document(Ui).set(usersToCreate);
    }

    public static void getAllUsers () {
        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (users documents : documentSnapshots.toObjects(users.class)) {
                    String nom = documents.getPassword();
                    Log.d("teste", nom);
                }
            }
        });
    }

    // --- GET ---
    public static Task<DocumentSnapshot> getUsers(String nom){
        return UsersHelper.getUsersCollection().document(nom).get();
    }

    // --- UPDATE ---
    public static Task<Void> updateUsersname(String nom, String nouveau) {
        return UsersHelper.getUsersCollection().document(nom).update("nom",nouveau);
    }
    public static Task<Void> updatePassword(String nom, String nouveau) {
        return UsersHelper.getUsersCollection().document(nom).update("password",nouveau);
    }
    public static Task<Void> updateNb_echec(String nom, int nouveau) {
        return UsersHelper.getUsersCollection().document(nom).update("nb_echec_solo",nouveau);
    }
    public static Task<Void> updateNb_success(String nom, int nouveau) {
        return UsersHelper.getUsersCollection().document(nom).update("nb_success_solo",nouveau);
    }

    // --- DELETE ---
    public static Task<Void> deleteUsers(String nom) {
        return UsersHelper.getUsersCollection().document(nom).delete();
    }

}