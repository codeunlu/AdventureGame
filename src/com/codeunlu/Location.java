package com.codeunlu;

import java.util.Scanner;

public abstract class Location {
    private Player player;
    private String name;
    public static Scanner input = new Scanner(System.in);


    public Location(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    public boolean awardStatus(){
        if(this.getPlayer().getInventory().isFood() &&
                this.getPlayer().getInventory().isFirewood() &&
                this.getPlayer().getInventory().isWater()
        ){
            return true;
        }else{
            return false;
        }
    }
    public boolean getAwardStatus(String award){
        if("food".equals(award)){
            return this.getPlayer().getInventory().isFood();
        }else if("firewood".equals(award)){
            return this.getPlayer().getInventory().isFirewood();
        }else{
            return this.getPlayer().getInventory().isWater();
        }
    }

    public abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
