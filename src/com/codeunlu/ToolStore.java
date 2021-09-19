package com.codeunlu;

public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation(){
        System.out.println("----------Mağazaya Hoşgeldiniz----------");
        boolean showMenu = true;
        while(showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış Yap");
            int selectCase = input.nextInt();

            while (selectCase < 1 || selectCase > 3){
                System.out.println("Geçersiz bir değer girdiniz!");
                selectCase = input.nextInt();
            }

            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Mekandan çıkıyorsunuz..");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("----Silahlar---");
        for(Weapon w: Weapon.weapons()){
            System.out.println("ID:" + w.getId()
                    + "\t Name: " + w.getName()
                    + "\t Damage: " + w.getDamage()
                    + "\t Price:" + w.getPrice());
        }
        System.out.println("0 - Çıkış Yap");

    }

    public  void buyWeapon(){
        System.out.println("--- Silah Seçiniz ---");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.println("Geçersiz değer girdiniz..");
            selectWeaponID = input.nextInt();
        }

        if(selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponById(selectWeaponID);
            if(selectedWeapon != null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Paranız yeterli değil.");
                }else{
                    System.out.println(selectedWeapon.getName() + " satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("----Yeni Ekipman----\n");
                }
            }
        }


    }

    public void printArmor(){
        System.out.println("----Zırhlar---");
        for(Armor armor : Armor.armors()){
            System.out.println("ID:" + armor.getId()
                    + "\t Name: " + armor.getName()
                    + "\t Block: " + armor.getBlock()
                    + "\t Price:" + armor.getPrice());
        }
        System.out.println("0 - Çıkış Yap");

    }

    public void buyArmor(){
        System.out.println("--- Armor Seçiniz ---");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.println("Geçersiz değer girdiniz..");
            selectArmorID = input.nextInt();
        }

        if(selectArmorID != 0 ){
            Armor selectedArmor = Armor.getArmorById(selectArmorID);
            if(selectedArmor != null){
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Paranız yeterli değil.");
                }else{
                    System.out.println(selectedArmor.getName() + " satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("----Yeni Ekipman----\n");
                }
            }
        }


    }
}
