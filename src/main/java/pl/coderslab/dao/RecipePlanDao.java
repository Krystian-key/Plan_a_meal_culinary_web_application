package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipePlanDao {
    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id, meal_name,display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";
    private static final String FIND_ALL_RECIPE_PLAN_QUERY = "SELECT * FROM recipe_plan;";
    private static final String READ_RECIPE_PLAN_QUERY = "SELECT * from recipe_plan where id = ?;";
    private static final String READ_RECIPE_PLAN_BY_PLAN_ID_QUERY = "SELECT * from recipe_plan where plan_id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	recipe_plan SET recipe_id = ? , meal_name = ?, display_order = ?, day_name_id = ?, plan_id = ?  WHERE	id = ?;";
    private static final String FIND_LAST_ADDED_PLAN = "SELECT plan.name as name, day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "JOIN plan on plan.id = plan_id\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = 1) -- zamiast 1 należy wstawić id użytkownika (tabela admins) --\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    /**
     * Get recipe_plan by id
     *
     * @param recipePlanId
     * @return
     */
    public RecipePlan readRecipePlan(Integer recipePlanId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_PLAN_QUERY)
        ) {
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
     * Remove recipe_plan by id
     *
     * @param recipePlanId
     */
    public void deleteRecipePlan(Integer recipePlanId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, recipePlanId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("recipePlan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update recipe_plan
     *
     * @param recipePlan
     */
    public void updatePlan(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(6, recipePlan.getId());
            statement.setInt(1, recipePlan.getRecipeId());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayNameId());
            statement.setInt(4, recipePlan.getPlanId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return full information for last added plan
     *
     * @param adminId
     * @return
     */

    public HashMap<String, Object> lastAddedPlan(int adminId) {
        HashMap<String, Object> plansList = new HashMap<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LAST_ADDED_PLAN);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RecipePlan planToADD = new RecipePlan();
                DayName dayName = new DayName();
                Recipe recipe = new Recipe();
                Plan plan = new Plan();
                plan.setName(resultSet.getString("name"));
                dayName.setName(resultSet.getString("day_name"));
                planToADD.setMealName(resultSet.getString("meal_name"));
                recipe.setName(resultSet.getString("recipe_name"));
                recipe.setDescription(resultSet.getString("recipe_description"));
                plansList.put("name", plan.getName());
                plansList.put("day_name", dayName.getName());
                plansList.put("meal_name", planToADD.getMealName());
                plansList.put("recipe_name", recipe.getName());
                plansList.put("recipe_description", recipe.getDescription());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plansList;
    }
}