import java.util.List;

public class ClassDirector extends User {
    private List<TeachingRequirement> teachingRequirementList;

    public ClassDirector(String username, String password) {
        super(username, password, "classDirector");
    }

    /* 
     * addTeachingRequirement()
     * deleteTeachingRequirement()
     * editTeachingRequirement()
     */
}
