package plugin.ui;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.DefaultLogger;
import com.intellij.openapi.diagnostic.Logger;
import org.apache.commons.lang3.StringUtils;
import testrail.api.client.APIClient;
import testrail.api.client.TestrailClient;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class TestrailAuthenticationForm extends JDialog {

    private static final Logger LOGGER = new DefaultLogger(TestrailAuthenticationForm.class.getName());

    private static final String ENDPOINT_KEY = "shketok.testrail.url";
    private static final String USERNAME_KEY = "shketok.testrail.username";
    private static final String PASSWORD_KEY = "shketok.testrail.password";

    private JButton saveButton;
    private JButton testButton;
    private JButton cancelButton;
    private JPanel rootPanel;

    private JTextField endpointField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField statusField;

    public TestrailAuthenticationForm() {
        setTitle("Testrail authorization");
        setSize(500, 200);
        add(rootPanel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        cancelButton.addActionListener(e -> handleCancelButton());
        testButton.addActionListener(e -> handleTestButton());
        saveButton.addActionListener(e -> handleOkButton());


        endpointField.setText(PropertiesComponent.getInstance().getValue(ENDPOINT_KEY));
        usernameField.setText(PropertiesComponent.getInstance().getValue(USERNAME_KEY));
        passwordField.setText(PropertiesComponent.getInstance().getValue(PASSWORD_KEY));

    }

    private void handleCancelButton() {
        setVisible(false);
    }

    private void handleTestButton() {
        Optional<String> username = getTestrailUsername();
        if (username.isPresent()) {
            statusField.setText(String.format("authorized as '%s'", username.get()));
        } else {
            statusField.setText("Bad credentials");
        }
    }

    private void handleOkButton() {
        Optional<String> username = getTestrailUsername();
        if (username.isPresent()) {
            PropertiesComponent.getInstance().setValue(ENDPOINT_KEY, endpointField.getText());
            PropertiesComponent.getInstance().setValue(USERNAME_KEY, usernameField.getText());
            PropertiesComponent.getInstance().setValue(PASSWORD_KEY, String.valueOf(passwordField.getPassword()));
            setVisible(false);
        } else {
            statusField.setText("Bad credentials");
        }
    }

    private Optional<String> getTestrailUsername() {
        if (isEmpty(endpointField) && isEmpty(usernameField) && isEmpty(passwordField)) {
            return Optional.empty();
        }
        final APIClient client = getTestrailClient();
        try {
            return Optional.of(client.getUser());
        } catch (Exception e) {
            LOGGER.error(e);
            return Optional.empty();
        }
    }

    private boolean isEmpty(final JTextField field) {
        return StringUtils.isBlank(field.getText());
    }

    private boolean isEmpty(final JPasswordField field) {
        return StringUtils.isBlank(String.copyValueOf(field.getPassword()));
    }

    private APIClient getTestrailClient() {
        return new TestrailClient.Authentication()
                .auth(endpointField.getText(), usernameField.getText(), String.copyValueOf(passwordField.getPassword()))
                .build();
    }
}
