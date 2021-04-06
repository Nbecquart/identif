package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.quickstart.auth.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.google.firebase.quickstart.auth.java.api.UsersHelper;
import com.google.firebase.quickstart.auth.java.models.chars;
import com.google.firebase.quickstart.auth.java.models.users;





public class ajouter_compte_admin extends AppCompatActivity implements View.OnClickListener{

    private Button ajouter;
    private EditText pseudo;
    private EditText mot_de_passe;
    private TextView text;
    private int users_total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_compte_admin);

        // On définit les paramètres de notre interface
        ajouter = (Button) findViewById(R.id.ajouter);
        pseudo = (EditText) findViewById(R.id.pseudo);
        mot_de_passe = (EditText) findViewById(R.id.mdp);
        text = (TextView) findViewById(R.id.text3);

        // On attend un click sur le bouton ajouter
        ajouter.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == ajouter){
            String mdp = mot_de_passe.getText().toString();
            String nom = pseudo.getText().toString();


            Log.d("teste",mdp);
            Log.d("teste", nom);

            if (mdp.equals("") || nom.equals("")){
                text.setText("Merci de remplir tout les champs");
            }
            else{
                ajouter_un_compte(nom, mdp);
                text.setText("Compte ajouté");
                mot_de_passe.setText("");
                pseudo.setText("");
            }
        }
    }


    public void ajouter_un_compte(String pseudo, String mdp){

        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                boolean creer = true;
                users user = null;
                for (users documents : documentSnapshots.toObjects(users.class)) {
                    users_total+=1;
                    if (documents.getSurnom().equals(pseudo)){
                        creer = false;
                    }
                }

                // on crée le compte si le pseudo n'existe pas déjà
                if(creer) {
                    UsersHelper.createUsers(pseudo, mdp, 1, 0, 0, 0, 0, pseudo);
                }


            }
        });
    }



}