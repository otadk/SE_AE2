import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Controller {
    private User user;

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public List<List<Object>> loadData() {

        List<List<Object>> result = new ArrayList<List<Object>> ();
        List<Object> teachingRequirementData = new ArrayList<Object> ();
        List<Object> staffData = new ArrayList<Object> ();
        List<Object> trainingData = new ArrayList<Object> ();

        String filePath = "data.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if (data[0].equals("TeachingRequirement")) {
                    teachingRequirementData.add(new TeachingRequirement(data[1]));
                } else if (data[0].equals("Staff")) {
                    List<String> skills = new ArrayList<String> ();
                    skills.addAll(Arrays.asList(Arrays.copyOfRange(data, 2, data.length)));
                    staffData.add(new Staff(data[1], skills));
                } else if (data[0].equals("Training")) {
                    List<String> stringList = Arrays.asList(Arrays.copyOfRange(data, 2, data.length));
                    List<Staff> staffList = stringList.stream().map(arr -> new Staff(arr)).collect(Collectors.toList());
                    trainingData.add(new Training(data[1], staffList));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.add(teachingRequirementData);
        result.add(staffData);
        result.add(trainingData);
       
        return result;
    }

    public void saveData(List<List<Object>> data) {
        // String filePath = "data.csv";

        // List<String[]> stringData = new ArrayList<String[]> ();
        // String[] data0 = {"teachingRequirementData", "staffData", "trainingData"};
        // for (int i = 0; i < 3; ++i) {
        //     for (int j = 0; j < data.get(i).size(); ++j) {
        //     }
        // }

        // try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        //     stringData.stream().map(row -> String.join(",", row)).forEach(writer::println);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}