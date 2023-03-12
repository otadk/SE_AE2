import java.util.List;

public class User {

    private String username;  //用户名
    private String password;  //用户密码
    private String type;      //用户类型，子类创建时会提供

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    
    //检查用户名和用户密码是否正确
    public boolean check(String username, String password) {
        return this.username.equals(username.trim()) && this.password.equals(password.trim());
    }

    //获取用户类型
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
