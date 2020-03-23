package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPE_QUERY = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String READ_RECIPE_BY_ADMIN_ID_QUERY = "SELECT * from recipe where admin_id = ?;";
  //  private static final String UPDATE_RECIPE_QUERY = "UPDATE	recipe SET name = ? , ingredients = ?, description = ?, created = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE	id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE	recipe SET name = ? , ingredients = ?, description = ?, updated = ?, preparation_time = ?, preparation = ? WHERE id = ?;";
    private static final String FIND_ALL_RECIPE_FOR_USER = "SELECT  COUNT(*) FROM recipe WHERE admin_id= ? ";

    /**
     * Get recipe by id
     *
     * @param recipeId
     * @return
     */
    public Recipe readRecipe(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                    recipe.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }

    /**
     * Method return all recipe for current user
     *
     * @return Recipe
     */
    public List<Recipe> showAllRecipeUser(int userId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_BY_ADMIN_ID_QUERY)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipeToADD = new Recipe();
                    recipeToADD.setId(resultSet.getInt("id"));
                    recipeToADD.setName(resultSet.getString("name"));
                    recipeToADD.setIngredients(resultSet.getString("ingredients"));
                    recipeToADD.setDescription(resultSet.getString("description"));
                    recipeToADD.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                    recipeToADD.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                    recipeToADD.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipeToADD.setPreparation(resultSet.getString("preparation"));
                    recipeToADD.setAdminId(resultSet.getInt("admin_id"));
                    recipeList.add(recipeToADD);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    /**
     * Return all recipes
     *
     * @return
     */

    public List<Recipe> findAllRecipe() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                recipeToAdd.setUpdated(resultSet.getTimestamp("updated").toLocalDateTime());
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    /**
     * Create recipe
     *
     * @param recipe
     * @return
     */
    public Recipe createRecipe(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setString(4, recipe.getCreated().toString());
            insertStm.setString(5, recipe.getUpdated().toString());
            insertStm.setInt(6, recipe.getPreparationTime());
            insertStm.setString(7, recipe.getPreparation());
            insertStm.setInt(8, recipe.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
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
     * Remove recipe by id
     *
     * @param recipeId
     */
    public void deleteRecipe(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update recipe
     *
     * @param recipe
     */
    public void updateRecipe(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(7, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
        //    statement.setString(4, recipe.getCreated().toString());
            statement.setString(4, recipe.getUpdated().toString());
            statement.setInt(5, recipe.getPreparationTime());
            statement.setString(6, recipe.getPreparation());
        //    statement.setInt(8, recipe.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int quantityRecipeUser(int userId) {
        int quantityRecipe = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_FOR_USER)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    quantityRecipe = resultSet.getInt("COUNT(*)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantityRecipe;
    }


}