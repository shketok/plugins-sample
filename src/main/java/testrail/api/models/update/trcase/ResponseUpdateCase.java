
package testrail.api.models.update.trcase;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseUpdateCase {

    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("created_on")
    private Long mCreatedOn;
    @SerializedName("custom_expected")
    private Object mCustomExpected;
    @SerializedName("custom_goals")
    private Object mCustomGoals;
    @SerializedName("custom_mission")
    private Object mCustomMission;
    @SerializedName("custom_preconds")
    private Object mCustomPreconds;
    @SerializedName("custom_prod_valid")
    private Boolean mCustomProdValid;
    @SerializedName("custom_status_at")
    private Long mCustomStatusAt;
    @SerializedName("custom_status_ft")
    private Long mCustomStatusFt;
    @SerializedName("custom_steps")
    private Object mCustomSteps;
    @SerializedName("custom_steps_separated")
    private List<CustomStepsSeparated> mCustomStepsSeparated;
    @SerializedName("display_order")
    private Long mDisplayOrder;
    @SerializedName("estimate")
    private Object mEstimate;
    @SerializedName("estimate_forecast")
    private Object mEstimateForecast;
    @SerializedName("id")
    private Long mId;
    @SerializedName("milestone_id")
    private Object mMilestoneId;
    @SerializedName("priority_id")
    private Long mPriorityId;
    @SerializedName("refs")
    private String mRefs;
    @SerializedName("section_id")
    private Long mSectionId;
    @SerializedName("suite_id")
    private Long mSuiteId;
    @SerializedName("template_id")
    private Long mTemplateId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type_id")
    private Long mTypeId;
    @SerializedName("updated_by")
    private Long mUpdatedBy;
    @SerializedName("updated_on")
    private Long mUpdatedOn;

    public Long getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        mCreatedBy = createdBy;
    }

    public Long getCreatedOn() {
        return mCreatedOn;
    }

    public void setCreatedOn(Long createdOn) {
        mCreatedOn = createdOn;
    }

    public Object getCustomExpected() {
        return mCustomExpected;
    }

    public void setCustomExpected(Object customExpected) {
        mCustomExpected = customExpected;
    }

    public Object getCustomGoals() {
        return mCustomGoals;
    }

    public void setCustomGoals(Object customGoals) {
        mCustomGoals = customGoals;
    }

    public Object getCustomMission() {
        return mCustomMission;
    }

    public void setCustomMission(Object customMission) {
        mCustomMission = customMission;
    }

    public Object getCustomPreconds() {
        return mCustomPreconds;
    }

    public void setCustomPreconds(Object customPreconds) {
        mCustomPreconds = customPreconds;
    }

    public Boolean getCustomProdValid() {
        return mCustomProdValid;
    }

    public void setCustomProdValid(Boolean customProdValid) {
        mCustomProdValid = customProdValid;
    }

    public Long getCustomStatusAt() {
        return mCustomStatusAt;
    }

    public void setCustomStatusAt(Long customStatusAt) {
        mCustomStatusAt = customStatusAt;
    }

    public Long getCustomStatusFt() {
        return mCustomStatusFt;
    }

    public void setCustomStatusFt(Long customStatusFt) {
        mCustomStatusFt = customStatusFt;
    }

    public Object getCustomSteps() {
        return mCustomSteps;
    }

    public void setCustomSteps(Object customSteps) {
        mCustomSteps = customSteps;
    }

    public List<CustomStepsSeparated> getCustomStepsSeparated() {
        return mCustomStepsSeparated;
    }

    public void setCustomStepsSeparated(List<CustomStepsSeparated> customStepsSeparated) {
        mCustomStepsSeparated = customStepsSeparated;
    }

    public Long getDisplayOrder() {
        return mDisplayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        mDisplayOrder = displayOrder;
    }

    public Object getEstimate() {
        return mEstimate;
    }

    public void setEstimate(Object estimate) {
        mEstimate = estimate;
    }

    public Object getEstimateForecast() {
        return mEstimateForecast;
    }

    public void setEstimateForecast(Object estimateForecast) {
        mEstimateForecast = estimateForecast;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Object getMilestoneId() {
        return mMilestoneId;
    }

    public void setMilestoneId(Object milestoneId) {
        mMilestoneId = milestoneId;
    }

    public Long getPriorityId() {
        return mPriorityId;
    }

    public void setPriorityId(Long priorityId) {
        mPriorityId = priorityId;
    }

    public String getRefs() {
        return mRefs;
    }

    public void setRefs(String refs) {
        mRefs = refs;
    }

    public Long getSectionId() {
        return mSectionId;
    }

    public void setSectionId(Long sectionId) {
        mSectionId = sectionId;
    }

    public Long getSuiteId() {
        return mSuiteId;
    }

    public void setSuiteId(Long suiteId) {
        mSuiteId = suiteId;
    }

    public Long getTemplateId() {
        return mTemplateId;
    }

    public void setTemplateId(Long templateId) {
        mTemplateId = templateId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getTypeId() {
        return mTypeId;
    }

    public void setTypeId(Long typeId) {
        mTypeId = typeId;
    }

    public Long getUpdatedBy() {
        return mUpdatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        mUpdatedBy = updatedBy;
    }

    public Long getUpdatedOn() {
        return mUpdatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        mUpdatedOn = updatedOn;
    }

}
