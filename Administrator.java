import java.util.List;

public class Administrator extends User {

    private List<Training> trainingList;
    private List<Training> staffList;
    private List<Staff> selectedStaff;

    public Administrator (String username, String password) {
        super(username, password, "Administrator");
    }
    /*
     * getStaff()
     * selectStaff()
     * getTraining()
     * addTraining()
     * deleteTraining()
     * editTraining()
     */
}
