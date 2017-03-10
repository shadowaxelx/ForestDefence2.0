package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 1/4/2017.
 */

public class MonsterWave extends GameObject {

    // each array contains the wave so the first array is round 1
    private int[][] monsterroom1 = new int[][]{
            //{4},
            {1, 1, 1, 1, 1, 1, 1, 1},
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
            {1, 1, 1, 1, 1, 13, 13, 13, 13, 13, 13, 10, 10, 10, 10, 3, 3, 3, 3}


    };
    public int [][] getwave(int roomnum){
        switch(roomnum){
            case 1:
                return monsterroom1;
        }

        return monsterroom1;
    }
}

