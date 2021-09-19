package com.codeunlu;

import java.util.Locale;
import java.util.Random;

public abstract class BattleLoc extends Location{

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;

    }

    @Override
    public boolean onLocation() {
        if(getAwardStatus(this.award)){
            return true;
        }
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız: " + this.getName());
        System.out.println("Dikkatli Ol! Burada " + this.randomObstacleNumber() + " tane " +  this.getObstacle().getName() + " yaşıyor!");
        System.out.print("<S>avaş veya <K>aç");
        String selectMode = input.nextLine();
        selectMode = selectMode.toUpperCase(Locale.ROOT);
        if(selectMode.equals("S") && combat(obsNumber)){
            System.out.println(this.getName() + " tüm düşmanları yendiniz!");
            return true;
        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz....");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber){

        for(int i=1; i <= obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = input.nextLine().toUpperCase(Locale.ROOT);
                if(selectCombat.equals("V")){
                    System.out.println("Siz vurdunuz!");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if(this.getObstacle().getHealth() > 0){
                        System.out.println();
                        System.out.println("Canavar size vurdu!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getArmor().getBlock();
                        if(obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth( this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı Yendiniz !!");
                System.out.println(this.getObstacle().getAward() + " para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel Paranız: " + this.getPlayer().getMoney());
                if(i == obsNumber){
                    if(this.award.equals("food")){
                        this.getPlayer().getInventory().setFood(true);
                    }else if(this.award.equals("firewood")){
                        this.getPlayer().getInventory().setFirewood(true);
                    }else{
                        this.getPlayer().getInventory().setWater(true);
                    }
                    System.out.println(this.award + " ödülünüz!!!");
                }

            }else{
                return false;
            }
        }
        return false;
    }

    public void afterHit(){
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println("Canavarın canı:" + this.getObstacle().getHealth());
        System.out.println("------");
    }

    public void playerStats(){
        System.out.println("----Oyuncu Değerleri----");
        System.out.println("Sağlık: "+ this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getWeapon().getName());
        System.out.println("Zırh: " + this.getPlayer().getArmor().getName());
        System.out.println("Hasar: "+ this.getPlayer().getTotalDamage());
        System.out.println("Bloklama: "+ this.getPlayer().getArmor().getBlock());
        System.out.println("Para: "+ this.getPlayer().getMoney());

    }

    public void obstacleStats(int i){
        System.out.println("("+i + ")----" + this.getObstacle().getName() + " Değerleri----");
        System.out.println("Sağlık: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödül: " + this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
