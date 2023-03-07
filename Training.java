import java.util.List;

public class Training {
    private String name;
    private List<Staff> staffList;

    public Training(String name, List<Staff> staffList) {
        this.name = name;
        this.staffList = staffList;
    }

    public String toString() {
        String result = "";
        result += this.name + "\n--";
        for (int i = 0; i < this.staffList.size(); ++i) {
            result += this.staffList.get(i).getName();
            if (i < this.staffList.size() - 1) {
                result += ",";
            }
        }
        return result;
    }
    
    /*
     * getName()
     * setName()
     * getStaffList()
     * setStaffList()
     */
    
}
