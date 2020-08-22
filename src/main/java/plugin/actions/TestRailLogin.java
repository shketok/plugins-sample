package plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import plugin.ui.TestrailAuthenticationForm;

public class TestRailLogin extends AnAction {

    @Override
    public void actionPerformed(final AnActionEvent e) {
        final TestrailAuthenticationForm form = new TestrailAuthenticationForm();
        form.setVisible(true);
    }


}
