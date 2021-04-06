package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.google.firebase.quickstart.auth.R;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.quickstart.auth.java.api.CharsHelper;

public class admin extends AppCompatActivity implements View.OnClickListener {

    private Button supprimer_compte;
    private Button ajouter_compte;
    private Button supprimer_char;
    private Button ajouter_char;
    private Button voir_statistiques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ajouter_compte= (Button) findViewById(R.id.ajouterutilisateur);
        supprimer_compte= (Button) findViewById(R.id.supprimerutilisateur);
        ajouter_char= (Button) findViewById(R.id.ajouterchar);
        supprimer_char= (Button) findViewById(R.id.enleverchar);
        voir_statistiques= (Button) findViewById(R.id.voirstats);
        ajouter_char.setOnClickListener(this);
        ajouter_compte.setOnClickListener(this);
        supprimer_char.setOnClickListener(this);
        supprimer_compte.setOnClickListener(this);
        voir_statistiques.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == ajouter_char) {
            Intent intent = new Intent(admin.this, ajouter_char_admin.class);
            startActivity(intent);
        }
        if(view == supprimer_char) {
            Intent intent = new Intent(admin.this, supprimer_char_admin.class);
            startActivity(intent);
        }
        if(view == ajouter_compte) {
            Intent intent = new Intent(admin.this, ajouter_compte_admin.class);
            startActivity(intent);
        }
        if(view == supprimer_compte) {
            Intent intent = new Intent(admin.this, supprimer_compte_admin.class);
            startActivity(intent);
        }
        if(view == voir_statistiques) {
            Intent intent = new Intent(admin.this, voir_statistiques_admin.class);
            startActivity(intent);
        }
    }


    protected OnFailureListener onFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        };
    }


}