import java.util.List;
import java.util.ArrayList;

public class Training {
    private String name;
    private List<Staff> staffList;

    public Training(String name) {
        this.name = name;
        this.staffList = new ArrayList<Staff> (); 
    }

    public Training(String name, List<Staff> staffList) {
        this.name = name;
        this.staffList = staffList;
    }

    public String toString() {
        String result = "";
        result += this.name + ",";
        for (int i = 0; i < this.staffList.size(); ++i) {
            result += this.staffList.get(i).getName();
            if (i < this.staffList.size() - 1) {
                result += ",";
            }
        }
        return result;
    }

    public String getName() {
		return name;
	}
    
    /*
     * getName()
     * setName()
     * getStaffList()
     * setStaffList()
     */
    
}
