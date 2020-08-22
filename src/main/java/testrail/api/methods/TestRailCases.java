package testrail.api.methods;

import com.google.gson.Gson;
import testrail.Urls;
import testrail.api.client.TestrailClient;
import testrail.api.client.exceptions.APIException;
import testrail.api.models.get.trcase.ResponseGetCase;
import testrail.api.models.update.trcase.ResponseUpdateCase;

import java.io.IOException;
import java.util.Map;

public class TestRailCases {
    private static final Gson gson = new Gson();

    public static ResponseUpdateCase updateCase(String caseId, Map<String, Object> data) {
        try {
            return gson.fromJson(TestrailClient.getInstance().sendPost(Urls.UPDATE_CASE.getUrlPart(caseId),
                    data).toString(), ResponseUpdateCase.class);
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResponseGetCase getCase(String caseId) {
        try {
            return gson.fromJson(TestrailClient.getInstance().sendGet(Urls.GET_CASE.getUrlPart(caseId)).toString(),
                    ResponseGetCase.class);
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
