import java.util.List;

public class TeachingRequirement {

    private String name;
    private List<Course> courseList;
    
    public TeachingRequirement(String data, List<Course> courseList) {
        this.name = data;
        this.courseList = courseList;
    }

    public String toString() {
        String result = "";
        result += this.name;
        for (int i = 0; i < this.courseList.size(); ++i) {
            result += "," + this.courseList.get(i).getName();
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}
