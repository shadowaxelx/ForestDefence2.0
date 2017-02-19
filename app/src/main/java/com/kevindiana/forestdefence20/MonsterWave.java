package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 1/4/2017.
 */

public class MonsterWave extends GameObject {

    // each array contains the wave so the first array is round 1
    private int[][] monsterroom1 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 13},
            //{1, 1, 1, 13, 1, 13, 1, 13, 1, 13}
    };
    public int [][] getwave(int roomnum){
        switch(roomnum){
            case 1:
                return monsterroom1;
        }

        return monsterroom1;
    }
}

