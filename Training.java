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
