package com.kevindiana.forestdefence20;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kevin on 1/4/2017.
 */

/**
 class MonsterWave

 NAME

 MonsterWave

 SYNOPSIS
    class MonsterWave

        int [][] monsterroom11 -> 2D array containing the monster wave for easy difficulty
        int [][] monsterroom12 -> 2D array containing the monster wave for normal difficulty
        int [][] monsterroom13 -> 2D array containing the monster wave for hard difficulty
        int [][] monsterroom14 -> 2D array containing the monster wave for infinite difficulty

 DESCRIPTION

 Creates the monsters, gives them all of their stats, keeps them on the correct path, and animates them

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 4:00Pm 1/4/2016

 */
public class MonsterWave extends GameObject {

    // each array contains the wave so the first array is round 1
    // numbers 1 - 13 correspond with the monster from the Monster class
    // 0 means empty space(spawn nothing)
    // 14 means after all the monsters in the array are gone the game is over

    // easy
    private int[][] monsterroom11 = new int[][]{

            //{10},

            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2},
            {1, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1},
            {3, 3, 2, 2, 3, 1, 1, 1, 3, 3, 2, 3, 3, 3},
            {4, 4, 3, 1, 1, 4, 1, 3, 4, 3, 3, 3, 4, 4},
            {5, 0, 0, 3, 0, 3, 3, 2, 2, 5, 4, 2, 4, 2, 4},
            {4, 0, 4, 0, 0, 7, 4, 3, 3, 3, 7, 2, 3, 3},
            {4, 8, 0, 0, 7, 8, 0, 0, 0, 4, 5, 4, 0, 0 , 6},
            {8, 9, 0, 0, 4, 4, 7, 0, 7, 3, 3, 3, 8, 0, 0, 0, 6, 7, 0, 0, 3, 3, 3},
            {10, 0, 0, 0, 9, 4, 0, 0, 0, 7, 4, 9, 7, 10},
            {11, 8, 10, 11, 6, 4, 4, 11, 12, 8, 8, 7},
            {12, 0, 0, 12, 11, 0, 0, 12, 11, 0, 0, 12, 12},
            {1, 1, 6, 2, 3, 2, 3, 4, 5, 7, 7, 8, 9, 10, 11, 9, 12},
            {13, 0, 0, 0, 12, 0, 0, 0, 10, 10, 10, 10, 10, 10, 14}


    };

    // normal
    private int[][] monsterroom12 = new int[][]{

            {1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1},
            {2, 2, 2, 3, 3, 2, 2, 2, 3, 3, 3, 2, 2, 2, 3, 3, 3, 2, 2, 3},
            {2, 2, 3, 3, 4, 4, 3, 3, 3, 4, 2, 2, 4, 2, 2, 4, 2, 2, 4, 0, 0, 0, 0, 3},
            {2, 2, 4, 4, 3, 3, 5, 2, 2, 2, 3, 6, 5, 2, 3, 4, 4, 5, 3, 3},
            {4, 4, 4, 3, 3, 3, 5, 5, 2, 2, 2, 7, 5, 4, 4, 4, 7, 2, 3, 3},
            {5, 4, 4, 7, 7, 4, 4, 8, 7, 5, 5, 8, 8, 5, 7, 5, 5, 8, 7, 7},
            {7, 7, 5, 6, 7, 8, 9, 9, 4, 4, 9, 9, 5, 7, 7, 7, 7, 7, 9, 8},
            {10, 7, 7, 7, 7, 8, 8, 10, 9, 9, 7, 7, 7, 9, 10, 7, 7, 8, 10},
            {9, 10, 11, 8, 7, 7, 7, 11, 9, 10, 10, 11, 8, 8, 9, 9, 9, 10},
            {11, 11, 11, 12, 10, 10, 10, 8, 7, 7, 7, 11, 12, 9, 10, 10, 12},
            {1, 1, 3, 3, 9, 9, 10, 10, 8, 8, 11, 11, 12, 12, 9, 10, 10, 10, 13},
            {13, 13, 13, 13, 9, 13, 9, 10, 13, 10, 13, 9, 9, 9, 9, 10, 10, 10, 9, 14}

    };

    // hard
    private int[][] monsterroom13 = new int[][]{

            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1 ,2, 2,},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6},
            {4, 4, 4, 4, 4, 3, 3, 4, 4, 3, 3, 4, 3, 2, 4, 2},
            {5, 4, 4, 2, 5, 5, 2, 5, 4, 2, 2, 2, 2},
            {7, 7, 7, 0, 0, 0, 0, 3, 3, 3, 3, 7, 4, 7, 4, 7, 0, 0, 6, 5, 7, 0, 0, 0, 3, 3, 3},
            {5, 0, 0, 5, 3, 8, 7, 8, 8, 4, 4, 7, 3, 8, 7, 7, 7,  0, 0, 0, 0, 3, 3, 3},
            {8, 8, 8, 9, 3, 4, 9, 9, 3, 4, 7, 8, 6, 7, 3, 3, 3, 3},
            {10, 10, 9, 7, 7, 6, 7, 9, 8, 9, 10, 9, 7, 10},
            {11, 7, 7, 10, 11, 6, 4, 4, 11, 12, 10, 10, 10},
            {12, 12, 11, 11, 12, 11, 12, 11, 12, 12 ,0, 0, 0, 0, 0, 0, 10, 9, 10, 9, 10},
            {1, 1, 6, 2, 3, 2, 3, 4, 5, 7, 7, 8, 9, 10, 10, 11, 9, 12, 13, 13, 13, 11, 11, 11, 10,
                    10, 10, 10},
            {13, 13, 13, 13, 13, 13,  0, 0, 0, 0, 0, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    10, 10, 10, 10, 14}

    };

    private int[][] monsterroom14 = new int[][]{

            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {5, 5, 4, 4, 5, 5, 4, 5, 5, 4, 4, 4, 5, 4, 5, 5, 5},
            {5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6},
            {7, 7, 7, 7, 7, 7, 7, 7, 7},
            {8, 8, 8, 8, 8, 8, 8, 8, 8},
            {9, 9, 9, 9, 9, 9, 9, 9, 9},
            {10, 10, 10, 10, 10, 10, 10},
            {11, 11, 11, 11, 11, 11, 11},
            {12, 12, 12, 12, 12, 12, 12},

            {13, 13, 13, 13, 13, 13},


    };


    /**
     public int [][] infinite()

     NAME

     infinite

     SYNOPSIS
     public int [][] infinite()

     DESCRIPTION

        keeps generating new monster waves randomly for infinite mode

     RETURNS

     NA

     AUTHOR

     Kevin Diana

     DATE

     4:00Pm 1/4/2016

     */
    public int [][] infinite(){


            monsterroom12 = new int [1][20];

            for(int i = 0; i < 20; i++){
                Random rn = new Random();
                int monster = rn.nextInt(13) + 1;

                monsterroom12[0][i] = monster;
            }

        return monsterroom12;
    }

    // retuns wave for difficulty selected
    public int [][] getwave(int a_difficulty){
        switch(a_difficulty){
            // easy
            case 11:
                return monsterroom11;

            // normal
            case 12:
                return monsterroom12;

            // hard
            case 13:
                return monsterroom13;

            // infinite
            case 14:
                return monsterroom14;
        }

        return monsterroom12;
    }
}

