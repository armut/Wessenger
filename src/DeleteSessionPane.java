import java.awt.*;

/**
 * zamma on 4/4/18.
 */
public class DeleteSessionPane extends SessionPaneBase {
    public DeleteSessionPane(Color bgColor, int width, int height) {
        super(bgColor, width, height);
        super.sessionTable.setListener(new DeleteSessionListHandler(super.sessionTable, tableModel));
    }
}
