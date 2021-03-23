package sk.stuba.fei.uim.oop;

import java.util.Random;
import java.util.*;

class Dice{
    Random rand = new Random();
    int roll(){
        return rand.nextInt(6);
    };
}
class Player{
    public static int money;
    public static int number;
    public String name;
    int jail_time;
    int isAlive;
    int prop_num;
    public Player(String n,int numberx){
        number = numberx;
        name = n;
        money = 100;
        isAlive = 1;
        jail_time = 0;
        prop_num = 0;
    }
}
class Game{
    int pl_num;
    Dice d = new Dice();


    void setup(){
        Player [] Pl_list = new Player[pl_num];
        //initialize players:
        for (int i = 0; i < pl_num; i++)
        {

            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
            System.out.printf("Enter player %d name: ",i+1);
            String name= sc.nextLine();
            Pl_list[i] = new Player(name,i+1);
        }
        //initialize field:
        Tile [] field = new Tile[24];
        field[0] = new Corner('s');
        field[8] = new Corner('j');
        field[12] = new Corner('p');
        field[20] = new Corner('t');
        field[2] = new Chance(1);
        field[5] = new Chance(2);
        field[10] = new Chance(3);
        field[16] = new Chance(4);
        field[22] = new Chance(5);
        field[1] = new Property(100,10);
        field[3] = new Property(120,11);
        field[4] = new Property(130,12);
        field[6] = new Property(100,10);
        field[7] = new Property(120,11);
        field[9] = new Property(130,12);
        field[10] = new Property(100,10);
        field[11] = new Property(120,11);
        field[13] = new Property(130,12);
        field[14] = new Property(100,10);
        field[15] = new Property(120,11);
        field[17] = new Property(130,12);
        field[18] = new Property(100,10);
        field[19] = new Property(120,11);
        field[21] = new Property(130,12);
        field[22] = new Property(100,10);
        field[23] = new Property(120,11);
        //start the actual game
        this.Gameon(Pl_list,field);
    }

    void turn(){                                   //turn

    }

    void Gameon(Player[] pl_list, Tile[] field){                 //game starter

    }

    public Game(int pl_numx){
        pl_num = pl_numx;
    } //constructor

}
class Tile{
    String name;
    String cname;
    public String getcname(){  //get class name
        return cname;
    }
}
class Property extends Tile{
    int price;
    int fee;
    int owner;          // or String

    public Property(int pricex,int feex){        //constructor
        price = pricex;
        fee = feex;
        cname = "Property";

    }
    public void changeOwner(int z){
        this.owner = z;
    }
    public void invoke(){       //invoke for a certain effect
        if((this.owner == 0) && (Player.money >= price)){
            Player.money -= price;
            changeOwner(Player.number);
        }
        else if(this.owner != 0){
            Player.money -= fee;
            //Pl_list[owner].money += fee
        }
    }

}
class Corner extends Tile{
    char type;
    public void invoke(){            //invoke for a certain effect

        switch (type) {
            case 's' -> /*player.money += 100*/System.out.println("Passed start...");
            case 'j' -> System.out.println("Visiting Jail...");
            case 'p' -> /*player.jailtime +=2*/System.out.println("Chillin at the Jail...");
            case 't' -> /*player.money -= 200*/System.out.println("Paying taxes...");
        }

    }
    public Corner(char typex){          //constructor
        type = typex;
        cname = "Corner";
    }
}
class Chance extends Tile{
    int type;
    public void invoke(){

        switch (type) {
            case 1 -> {
                System.out.println("You've won a lottery!Get $200 in the bank.");
                Player.money += 200;
            }
            case 2 -> {
                System.out.println("Bribe the judges in court.If you get to jail,you spend no time there.");
                Player.jailtime -= 2;
            }
            case 3 -> {
                System.out.println("An icicle fell on you head.You lose!");
                Player.isAlive = 0;
            }
            case 4 -> {
                System.out.println("Your car broke.Pay $200 to repair it.");
                Player.money -= 100;
            }
            case 5 -> {
                System.out.println("");
                //smth
            }
        }

    }
    public Chance(int typez){
        this.type = typez;
        cname = "Chance";
    }
}
public class Main {

    public static void main(String[] args) {
        Game newGame = new Game(3);
        newGame.setup();
    }
}

