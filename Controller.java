import java.util.List;
import java.util.ArrayList;

public class Controller {
    private User user;

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public <T> List<T> loadData(T loadClassName) {
        List<T> s = new ArrayList<T> ();
        return s;
    }
}