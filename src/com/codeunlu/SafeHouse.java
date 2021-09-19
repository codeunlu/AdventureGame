package com.codeunlu;

public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation(){
        System.out.println("Güvenli evdesiniz !");
        System.out.println("Canınız yenilendi !");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        System.out.println("Envanterinizde ki Ödüller");
        String[] awards = {
                this.getPlayer().getInventory().isFood() == true ? "Yemek" : "",
                this.getPlayer().getInventory().isFirewood() == true ? "Odun" : "",
                this.getPlayer().getInventory().isWater() == true ? "Su" : ""
        };
        for (String award : awards){
            System.out.println(award);
        }
        if(awardStatus()){
            System.out.println("Oyunu kazandınız!!!");
            return false;
        }
        return true;
    }
}
