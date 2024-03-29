package com.mycompany.tele;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Application
{  
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

     
        
    public static void main( String[] args )          // Programme principal. 
    {
        
                                  // déclaration des tableaux 
        ProgrammeMere Tabprog[] = new ProgrammeMere [50];       
        int Grilletele[] = new int [24];                        // tableau "Grilletele" de 24 valeurs d'entiers
        String Tabnom[] = new String [50];                      // tableau "tabnom" de 50 valeurs de String
        
        
                      
        logger.info("Demarrage");
        System.out.println( "Bienvenue, voici la liste des programmes :" );
        
        ArrayList<String> programmeList = new ArrayList();
        
// ---------------------------------------- Zone d'ajout des programmes ------------------------------------------------
        
        programmeList.add("fiction, 21, 2, Jurrasic World, 2001, Steven Spielberg, oui");
        programmeList.add("divertissement, 12, toto, titi");
        programmeList.add("reportage, 7, 3, Enquête d'action, culturel");
        programmeList.add("reportage, 2, 3, Au coeur d'une friterie, monde");
        programmeList.add("FICTION, 22, 2, Interstellar, 2015, Christopher Nolan, OUI");
        programmeList.add("divertiSSement, 12, Vendreditoutestpermis, Arthur");
        
        logger.info ("Programmes ajoutes");
        
        
        
       for (int i = 0; i< Grilletele.length;i++) { // Boucle initialisant le tableau d'heure à 0, 0 = horraire disponible.
           Grilletele[i]=0; 
       } 
       logger.info("Initialisation de la Grille contenant les programme a 0");
       
       logger.info("Creation des constructeurs par type des programmes");
        for (int i = 0; i < programmeList.size(); i++ ) {                                         // Pour tout les programmes ajoutés précedemment, on les trie
            
            String[] TSplitted = programmeList.get(i).split(",");
            if (TSplitted[0].equalsIgnoreCase("Divertissement")) {              // si le premier champ est équivalent à "Divertissement", alors on ajoute ce programme comme étant un divertissement dans le tableau des programmes. 
               Tabprog[i]= new Divertissement("Divertissement", Integer.parseInt(TSplitted[1].trim()),TSplitted[2],TSplitted[3]); // Création de l'objet divertissement
               logger.trace("Un objet divertissement vient d'etre cree");
               for (int j = Tabprog[i].heure; j < Tabprog[i].heure+Tabprog[i].duree; j++) {
                   Tabnom[j]= Tabprog[i].nom; // On sauvegarde le nom pour permettre ensuite un meilleur affichage
                   logger.trace("Un objet divertissement est cree et rempli son creneau");
                   Grilletele[j]=Grilletele[j]+1;                               // en fonction de la durée du programme diffusé, on réserve les cases suivantes du tableau en fonction de la durée pour y afficher le programme.
               }
               }
            logger.info("Les programmes de type Divertissement on été ajoutes");
               
            
            
            
            if (TSplitted[0].equalsIgnoreCase("Fiction")) {                     // si le premier champ est équivalent à "Fiction", alors on ajoute ce programme comme étant une fiction dans le tableau des programmes. 
                Tabprog[i]=new Fiction("Fiction", Integer.parseInt(TSplitted[1].trim()), Short.parseShort(TSplitted[2].trim()),TSplitted[3], Short.parseShort(TSplitted[4].trim()), TSplitted[5], Boolean.parseBoolean(TSplitted[6].trim()));
                logger.trace("Un objet Fiction vient d'etre cree");
                for (int j = Tabprog[i].heure; j < Tabprog[i].heure+Tabprog[i].duree; j++) {
                    Tabnom[j]= Tabprog[i].nom;
                    logger.trace("Un objet Fiction est cree et rempli son creneau horaire");
                   Grilletele[j]=Grilletele[j]+1;
            }
                }
            logger.info("Les programmes de type Fiction ont été ajoutes");
            
            if (TSplitted[0].equalsIgnoreCase("Reportage")) {                   // si le premier champ est équivalent à "Reportage", alors on ajoute ce programme comme étant un reportage dans le tableau des programmes. 
                Tabprog[i]=new Reportage("Reportage", Integer.parseInt(TSplitted[1].trim()), Short.parseShort(TSplitted[2].trim()),TSplitted[3], TSplitted[4]);
                logger.trace("Un objet reportage vient d'etre cree");
                for (int j = Tabprog[i].heure; j < Tabprog[i].heure+Tabprog[i].duree; j++) {                 
                   Tabnom[j]= Tabprog[i].nom;
                   Grilletele[j]=Grilletele[j]+1;
                   logger.trace("Un objet reportage est cree et rempli son creneau horaire"); // en fonction de la durée du programme diffusé, on réserve les cases suivantes du tableau en fonction de la durée pour y afficher le programme.
                        }   // la méthode .trim() permet, entre deux champs, de ne pas prendre en compte les espaces. 
                             }
            
            logger.info("Les programmes de type Reportage on été ajoutes");
            
             // A ce stade, le tableau d'heure s'est remplit à 1 pour les programmes et parfois à 2 si 2 programmes se superposent, si 4, et bien 4 programmes se superposent.
            
        
        }
        
        
        
            
        
            
        
        System.out.println("Voici le programme TV de la journée :");
        logger.info("Calcul des programmes, verfication des creneaux horaires et des superpositions");
        for (int y = 0; y< Grilletele.length;y++) {            // On parcours la grille d'horraire télé pour afficher le programme TV de la journée et afficher l'erreur de superposition dès qu'elle est detectée
            
            if (Tabnom[y] == null) { // L'emplacement est vide, donc il n'y a pas de programme à cette heure
                System.out.println(" Ce créneau est vide " + y + "h " + Grilletele[y]);
                logger.trace("Aucun programme sur ce créneau");
            }
            else if (Tabnom[y] != null) { // L'emplacement contient bien un nom de programme, donc il y'a une diffusionà

            System.out.println(Tabnom[y] +" Est diffusé dans le créneau " + y + "h " + Grilletele[y]);
  
            }
            
            if (Grilletele[y] >= 2) { // Si plusieurs programme ont mis leurs réservations sur cette heure alors il y'a conflit et donc superposition donc -> error
                System.out.println("SUPERPOSITION DETECTED ERROR : Il y'a une superposition de programme !"); 
                logger.trace("Superposition de programmes");
            }
        
        }
        
        logger.info("Arret du programme");  
    }
}

                // Après vérification de l'énoncé, seul les programmes de types divertissement et fiction peuvent se croiser
             



