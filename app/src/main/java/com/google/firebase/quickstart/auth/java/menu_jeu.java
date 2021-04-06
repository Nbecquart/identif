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

public class menu_jeu extends AppCompatActivity implements View.OnClickListener {

    private Button solo;
    private Button multijoueur;

    // Variable communes
    String ref_commun;
    String nom_commun;
    int admin_commun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_jeu);
        solo = (Button) findViewById(R.id.solo);
        multijoueur = (Button) findViewById(R.id.multijoueur);

        // On récupere les valeurs
        Bundle nomBundle = this.getIntent().getExtras();
        ref_commun = nomBundle.get("ref_commun").toString();
        nom_commun = nomBundle.get("nom_commun").toString();
        admin_commun = (int) nomBundle.get("admin_commun");

        solo.setOnClickListener(this);
        multijoueur.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view == solo){
            Intent intent = new Intent(menu_jeu.this, jeu_solo.class);
            intent.putExtra("ref_commun",ref_commun);
            intent.putExtra("nom_commun",nom_commun);
            intent.putExtra("admin_commun",admin_commun);
            startActivity(intent);
        }
        if (view == multijoueur){
            Intent intent = new Intent(menu_jeu.this, jeu_multi.class);
            intent.putExtra("ref_commun",ref_commun);
            intent.putExtra("nom_commun",nom_commun);
            intent.putExtra("admin_commun",admin_commun);
            startActivity(intent);
        }
    }
}