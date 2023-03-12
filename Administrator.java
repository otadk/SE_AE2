import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Administrator extends User {


    public Administrator (String username, String password) {
        super(username, password, "Administrator");
    }

     public void getStaffList(List<Staff> data){
        for (int i = 0; i < data.size(); ++i) {
            System.out.print("Staff " + String.format("%3d", i) + " :: " + data.get(i).getName() + " ---");
            for (String skill : data.get(i).getSkills()) {
                System.out.print(" | " + skill);
            }
            System.out.println();
        }
    }    

    public void getTrainingList(List<Training> data){
        for (int i = 0; i < data.size(); ++i) {
            System.out.print("Training " + String.format("%3d", i) + " :: " + data.get(i).getName() + " ---");
            for (Staff staff : data.get(i).getStaffList()) {
                System.out.print(" | " + staff.getName());
            }
            System.out.println();
        }
    }

    public List<Training> addTraining(List<Training> data, String input) {

        String[] stringData = input.split(",");

        // Create a new Staff object with the given name and staff
        Training training = new Training(stringData[0].trim(), Arrays.stream(stringData, 1, stringData.length).map(arr -> new Staff(arr.trim())).collect(Collectors.toList()));
        
        // Find the Training object in the list with the given name
        for (int i = 0; i < data.size(); ++i) {
            if (training.getName().equals(data.get(i).getName())) {
                data.set(i, training);
                System.out.println("change " + training.getName() + " successfully");
                return data;
            }
        }

        data.add(training);
        System.out.println("add " + training.getName() + " successfully");
        return data;
    }
    
    
    public List<Training> deleteTraining(List<Training> data, String input) {

        // Find the training program and staff member to remove
        for (Training training : data) {
            if (training.getName().equals(input)) {
                data.remove(training);
                System.out.println("Remove " + input + " successfully!");
                return data;
            }
        }

        System.out.println("Can not find " + input);
        return data;
    }

    public void getCourseList(List<Course> data) {
        for (int i = 0; i < data.size(); ++i) {
            System.out.print("Course " + String.format("%3d", i) + " :: " + data.get(i).getName() + " ---");
            for (Staff staff : data.get(i).getStaffList()) {
                System.out.print(" | " + staff.getName());
            }
            System.out.println();
        }
    }

    public List<Course> addCourse(List<Course> data, String input) {

        String[] stringData = input.split(",");

        // Create a new Staff object with the given name and staff
        Course course = new Course(stringData[0].trim(), Arrays.stream(stringData, 1, stringData.length).map(arr -> new Staff(arr.trim())).collect(Collectors.toList()));
        
        // Find the Course object in the list with the given name
        for (int i = 0; i < data.size(); ++i) {
            if (course.getName().equals(data.get(i).getName())) {
                data.set(i, course);
                System.out.println("change " + course.getName() + " successfully");
                return data;
            }
        }

        data.add(course);
        System.out.println("change " + course.getName() + " successfully");
        return data;
    }
    
    public List<Course> deleteCourse(List<Course> data, String input) {

        // Find the training program and staff member to remove
        for (Course course : data) {
            if (course.getName().equals(input)) {
                data.remove(course);
                System.out.println("Remove " + input + " successfully!");
                return data;
            }
        }

        System.out.println("Can not find " + input);
        return data;
    }


}
