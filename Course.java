import java.util.List;
import java.util.ArrayList;

public class Course {

    private String name;
    private List<Staff> staffList;

    public Course(String data) {
        this.name = data;
        this.staffList = new ArrayList<Staff> ();
    }

    public Course(String data, List<Staff> staffList) {
        this.name = data;
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

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
