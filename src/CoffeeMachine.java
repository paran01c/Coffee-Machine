public class CoffeeMachine {

    //default values
    int water = 400;
    int milk = 540;
    int coffeeBeans = 120;
    int dispCups = 9;
    int money = 550;

    //the array contains the amount of each that the machine will be filled with
    //indexes 0 - water; 1 - milk; 2 - coffeeBeans; 3 - dispCups
    int[] fillMachine = new int[4];
    int fillMachineIndex = 0;

    String response = "start";
    //temp storage as string for the value that will go in the fillMachine array;
    String responseFill = " ";
    String responseBuy = "waiting";

    String startQuestion = "Write action (buy, fill, take, remaining, exit):";
    String buyQuestion = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ";
    String fillWater = "Write how many ml of water do you want to add: ";
    String fillMilk = "Write how many ml of milk do you want to add: ";
    String fillCoffeeBeans = "Write how many grams of coffee beans do you want to add: ";
    String fillDispCups = "Write how many disposable cups of coffee do you want to add: ";
    //we do not need a constructor the default one works

    //the methods
    //the users response
    public void userAnswer(String s){
        switch(response) {
            case "start":
                response = s;
                System.out.println();
                break;
            case "buy":
                responseBuy = s;
                break;
            case "fill":
                responseFill = s;
                fillMachine[fillMachineIndex] = Integer.parseInt(s);
                fillMachineIndex++;
                break;
            default:
                break;
        }
    }

    //asking the user questions
    public void question(){
        switch(response) {
            case "start":
                System.out.println(startQuestion);
                if(fillMachineIndex != 0) {
                    fillCoffeMachine();
                    fillMachineIndex = 0;
                }
                break;
            case "buy":
                System.out.println(buyQuestion);
                break;
            case "fill":
                switch(fillMachineIndex){
                    case 0:
                        System.out.println(fillWater);
                        break;
                    case 1:
                        System.out.println(fillMilk);
                        break;
                    case 2:
                        System.out.println(fillCoffeeBeans);
                        break;
                    case 3:
                        System.out.println(fillDispCups);
                        break;
                    default:
                        System.out.println();
                        response = "start";
                        break;
                }
                break;
            default:
                break;
        }
    }

    //will answer the user or will restart teh cicle if a question would folow
    public void machineAnswer(){
        switch (response) {
            case "remaining":
                machineStock();
                response = "start";
                break;
            case"take":
                System.out.println("I gave you $" + money);
                System.out.println();
                money = 0;
                response = "start";
                break;
            case "buy":
                if(responseBuy.equals("1") || responseBuy.equals("2") || responseBuy.equals("3")) {
                    makeCoffe(responseBuy);
                    response = "start";
                    responseBuy = "waiting";
                } else if(responseBuy.equals("back")) {
                    response = "start";
                    responseBuy = "waiting";
                } else {
                    response = "buy";
                }
                break;
            case "fill":
                if(fillMachineIndex == 4) {
                    response = "start";
                }
                break;
            case "exit":
                break;
            default:
                //in case the user inputed a invalid comand
                response = "error";
        }

    }

    //will turn of the machine if the user inputs exit
    public boolean power(){
        switch(response) {
            case "error":
                System.out.println("Unknown comand introduced");
                return false;
            case "exit":
                return false;
            default:
                return true;
        }
    }

    //printing how much of every resource the machine has
    private void machineStock(){

        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(dispCups + " of disposable cups");
        System.out.println("$" + money + " of money");
        System.out.println();
    }

    //checking if the machine has enough resources t make the coffee and to say with resource it is missing;
    private void makeCoffe(String s){

        String notEnough = "Sorry, not enough ";
        CoffeeTypes coffeeWanted;
        coffeeWanted = CoffeeTypes.findByIndex(s);

        //cheks if we have enough and tells us
        if(!enoughWater(coffeeWanted)) {
            System.out.println(notEnough + "water!");
        } else if(!enoughMilk(coffeeWanted)) {
            System.out.println(notEnough + "milk!");
        } else if(!enoughcoffeeBeans(coffeeWanted)) {
            System.out.println(notEnough + "coffee beans!");
        } else if(!enoughCups()){
            System.out.println(notEnough + "disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");

            //changing the values after the coffee is made
            water -= coffeeWanted.water;
            milk -= coffeeWanted.milk;
            coffeeBeans -= coffeeWanted.coffeeBeans;
            dispCups--;
            money += coffeeWanted.money;
        }

        System.out.println();
    }

    //checkinf if we have enough resources from each one
    private boolean enoughWater(CoffeeTypes coffeeWanted){
        if(water - coffeeWanted.water < 0) {
            return false;
        } else {
            return true;
        }
    }
    private boolean enoughMilk(CoffeeTypes coffeeWanted){
        if(milk - coffeeWanted.milk < 0) {
            return false;
        } else {
            return true;
        }
    }
    private boolean enoughcoffeeBeans(CoffeeTypes coffeeWanted){
        if(coffeeBeans - coffeeWanted.coffeeBeans < 0) {
            return false;
        } else {
            return true;
        }
    }
    private boolean enoughCups(){
        if(dispCups <= 0) {
            return false;
        } else {
            return true;
        }
    }

    //will add the values the user introduced to the values the machine had
    //indexes 0 - water; 1 - milk; 2 - coffeeBeans; 3 - dispCups
    private void fillCoffeMachine(){
        water = water + fillMachine[0];
        milk = milk + fillMachine[1];
        coffeeBeans = fillMachine[2];
        dispCups = fillMachine[3];
    }
}
