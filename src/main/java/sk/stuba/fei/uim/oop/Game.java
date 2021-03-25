package sk.stuba.fei.uim.oop;

public class Game {
    int pl_num;

    void Resident_Sleeper(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int endchecker(Player[] Pl_list, int pl_num) {
        int endgame = 0;
        int pl_still_in = 0;
        for (int i = 0; i < pl_num; i++) {
            if (Pl_list[i].isAlive == 1) {
                pl_still_in++;
            }
        }
        if (pl_still_in == 1) {
            endgame = 1;
        }
        return endgame;
    }

    void setup() {
        Dice d = new Dice();
        Player[] Pl_list = new Player[pl_num];
        System.out.println("<<MONOPOLY>>");
        //initialize players:
        for (int i = 0; i < pl_num; i++) {


            System.out.printf("Enter player %d name: ", i + 1);
            String name = KeyboardInput.readString();
            Pl_list[i] = new Player(name, i);
        }
        //initialize field:
        Tile[] field = new Tile[24];
        Tile chance = new Chance();
        field[0] = new Corner('s');
        field[8] = new Corner('j');
        field[12] = new Corner('p');
        field[20] = new Corner('t');
        field[2] = chance;
        field[5] = chance;
        field[10] = chance;
        field[16] = chance;
        field[22] = chance;
        field[1] = new Property(100, 10);
        field[3] = new Property(120, 11);
        field[4] = new Property(130, 12);
        field[6] = new Property(100, 10);
        field[7] = new Property(120, 11);
        field[9] = new Property(130, 12);
        field[10] = new Property(100, 10);
        field[11] = new Property(120, 11);
        field[13] = new Property(130, 12);
        field[14] = new Property(100, 10);
        field[15] = new Property(120, 11);
        field[17] = new Property(130, 12);
        field[18] = new Property(100, 10);
        field[19] = new Property(120, 11);
        field[21] = new Property(130, 12);
        field[22] = new Property(100, 10);
        field[23] = new Property(120, 11);
        //start the actual game
        this.Gameon(Pl_list, field, pl_num, d);
    }


    void Gameon(Player[] pl_list, Tile[] field, int pl_num, Dice d) {                 //game starter
        int current_pl_id = 0; //current player id
        int endgame = 0;
        while (endgame == 0) {

            System.out.println("_____________________________");

            current_pl_id = current_pl_id % pl_num;
            System.out.printf("Current player: %d\n", current_pl_id + 1);
            Player obj = pl_list[current_pl_id];
            int prevpos = obj.position;             //position before turn
            System.out.println("[" + obj.name + "]");


            //turn logic start
            if (obj.money < 0 && obj.isAlive == 1) {  //if player has no money left
                obj.isAlive = 0;
                System.out.println("You went bankrupt.GG for you!");
            } else if (obj.isAlive == 0) {          //if player has lost
                for (int h = 0; h < 24; h++) {    //check if any property belongs to the dead players.if so, make it lose the owner
                    if ((field[h].cname.equals("Property")) && ((Property) field[h]).owner == obj.number) {
                        ((Property) field[h]).owner = 0;
                    }
                }
                System.out.println("Current player's game is over.Going on to the next player...");
            } else if (obj.jail_time <= 0 && obj.isAlive == 1) {    //if player is in game
                System.out.println("Position: " + obj.position);
                System.out.println("Money: " + obj.money);
                System.out.println("Current jail time:" + obj.jail_time);
                String ans = "not";
                System.out.print("Press enter to roll the dice? : ");
                while (!ans.equals("")) {
                    ans = KeyboardInput.readString();
                    if (!ans.equals("")) {
                        System.out.print("\nTry again (press enter to roll the dice) : ");
                    }
                }
                int x = d.roll();
                System.out.println("Rolling the dice...");
                Resident_Sleeper(500);
                System.out.printf("Your roll is %d\n", x);
                obj.position += x;

                //check if passed start
                if (prevpos < 24 && obj.position > 24) {
                    obj.money += 200;
                    System.out.println("Passed start");
                }

                obj.position = obj.position % 24;
                switch (field[obj.position].cname) {
                    case "Property" -> {
                        System.out.println("You've stepped on a property type card!");
                        Resident_Sleeper(300);
                        ((Property) field[obj.position]).invoke(obj, pl_list);
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
            } else if (obj.jail_time > 0) {         //if player is in jail
                System.out.println("A turn in jail passed!");
                obj.jail_time--;
                System.out.printf("Turns in jail left: %d\n", obj.jail_time);
            }


            //turn logic end

            pl_list[current_pl_id] = obj;
            endgame = endchecker(pl_list, pl_num);
            current_pl_id++;
            System.out.println("\n\n");
            Resident_Sleeper(1000);
        }
        int winner = 0;
        for (int j = 0; j < pl_num; j++) {
            if (pl_list[j].isAlive == 1) {
                winner = j;
            }
        }
        System.out.printf("Game Over!Player %d won!!!", winner + 1);
    }


    public Game(int pl_numx) {       //constructor
        pl_num = pl_numx;
        setup();
    }

}
