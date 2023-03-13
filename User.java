import java.util.List;

public class User {

    private String username;
    private String password;
    private String type; // user type, provided by subclass

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    
    // check username and password
    public boolean check(String username, String password) {
        return this.username.equals(username.trim()) && this.password.equals(password.trim());
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        return username + "," + password + "," + type;
    }

    public void getTeachingRequirement(List<TeachingRequirement> data) {
        for (int i = 0; i < data.size(); ++i) {
            System.out.print("TeachingRequirement " + String.format("%3d", i) + " :: " + data.get(i).getName() + " ---");
            for (Course course : data.get(i).getCourseList()) {
                System.out.print(" | " + course.getName());
            }
            System.out.println();
        }
    }
}
