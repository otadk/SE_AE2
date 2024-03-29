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
        result += this.name;
        for (int i = 0; i < this.staffList.size(); ++i) {
            result += "," + this.staffList.get(i).getName();
        }
        return result;
    }

    public String getName() {
		return name;
	}

    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
    
}
