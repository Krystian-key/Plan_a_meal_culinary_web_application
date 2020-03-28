package pl.coderslab.dao;
import com.google.common.collect.LinkedHashMultimap;
import pl.coderslab.model.*;
import pl.coderslab.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import pl.coderslab.model.DetailsPlan;

public class RecipePlanDao {
    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id, meal_name,display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";
    private static final String DELETE_RECIPE_PLAN_BY_PLAN_ID_QUERY = "DELETE FROM recipe_plan where plan_id = ?;";
    private static final String FIND_ALL_RECIPE_PLAN_QUERY = "SELECT * FROM recipe_plan;";
    private static final String READ_RECIPE_PLAN_QUERY = "SELECT * from recipe_plan where id = ?;";
    private static final String READ_RECIPE_PLAN_BY_PLAN_ID_QUERY = "SELECT * from recipe_plan where recipe_id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	recipe_plan SET recipe_id = ? , meal_name = ?, day_name_id = ? WHERE id = ?;";
    private static final String FIND_LAST_ADDED_PLAN_QUERY = "SELECT plan.name as name, plan.description as description, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe_plan.id, recipe.id as recipe_id\n" +
            "FROM recipe_plan\n" +
            "    JOIN plan on plan.id = plan_id\n" +
            "    JOIN day_name on day_name.id = day_name_id\n" +
            "    JOIN recipe on recipe.id = recipe_id\n" +
            "    WHERE recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER BY  day_name.display_order, recipe_plan.display_order;";
    private static final String FIND_PLAN_ALL_DETAILS_BY_ID_QUERY = "SELECT plan.name as name, plan.description as description, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe_plan.id as id, recipe.id as recipe_id \n" +
            "FROM recipe_plan\n" +
            "    JOIN plan on plan.id = plan_id\n" +
            "    JOIN day_name on day_name.id = day_name_id\n" +
            "    JOIN recipe on recipe.id = recipe_id\n" +
            "    WHERE plan_id = ?\n" +
            "ORDER BY day_name.display_order, recipe_plan.display_order";

    /**
     * Get recipe_plan by id
     *
     * @param recipePlanId
     * @return
     */
    public RecipePlan readRecipePlan(Integer recipePlanId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, recipePlanId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipePlan.setId(resultSet.getInt("id"));
                    recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                    recipePlan.setDayNameId(resultSet.getInt("day_name_id"));
                    recipePlan.setPlanId(resultSet.getInt("plan_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipePlan;

    }

    /**
     * Method return all recipe_plans for current plan_id
     *
     * @return Plan
     */
    public List<RecipePlan> showAllRecipePlansUser(int planId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_PLAN_BY_PLAN_ID_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RecipePlan recipePlanToAdd = new RecipePlan();
                    recipePlanToAdd.setId(resultSet.getInt("id"));
                    recipePlanToAdd.setPlanId(resultSet.getInt("recipe_id"));
                    recipePlanToAdd.setMealName(resultSet.getString("meal_name"));
                    recipePlanToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                    recipePlanToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                    recipePlanToAdd.setDayNameId(resultSet.getInt("plan_id"));
                    recipePlanList.add(recipePlanToAdd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }

    /**
     * Return all recipe_plan
     *
     * @return
     */

    public List<RecipePlan> findAllRecipePlan() {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_PLAN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                RecipePlan recipePlanToAdd = new RecipePlan();
                recipePlanToAdd.setId(resultSet.getInt("id"));
                recipePlanToAdd.setPlanId(resultSet.getInt("recipe_id"));
                recipePlanToAdd.setMealName(resultSet.getString("meal_name"));
                recipePlanToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlanToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                recipePlanToAdd.setDayNameId(resultSet.getInt("plan_id"));
                recipePlanList.add(recipePlanToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }

    /**
     * Create recipe_plan
     *
     * @param recipePlan
     * @return
     */
    public RecipePlan createPlan(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipePlan.getRecipeId());
            insertStm.setString(2, recipePlan.getMealName());
            insertStm.setInt(3, recipePlan.getDisplayOrder());
            insertStm.setInt(4, recipePlan.getDayNameId());
            insertStm.setInt(5, recipePlan.getPlanId());

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Remove recipe_plan by plan_id
     *
     * @param id
     */
    public void deleteRecipePlan(Integer id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove recipe_plan by id
     *
     * @param id
     */
    public void deleteRecipePlanByPlanID(Integer id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_BY_PLAN_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update recipe_plan
     *
     * @param recipePlanList
     */
    public void updatePlan(List<RecipePlan> recipePlanList) {
        for (RecipePlan recipePlan : recipePlanList) {

            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
                statement.setInt(4, recipePlan.getId());
                statement.setInt(1, recipePlan.getRecipeId());
                statement.setString(2, recipePlan.getMealName());
                statement.setInt(3, recipePlan.getDayNameId());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for chose between  query with all details(set 1) or
     * query last added plan (set 2)
     * @param chose
     * @return
     */
    public String choseQuery(int chose){
        if(chose ==1 ){
            return FIND_PLAN_ALL_DETAILS_BY_ID_QUERY;
        } else if (chose == 2){
            return FIND_LAST_ADDED_PLAN_QUERY;
        }
        return null;
    }
    /**
     * Return all details pf plan
     * @param id
     * @return
     */
    public DisplayPlan detailsPlan(int id, String query) {
        DisplayPlan displayPlan = new DisplayPlan();
        Plan plan = new Plan();
        Multimap displayMap = LinkedHashMultimap.create();
        int i = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    i++;
                    String dayName = resultSet.getString("day_name");
                    if (i == 1) {
                        plan.setName(resultSet.getString("name"));
                        plan.setDescription(resultSet.getString("description"));
                    }
                    DetailsPlan detailsPlan = new DetailsPlan();
                    detailsPlan.setMealName(resultSet.getString("meal_name"));
                    detailsPlan.setRecipeName(resultSet.getString("recipe_name"));
                    detailsPlan.setDayName(dayName);
                    detailsPlan.setId(resultSet.getInt("id"));
                    detailsPlan.setRecipeId(resultSet.getInt("recipe_id"));
                    displayMap.put(dayName,detailsPlan);
                }
            }
            displayPlan.setPlan(plan);
            displayPlan.setDisplayMap(displayMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return displayPlan;
    }
}