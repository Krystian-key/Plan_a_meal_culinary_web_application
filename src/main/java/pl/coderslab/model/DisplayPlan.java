package pl.coderslab.model;

import com.google.common.collect.Multimap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DisplayPlan {
    private Plan plan;
    private Multimap displayMap;

    public DisplayPlan() {
    }

    public DisplayPlan(Plan plan, Multimap displayMap) {
        this.plan = plan;
        this.displayMap = displayMap;
    }

    public Multimap getDisplayMap() {
        return displayMap;
    }

    public void setDisplayMap(Multimap displayMap) {
        this.displayMap = displayMap;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}

