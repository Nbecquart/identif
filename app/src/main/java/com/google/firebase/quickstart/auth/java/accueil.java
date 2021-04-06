package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.firebase.quickstart.auth.R;
import android.widget.Button;
import android.util.Log;


public class accueil extends AppCompatActivity implements View.OnClickListener{

    private Button menu_jeu;
    private Button compte;
    private Button admin;
    private Button statistique;

    String ref_commun;
    String nom_commun;
    int admin_commun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle nomBundle = this.getIntent().getExtras();
        ref_commun = nomBundle.get("ref_commun").toString();
        nom_commun = nomBundle.get("nom_commun").toString();
        admin_commun = (int) nomBundle.get("admin_commun");


        setContentView(R.layout.activity_accueil);
        menu_jeu = (Button) findViewById(R.id.menu_jeu);
        compte = (Button) findViewById(R.id.compte);
        admin = (Button) findViewById(R.id.admin);
        statistique = (Button) findViewById(R.id.stats);


        if (admin_commun != 2){
            admin.setVisibility(Button.GONE);
        }





        menu_jeu.setOnClickListener(this);
        compte.setOnClickListener(this);
        admin.setOnClickListener(this);
        statistique.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == menu_jeu){
            Intent intent = new Intent(accueil.this, menu_jeu.class);
            intent.putExtra("ref_commun",ref_commun);
            intent.putExtra("nom_commun",nom_commun);
            intent.putExtra("admin_commun",admin_commun);
            startActivity(intent);
        }
        if (view == compte){
            Intent intent = new Intent(accueil.this, compte.class);
            intent.putExtra("ref_commun",ref_commun);
            intent.putExtra("nom_commun",nom_commun);
            intent.putExtra("admin_commun",admin_commun);
            startActivity(intent);
        }
        if (view == admin){
            Intent intent = new Intent(accueil.this, admin.class);
            intent.putExtra("ref_commun",ref_commun);
            intent.putExtra("nom_commun",nom_commun);
            intent.putExtra("admin_commun",admin_commun);
            startActivity(intent);
        }
        if (view == statistique){
            Intent intent = new Intent(accueil.this, statistique.class);
            intent.putExtra("ref_commun",ref_commun);
            intent.putExtra("nom_commun",nom_commun);
            intent.putExtra("admin_commun",admin_commun);
            startActivity(intent);
        }
    }


}