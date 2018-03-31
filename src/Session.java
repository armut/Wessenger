/**
 * zamma on 3/31/18.
 */
public class Session {
    private String sessionName;
    private int sessionId;
    private int[] users;
    
    public Session(String sessionName, int sessionId) {
        this.sessionName = sessionName;
        this.sessionId = sessionId;
    }
    
    public Session(String sessionName, int[] users, int sessionId) {
        this.sessionName = sessionName;
        this.users = users;
        this.sessionId = sessionId;
    }
    
    public String getSessionName() {
        return sessionName;
    }
    
    public int[] getSessionUsers() {
        return users;
    }
    
    public int getSessionId() { return sessionId; }
}
