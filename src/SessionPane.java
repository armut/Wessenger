import java.awt.*;

/**
 * zamma on 4/4/18.
 */
public class SessionPane extends SessionPaneBase {
    public SessionPane(Color bgColor, int width, int height) {
        super(bgColor, width, height);
        super.sessionTable.setListener(new SessionListHandler(super.sessionTable, super.tableModel));
    }
}
