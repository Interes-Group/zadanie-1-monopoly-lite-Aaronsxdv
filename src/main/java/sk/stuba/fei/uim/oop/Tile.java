package sk.stuba.fei.uim.oop;

public class Tile {
    String name;
    String cname;

}
class Property extends Tile{
    int price;
    int fee;
    int owner;          // or String

    public Property(int pricex,int feex){        //constructor
        price = pricex;
        fee = feex;
        cname = "Property";
        owner = -1;

    }
    public void setOwner(int z){
        this.owner = z;
    }
    public void invoke(Player pl,Player[] pl_list){       //invoke for a certain effect

        if((this.owner == -1) && (pl.money >= this.price)){     //if property is on sale
            System.out.print("\nDo you want to buy this property for "+this.price+"$? Type 'y' to buy : ");
            char answer = KeyboardInput.readChar();
            if(answer == 'y'){
                pl.money -= this.price;
                setOwner(pl.number);
                System.out.println("You've successfully bought this property!");
            }
            else{
                System.out.println("You decided not to buy this property.");
            }
        }
        else if((this.owner != -1) && (this.owner != pl.number)){                              //if property belongs to someone
            pl.money -= fee;
            pl_list[this.owner].money += fee;
            System.out.println("Paying fee ("+fee+") to the owner ("+pl_list[this.owner].name+").");
        }
        else if(this.owner == pl.number){
            System.out.println("This property is yours.");
        }
        else{
            System.out.println("Not enough money to buy this property!");
        }
    }

}
class Corner extends Tile{
    char type;
    public void invoke(Player pl){            //invoke for a certain effect

        switch (type) {
            case 's' :
                pl.money += 100;
                System.out.println("~On start again...~");
                break;
            case 'j' :
                System.out.println("~Visiting the Jail...~");
                break;
            case 'p' :
                pl.jail_time +=2;
                System.out.println("~Aaaand its a jail =( -> Chillin at the Jail...~");
                pl.position = 8;
                break;
            case 't' :
                pl.money -= 200;
                System.out.println("~Paying taxes...~");
                break;
        }

    }
    public Corner(char typex){          //constructor
        type = typex;
        cname = "Corner";
    }
}
class Chance extends Tile{
    int type;
    int [] x = {0,0,0,0,0};

    int num = 0;
    Dice r = new Dice();
    public void checker(int [] x){
        int ans = 0;
        for(int o = 0;o<5;o++){
            if(x[o] != 0){
                ans++;
            }
        }
        if(ans == 5){
            for(int h = 0;h<5;h++){
                x[h] = 0;
            }
        }
    }
    public void invoke(Player pl){
        num = num%5;
        int z = r.roll();
        while((z == x[0]) || (z == x[1]) || (z == x[2]) || (z == x[3]) || (z == x[4])){
            z = r.roll();
        }

        switch (z) {
            case 1 :
                System.out.println("You've won a lottery!Get $200 in the bank.");
                pl.money += 200;
                x[num] = 1;
                break;
            case 2 :
                System.out.println("Bribe the judges in court.If you get to jail,you spend no time there.");
                pl.money -= 50;
                pl.jail_time -= 2;
                x[num] = 2;
                break;
            case 3 :
                System.out.println("An icicle fell on you head.You lose!");
                pl.isAlive = 0;
                x[num] = 3;
                break;
            case 4 :
                System.out.println("Your car broke.Pay $200 to repair it.");
                pl.money -= 100;
                x[num] = 4;
                break;
            case 5 :
                System.out.println("You almost died.But you didnt.");
                x[num] = 5;
                break;


        }
        num ++;
        checker(x);
    }
    public Chance(){
        cname = "Chance";

    }
}