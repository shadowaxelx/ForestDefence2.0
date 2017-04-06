package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 2/19/2017.
 */

public class Player {

    private int m_health;
    private int m_money;

    public Player(){
        m_health = 20;
        m_money = 20;

    }

    public int GetHealth(){
        return m_health;
    }
    public void SetHealth(int damage){

        m_health -= damage;
    }
    public int GetMoney(){
        return m_money;
    }
    public void AddMoney(int income){
        m_money += income;
    }
    public void SubMoney(int purchase){
        m_money -= purchase;
    }


}
