import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
                    staffData.add(new Staff(data[1], Arrays.stream(data, 2, data.length).collect(Collectors.toList())));
                } else if (data[0].equals("Training")) {
                    trainingData.add(new Training(data[1], Arrays.stream(data, 2, data.length).map(arr -> new Staff(arr)).collect(Collectors.toList())));
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
        String filePath = "data.csv";

        List<String[]> stringData = new ArrayList<String[]> ();
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
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            stringData.stream().map(row -> String.join(",", row)).forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TeachingRequirement> dataToTeachingRequirements(List<List<Object>> data) {
        return data.get(0).stream().map(obj -> (TeachingRequirement) obj).collect(Collectors.toList());
    }

    public List<Staff> dataToStaffs(List<List<Object>> data) {
        return data.get(1).stream().map(obj -> (Staff) obj).collect(Collectors.toList());
    }

    public List<Training> dataToTrainings(List<List<Object>> data) {
        return data.get(2).stream().map(obj -> (Training) obj).collect(Collectors.toList());
    }

}