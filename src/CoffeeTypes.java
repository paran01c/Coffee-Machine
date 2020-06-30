public enum CoffeeTypes {

    ESPRESSO(250, 0, 16, 4, "1"),
    LATTE(350, 75, 20, 7, "2"),
    CAPUCCINO(200, 100, 12, 6, "3");

    int water;
    int milk;
    int coffeeBeans;
    int money;
    String index;

    CoffeeTypes(int water, int milk, int coffeeBeans, int money, String index){
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.money = money;
        this.index = index;
    }

    public static CoffeeTypes findByIndex(String index){
        for(CoffeeTypes value: values()) {
            if(index.equals(value.index)){
                return value;
            }
        }
        return null;
    }

    public int getWater(){
        return water;
    }

    public int getMilk(){
        return milk;
    }

    public int getcoffeeBeans(){
        return coffeeBeans;
    }

    public int getPrice(){
        return money;
    }

    public String getIndx(){
        return index;
    }
}
