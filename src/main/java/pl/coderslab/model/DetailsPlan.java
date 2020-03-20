package pl.coderslab.model;

public class DetailsPlan {
    private int id;
    private String dayName;
    private String mealName;
    private String recipeName;
    private String description;

    public DetailsPlan(int id, String dayName, String mealName, String recipeName, String description) {
        this.id = id;
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.description = description;
    }

    public DetailsPlan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
