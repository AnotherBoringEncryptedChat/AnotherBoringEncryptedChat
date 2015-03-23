/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package abec.encryption;

import java.time.*;
import java.util.*;

/**
 * Une classe qui permet de générer des clefs selon plusieurs méthodes.
 * On a la méthode du tableau de chaine de caractere par jour. Egalement la creation 
 * d'une clef de cryptage aléatoirement par jour
 * @author Quagliatini Jordan
 * @class ClefDuJour
 */
public class ClefDuJour {
    
    /**
     * La liste des 7 clefs
     */
    protected List<String> clefArray;

    /**
     * Constructeur par défaut.
     */
    public ClefDuJour() {
        this.clefArray = new ArrayList<>();
    }
    
    /**
     * Cette méthode génère 7 clefs aléatoires pour toute la semaine.
     */
    public void generateRandomKeys(){
        Random myRandom;
        myRandom = new Random();
        for(int i=0; i<7;i++){
            //On a une longueur entre 3 et 20 caractères
            int lengthOfWord = myRandom.nextInt(17) + 3;
            String word = "";
            for(int j=0; j<lengthOfWord;j++){
                word += (char)(myRandom.nextInt(26)+'A');
            }
            this.clefArray.add(word);
        }
    }
    
    /**
     * Cette méthode se contente d'ajouter 7 mots dans le tableau de clefs.
     */
    public void generateKeys(){
        this.clefArray.add("AUTOMATIST");
        this.clefArray.add("ALERION");
        this.clefArray.add("CONVERSABLENESS");
        this.clefArray.add("DCMG");
        this.clefArray.add("MGR");
        this.clefArray.add("FLOATIEST");
        this.clefArray.add("CONTEMPLATED");
        this.clefArray.add("BOLE");
        this.clefArray.add("PRIMORDIALITY");
    }
    
    /**
     * Cette méthode retourne la clef contenue à l'indice numérique du jour de la semaine.
     * @return une clef en fonction du jour
     */
    public String getKeyOfTheDay(){
       ZonedDateTime date;
       date = ZonedDateTime.now();
       int today = date.getDayOfWeek().getValue();
       return clefArray.get(today-1);
    }
    
    @Override
    public String toString(){
        int cpt = 0;
        String out = "{";
        for(String clef : this.clefArray){
            out += String.valueOf(cpt) + ": "+clef+",\n";
            cpt++;
        }
        
        out = out.substring(0,out.length()-2);
        out+="\n}";
        return out;
    }
}
