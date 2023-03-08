import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Demo {
    public static void main(String[] args) {
        Controller controller = new Controller();

        List<List<Object>> data = controller.loadData();

        // 这里只是用来展示数据，最后可以删除
        System.out.println("\nWelcome!");
        String[] show = {"teachingRequirementData", "staffData", "trainingData"};
        for (int i = 0; i < data.size(); ++i) {
            System.out.println("\n---->>" + show[i]);
            for (int j = 0; j < data.get(i).size(); ++j) {
                System.out.println(data.get(i).get(j));
            }
        }

        // 获取 teachingRequirements，training和staff同理
        // List<TeachingRequirement> teachingRequirements = data.get(0).stream()
        // .map(obj -> (TeachingRequirement) obj)
        // .collect(Collectors.toList());

        Scanner scanner = new Scanner(System.in);
        scanner.close();

        controller.saveData(data);
    }
}