package Othello;
import java.util.Scanner;
import static Othello.Othello.*;

public class Menu {
    public static void Start() {
        Scanner scanner = new Scanner(System.in);
        int choixConvertie,gagnant;
        String choixMenu;

      /*
        tant que le joueur n'entre pas une valeur comprise entre plus de 1 caractère on lui demande de saisir une valeur entre 1 et 3
        tant que le joueur entre une valeur qui n'est pas un entier on lui demande saisir une valeur correcte :
         pour le vérifier on recupère le code ascii du caractère entrée et on le soustrait par la valeur 0 en code asci soit 48 (1 = 49, 2 = 50 , 3 = 51)
         ex le joueur entre 1

         choixMenu = 1  code asci = 49
         choixConvertit = 49 - 48 = 1
         la condition du premier while est bien validé donc on lance le jeu

         */

            do {
                do {
                    System.out.println();
                    System.out.println("Bienvuenue sur le jeu Othello" + '\n');
                    System.out.println("1. Jouer ");
                    System.out.println("2. Règle du jeu");
                    System.out.println("3. Quitter\n");
                    System.out.println("saisir le nombre correspondant à votre choix d'opérations : " + "\n");
                    choixMenu = scanner.nextLine();
                    System.out.println("\n");
                } while (choixMenu.length() != 1);
                choixConvertie = (choixMenu.charAt(0) - 48);
            } while (choixConvertie != 3 && choixConvertie != 2 && choixConvertie != 1);


            /*on lance le jeu
            si un des joueurs abandonne alors on arrête le jeu et on fait appel à la méthode finJeu qui va afficher le gagnant
            si un des joueur quitte le jeu alors on affiche le menu
             */

            switch (choixConvertie) {
                case 1:
                    gagnant = Othello.Debutjeu();
                    if (gagnant == Blanc|| gagnant == Noir || gagnant == 3 || gagnant == 5) {
                        Othello.finJeu(plateau, gagnant);
                    }
                    break;

                case 2:
                    int reponseJoueur;

                    /* si le joueur veut retourner au menu du jeu il doit taper 1 sur la console
                    /même méthode que pour le menu principale avec la réponse du joueur
                     si il tape plus de 1 caractère on lui affiche les règles
                     si il tape un entier pas égal à 1 on lui affiche les règles
                    si il tape 1 on le renvoie au menu principle
                     */

                    do {
                        System.out.println("1/Les règles du jeu Othello:" + '\n');
                        System.out.println("- Nombre de joueurs : 2"
                                + "\n" + "- Plateau de 64 cases : 64 pions recto verso (face blanche , face noir) tableau 8**8"
                                + "\n" + "- 32 pions pour chaque joueur, le joueur noir commence" + "\n");

                        System.out.println("2/ Principe du jeu :" + "\n");
                        System.out.println("Le joueur doit encadrer un pion adverse entre deux de ses pions  afin de le convertir dans sa couleur."
                                + "\n" + "Si deux pions encapsulent toute une ligne alors les pions situés entre ces deux pions prennent la couleur du joueur."
                                + "\n" + "Si un pion est posé dans une case qui peut potentiellement convertir plusieurs pions à la fois, alors il les convertis tous (Faites attention à cette éventualité !)"
                                + "\n" + "Lorsque une ligne contient deux pions du joueur et qu'il encapsule cette ligne, alors le pion pris en compte pour l’encapsulation et le premier pion situé après les pion adverse."
                                + "\n" + "Le pion posé doit obligatoirement convertir au moins un pion adverse sur le plateau." + "\n");

                        System.out.println("3/ Début de partie : " + "\n");
                        Othello.afficherplateau(plateau);
                        System.out.println("On place 4 pions au centre du plateau par défaut : 2 pions blanc en diagonales et deux noires en diagonales. A chaque tour le joueur pose un pions" + '\n');

                        System.out.println("4/ fin de partie : " + "\n");

                        System.out.println("Lorsque le plateau est entièrement rempli, on fait le comptage des couleurs. " + "\n" + "condition victoire : lorsque le plateau de jeu est rempli , le joueur ayant le plus de pions sur le plateau à gagner."
                                + "\n" + " Fin de partie 1 : compter le nombre de pions de chaque joueur, le joueur ayant le plus de pions à gagner."
                                + "\n" + "- Si Il ne reste que des pions d’une seule couleur sur le plateau, alors le joueur de cette couleur à gagné." + "\n");

                        System.out.println("Si vous voulez retourner au menu du jeu taper 1 : " + '\n');
                        choixMenu = scanner.nextLine();
                        reponseJoueur = (choixMenu.charAt(0) - 48);
                        System.out.println();
                    } while (choixMenu.length() != 1 || reponseJoueur != 1);

                    Start();
                    break;

                case 3:
                    System.out.println("Au revoir !" + "\n");
                    break;
            }
        }

    }

