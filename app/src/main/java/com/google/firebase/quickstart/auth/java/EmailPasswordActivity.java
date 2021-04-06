/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.firebase.quickstart.auth.java;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.quickstart.auth.R;
import com.google.firebase.quickstart.auth.databinding.ActivityEmailpasswordBinding;
import com.google.firebase.quickstart.auth.java.api.UsersHelper;
import com.google.firebase.quickstart.auth.java.models.users;

import android.widget.Button;
import android.widget.EditText;



public class EmailPasswordActivity extends BaseActivity implements View.OnClickListener {

    private Button valider;
    private EditText pseudo;
    private EditText mot_de_passe;
    private TextView commentaire;

    static String ref_commun;
    static String nom_commun;
    static int admin_commun;

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emailpassword);
        commentaire = (TextView) findViewById(R.id.commentaire);
        valider = (Button) findViewById(R.id.validation);
        pseudo = (EditText) findViewById(R.id.surnom);
        mot_de_passe = (EditText) findViewById(R.id.pasword);



        valider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nom = pseudo.getText().toString();
        String mdp = mot_de_passe.getText().toString();
        if (view == valider){
            if (nom.equals("") || mdp.equals("")) {
                commentaire.setText("Veuillez remplir les champs");
            }
            else{
                se_connecter(nom,mdp);
            }
        }
    }




    public void se_connecter(String nom, String mdp){

        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                int i = 0;
                for (users documents : documentSnapshots.toObjects(users.class)) {
                    i+=1;
                    if(documents.getSurnom().equals(nom)){
                        if(documents.getPassword().equals(mdp)){
                            ref_commun = nom;
                            nom_commun = nom;
                            admin_commun = documents.getAdmin();
                            pseudo.setText("");
                            commentaire.setText("");
                            mot_de_passe.setText("");
                            Intent intent = new Intent(EmailPasswordActivity.this, accueil.class);
                            intent.putExtra("ref_commun",ref_commun);
                            intent.putExtra("nom_commun",nom_commun);
                            intent.putExtra("admin_commun",admin_commun);
                            startActivity(intent);
                        }
                        else{
                            commentaire.setText("mot de passe incorrect");
                        }
                    }
                    pseudo.setText("");
                    mot_de_passe.setText("");
                }
            }
        });
    }
}
