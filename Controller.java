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

        for (int i = 0; i < 5; ++i) {
            data.add(new ArrayList<Object>());
        }

        //通过BufferedReader将文件转为String，通过数据流输入到list里
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data.csv"))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] stringData = line.split(",");
                if (stringData[0].equals("TeachingRequirement")) {
                    data.get(0).add(new TeachingRequirement(stringData[1], Arrays.stream(stringData, 2, stringData.length).map(arr -> new Course(arr)).collect(Collectors.toList())));
                } else if (stringData[0].equals("Staff")) {
                    data.get(1).add(new Staff(stringData[1], Arrays.stream(stringData, 2, stringData.length).collect(Collectors.toList())));
                } else if (stringData[0].equals("Training")) {
                    data.get(2).add(new Training(stringData[1], Arrays.stream(stringData, 2, stringData.length).map(arr -> new Staff(arr)).collect(Collectors.toList())));
                } else if (stringData[0].equals("User")) {
                    data.get(3).add(new User(stringData[1], stringData[2], stringData[3]));
                } else if (stringData[0].equals("Course")) {
                    data.get(4).add(new Course(stringData[1], Arrays.stream(stringData, 2, stringData.length).map(arr -> new Staff(arr)).collect(Collectors.toList())));
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
        for (TeachingRequirement t : dataToTeachingRequirementList(data)) {
            stringData.add(
                    Stream.concat(Stream.of("TeachingRequirement"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (Staff t : dataToStaffList(data)) {
            stringData.add(
                    Stream.concat(Stream.of("Staff"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (Training t : dataToTrainingList(data)) {
            stringData.add(
                    Stream.concat(Stream.of("Training"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (User t : dataToUserList(data)) {
            stringData.add(
                Stream.concat(Stream.of("User"), Arrays.stream(t.toString().split(",")))
                        .toArray(String[]::new)
            );
        }

        for (Course t : dataToCourseList(data)) {
            stringData.add(
                Stream.concat(Stream.of("Course"), Arrays.stream(t.toString().split(",")))
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

                String[] commands = {"getTeachingRequirement", "addTeachingRequirement", "deleteTeachingRequirement", "exit"};

                System.out.println("\nThis is the menu of classDirector : ");
                for (int i = 0; i < 4; ++i) {
                    System.out.println("input " + i + " ->> " + commands[i]);
                }
                System.out.println("Please select your operation: ");

                String input = scanner.nextLine();

                if (input.equals(commands[0]) || input.equals("0")) {
                    System.out.println("Selected operation: " + commands[0] + "\n");
                    classDirector.getTeachingRequirement(dataToTeachingRequirementList(data));
                } else if (input.equals(commands[1]) || input.equals("1")) {
                    System.out.println("Selected operation: " + commands[1] + "\n");
                    System.out.println("Please add or change a teachingRequirement with name and course: ");
                    // classDirector.addTeachingRequirement(dataToTeachingRequirementList(data), scanner.nextLine().trim());
                } else if (input.equals(commands[2]) || input.equals("2")) {
                    System.out.println("Selected operation: " + commands[2] + "\n");
                    System.out.println("Please delte a teachingRequirement with name: ");
                    // classDirector.deleteTeachingRequirement(dataToTeachingRequirementList(data), scanner.nextLine().trim());
                } else if (input.equals(commands[3]) || input.equals("3")) {
                    System.out.println("Selected operation: " + commands[3] + "\n");
                    break;
                } else {
                    System.out.println("Please input a valid command");
                }

            } else if (user.getType().equals("Administrator")) {

                Administrator administrator = (Administrator)user;

                //展示可以输出的命令
                //调用administrator的方法

                String[] commands = {"getTeachingRequirement", "getStaffList", "getTrainingList", "addTraining", "deleteTraining", "getCoursegList", "addCourse","deleteCourse","exit"};
                
                System.out.println("\nThis is the menu of administrator : ");
                for (int i = 0; i < 9; ++i) {
                    System.out.println("input " + i + " ->> " + commands[i]);
                }
                System.out.println("Please select your operation: ");
                
                String input = scanner.nextLine();
                
                if (input.equals(commands[0]) || input.equals("0")) {
                    System.out.println("Selected operation: " + commands[0] + "\n");
                    administrator.getTeachingRequirement(dataToTeachingRequirementList(data));
                } else if (input.equals(commands[1]) || input.equals("1")) {
                    System.out.println("Selected operation: " + commands[1] + "\n");
                    // administrator.getTeachingRequirement(dataToTeachingRequirementList(data));
                } else if (input.equals(commands[2]) || input.equals("2")) {
                    System.out.println("Selected operation: " + commands[2] + "\n");
                    // administrator.getTrainingList(dataToTrainingList(data));
                } else if (input.equals(commands[3]) || input.equals("3")) {
                    System.out.println("Selected operation: " + commands[3] + "\n");
                    System.out.println("Please add or change a training with name and staff: ");
                    // administrator.addTraining(dataToTrainingList(data), scanner.nextLine().trim());
                } else if (input.equals(commands[4]) || input.equals("4")) {
                    System.out.println("Selected operation: " + commands[4] + "\n");
                    System.out.println("Please delete a training with name: ");
                    // administrator.deleteTraining(dataToTrainingList(data), scanner.nextLine().trim());
                } else if (input.equals(commands[5]) || input.equals("5")) {
                    System.out.println("Selected operation: " + commands[5] + "\n");
                    // administrator.getCoursegList(dataToCourseList(data));
                } else if (input.equals(commands[6]) || input.equals("6")) {
                    System.out.println("Selected operation: " + commands[6] + "\n");
                    System.out.println("Please add or change a course with name and staff: ");
                    // administrator.addCourse(dataToCourseList(data), scanner.nextLine().trim());
                } else if (input.equals(commands[7]) || input.equals("7")) {
                    System.out.println("Selected operation: " + commands[7] + "\n");
                    System.out.println("Please delete a course with name: ");
                    // administrator.deleteCourse(dataToCourseList(data), scanner.nextLine().trim());
                } else if (input.equals(commands[8]) || input.equals("8")) {
                    System.out.println("Selected operation: " + commands[6] + "\n");
                    break;
                } else {
                    System.out.println("Please input a valid command");
                }
            }
        }

        //结束程序
        System.out.println("See you latter ~~~ ");
    }

    //将抽象类的list转为具体数据类的list，本质是强制类型转化
    public List<TeachingRequirement> dataToTeachingRequirementList(List<List<Object>> data) {
        return data.get(0).stream().map(obj -> (TeachingRequirement) obj).collect(Collectors.toList());
    }

    public List<Staff> dataToStaffList(List<List<Object>> data) {
        return data.get(1).stream().map(obj -> (Staff) obj).collect(Collectors.toList());
    }

    public List<Training> dataToTrainingList(List<List<Object>> data) {
        return data.get(2).stream().map(obj -> (Training) obj).collect(Collectors.toList());
    }

    public List<User> dataToUserList(List<List<Object>> data) {
        return data.get(3).stream().map(obj -> (User) obj).collect(Collectors.toList());
    }

    public List<Course> dataToCourseList(List<List<Object>> data) {
        return data.get(4).stream().map(obj -> (Course) obj).collect(Collectors.toList());
    }
}
