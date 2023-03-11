import java.util.Scanner;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        //新建控制器
        Controller controller = new Controller();

        //新建数据表，读取数据
        List<List<Object>> data = controller.loadData();

        //新建scanner用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        //用户登陆，并获得特定的User类，也就是ClassDirector或Administrator
        User user = controller.login(scanner, controller.dataToUserList(data));

        //循环 用户指令交互
        if (user != null) controller.commandLoop(scanner, user, data);

        //关闭scanner
        scanner.close();

        //保存数据
        controller.saveData(data);
    }
}
