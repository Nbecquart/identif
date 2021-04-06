package com.google.firebase.quickstart.auth.java;

import android.graphics.Color;
import android.os.Bundle;

import com.firebase.ui.auth.data.model.User;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.quickstart.auth.R;

import com.google.firebase.quickstart.auth.java.api.CharsHelper;
import com.google.firebase.quickstart.auth.java.api.UsersHelper;
import com.google.firebase.quickstart.auth.java.divers.aleatoire;
import com.google.firebase.quickstart.auth.java.models.chars;
import com.google.firebase.quickstart.auth.java.models.users;
import com.google.zxing.PlanarYUVLuminanceSource;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.components.Description;

public class statistique extends AppCompatActivity implements View.OnClickListener {

    int nbstat11 = 0;
    int nbstat21 = 0;
    int nbstat12 = 0;
    int nbstat22 = 0;

    // On nomme les donnees necessaires pour l'affichage des statistiques
    PieChart pieChart;
    PieChart pieChart2;
    PieDataSet pieDataSet;
    PieDataSet pieDataSet2;

    // On définit les valeurs communes
    String ref_commun;
    String nom_commun;
    int admin_commun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);

        Bundle nomBundle = this.getIntent().getExtras();
        ref_commun = nomBundle.get("ref_commun").toString();
        nom_commun = nomBundle.get("nom_commun").toString();
        admin_commun = (int)nomBundle.get("admin_commun");

        //CharsHelper.createChars("nom char","reference", "nouveau char").addOnFailureListener(this.onFailureListener());

        // la fonction suivante fonctionne
        //UsersHelper.createUsers(1,2, "surnom1","password2", "users3").addOnFailureListener(this.onFailureListener());
        //Log.d("teste", "teste2");

        //CharsHelper.getChars("user1").addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){

        stats1_graph1();
        stats2_graph1();
        stats1_graph2();
        stats2_graph2();

        pieChart = findViewById(R.id.piechart);
        List <PieEntry> value = new ArrayList<>();


        pieDataSet = new PieDataSet(value, "");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);



        pieChart2 = findViewById(R.id.piechart2);
        List <PieEntry> value2 = new ArrayList<>();
        //value2.add(new PieEntry(100,"echec"));
        //value2.add(new PieEntry(25,"succes"));

        pieDataSet2 = new PieDataSet(value2, "");
        PieData pieData2 = new PieData(pieDataSet2);
        pieChart2.setData(pieData2);
        pieDataSet2.setColors(ColorTemplate.JOYFUL_COLORS);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.getLegend().setEnabled(false);


    }

    @Override
    public void onClick(View view) {

    }

    protected OnFailureListener onFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("teste", "echec");
            }
        };
    }

    private void stats1_graph1() {
        UsersHelper.getUsers(ref_commun).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users currentUsers = documentSnapshot.toObject(users.class);

                int j = currentUsers.getNb_success_solo();
                nbstat11 = j;
                String nom = String.valueOf(j);

                pieDataSet.addEntry(new PieEntry(nbstat11, "succès"));
                pieChart.notifyDataSetChanged();
                pieChart.invalidate();
            }
        });
    }

    private void stats2_graph1() {
        UsersHelper.getUsers(ref_commun).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users currentUsers = documentSnapshot.toObject(users.class);

                int j = currentUsers.getNb_echec_solo();
                nbstat21 = j;
                String nom = String.valueOf(j);

                pieDataSet.addEntry(new PieEntry(nbstat21, "echec"));
                pieChart.notifyDataSetChanged();
                pieChart.invalidate();

            }
        });
    }

    private void stats1_graph2() {
        UsersHelper.getUsers(ref_commun).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users currentUsers = documentSnapshot.toObject(users.class);

                int j = currentUsers.getNb_success_multi();
                nbstat12 = j;

                pieDataSet2.addEntry(new PieEntry(nbstat12, "succès"));
                pieChart2.notifyDataSetChanged();
                pieChart2.invalidate();
            }
        });
    }

    private void stats2_graph2() {
        UsersHelper.getUsers(ref_commun).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users currentUsers = documentSnapshot.toObject(users.class);

                int j = currentUsers.getNb_echec_multi();
                nbstat22 = j;

                pieDataSet2.addEntry(new PieEntry(nbstat22, "echec"));
                pieChart2.notifyDataSetChanged();
                pieChart2.invalidate();

            }
        });
    }
}


