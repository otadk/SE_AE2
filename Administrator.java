import java.util.List;

public class Administrator extends User {

    private List<Training> trainingList;
    private List<Staff> staffList;
    private List<Staff> selectedStaff;

    public Administrator (String username, String password) {
        super(username, password, "Administrator");
    }

	//get the staff list
    public List<Staff> getStaff() {
    	System.out.println("test");
    	
        return staffList;
    }
    
    public void selectStaff(String name) {
        boolean flag1 = true;
        while (flag1) {
            for (Staff staff : staffList) {
                if (staff.getName().equals(name)) {
                    selectedStaff.add(staff);
                    break;
                } else {
                    System.out.println("Please enter the correct name.");
                }

            }
        }
    }

    public void deleteSelectStaff(String name) {
        boolean flag2 = true;
        while (flag2){
            for (Staff staff : selectedStaff) {
                flag2 = false;
                if (staff.getName().equals(name)) {
                    selectedStaff.remove(staff);
                    break;
                } else {
                    System.out.println("Please enter the correct name.");
                }
            }
        }
    }

    public void addTraining(String name) {
        Training training = new Training(name);
        trainingList.add(training);
    }

    public void deleteTraining(String name) {
        boolean flag3 = true;
        while (flag3){
            for (Training training : trainingList) {
                flag3 = false;
                if (training.getName().equals(name)) {
                    trainingList.remove(training);
                    break;
                } else {
                    System.out.println("Please enter the correct name.");
                }
            }
        }
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public List<Staff> getSelectedStaff() {
        return selectedStaff;
    }
}
