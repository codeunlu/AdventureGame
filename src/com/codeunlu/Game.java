package com.codeunlu;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Hoş Geldiniz !");
        System.out.println("Lütfen bir isim giriniz: ");
        //String playerName = input.nextLine();
        Player player = new Player("mustafa");
        System.out.println(player.getName() + ", Hoşgeldiniz!!");
        System.out.println("Oyuna başlamak için karakter seçiniz: ");
        player.selectChar();

        Location location = null;
        while(true){
            player.printInfo();
            System.out.println("---- Bölgeler ----");

            System.out.println("1 - Güvenli Ev" + "--> Burası güvenli ev, düşman yoktur.");
            System.out.println("2 - Eşya Dükkanı" + "--> Silah ve Zırh alabilirsiniz, düşman yoktur.");
            System.out.println("3 - Mağara" + "--> Ödül <Yemek>, dikkatli ol canavar çıkabilir!");
            System.out.println("4 - Orman"  + "--> Ödül <Odun>, dikkatli ol canavar çıkabilir!");
            System.out.println("5 - Nehir" +  "--> Ödül<Su>, dikkatli ol canavar çıkabilir!");
            System.out.println("0 - Çıkış Yap  --> Oyunu Sonlandır");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");

            int selectLocal = input.nextInt();
            switch (selectLocal){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz!");
            }
            if(location == null){
                System.out.println("Oyun bitti Görüşmek üzere");
                break;
            }
            if(!location.onLocation()){
                System.out.println("---- GAME OVER ----");
                break;
            }
        }
    }
}
