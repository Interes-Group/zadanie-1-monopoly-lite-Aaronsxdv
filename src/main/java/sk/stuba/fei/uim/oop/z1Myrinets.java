package sk.stuba.fei.uim.oop;
import java.util.Random;
import java.util.*;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

class KeyboardInput {

    public static final String INPUT_FAILURE_TEXT = "Your input has failed. Please try again!";
    public static final int INFINITE_TRIES = Integer.MAX_VALUE;
    public static final String[] TRUE_INPUTS = {"y", "yes", "true", "1", "ano", "áno"};
    public static final String[] FALSE_INPUTS = {"n", "no", "false", "0", "nie"};


    public static char readChar(String promptText, int numberOfTries, String failureText) {
        printPrompt(promptText);
        return readChar(numberOfTries, failureText);
    }

    public static char readChar(String promptText, int numberOfTries) {
        printPrompt(promptText);
        return readChar(numberOfTries);
    }

    public static char readChar(int numberOfTries, String failureText) {
        return repeatInput(numberOfTries, failureText, c -> c == (char) 0, KeyboardInput::readChar);
    }

    public static char readChar(int numberOfTries) {
        return readChar(numberOfTries, INPUT_FAILURE_TEXT);
    }

    public static char readChar(String promptText) {
        printPrompt(promptText);
        return readChar();
    }

    public static char readChar() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            return trimChar((char) inputStreamReader.read());
        } catch (IOException e) {
            e.printStackTrace();
            return (char) 0;
        }
    }


    public static String readString(String promptText, int numberOfTries, String failureText) {
        printPrompt(promptText);
        return readString(numberOfTries, failureText);
    }

    public static String readString(String promptText, int numberOfTries) {
        printPrompt(promptText);
        return readString(numberOfTries);
    }

    public static String readString(int numberOfTries, String failureText) {
        return repeatInput(numberOfTries, failureText, String::isEmpty, KeyboardInput::readString);
    }

    public static String readString(int numberOfTries) {
        return readString(numberOfTries, INPUT_FAILURE_TEXT);
    }

    public static String readString(String promptText) {
        printPrompt(promptText);
        return readString();
    }

    public static String readString() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            return bufferedReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    public static int readInt(String promptText, int numberOfTries, String failureText) {
        printPrompt(promptText);
        return readInt(numberOfTries, failureText);
    }

    public static int readInt(String promptText, int numberOfTries) {
        printPrompt(promptText);
        return readInt(numberOfTries, INPUT_FAILURE_TEXT);
    }

    public static int readInt(int numberOfTries, String failureText) {
        return repeatInput(numberOfTries, failureText, i -> i == Integer.MIN_VALUE, KeyboardInput::readInt);
    }

    public static int readInt(int numberOfTries) {
        return readInt(numberOfTries, INPUT_FAILURE_TEXT);
    }

    public static int readInt(String promptText) {
        printPrompt(promptText);
        return readInt();
    }

    public static int readInt() {
        try {
            String s = readString();
            return s.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }


    public static double readDouble(String promptText, int numberOfTries, String failureText) {
        printPrompt(promptText);
        return readDouble(numberOfTries,failureText);
    }

    public static double readDouble(String promptText, int numberOfTries) {
        printPrompt(promptText);
        return readDouble(numberOfTries, INPUT_FAILURE_TEXT);
    }

    public static double readDouble(int numberOfTries, String failureText) {
        return repeatInput(numberOfTries,failureText, d -> d == Double.MIN_VALUE, KeyboardInput::readDouble);
    }

    public static double readDouble(int numberOfTries) {
        return readDouble(numberOfTries, INPUT_FAILURE_TEXT);
    }

    public static double readDouble(String promptText) {
        printPrompt(promptText);
        return readDouble();
    }

    public static double readDouble() {
        try {
            String s = readString();
            return s.isEmpty() ? Double.MIN_VALUE : Double.parseDouble(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Double.MIN_VALUE;
        }
    }

    public static boolean readBooleanOrElse(boolean defaultValue) {
        try {
            return readBoolean();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static boolean readBoolean() {
        String input = readString();
        if (input.isEmpty() || Arrays.stream(FALSE_INPUTS).anyMatch(s -> s.equalsIgnoreCase(input))) {
            return false;
        }
        if (Arrays.stream(TRUE_INPUTS).anyMatch(s -> s.equalsIgnoreCase(input))) {
            return true;
        } else {
            throw new IllegalArgumentException("Invalid boolean input! Input " + input + " cannot be parsed into boolean value.");
        }
    }

    private static void printPrompt(String prompt) {
        System.out.print(prompt + ": ");
    }

    private static char trimChar(char c) {
        String s = ("" + c).trim();
        return s.isEmpty() ? (char) 0 : s.charAt(0);
    }

    private static <T> T repeatInput(int repetition, String failureMessage, Predicate<T> predicate, Supplier<T> supplier) {
        T o = supplier.get();
        while (predicate.test(o) && repetition != 0) {
            System.out.println(failureMessage != null ? failureMessage : "");
            o = supplier.get();
            repetition--;
        }
        return o;
    }

}


class Dice{
    Random rand = new Random();
    int roll(){
        return (rand.nextInt(5))+1;
    }
}
class Player{
    public int money;
    public int number;
    public int position;
    public String name;
    public int jail_time;
    public int isAlive;
    public int prop_num;
    public Player(String n,int numberx){
        number = numberx;
        name = n;
        money = 200;
        isAlive = 1;
        jail_time = 0;
        prop_num = 0;
        position = 0;
    }
}
class Game{
    int pl_num;

    void Resident_Sleeper(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int endchecker(Player[] Pl_list,int pl_num){
        int endgame = 0;
        int pl_still_in = 0;
        for(int i = 0;i<pl_num;i++){
            if(Pl_list[i].isAlive == 1){
                pl_still_in ++;
            }
        }
        if(pl_still_in == 1){
            endgame = 1;
        }
        return endgame;
    }

    void setup(){
        Dice d = new Dice();
        Player [] Pl_list = new Player[pl_num];
        System.out.println("<<MONOPOLY>>");
        //initialize players:
        for (int i = 0; i < pl_num; i++)
        {

            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
            System.out.printf("Enter player %d name: ",i+1);
            String name= sc.nextLine();
            Pl_list[i] = new Player(name,i);
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
        this.Gameon(Pl_list,field,pl_num,d);
    }


    void Gameon(Player[] pl_list, Tile[] field,int pl_num,Dice d){                 //game starter
        int current_pl_id = 0; //current player id
        int endgame = 0;
        while(endgame == 0){

            System.out.println("_____________________________");

            current_pl_id = current_pl_id%pl_num;
            System.out.printf("Current player: %d\n",current_pl_id+1);
            Player obj = pl_list[current_pl_id];

            System.out.println("["+ obj.name+"]");



            //turn logic start
            if(obj.money < 0 && obj.isAlive == 1){  //if player has no money left
                obj.isAlive = 0;
                System.out.println("You went bankrupt.GG for you!");
            }
            else if(obj.isAlive == 0){          //if player has lost
                for(int h = 0;h<24;h++){    //check if any property belongs to the dead players.if so, make it lose the owner
                    if((field[h].cname.equals("Property")) && ((Property) field[h]).owner == obj.number){
                        ((Property) field[h]).owner = 0;
                    }
                }
                System.out.println("Current player's game is over.Going on to the next player...");
            }
            else if(obj.jail_time <= 0 && obj.isAlive == 1){    //if player is in game
                System.out.println("Position: "+obj.position);
                System.out.println("Money: "+obj.money);
                System.out.println("Current jail time:"+obj.jail_time);
                String ans = "not";
                System.out.print("Press enter to roll the dice? : ");
                while(!ans.equals("")){
                    ans = KeyboardInput.readString();
                    if(!ans.equals("")){
                        System.out.print("\nTry again (press enter to roll the dice) : ");
                    }
                }
                int x = d.roll();
                System.out.println("Rolling the dice...");
                Resident_Sleeper(500);
                System.out.printf("Your roll is %d\n",x);
                obj.position += x;
                obj.position = obj.position%24;
                switch (field[obj.position].cname){
                    case "Property" -> {
                        System.out.println("You've stepped on a property type card!");
                        Resident_Sleeper(300);
                        ((Property) field[obj.position]).invoke(obj,pl_list);
                    }
                    case "Chance" -> {
                        System.out.println("You've stepped on a chance type card!");
                        Resident_Sleeper(300);
                        ((Chance) field[obj.position]).invoke(obj);
                    }
                    case "Corner" -> {
                        System.out.println("You've stepped on a corner type card!");
                        Resident_Sleeper(300);
                        ((Corner) field[obj.position]).invoke(obj);
                    }
                }   //tile type switch
            }
            else if(obj.jail_time > 0){         //if player is in jail
                System.out.println("A turn in jail passed!");
                obj.jail_time --;
                System.out.printf("Turns in jail left: %d\n",obj.jail_time);
            }


            //turn logic end

            pl_list[current_pl_id] = obj;
            endgame = endchecker(pl_list,pl_num);
            current_pl_id++;
            System.out.println("\n\n");
            Resident_Sleeper(1000);
        }
        int winner = 0;
        for(int j = 0;j<pl_num;j++){
            if(pl_list[j].isAlive == 1){
                winner = j;
            }
        }
        System.out.printf("Game Over!Player %d won!!!",winner+1);
    }


    public Game(int pl_numx){       //constructor
        pl_num = pl_numx;
        setup();
    }

}
class Tile{
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
            case 's' -> {
                pl.money += 100;
                System.out.println("~On start again...~");
            }
            case 'j' -> System.out.println("~Visiting the Jail...~");
            case 'p' -> {
                pl.jail_time +=2;
                System.out.println("~Aaaand its a jail =( -> Chillin at the Jail...~");
                pl.position = 8;
            }
            case 't' -> {
                pl.money -= 200;
                System.out.println("~Paying taxes...~");
            }
        }

    }
    public Corner(char typex){          //constructor
        type = typex;
        cname = "Corner";
    }
}
class Chance extends Tile{
    int type;
    public void invoke(Player pl){

        switch (type) {
            case 1 -> {
                System.out.println("You've won a lottery!Get $200 in the bank.");
                pl.money += 200;
            }
            case 2 -> {
                System.out.println("Bribe the judges in court.If you get to jail,you spend no time there.");
                pl.money -= 50;
                pl.jail_time -= 2;
            }
            case 3 -> {
                System.out.println("An icicle fell on you head.You lose!");
                pl.isAlive = 0;
            }
            case 4 -> {
                System.out.println("Your car broke.Pay $200 to repair it.");
                pl.money -= 100;
            }
            case 5 -> System.out.println("You almost died.But you didnt.");
        }

    }
    public Chance(int typez){
        this.type = typez;
        cname = "Chance";
    }
}
class Main {

    public static void main(String[] args) {
        new Game(3);
    }
}

