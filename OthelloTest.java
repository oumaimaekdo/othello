package Othello;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OthelloTest {


@Test
   public void retournePions() {


    //case vide = 0
    //case noir = 1
    //case blanche = 2

    int[][]   plateauConvertiNoire = {

   //        A  B  C  D  E  F  G  H
            {0, 2, 0, 0, 2, 0, 0, 2}, //  0
            {0, 0, 1, 0, 1, 0, 1, 0}, //  1
            {0, 0, 0, 1, 1, 1, 0, 0}, //  2
            {2, 1, 1, 1, 0, 1, 1, 2}, // 3
            {0, 0, 0, 1, 1, 1, 0, 0}, // 4
            {0, 0, 1, 0, 1, 0, 1, 0},  // 5
            {0, 1, 0, 0, 1, 0, 0, 2},  // 6
            {2, 0, 0, 0, 2, 0, 0, 0}}; // 7
    //       0  1  2  3  4  5  6  7

            /*
             dir {ligne,colonne ou on peut convertir :   {0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}
             */
            //cas ou le pion posé convertit dans toute les directions :
            assertEquals(8, Othello.retournePions(2, 3, 4, plateauConvertiNoire), " Coup en E3 cas : converti 8 pions ");

            // le pion blanc ne convertit aucun pion noire car pas adjacent
            assertEquals(0, Othello.retournePions(1, 3, 4, plateauConvertiNoire), " Coup en E3 cas : converti 0 pions ");

    int[][]   plateauConvertiBlanc = {

    //       A  B  C  D  E  F  G  H
            {0, 1, 0, 0, 1, 0, 0, 1}, //  0
            {0, 0, 2, 0, 2, 0, 2, 0}, //  1
            {0, 0, 0, 2, 2, 2, 0, 0}, //  2
            {1, 2, 2, 2, 0, 2, 2, 1}, // 3
            {0, 0, 0, 2, 2, 2, 0, 0}, // 4
            {0, 0, 2, 0, 2, 0, 2, 0},  // 5
            {0, 2, 0, 0, 2, 0, 0, 1},  // 6
            {1, 0, 0, 0, 1, 0, 0, 0}}; // 7
    //       0  1  2  3  4  5  6  7

    // cas pion blanc convertit dans toute les directions plus de deux pions :
        assertEquals(8, Othello.retournePions(1, 3, 4, plateauConvertiBlanc), " Coup en E3 cas : converti 8 pions ");

        // cas ou le pion noir ne convertit aucun pion blanc quelle que soit la direction
        assertEquals(0, Othello.retournePions(2, 3, 4, plateauConvertiNoire), " Coup en E3 cas : converti 0 pions ");


            int[][] ConvertitBlancExtremite = {

   //        A  B  C  D  E  F  G  H
            {1, 0, 0, 1, 0, 1, 2, 0}, //  0
            {0, 2, 1, 2, 2, 0, 2, 0}, //  1
            {1, 2, 2, 2, 2, 2, 2, 0}, //  2
            {0, 0, 2, 2, 2, 2, 2, 2}, // 3
            {1, 2, 1, 2, 2, 2, 0, 2}, // 4
            {0, 2, 2, 2, 2, 2, 2, 1},  // 5
            {0, 2, 2, 2, 2, 0, 2, 0},  // 6
            {1, 1, 1, 0, 1, 0, 0, 0}}; // 7
    //       0  1  2  3  4  5  6  7

    // direction : {1,1}, {1,0}, {-1,0}, {1,-1}, {-1,-1}, {0,-1},{0,1}, {-1,1}

    // convertit plus de 6 pions en diagonale en colonne et en ligne, et convertit dans deux directions en même temps :

    // cas {1,-1} et {0,-1}
    assertEquals(2, Othello.retournePions(1, 0, 7, ConvertitBlancExtremite), " Coup en H3 cas : converti les pions dans deux directions opposé");

    // cas {-1,-1} et {0,-1}
    assertEquals(1, Othello.retournePions(1, 1, 7, ConvertitBlancExtremite), " Coup en H1 cas : converti les pions dans deux directions opposé");

    // cas {0,-1} et {0,1}
    assertEquals(2, Othello.retournePions(1, 2, 7, ConvertitBlancExtremite), " Coup en H2 cas : converti les pions dans deux directions opposé");

    // cas {0,-1} et {0,1}
    assertEquals(2, Othello.retournePions(1, 5, 0, ConvertitBlancExtremite), " Coup en A5 cas : converti les pions dans deux directions opposé ");

    // cas {-1,-1]
    assertEquals(1, Othello.retournePions(1, 7, 7, ConvertitBlancExtremite), " Coup en H7 cas : converti les pions dans deux directions opposé");




    int[][] convertitNoirExtremite = {

            //        A  B  C  D  E  F  G  H
            {2, 0, 0, 2, 0, 2, 1, 0}, //  0
            {0, 1, 2, 1, 1, 0, 1, 0}, //  1
            {1, 1, 1, 1, 1, 1, 1, 0}, //  2
            {0, 0, 1, 1, 1, 1, 1, 1}, // 3
            {2, 1, 2, 1, 1, 1, 0, 1}, // 4
            {0, 1, 1, 1, 1, 1, 1, 2},  // 5
            {0, 1, 1, 1, 1, 0, 1, 0},  // 6
            {2, 2, 2, 0, 2, 0, 0, 0}}; // 7
    //       0  1  2  3  4  5  6  7

    // direction : {1,1}, {1,0}, {-1,0}, {1,-1}, {-1,-1}, {0,-1},{0,1}, {-1,1}


    // cas {1,-1} et {0,-1}
    assertEquals(2, Othello.retournePions(2, 0, 7, convertitNoirExtremite), " Coup en H3 cas : converti les pions dans deux directions opposé");

    // cas {-1,-1} et {0,-1}
    assertEquals(1, Othello.retournePions(2, 1, 7, convertitNoirExtremite), " Coup en H1 cas : converti les pions dans deux directions opposé");

    // cas {0,-1} et {0,1}
    assertEquals(2, Othello.retournePions(2, 2, 7, convertitNoirExtremite), " Coup en H2 cas : converti les pions dans deux directions opposé");

    // cas {0,-1} et {0,1}
    assertEquals(2, Othello.retournePions(2, 5, 0, convertitNoirExtremite), " Coup en A5 cas : converti les pions dans deux directions opposé ");

    // cas {-1,-1]
    assertEquals(1, Othello.retournePions(2, 7, 7, convertitNoirExtremite), " Coup en H7 cas : converti les pions dans deux directions opposé");

}


    @Test
    public final void TestFinJeu() {



        int [][] PlateauPasRemplie = {

             //  A B C D E F G H
                {0,0,0,0,0,0,0,0}, //  0
                {0,0,0,1,0,0,0,0}, //  1
                {0,2,1,2,2,0,0,0}, //  2
                {0,1,2,1,2,0,0,0}, // 3
                {0,2,1,2,1,1,1,0}, // 4
                {0,0,2,0,0,0,0,0},  // 5
                {0,0,1,0,0,0,0,0},  // 6
                {0,0,0,0,0,0,0,0}}; // 7
        //       0 1 2 3 4 5 6 7

        System.out.println();
        // cas ou les deux joueurs ont encore au moins un pions sur le plateau et le tableau n'est pas totalement rempli
        assertFalse(Othello.finJeu(PlateauPasRemplie,0), "la partie continue");
        System.out.println();

        // cas le joueur noir à abondonner :
        assertTrue(Othello.finJeu(PlateauPasRemplie,1), "la partie s'arrête  et affiche joueur blanc gagnant ");
        System.out.println();

        // cas le joueur blanc à abondonner :
        assertTrue(Othello.finJeu(PlateauPasRemplie,2), "la partie s'arrête  et affiche joueur noir gagnant ");
        System.out.println();

        // cas ou un joueur quitte la partie :
        assertTrue(Othello.finJeu(PlateauPasRemplie,5), "la partie s'arrête  et affiche le menue principale  ");
        System.out.println();

        //cas ou un joueur ou les deux joueur passe leur tour :

        assertTrue(Othello.finJeu(PlateauPasRemplie,3), "la partie s'arrête  et affiche le nom du gagnant");
        System.out.println();

        int[][] victoireJoueurNoir = {

             //  A  B  C  D  E  F  G  H
                {0, 0, 0, 0, 0, 0, 0, 0}, //  0
                {0, 0, 0, 0, 1, 0, 0, 0}, //  1
                {0, 0, 1, 1, 1, 1, 0, 0}, //  2
                {0, 1, 1, 1, 1, 1, 0, 0}, // 3
                {0, 0, 0, 0, 1, 0, 1, 0}, // 4
                {0, 0, 1, 0, 0, 1, 0, 0},  // 5
                {0, 0, 0, 1, 1, 1, 0, 0},  // 6
                {0, 0, 0, 0, 0, 1, 0, 0}}; // 7
        //       0  1  2  3  4  5  6  7


        // cas ou la partie s'arrête avant que le plateau ne soit totalement rempli : il n'y'a plus de pions noir sur le plateau, le joueur noir ne peut donc plus convertir de pions en jouant
        // La partie est terminé, le programme s'arrête et la méthode renvoie truen le joueur blanc à donc gagné

        assertTrue(Othello.finJeu(victoireJoueurNoir,0), "cas ou le joueur noir ne peut plus joué, le joueur blanc à gagné ");
        System.out.println();


        int[][] victoireJoueurBlanc = {

            //   A  B  C  D  E  F  G  H
                {0, 0, 0, 0, 0, 0, 0, 0}, //  0
                {0, 0, 0, 0, 2, 0, 0, 0}, //  1
                {0, 0, 2, 2, 2, 2, 0, 0}, //  2
                {0, 2, 2, 2, 2, 2, 0, 0}, // 3
                {0, 0, 0, 0, 2, 0, 2, 0}, // 4
                {0, 0, 2, 0, 0, 2, 0, 0},  // 5
                {0, 0, 0, 2, 2, 2, 0, 0},  // 6
                {0, 0, 0, 0, 0, 2, 0, 0}}; // 7
        //       0  1  2  3  4  5  6  7


        // cas ou la partie s'arrête avant que le plateau ne soit totalement rempli : il n'y'a plus de pions Blanc sur le plateau, le joueur Blanc ne peut donc plus convertir de pions en jouant
        // La partie est terminé, le programme s'arrête et la méthode renvoie true, le joueur noir à donc gagné

        assertTrue(Othello.finJeu(victoireJoueurBlanc,0), "cas ou le joueur blanc ne peut plus joué, le joueur noir à gagné ");


        int[][] plateauRempli = {

         //      A  B  C  D  E  F  G  H
                {1, 1, 1, 1, 1, 1, 1, 1}, //  0
                {1, 1, 1, 1, 1, 1, 1, 1}, //  1
                {1, 1, 1, 1, 1, 1, 2, 2}, //  2
                {1, 1, 1, 1, 1, 1, 1, 1}, // 3
                {1, 1, 1, 1, 1, 1, 2, 1}, // 4
                {1, 1, 1, 1, 1, 1, 1, 1},  // 5
                {1, 1, 1, 1, 1, 1, 1, 1},  // 6
                {1, 1, 1, 1, 1, 1, 1, 1}}; // 7
        //       0  1  2  3  4  5  6  7

        //cas ou la partie s'arrête car le plateau est  totalement rempli : les joueurs ne peuvent plus joué
        //La partie est terminé, le programme s'arrête et la méthode renvoie true et affiche le joueur gagnant

        assertTrue(Othello.finJeu(plateauRempli,0), "cas ou le plateau est totalement rempli et le joueur noir à gagné");
        System.out.println();


        int[][] tabMatchNulle = {

        //       A  B  C  D  E  F  G  H
                {1, 1, 1, 1, 2, 2, 2, 2}, //  0
                {1, 1, 1, 1, 2, 2, 2, 2}, //  1
                {1, 1, 1, 1, 2, 2, 2, 2}, //  2
                {1, 1, 1, 1, 2, 2, 2, 2}, // 3
                {1, 1, 1, 1, 2, 2, 2, 2}, // 4
                {1, 1, 1, 1, 2, 2, 2, 2},  // 5
                {1, 1, 1, 1, 2, 2, 2, 2},  // 6
                {1, 1, 1, 1, 2, 2, 2, 2}}; // 7
        //       0  1  2  3  4  5  6  7

        // cas ou le tableau est totalement rempli et qu'il y'a autant de pions noire que de pions blanc, c'est alors un match nulle.

        assertTrue(Othello.finJeu(tabMatchNulle,0),"Match nulle aucune des joueur n'a gagné");
        System.out.println();

    }

}
