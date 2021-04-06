package com.google.firebase.quickstart.auth.java.divers;

public class aleatoire {

    public static int[] random_indice(int max, int indice_image){
        int tab[] = new int [3];

        int hasard = 1+(int)(Math.random() * max);
        while (hasard == indice_image){
            hasard = 1+(int)(Math.random() * max);
        }
        tab[0] = hasard;

        hasard= 1+(int)(Math.random() * max);
        while(hasard == indice_image || hasard == tab[0]){
            hasard = 1+(int)(Math.random() * max);
        }
        tab[1] = hasard;

        hasard= 1+(int)(Math.random() * max);
        while(hasard == indice_image || hasard == tab[0] || hasard == tab[1]){
            hasard = 1+(int)(Math.random() * max);
        }
        tab[2] = hasard;


        return tab;
    }

    public static int[] inserer_valeur(int initiale[], int valeur, int place){
        place = place-1;
        int taille = initiale.length+1;
        int finale [] = new int [4];
        for (int i = 0; i<place; i++){
            finale[i] = initiale[i];
        }
        finale[place] = valeur;
        for (int i = place+1; i<4; i++){
            finale[i] = initiale[i-1];
        }
        return finale;
    }
}
