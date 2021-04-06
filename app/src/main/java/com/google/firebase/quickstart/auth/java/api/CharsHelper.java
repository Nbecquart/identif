package com.google.firebase.quickstart.auth.java.api;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.Task;
import com.google.firebase.quickstart.auth.java.models.chars;
import com.google.firebase.firestore.DocumentSnapshot;



public class CharsHelper {

    private static final String COLLECTION_NAME = "chars";

    // --- COLLECTION REFERENCE ---
    public static CollectionReference getCharsCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---
    public static Task <Void> createChars(String nom, String ref, String Ui) {
        chars charsToCreate = new chars(nom, ref);
        return CharsHelper.getCharsCollection().document(Ui).set(charsToCreate);
    }

    // --- GET ---
    public static Task<DocumentSnapshot> getChars(String nom){
        return CharsHelper.getCharsCollection().document(nom).get();
    }

    // --- UPDATE ---
    public static Task<Void> updateCharsname(String nom, String nouveau) {
        return CharsHelper.getCharsCollection().document(nom).update("nom",nouveau);
    }



    // --- DELETE ---
    public static Task<Void> deleteChars(String nom) {
        return CharsHelper.getCharsCollection().document(nom).delete();
    }

}