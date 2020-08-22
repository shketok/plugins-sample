package plugin.ui;

import com.intellij.openapi.diagnostic.DefaultLogger;
import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import java.awt.*;

public class TestRailSetStatusesProgressBar extends JDialog  {

    private static final Logger LOGGER = new DefaultLogger(TestRailSetStatusesProgressBar.class.getName());

    private JPanel rootPanel;
    private JProgressBar progressBar;
    private JButton cancelButton;
    private JLabel statusLabel;


    public TestRailSetStatusesProgressBar() {
        setTitle("TestRail set statuses progress bar");
        setSize(500, 200);
        add(rootPanel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        cancelButton.addActionListener(e -> handleCancelButton());
    }

    private void handleCancelButton() {
        close();
        System.exit(0);
    }

    public void setProgressBarMax(int max) {
        progressBar.setMaximum(max);
    }

    public void updateProgressBar(int value) {
        progressBar.setValue(value);
        if (value == progressBar.getMaximum()) {
            close();
        }
    }

    public void close() {
        setVisible(false);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }

}
