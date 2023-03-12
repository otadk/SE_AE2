import java.util.List;

public class Administrator extends User {


    public Administrator (String username, String password) {
        super(username, password, "Administrator");
    }

     public void getStaffList(List<Staff> data){
        for (int i = 0; i < data.size(); ++i) {
            System.out.println("Staff " + i + " :: " + data.get(i).toString());
        }
    }    

    public void getTrainingList(List<Training> data){
        for (int i = 0; i < data.size(); ++i) {
            System.out.println(data.get(i).toString());
        }
    }

    public void addTraining(List<Training> data, String input) {


        String[] parts = input.split(",");
        String trainingName = parts[0];
        String staffName = parts[1];
    
        // Create a new Staff object with the given name
        Staff staff = new Staff(staffName);
    
        // Find the Training object in the list with the given name
        Training matchingTraining = null;
        for (Training training : data) {
            if (training.getName().equals(trainingName)) {
                matchingTraining = training;
                break;
            }
        }
    
        if (matchingTraining != null) {
            // Add the new Staff object to the matching Training object's staff list
            // matchingTraining.addStaffToList(staff);
            List<Staff> newList = matchingTraining.getStaffList();
            newList.add(staff);
            matchingTraining.setStaffList(newList);
            System.out.println(data);
        } else {
            // If the matching Training object wasn't found, create a new one with the given name
            Training newAddTraining = new Training(trainingName);
            List<Staff> newList = newAddTraining.getStaffList();
            newList.add(staff);
            newAddTraining.setStaffList(newList);
            data.add(newAddTraining);
            System.out.println(data);
        }
    }
    
    
    public void deleteTraining(List<Training> data, String input) {
        Training matchingTraining = null;
        Staff matchingStaff = null;
    
        String[] parts = input.split(",");
        String trainingName = parts[0];
        String staffName = parts[1];

        // Find the training program and staff member to remove
        for (Training training : data) {
            for (Staff staff : training.getStaffList()) {
                if (staff.getName().equals(staffName)) {
                    matchingStaff = staff;
                    break;
                }
            }
            if (training.getName().equals(trainingName)) {
                matchingTraining = training;
                break;
            }
        }
    
        if (matchingTraining != null && matchingStaff != null) {
            // Remove the staff member from the training program's staff list
            List<Staff> staffList = matchingTraining.getStaffList();
            staffList.remove(matchingStaff);
            matchingTraining.setStaffList(staffList);
            System.out.println(data);
        } else {
            System.out.println("Staff member or training program not found.");
        }
    }

    public void addCourse(List<Course> data, String input) {

        String[] parts = input.split(",");
        String courseName = parts[0];
        String staffName = parts[1];
    
        // Create a new Staff object with the given name
        Staff staff = new Staff(staffName);
    
        // Find the Course object in the list with the given name
        Course matchingCourse = null;
        for (Course course : data) {
            if (course.getName().equals(courseName)) {
                matchingCourse = course;
                break;
            }
        }
    
        if (matchingCourse != null) {
            // Add the new Staff object to the matching Course object's staff list
            // matchingCourse.addStaffToList(staff);
            List<Staff> newList = matchingCourse.getStaffList();
            newList.add(staff);
            matchingCourse.setStaffList(newList);
            System.out.println(data);
        } else {
            // If the matching Course object wasn't found, create a new one with the given name
            Course newAddCourse = new Course(courseName);
            List<Staff> newList = newAddCourse.getStaffList();
            newList.add(staff);
            newAddCourse.setStaffList(newList);
            data.add(newAddCourse);
            System.out.println(data);
        }
    }
    
    public void deleteCourse(List<Course> data, String input) {
        Course matchingCourse = null;
        Staff matchingStaff = null;
    
        String[] parts = input.split(",");
        String courseName = parts[0];
        String staffName = parts[1];

        // Find the training program and staff member to remove
        for (Course course : data) {
            for (Staff staff : course.getStaffList()) {
                if (staff.getName().equals(staffName)) {
                    matchingStaff = staff;
                    break;
                }
            }
            if (course.getName().equals(courseName)) {
                matchingCourse = course;
                break;
            }
        }
    
        if (matchingCourse != null && matchingStaff != null) {
            // Remove the staff member from the training program's staff list
            List<Staff> staffList = matchingCourse.getStaffList();
            staffList.remove(matchingStaff);
            matchingCourse.setStaffList(staffList);
            System.out.println("adm"+data);
        } else {
            System.out.println("Staff member or course program not found.");
        }
    }


}
