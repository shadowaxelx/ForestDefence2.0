package com.kevindiana.forestdefence20;

/**
 * Created by kevin on 2/19/2017.
 */

/**
 class  Player

 NAME

 Player

 SYNOPSIS
 class Player
    int m_health -> amount of health player has
    int m_money -> the amounst of money the player has


 DESCRIPTION

    Creates everything the player will have which is health and money
    which will be displayed on the bottom right corner of the map

 RETURNS

 NA

 AUTHOR

 Kevin Diana

 DATE

 8:00Pm 2/19/2017

 */
public class Player {

    private int m_health;
    private int m_money;

    // Constructor setting health and money
    public Player(){
        m_health = 20;
        m_money = 20;

    }

    // returns the amount of health player has
    public int GetHealth(){
        return m_health;
    }
    // sets new player health if they take damage
    public void SetHealth(int damage){

        m_health -= damage;
    }
    // return amount of money player has
    public int GetMoney(){
        return m_money;
    }
    // add money to the player when monster is killed
    public void AddMoney(int income){
        m_money += income;
    }
    // sub money from player when towers are bought or upgraded
    public void SubMoney(int purchase){
        m_money -= purchase;
    }

}
