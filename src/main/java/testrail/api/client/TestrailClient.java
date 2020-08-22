package testrail.api.client;

import lombok.Getter;
import testrail.api.client.exceptions.ClientInstanceException;

@Getter
public class TestrailClient {
    private static APIClient apiClient;

    private TestrailClient(Authentication authentication) {
        apiClient = new APIClient(authentication.serverUrl);
        apiClient.setUser(authentication.userName);
        apiClient.setPassword(authentication.password);
    }

    public static APIClient getInstance() {
        if (apiClient == null) {
            throw new ClientInstanceException("Call new TestrailClient.Authentication().auth().build() "
                    + "before calling getInstance");
        }
        return apiClient;
    }

    public static class Authentication {
        private String serverUrl;
        private String userName;
        private String password;

        public Authentication auth(String serverUrl, String userName, String password) {
            this.serverUrl = serverUrl;
            this.userName = userName;
            this.password = password;
            return this;
        }

        public APIClient build() {
            new TestrailClient(this);
            return apiClient;
        }
    }
}
