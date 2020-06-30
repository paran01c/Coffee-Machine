import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();

        boolean powerOn = true;

        while (powerOn){
            machine.question();
            machine.userAnswer(sc.nextLine());
            machine.machineAnswer();

            powerOn = machine.power();
        }
    }
}
