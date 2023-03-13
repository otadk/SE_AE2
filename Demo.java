import java.util.Scanner;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        Controller controller = new Controller();
        List<List<Object>> data = controller.loadData();
        Scanner scanner = new Scanner(System.in);

        // user will be ClassDirector or Administrator or null after log in 
        User user = controller.login(scanner, controller.dataToUserList(data));

        // command interaction start 
        if (user != null) controller.commandLoop(scanner, user, data);

        scanner.close();
        controller.saveData(data);
    }
}