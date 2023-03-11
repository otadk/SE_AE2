import java.util.List;

public class Administrator extends User {

    private List<Training> trainingList;
    private List<Staff> staffList;
    private List<Staff> selectedStaff;

    public Administrator (String username, String password) {
        super(username, password, "Administrator");
    }

    // 完成以下调用，其他的函数可以删除
    // administrator.getStaffList(dataToTeachingRequirementList(data));
    // administrator.getTrainingList(dataToTrainingList(data));
    // administrator.addTraining(dataToTrainingList(data), scanner.nextLine().trim());
    // administrator.deleteTraining(dataToTrainingList(data), scanner.nextLine().trim());
    // administrator.getCourseList(dataToCourseList(data));
    // administrator.addCourse(dataToCourseList(data), scanner.nextLine().trim());
    // administrator.deleteCourse(dataToCourseList(data), scanner.nextLine().trim());

    /*
     * getStaffList()
     * getTrainingList()
     * addTraining()
     * deleteTraining()
     * getCourseList()
     * addCourse()
     * deleteCourse()
     */

	//get the staff list  这里直接System.out.println()不返回内容
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
