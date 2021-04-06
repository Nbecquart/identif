package com.google.firebase.quickstart.auth.java;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.quickstart.auth.R;


import com.google.firebase.quickstart.auth.java.models.users;
import com.google.firebase.quickstart.auth.java.api.UsersHelper;
import android.widget.Button;
import android.util.Log;
import java.util.Arrays;



public class supprimer_compte_admin extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout linear_layout;
    private int Nb_total_users = 0;
    private Button supprimer;
    private EditText numero;
    String liaison[];
    int transition[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_compte_admin);

        supprimer = (Button) findViewById(R.id.supprimer);
        numero = (EditText) findViewById(R.id.numero);
        supprimer.setOnClickListener(this);


        linear_layout = (LinearLayout) findViewById(R.id.linear_layout);
        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots)  {
                String nom;
                int valeur_admin;
                int i = 0;
                int j = 0;

                // On compte le nombre d'admin et de non admin
                for (users documents : documentSnapshots.toObjects(users.class)) {
                    i+=1;
                    if(documents.getAdmin() == 1){
                        j+=1;
                    }
                }

                // On crée les matrices de liaisons
                liaison = new String [i+1];
                transition = new int [j+1];
                i = 0;
                j = 0;

                // On gère l'affichage
                for (users documents : documentSnapshots.toObjects(users.class)) {
                    nom = documents.getSurnom();
                    valeur_admin = documents.getAdmin();
                    i +=1;

                    if(valeur_admin==1){
                        j+=1;
                        liaison[i] = nom;
                        transition[j] = i;
                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText("Compte n° "+String.valueOf(j)+ " : "+nom);
                        tv1.setTextColor(Color.parseColor("#000000"));
                        linear_layout.addView(tv1);
                    }
                }
                Nb_total_users = i;
            }
        });







    }


    @Override
    public void onClick(View view) {
        if (view == supprimer){
            int num_compte = Integer.parseInt(numero.getText().toString());
            FirebaseFirestore.getInstance().collection("users").document(liaison[transition[num_compte]]).delete();
            numero.setText("");
        }

    }
}