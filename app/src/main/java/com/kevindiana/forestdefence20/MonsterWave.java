package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 1/4/2017.
 */

public class MonsterWave extends GameObject {

    // each array contains the wave so the first array is round 1
    // numbers 1 - 13 correspond with the monster from the Monster class
    // 0 means empty space(spawn nothing)
    // 14 means after all the monsters in the array are gone the game is over

    // map 1 easy
    private int[][] monsterroom11 = new int[][]{

            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 2, 2, 2, 2, 2},
            {3, 3, 2, 2, 3, 3, 3, 2},
            {4, 4, 4, 3, 3, 4, 3, 2},
            {5, 3, 3, 1, 1, 5, 4, 2},
            {4, 4, 7, 4, 7, 2, 3, 3},
            {4, 8, 7, 8, 4, 5, 4, 0, 0 , 6},
            {8, 9, 0, 0, 3, 3, 7, 0, 7, 8, 0, 0, 0,6, 7},
            {10, 9, 4, 7, 4, 9, 7, 10},
            {11, 8, 10, 11, 6, 4, 4, 11, 12, 8, 8, 7},
            {12, 0, 0, 12, 11, 0, 0, 12, 11, 0, 0, 12, 12},
            {1, 1, 6, 2, 3, 2, 3, 4, 5, 7, 7, 8, 9, 10, 11, 9, 12},
            {13, 0, 0, 0, 12, 0, 0, 0, 10, 10, 10, 10, 10, 10, 14  }

    };

    // map 1 normal
    private int[][] monsterroom12 = new int[][]{
            {5},
            {5},
            {5},
            {5},

            /*{1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6},
            {4, 4, 4, 3, 3, 4, 3, 2, 4, 2},
            {5, 4, 4, 2, 2, 5, 4, 2, 2, 2, 2},
            {4, 4, 7, 4, 7, 4, 4, 6, 5, 7},
            {5, 8, 7, 8, 8, 3, 2, 3, 2},
            {8, 9, 2, 2, 9, 2, 2, 7, 8, 6, 7},
            {10, 10, 9, 4, 4, 6, 4, 9, 7, 10},
            {11, 7, 7, 10, 11, 6, 4, 4, 11, 12, 10, 10, 10},
            {12, 12, 11, 11, 12, 11, 12, 11, 12, 12},
            {1, 1, 6, 2, 3, 2, 3, 4, 5, 7, 7, 8, 9, 10, 10, 11, 9, 12, 13},
            {1, 1, 1, 1, 1, 13, 13, 13, 13, 13, 13, 10, 10, 10, 10, 3, 3, 3, 3, 14}
            */


    };
    public int [][] getwave(int roomnum){
        switch(roomnum){
            case 11:
                return monsterroom11;

            case 12:
                return monsterroom12;
        }

        return monsterroom12;
    }
}

