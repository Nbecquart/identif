package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.quickstart.auth.R;
import android.widget.Button;

import com.google.firebase.quickstart.auth.java.api.UsersHelper;
import com.google.firebase.quickstart.auth.java.models.users;


public class compte extends AppCompatActivity implements View.OnClickListener{

    private TextView commentaire;
    private EditText nouveau_mdp;
    private EditText ancien_mdp;
    private Button modifier;

    String ref_commun;
    String nom_commun;
    int admin_commun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        // on récupère l'utilisateur
        Bundle nomBundle = this.getIntent().getExtras();
        ref_commun = nomBundle.get("ref_commun").toString();
        nom_commun = nomBundle.get("nom_commun").toString();
        admin_commun = (int) nomBundle.get("admin_commun");

        // On fait le lien entre l'affichage et le code
        commentaire = (TextView) findViewById(R.id.commentaire);
        nouveau_mdp = (EditText) findViewById(R.id.mot_de_passe);
        ancien_mdp = (EditText) findViewById(R.id.ancien_mot_de_passe);
        modifier = (Button) findViewById(R.id.modifier);

        modifier.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == modifier){

            // On récupère les deux textes
            String nouv_mdp = nouveau_mdp.getText().toString();
            String anc_mdp = ancien_mdp.getText().toString();
            if(nouv_mdp.equals("") || anc_mdp.equals("")){
                commentaire.setText("Merci de compléter le champ");
            }
            else{

                UsersHelper.getUsers(ref_commun).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        users currentUsers = documentSnapshot.toObject(users.class);
                        String mdp = currentUsers.getPassword();

                        if(mdp.equals(anc_mdp)){
                            changer_mdp(nouv_mdp, ref_commun);
                            commentaire.setText("Mot de passe modifié");
                            ancien_mdp.setText("");
                            nouveau_mdp.setText("");
                        }
                        else{
                            commentaire.setText("Ancien mot de passe incorrect");
                            ancien_mdp.setText("");
                            nouveau_mdp.setText("");
                        }
                    }
                });



            }
        }
    }

    public void changer_mdp(String mdp, String nom){
        UsersHelper.updatePassword(nom, mdp);
    }
}