package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.request.target.ViewTarget;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.common.net.InternetDomainName;
import com.google.common.primitives.Chars;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.quickstart.auth.R;
import com.google.android.gms.tasks.OnFailureListener;
import androidx.annotation.NonNull;

import android.widget.Button;
import com.google.firebase.quickstart.auth.java.api.CharsHelper;
import com.google.firebase.quickstart.auth.java.models.chars;
import com.google.firebase.quickstart.auth.java.divers.aleatoire;

import android.util.Log;
import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.quickstart.auth.java.models.users;
import com.google.firebase.storage.StorageReference;
import com.bumptech.glide.request.RequestOptions;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.Glide;

import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;

import com.google.firebase.quickstart.auth.java.api.UsersHelper;


public class jeu_solo extends AppCompatActivity implements View.OnClickListener {

    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private ImageView image;

    int place_indice;

    int echec = 0;
    int success = 0;

    int image_precedente = 0;
    int char_total = 0;

    // Variable communes
    String ref_commun;
    String nom_commun;
    int admin_commun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_solo);

        // On compte le nombre de char total
        CompterAllChars();

        // On ajoute les boutons
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        image = (ImageView) findViewById(R.id.image);

        // on récupére les valeurs du joueur
        Bundle nomBundle = this.getIntent().getExtras();
        ref_commun = nomBundle.get("ref_commun").toString();
        nom_commun = nomBundle.get("nom_commun").toString();
        admin_commun = (int)nomBundle.get("admin_commun");

        // On écoute les clics sur les boutons
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        // on colore les boutons
        option1.setBackgroundColor(Color.rgb(18, 135, 223));
        option2.setBackgroundColor(Color.rgb(18, 135, 223));
        option3.setBackgroundColor(Color.rgb(18, 135, 223));
        option4.setBackgroundColor(Color.rgb(18, 135, 223));

        GetEchecInFirestore();
        GetSuccessInFirestore();

        // Si le nombre de char ne s'est pas encore actualisé (appel assynchrone) alors on fixe le nombre de char à 5
        if(char_total == 0){
            char_total = 5;
        }
        int indice_image = 1 + (int)(Math.random() * (char_total));
        image_precedente = indice_image;

        Afficher_image(indice_image);
        int i_reponse[] = aleatoire.random_indice(char_total,indice_image);
        place_indice = 1 + (int)(Math.random() * (4));
        int i_reponse_finale [] = aleatoire.inserer_valeur(i_reponse,indice_image,place_indice);

        // On récupère les noms de 4 chars tirés
        GetCharsInFirestore1(i_reponse_finale[0]);
        GetCharsInFirestore2(i_reponse_finale[1]);
        GetCharsInFirestore3(i_reponse_finale[2]);
        GetCharsInFirestore4(i_reponse_finale[3]);

    }

    @Override
    public void onClick(View view) {

        // On desactive les boutons
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);


        // On regarde ou l'on a appuyer
        int numero_bouton = 0;
        if (view == option1){
            numero_bouton =1;
        }
        if (view == option2){
            numero_bouton =2;
        }
        if (view == option3){
            numero_bouton =3;
        }
        if (view == option4){
            numero_bouton =4;
        }

        if (numero_bouton == place_indice){
            if (place_indice == 1){
                option1.setBackgroundColor(Color.GREEN);
            }
            if (place_indice == 2){
                option2.setBackgroundColor(Color.GREEN);
            }
            if (place_indice == 3){
                option3.setBackgroundColor(Color.GREEN);
            }
            if (place_indice == 4){
                option4.setBackgroundColor(Color.GREEN);
            }
            Ajouter_score();
        }
        else{
            if (place_indice == 1){
                option1.setBackgroundColor(Color.RED);
            }
            if (place_indice == 2){
                option2.setBackgroundColor(Color.RED);
            }
            if (place_indice == 3){
                option3.setBackgroundColor(Color.RED);
            }
            if (place_indice == 4){
                option4.setBackgroundColor(Color.RED);
            }
            Enlever_score();
        }


        option1.postDelayed(new Runnable() {
            public void run() {

                // On reactive les boutons
                option1.setEnabled(true);
                option2.setEnabled(true);
                option3.setEnabled(true);
                option4.setEnabled(true);

                // On remet la couleur des boutons
                option1.setBackgroundColor(Color.rgb(18, 135, 223));
                option2.setBackgroundColor(Color.rgb(18, 135, 223));
                option3.setBackgroundColor(Color.rgb(18, 135, 223));
                option4.setBackgroundColor(Color.rgb(18, 135, 223));

                // on vérifie que l'on tire une nouvelle image
                int indice_image = 1 + (int) (Math.random() * (char_total));
                while (indice_image == image_precedente){
                    indice_image = 1 + (int) (Math.random() * (char_total));
                }
                image_precedente = indice_image;

                Afficher_image(indice_image);
                int i_reponse[] = aleatoire.random_indice(char_total,indice_image);
                place_indice = 1 + (int)(Math.random() * (4));
                int i_reponse_finale [] = aleatoire.inserer_valeur(i_reponse,indice_image,place_indice);

                GetCharsInFirestore1(i_reponse_finale[0]);
                GetCharsInFirestore2(i_reponse_finale[1]);
                GetCharsInFirestore3(i_reponse_finale[2]);
                GetCharsInFirestore4(i_reponse_finale[3]);
            }
        }, 1000);
    }


    protected OnFailureListener onFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        };
    }


    private void GetCharsInFirestore1(int i) {
        CharsHelper.getChars("char"+String.valueOf(i)).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                chars currentChars = documentSnapshot.toObject(chars.class);
                String nom = "";
                nom = currentChars.getNom();
                option1.setText(nom);
            }
        });
    }
    private void GetCharsInFirestore2(int i) {
        CharsHelper.getChars("char"+String.valueOf(i)).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                chars currentChars = documentSnapshot.toObject(chars.class);
                String nom = "";
                nom = currentChars.getNom();
                option2.setText(nom);
            }
        });
    }
    private void GetCharsInFirestore3(int i) {
        CharsHelper.getChars("char"+String.valueOf(i)).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                chars currentChars = documentSnapshot.toObject(chars.class);
                String nom = "";
                nom = currentChars.getNom();
                option3.setText(nom);
            }
        });
    }
    private void GetCharsInFirestore4(int i) {
        CharsHelper.getChars("char"+String.valueOf(i)).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                chars currentChars = documentSnapshot.toObject(chars.class);
                String nom = "";
                nom = currentChars.getNom();
                option4.setText(nom);
            }
        });
    }


    private void CreateCharsInFirestore(){
        CharsHelper.createChars("nom char","reference", "nouveau char").addOnFailureListener(this.onFailureListener());
    }

    private void Afficher_image(int i){
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("/image/"+String.valueOf(i)+".jpg");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
                String url = downloadUrl.toString();
                Glide
                        .with(getApplicationContext())
                        .load(url)
                        .into(image);
            }
        });

    }

    private void Ajouter_score(){
        success+=1;
        UsersHelper.updateNb_success(nom_commun,success).addOnFailureListener(this.onFailureListener());
    }
    private void Enlever_score(){
        echec+=1;
        UsersHelper.updateNb_echec(nom_commun,echec).addOnFailureListener(this.onFailureListener());
    }
    private void GetEchecInFirestore() {
        UsersHelper.getUsers(nom_commun).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users currentUsers = documentSnapshot.toObject(users.class);
                int j = currentUsers.getNb_echec_solo();
                echec = j;
            }
        });
    }
    private void GetSuccessInFirestore() {
        UsersHelper.getUsers(nom_commun).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users currentUsers = documentSnapshot.toObject(users.class);
                int k = currentUsers.getNb_success_solo();
                success = k;
            }
        });
    }

    public void CompterAllChars () {
        FirebaseFirestore.getInstance().collection("chars").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                char_total = 0;
                for (chars documents : documentSnapshots.toObjects(chars.class)) {
                    char_total+=1;
                }
                Log.d("teste", String.valueOf(char_total));
            }
        });
    }

}