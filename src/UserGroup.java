import java.util.ArrayList;

/**
 * zamma on 4/1/18.
 */
public class UserGroup {
    private ArrayList<User> users;
    private String name;
    private int id;
    
    public UserGroup() {
        users = new ArrayList<>();
    }
    
    public UserGroup(String name, int id) {
        users = new ArrayList<>();
        this.name = name;
        this.id = id;
    }
    
    public void addUser(User user) {
        users.add(user);
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }
    
    public String getName() { return name; }
    
    public int getId() { return id; }
    
    public String toString() {
        if (this.users.size() > 1)
            return this.name + " (group)";
        else if (this.users.size() == 1)
            return this.getUsers().get(0).toString();
        else
            return "namelessGroup";
    }
    
    public class User {
        private String nickName;
        private int id;
        public User(String nickName, int id) {
            this.nickName = nickName;
            this.id = id;
        }
        public String getNickName() { return nickName; }
        public int getId() { return id; }
        
        public String toString() {
            return nickName;
        }
    }
}
