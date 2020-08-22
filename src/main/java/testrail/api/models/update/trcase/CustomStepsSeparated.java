
package testrail.api.models.update.trcase;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CustomStepsSeparated {

    @SerializedName("content")
    private String mContent;
    @SerializedName("expected")
    private String mExpected;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getExpected() {
        return mExpected;
    }

    public void setExpected(String expected) {
        mExpected = expected;
    }

}
