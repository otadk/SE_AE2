import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassDirector extends User {

    public ClassDirector(String username, String password) {
        super(username, password, "ClassDirector");
    }

    // When the classDirector enters a new TeachingRequirement, add it to the list
    public List<TeachingRequirement> addTeachingRequirement(List<TeachingRequirement> data, String input){
        // Determine if the input format is correct
        if(input.contains(",")){
            String [] stringData = input.split(",");
            TeachingRequirement tr = new TeachingRequirement(stringData[0].trim(), Arrays.stream(stringData, 1, stringData.length).map(arr -> new Course(arr.trim())).collect(Collectors.toList()));
            data.add(tr);
            System.out.println("add " + tr.getName() + " successfully");
            return data;
        }
        System.out.println("Wrong format, please enter the course name and split it with a comma");
        return data;
    }

    // When the classDirector wants to delete an original TeachingRequirement, delete it in the list。
    public List<TeachingRequirement> deleteTeachingRequirement(List<TeachingRequirement> data, String input){
        // Find the TeachingRequirement to remove
        for (TeachingRequirement tr : data) {
            if (tr.getName().equals(input)) {
                data.remove(tr);
                System.out.println("Remove " + input + " successfully!");
                return data;
            }
        }

        System.out.println("Can not find " + input);
        return data;
    }
}
