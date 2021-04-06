package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.quickstart.auth.R;

import com.google.firebase.quickstart.auth.java.models.chars;
import com.google.firebase.quickstart.auth.java.api.CharsHelper;

public class supprimer_char_admin extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout linear_layout;
    private int Nb_total_char = 0;
    private Button supprimer;
    private EditText numero;
    String liaison[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_char_admin);

        supprimer = (Button) findViewById(R.id.supprimer);
        numero = (EditText) findViewById(R.id.numero);
        supprimer.setOnClickListener(this);
        linear_layout = (LinearLayout) findViewById(R.id.linear_layout);

        FirebaseFirestore.getInstance().collection("chars").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots)  {
                String nom;
                int nb_total = 0;
                int i = 0;
                
                // On compte le nombre d'admin et de non admin
                for (chars documents : documentSnapshots.toObjects(chars.class)) {
                    i+=1;
                }
                
                // On cree les tableaux pour relier les donnees
                liaison = new String [i+1];

                i=0;
                for (chars documents : documentSnapshots.toObjects(chars.class)) {
                    nom = documents.getNom();
                    i +=1;
                    liaison[i] = nom;
                    TextView tv1 = new TextView(getApplicationContext());
                    tv1.setText("Char n° "+String.valueOf(i)+ " : "+nom);
                    tv1.setTextColor(Color.parseColor("#000000"));
                    linear_layout.addView(tv1);
                }
                Nb_total_char = i;
            }
        });


    }


    @Override
    public void onClick(View view) {
        if (view == supprimer){
            int num_char = Integer.parseInt(numero.getText().toString());
            FirebaseFirestore.getInstance().collection("chars").document(liaison[num_char]).delete();
            numero.setText("");
        }

    }
}