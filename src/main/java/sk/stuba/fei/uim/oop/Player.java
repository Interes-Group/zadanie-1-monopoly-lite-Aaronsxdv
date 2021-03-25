package sk.stuba.fei.uim.oop;

public class Player {
    public int money;
    public int number;
    public int position;
    public String name;
    public int jail_time;
    public int isAlive;
    public int prop_num;

    public Player(String n, int numberx) {
        number = numberx;
        name = n;
        money = 500;
        isAlive = 1;
        jail_time = 0;
        prop_num = 0;
        position = 0;
    }
}
