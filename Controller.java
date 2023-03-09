import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Controller {

    //读取csv数据转化为list
    public List<List<Object>> loadData() {

        List<List<Object>> data = new ArrayList<List<Object>>();

        for (int i = 0; i < 4; ++i) {
            data.add(new ArrayList<Object>());
        }

        //通过BufferedReader将文件转为String，通过数据流输入到list里
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data.csv"))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] stringData = line.split(",");
                if (stringData[0].equals("TeachingRequirement")) {
                    data.get(0).add(new TeachingRequirement(stringData[1]));
                } else if (stringData[0].equals("Staff")) {
                    data.get(1).add(new Staff(stringData[1], Arrays.stream(stringData, 2, stringData.length).collect(Collectors.toList())));
                } else if (stringData[0].equals("Training")) {
                    data.get(2).add(new Training(stringData[1], Arrays.stream(stringData, 2, stringData.length).map(arr -> new Staff(arr)).collect(Collectors.toList())));
                } else if (stringData[0].equals("User")) {
                    data.get(3).add(new User(stringData[1], stringData[2], stringData[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    //保存数据，也就是list转为csv
    public void saveData(List<List<Object>> data) {

        //用stirngData作为输入文件的数据流的源
        List<String[]> stringData = new ArrayList<String[]>();

        //把每种数据都通过toString导入stringData
        for (TeachingRequirement t : dataToTeachingRequirements(data)) {
            stringData.add(
                    Stream.concat(Stream.of("TeachingRequirement"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (Staff t : dataToStaffs(data)) {
            stringData.add(
                    Stream.concat(Stream.of("Staff"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (Training t : dataToTrainings(data)) {
            stringData.add(
                    Stream.concat(Stream.of("Training"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (User t : dataToUsers(data)) {
            stringData.add(
                Stream.concat(Stream.of("user"), Arrays.stream(t.toString().split(",")))
                        .toArray(String[]::new)
            );
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("data.csv"))) {
            stringData.stream().map(row -> String.join(",", row)).forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //用户登陆
    public User login(Scanner scanner, List<User> userData) {
        
        //尝试登陆次数最多100次
        for (int times = 0; times < 100; ++times) {

            System.out.println("Please input your username::");
            String username = scanner.nextLine();
            
            System.out.println("Please input your password::");
            String password = scanner.nextLine();
            
            //查看是否存在该用户
            for (int i = 0; i < userData.size(); ++i) {
                if (userData.get(i).check(username, password)) {
                    if (userData.get(i).getType().equals("ClassDirector")) {
                        System.out.println("ClassDirector logs in successfully!");
                        return new ClassDirector(username, password);
                    } else if (userData.get(i).getType().equals("Administrator")) {
                        System.out.println("Administrator logs in successfully!");
                        return new Administrator(username, password);
                    }
                }
            }
            System.out.println("Logs in unsuccessfully!");
        }

        return null;
    }
    
    //循环 用户交互指令，调用子方法实现用户指令
    public void commandLoop(Scanner scanner, User user, List<List<Object>> data) {
        
        //一次登陆最多操作1e5次
        for (int times = 0; times < 1e5; ++times) {
            
            if (user.getType().equals("ClassDirector")) {

                ClassDirector classDirector = (ClassDirector)user;
                
                //展示可以输出的命令
                //调用classDirector的方法

            } else if (user.getType().equals("Administrator")) {

                Administrator administrator = (Administrator)user;

                //展示可以输出的命令
                //调用administrator的方法
            }
        }

        //结束程序
        System.out.println("See you letter~");
    }

    //将抽象类的list转为具体数据类的list，本质是强制类型转化
    public List<TeachingRequirement> dataToTeachingRequirements(List<List<Object>> data) {
        return data.get(0).stream().map(obj -> (TeachingRequirement) obj).collect(Collectors.toList());
    }

    public List<Staff> dataToStaffs(List<List<Object>> data) {
        return data.get(1).stream().map(obj -> (Staff) obj).collect(Collectors.toList());
    }

    public List<Training> dataToTrainings(List<List<Object>> data) {
        return data.get(2).stream().map(obj -> (Training) obj).collect(Collectors.toList());
    }

    public List<User> dataToUsers(List<List<Object>> data) {
        return data.get(3).stream().map(obj -> (User) obj).collect(Collectors.toList());
    }

}
