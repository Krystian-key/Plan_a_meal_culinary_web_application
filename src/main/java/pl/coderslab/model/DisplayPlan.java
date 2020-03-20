package pl.coderslab.model;

import java.util.List;
import java.util.Map;

public class DisplayPlan {
    private Plan plan;
    private Map<String,List<DetailsPlan>> planDetails;

    public DisplayPlan() {
    }

    public DisplayPlan(Plan plan, Map<String, List<DetailsPlan>> planDetails) {
        this.plan = plan;
        this.planDetails = planDetails;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Map<String, List<DetailsPlan>> getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(Map<String, List<DetailsPlan>> planDetails) {
        this.planDetails = planDetails;
    }
}

