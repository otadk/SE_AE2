import java.util.Scanner;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Controller controller = new Controller();

        List<List<Object>> data = controller.loadData();

        System.out.println("Welcome!");

        String[] show = {"teachingRequirementData", "staffData", "trainingData"};
        for (int i = 0; i < data.size(); ++i) {
            System.out.println("\n---->>" + show[i]);
            for (int j = 0; j < data.get(i).size(); ++j) {
                System.out.println(data.get(i).get(j));
            }
        }

        Scanner scanner = new Scanner(System.in);
        scanner.close();
<<<<<<< HEAD

        controller.saveData(data);
=======
>>>>>>> 06be5760fde51b64f11f74c73323c264b1413e02
    }
}