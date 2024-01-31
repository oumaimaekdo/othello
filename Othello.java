package Othello;

import java.util.Scanner;

public class Othello {
    public static int Vide = 0;
    public static int Noir = 1;
    public static int Blanc = 2;

    /*
    En mettant le tableau en static cela va nous permettre de le réutilisé dans toutes les autres classes
    methode qui va remplir le tableau en début de partie avec les 4 pions deux à deux en diagonales au début et va retourner le plateau remplie.
     */

    public static int [][] rempliPlateau(){
        int [][] plateau = new int[8][8];
        System.out.println();
        for(int ligne = 0; ligne<plateau.length; ligne++){
            for(int colonne = 0; colonne< plateau[ligne].length; colonne++){
                plateau[ligne][colonne] = 0;
            }
        }

        plateau[3][3] = Blanc;
        plateau[3][4] = Noir;
        plateau[4][3] = Noir;
        plateau[4][4] = Blanc;
        return plateau;
    }

    /*
    Au debut notre plateau et le plateau retourner par la méthode rempliPlateau.
     */
    public static int[][] plateau;

    static {
        plateau = rempliPlateau();
    }

    /*methode qui va afficher le plateau rempli à chaque tour.
    cellule prend la valeur du tableau à cette case, puis affiche les émojis avec la variable cellule final.

     */

    public static int [][] afficherplateau(int [][] plateau){
        int cellule;
        String celluleFinal;
        int [][] plateauJeu = new int[plateau.length][plateau.length];
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println("    _   _   _   _   _   _   _   _");
        for (int ligne = 0; ligne < plateau.length; ligne++) {
            System.out.print(ligne+1);
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                cellule = plateau[ligne][colonne];

                if(cellule == Vide){
                    celluleFinal = ".";
                }
                else if(cellule == Noir){
                    celluleFinal = "⚪";
                }
                else {
                    celluleFinal = "⚫";
                }
                System.out.print(" | " + celluleFinal);
            }
            System.out.println(" | ");
        }
        System.out.println("    _   _   _   _   _   _   _   _");
        System.out.println();
        return plateauJeu;
    }



    /*
    Méthode ou le jeu va se dérouler
    retourne la valeur du gagnant.
     */
    public static int Debutjeu() {
        Scanner scanner = new Scanner(System.in);
        int tour,ligne,colonne,nombrePionBlanc,nombrePionNoire,coupInvalide,passeTour,cumulePasseTour,gagnant;
        String caseSelectionne;

        gagnant = 0;
        tour = Noir;
        nombrePionBlanc = 30;
        nombrePionNoire = 30;
        cumulePasseTour = 0;

        System.out.println("La partie va commencer !" + '\n' + "Bonne chance à vous ! ");
        System.out.println();

        /*
         tant que le plateau n'est pas entierement rempli ou que les deux joueurs ont au moins un pions sur le plateau le jeu continue
         */
        while (!Othello.finJeu(plateau,gagnant)) {

            coupInvalide = 0;
            passeTour = 0;

            /*
            si au bout de deux tour les joueurs n'ont pas passer leur tour à la suite on reset le cumule
             */

            Othello.afficherplateau(plateau);
            System.out.println();
            if (tour == Noir) {
                System.out.println("C'est le tour du joueur noir de jouer : ");
                System.out.println("Il vous reste :  " + nombrePionNoire + " pions " + '\n');
            } else {
                System.out.println("C'est le tour du joueur blanc de jouer : ");
                System.out.println("Il vous reste :  " + nombrePionBlanc + " pions " + '\n');
            }

            /* boucle qui va demander au joueur d'enter son coup
            si celui-ci est valide le pions va être placé sur le plateau.
            sinon on affiche un message d'erreur sur le plateau et on demande au joueur d'entrer un autre coup.
            Le joueur peut passer son tour en tapant "PT" ou abondonner en tapant FF, cela retournera le tour du joueur qui a abondonné.
            la valeur sera récupérer par le menue qui va appelé la méthode finDeJeu, afin d'afficher le joueur gagnant.
             */

            do{
                do {
                    System.out.println("Sur quelle case voulez-vous jouer ?" + '\n' + "Saissisez la colonne, puis la ligne sur laquelle vous souhaitez jouer :  " + '\n');
                    System.out.println("Taper 'PT' pour passer votre tour, 'FF' pour abondonner ou 'QQ' pour quitter la partie : " + '\n');
                    caseSelectionne = scanner.nextLine();
                    System.out.println();
                    if (coupInvalide > 0 || caseSelectionne.length() != 2) {
                        Othello.afficherplateau(plateau);
                        System.out.println();
                        System.out.println("Erreur : coup invalide, veuillez rejouer ! " + "\n");
                    }
                } while (caseSelectionne.length() != 2);



                /*si le joueur passe son tour on sort de la boucle, c'est le tour du joueur suivant, on utilise equals pour vérifier que la valeur entrer est bien PT ou pt.
                  si les deux joueurs ont passés leurs tour de suite, le cumule vaut 2  alors on retourne 3 et on affiche le gagnant.
                  si le joueur tape qq il quitte la partie et reviens au menu principale
                 */

                if(!(caseSelectionne.equals("PT")) && !(caseSelectionne.equals("pt")) ) {
                    cumulePasseTour = 0;
                    if ((caseSelectionne.equals("FF") || caseSelectionne.equals("ff"))) {
                        if (tour == Noir) {
                            return Blanc;
                        } else {
                            return Noir;
                        }
                    } else if ((caseSelectionne.equals("QQ") || caseSelectionne.equals("qq"))) {
                        return 5;
                    }
                }

                else if(caseSelectionne.equals("PT") || caseSelectionne.equals("pt")){
                    passeTour++;
                    cumulePasseTour++;
                    if (cumulePasseTour == 2){
                        return 3;
                    }
                    break;
                }

                /* extrait le premier caractère correspondant à la colonne entrer par le joueur
                prend le code ascii de la lettre et la soustrait par le code ascii de la lettre (a/A)
                Si le joueur entre un entier comme la valeur du code ascii de 0 est de 48 et augmente de 1 pour chaque nombre suivant, la valeur calculé ne sera jamais inférieur où égal à 8.
                 */

                colonne = Character.getNumericValue(caseSelectionne.charAt(0)) - 10;

                /*
                 Si le joueur entre un entier, comme la valeur du code ascii de 0 à 8 va de 48 à 56 .
                   pour avoir le coup on soustrait le code ascii de 8 par le nombre entrer, puis le total par 7 pour voir si il est bien inclus dans le plateau
                 */

                ligne = (56 - caseSelectionne.charAt(1)) - 7;


                // si la ligne joué est inférieur à zéro alors
                if (ligne < 0) {
                    ligne = -ligne;
                    coupInvalide++;
                }

                /*
                Dans le cas ou le joueur rentre une entre une valeur en dehors des zones du tableau
                 */

            }while (colonne<-1 || colonne >=8 || ligne<0 || ligne >=8 ||Othello.retournePions(tour, ligne,colonne,plateau) == 0);

            /*
            après avoir convertis le pions c'est le tour du joueur suivant de jouer
             */

            if (tour == Noir) {
                if(passeTour == 1){
                    tour = Blanc;
                }
                else {
                    nombrePionNoire--;
                    tour = Blanc;
                }
            }
            else {
                if(passeTour == 1){
                    tour = Noir;
                }
                else {
                    nombrePionBlanc--;
                    tour = Noir;
                }
            }
        }

        /*
         lorque le plateau est complétement remplie où qu'un joueur n'a plus de pièces sur le plateau, donc ne peut plus convertir de pièce.
           La partie est terminé.
         */

        System.out.println();
        return 0;
    }

    public static boolean finJeu(int [][] plateau,int gagnant) {
        boolean fin = false;
        int nbTotalPiece = 0;
        int totalPionBlanc = 0;
        int totalPionNoir = 0;

        for (int ligne = 0; ligne < plateau.length; ligne++) {
            for (int colonne = 0; colonne < plateau[ligne].length; colonne++) {
                if (plateau[ligne][colonne] == Noir) {
                    nbTotalPiece++;
                    totalPionNoir++;
                } else if (plateau[ligne][colonne] == Blanc) {
                    nbTotalPiece++;
                    totalPionBlanc++;
                }
            }
        }
        /* si le plateau est remplie il renvoie true et la partie s'arrete, affiche le joueur gagnant.
        si le gagnant à gagné par abondon alors on retourne vrai.
         si les deux joueurs ne peuvent plus joué alors on compte le nombre de pions sur le plateau et on affiche le vaiqueur.

         */

        if (gagnant == Noir || gagnant == Blanc || gagnant == 3 || gagnant == 5 || nbTotalPiece == 64 || totalPionNoir == 0 || totalPionBlanc == 0 ) {
            fin = true;

            Othello.afficherplateau(plateau);
            System.out.println();
            System.out.println("La partie est terminé !!!");
            System.out.println("Le joueur noir a fini la partie avec : " + totalPionNoir + " pièces sur le plateau");
            System.out.println("Le joueur blanc a fini la partie avec : " + totalPionBlanc + " pièces sur le plateau" + '\n');

            if (gagnant == Noir){
                System.out.println("Le joueur noir a gagné la partie sur abondon  du joueur blanc");
                return fin;
            }

            if (gagnant == Blanc){
                System.out.println("Le joueur blanc a gagné la partie sur abondon du joueur Noir");
                return fin;
            }

            if(totalPionNoir>totalPionBlanc){
                System.out.println(" Le joueur noir a donc gagné la partie avec un écart de : " + (totalPionNoir-totalPionBlanc) + " pions sur le joueur blanc");
            }
            else if(totalPionBlanc>totalPionNoir){
                System.out.println(" Le joueur blanc a donc gagné la partie avec un écart de : " + (totalPionBlanc-totalPionNoir) + " pions sur le joueur noir");
            }
            else {
                System.out.println("Vous avez tous les deux : " + totalPionNoir + " pièces sur le plateau" + '\n');
                System.out.println("C'est un match nulle !!!" + '\n');
            }
        }
        return fin;
    }
     /*
    Après que le coup du joueur a été validé par la fonction validationCoup, il faut vérfier que le pion joué converti bien un pion adverse avant de le posé.

    spécification : La méthode retournePions prend en paramètre le plateau, le tour du joueur, la ligne, colonne  sur lequelle le joueur souhaite jouer.
     Fait appel à la méthode direction qui renvoie le nombre de direction où le joueur peut convertir un pions adverse.
    Si le nombre de direction valide est strictement supérieur à zéro alors le joueur peut convertir au moins un pions, donc on place le pions.
     */

    public static int  retournePions(int tour, int ligne, int colonne,int [][] plateau) {

        int retournePions = 0;

        /*
         Au dessus : appel de la méthode direction afin de vérifier si il y'a un pion opposé qui est situé à la ligne du dessus mais sur la même colonne
         */
        if(direction(tour,ligne, colonne,  -1, 0,plateau)){
            retournePions++;
        };

        /*
          En dessous : vérifie si il y'a un pion opposé qui est situé à la ligne d'en dessus mais sur la même colonne
         */
        if(direction(tour,ligne, colonne,  1, 0,plateau)){
            retournePions++;
        };

        /*
        Colonne droite :  vérifie si il y'a un pion opposé qui est situé sur la même ligne mais sur la colonne de droite
         */
        if(direction(tour,ligne, colonne, 0,1,plateau)){
            retournePions++;
        }


        /*
        Colonne gauche :  vérifie si il y'a un pion opposé qui est situé sur la même ligne mais sur la colonne de gauche
         */
        if(direction(tour,ligne, colonne,  0, -1,plateau)){
            retournePions++;
        }

        /*
        Diagonale haut droit :  vérifie si il y'a un pion opposé qui est situé sur la ligne au dessus  et sur la colonne de droite (Diagonale haut droite)
         */
        if(direction(tour,ligne, colonne, -1,1,plateau)){
            retournePions++;
        }

        /*
         Diagonale haut Gauche : vérifie si il y'a un pion opposé qui est situé sur la ligne du dessus  et sur la colonne de gauche (Diagonale haut gauche)
         */
        if(direction(tour,ligne, colonne, -1,-1,plateau)){
            retournePions++;
        };

        /*
         Diagonale bas droite : vérifie si il y'a un pion opposé qui est situé sur la ligne en dessous  et sur la colonne de droite (Diagonale bas droite)
         */

        if(direction( tour,ligne, colonne,  1,1,plateau)){
            retournePions++;
        };

        /*
        Diagonale bas gauche :  vérifie si il y'a un pion opposé qui est situé sur la ligne en dessous  et sur la colonne de gauche (Diagonale bas gauche)
         */
        if(direction(tour,ligne, colonne, 1,-1,plateau)){
            retournePions++;
        };
        if (retournePions == 0) {
            Othello.afficherplateau(plateau);
            System.out.println("Erreur : coup invalide, veuillez rejouer ! " + "\n");
        }

        return retournePions;
    }



    /*
    Methode qui place le pion seulement si, celui-ci encapsule des pions de la couleur opposé  avec un pion de sa couleur.
    Si le pions joué n' encapsule pas un pion du joueur adverse  alors on place pas le pion et on demande au joueur de rejouer.
     */

    public static boolean direction(int colour,int ligne, int colonne, int directionLigne, int directionColonne,int [][] plateau)
    {

        int lignePionOppose= ligne + directionLigne;
        int colonnePionOppose = colonne + directionColonne;

        boolean placePions = false;

        /*
         Si le pion adjacent au pion posé et situé à l'extremité du tableau, on ne peut pas convertir le pions (on peut pas l'encapsuler).
         */

        if (lignePionOppose==8 || lignePionOppose<0 || colonnePionOppose==8 || colonnePionOppose<0) {
            return placePions;
        }

        /*
         tant que que la case adjacent aux pions du joueur dans cette direction  n'est pas vide et que le pions adjacent et de la couleur opposé ,alors on passe à la case suivante dans la direction demandé.
         si le pion rencontré est un pions de la même couleur que celle du joueur , alors on va allez dans le sens inverse afin de convertir tout les pions de la couleur opposé jusqu'au pions posé par le joueur.
        Mais si le pion rencontré dans cette direction et un pion de la même couleur alors ça ne sert à rien de continuer.
         */

        while (plateau[lignePionOppose][colonnePionOppose]!= 0) {

            /*
             vérifie si le pion qui encapsule dans cette direction et de la même couleur  que le pions posé
             et que le pion situé à coté du pion posé et de la couleur opposé.
             Si c'est vrai alors on part du pions qui encapsule jusqu'au pion posé et on convertit les pions entre les deux.
             */

            if (plateau[lignePionOppose][colonnePionOppose]==colour && plateau[ligne + directionLigne][colonne+directionColonne] != colour) {

                //tant que nous somme pas sur la case du pion posé par le joueur, on reviens en arrière et on convertis les pions de la couleur opposé.
                // condition du while : tant que la ligne et la colonne ou se trouve le pion posé par le joueur n'est pas atteint alors on continue de convertir les pions entre celle -ci.

                while(!(ligne==lignePionOppose && colonne==colonnePionOppose)) {
                    plateau[lignePionOppose][colonnePionOppose]=colour;
                    lignePionOppose=lignePionOppose-directionLigne;
                    colonnePionOppose=colonnePionOppose-directionColonne;
                }
                placePions = true;
                break;
            }

            /*
            si la couleur du pions adjacent et different alors on continue sur la même ligne ou/et sur la colonne de droite;
             cela dépend des paramètres de la direction de la ligne et de la colonne  appelé par la méthode.
            ex : on reste sur la même ligne donc DirLigne = 0, alors on restera toujours sur la même ligne;
            à l'inverse si Dircol = 0, alors on restera toujours sur la même colonne.
             */

            else {
                lignePionOppose = lignePionOppose + directionLigne;
                colonnePionOppose = colonnePionOppose + directionColonne;
            }
            /*
            dans le cas où à la case suivante on se situe hors du tableau
             */

            if (lignePionOppose<0 || colonnePionOppose<0 || lignePionOppose==8 || colonnePionOppose==8) {
                break;
            }
        }
        /*
         Si la valeur de placePion est vrai, cela veut dire que le pions posé par le joueur convertit au moins un pions adverse dans une direction, alors on le place sur le plateau.
         */

        if (placePions){
            plateau[ligne][colonne] = colour;
        }
        return placePions;
    }
}
