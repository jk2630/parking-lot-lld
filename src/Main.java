import service.ParkingLotService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static ParkingLotService service = new ParkingLotService();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> commands = new ArrayList<>();
        String inputCmd = input.nextLine();

        while(!inputCmd.equals("exit")){
            commands.add(inputCmd);
            inputCmd = input.nextLine();
        }
        service.startApp(commands);
    }
}
