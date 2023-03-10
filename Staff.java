import java.util.List;

public class Staff {
    private String name;
    private List<String> skills;

    public Staff(String name) {
        this.name = name;
    }

    public Staff(String name, List<String> skills) {
        this.name   = name;
        this.skills = skills;
    }

    public String toString() {
        String result = "";
        result += this.name + ",";
        for (int i = 0; i < this.skills.size(); ++i) {
            result += this.skills.get(i);
            if (i < this.skills.size() - 1) {
                result += ",";
            }
        }
        return result;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getSkills() {
        return skills;
    }
}
