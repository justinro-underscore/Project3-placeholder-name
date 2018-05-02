package com.base.game.gameobject.entity;

import com.base.game.Game;
import com.base.game.gameobject.projectile.HeatSeekingProjectile;
import com.base.game.gameobject.projectile.StandardProjectile;
import com.base.game.scenes.Scene;
import com.base.game.utilities.Delay;

import java.util.ArrayList;

public class Boss448 extends Boss {
    // Different attack delays
    private Delay lRwallAttackDelay;
    private Delay rLwallAttackDelay;
    private Delay burstAttackDelay;
    private Delay fourCornersDelay;
    private Delay heatSeekingDelay;

    private boolean isInitialAttack;

    /**
     * Creates a new Boss168
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    public Boss448(float xPos, float yPos, int width, int height, String imgPath, float speed, int health, int attackDamage) {
        super(xPos, yPos, width, height, imgPath, speed, health, attackDamage);

        standardProjectiles = new ArrayList<>(); // Initalizes the list
        heatSeekingProjectiles = new ArrayList<>();

        //Delay between attacks from the Boss
        lRwallAttackDelay = new Delay(6000);
        rLwallAttackDelay = new Delay(7000);
        burstAttackDelay = new Delay(8000);
        fourCornersDelay = new Delay(5000);
        heatSeekingDelay = new Delay(15000);

        isInitialAttack = true;

        //Initiates the Attacks from the boss
        lRwallAttackDelay.start();
        rLwallAttackDelay.start();
        burstAttackDelay.start();
        fourCornersDelay.start();
    }

    @Override
    /**
     * Runs the attack pattern
     */
    public void attack() {
        if(isInitialAttack){
            heatSeekingDelay.start();
            isInitialAttack = false;
        }
        if(lRwallAttackDelay.isOver()){ // Wall attack
            leftOrRightWallOfFireAbility(8,8,3, 4, 3, true);
            lRwallAttackDelay.start();
        }
        if(rLwallAttackDelay.isOver()){ // Wall attack
            leftOrRightWallOfFireAbility(8,8,4, 4, 3, false);
            rLwallAttackDelay.start();
        }
        if(burstAttackDelay.isOver()) { // Burst attack
            if(Math.random() * 2 > 1){
                shootAbility(10,10,10, 3, 3);
            }
            burstAttackDelay.start();
        }
        if(heatSeekingDelay.isOver()){ // Wall attack
            heatSeekingAbility(40,40,5, 10);
            heatSeekingDelay.start();
        }
        if(fourCornersDelay.isOver()){
            fourCornersAbility(15,15, 10, 3);
            fourCornersDelay.start();
        }
    }
}
