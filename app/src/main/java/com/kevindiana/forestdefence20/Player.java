package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 2/19/2017.
 */

public class Player {

    private int health;
    private int money;

    public Player(){
        health = 2000;
        money = 2000;

    }

    public int GetHealth(){
        return health;
    }
    public void SetHealth(int damage){

        health -= damage;
    }
    public int GetMoney(){
        return money;
    }
    public void AddMoney(int income){
        money += income;
    }
    public void SubMoney(int purchase){
        money -= purchase;
    }


}
