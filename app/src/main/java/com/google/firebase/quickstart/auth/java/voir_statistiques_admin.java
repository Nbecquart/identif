package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.quickstart.auth.R;
import com.google.firebase.quickstart.auth.java.models.users;
import android.widget.TextView;
import android.util.Log;
import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;



public class voir_statistiques_admin extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout linear_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_statistiques_admin);

        linear_layout = (LinearLayout) findViewById(R.id.layout_text);
        //Log.d("teste", "ceci est un test");
        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                int i = 0;
                String nom;
                int stat_solo_success = 0;
                int stat_solo_echec = 0;
                int stat_multi_success = 0;
                int stat_multi_echec = 0;

                for (users documents : documentSnapshots.toObjects(users.class)) {
                    nom = documents.getSurnom();
                    stat_solo_success = documents.getNb_echec_solo();
                    stat_solo_echec = documents.getNb_success_solo();

                    //Log.d("teste", "ceci est un test");
                    TextView tv1 = new TextView(getApplicationContext());
                    tv1.setText("Pseudo : "+nom);
                    tv1.setTextColor(Color.parseColor("#000000"));
                    linear_layout.addView(tv1);
                    
                    TextView tv2 = new TextView(getApplicationContext());
                    tv2.setText("Succes Solo : "+String.valueOf(stat_solo_success));
                    tv2.setTextColor(Color.parseColor("#000000"));
                    linear_layout.addView(tv2);

                    TextView tv3 = new TextView(getApplicationContext());
                    tv3.setText("Echec Solo : "+String.valueOf(stat_solo_echec));
                    tv3.setTextColor(Color.parseColor("#000000"));
                    linear_layout.addView(tv3);
                }
            }
        });


    }


    @Override
    public void onClick(View view) {

    }

}