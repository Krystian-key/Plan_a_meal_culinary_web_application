package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Book;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminsDao {

    private static final String CREATE_Admins_QUERY = "INSERT INTO admins(firstName,lastName,email,password,superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_Admins_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_Admins_QUERY = "SELECT * FROM admins;";
    private static final String READ_Admins_QUERY = "SELECT * from admins where id = ?;";
    private static final String UPDATE_Admins_QUERY = "UPDATE	admins SET title = ? , author = ?, isbn = ? WHERE id = ?;";

    /**
     * Get book by id
     *
     * @param adminsId
     * @return
     */
    public Admins readAdmins(Integer adminsId) {
        Admins admins = new Admins();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_Admins_QUERY)
        ) {
            statement.setInt(1, adminsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admins.setId(resultSet.getInt("id"));
                    admins.setFirstName(resultSet.getString("firstName"));
                    admins.setLastName(resultSet.getString("lastName"));
                    admins.setEmail(resultSet.getString("email"));
                    admins.setPassword(resultSet.getString("password"));
                    admins.setSuperadmin(resultSet.getInt("superadmin"));
                    admins.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;

    }

    /**
     * Return all admins
     *
     * @return
     */
    public List<Admins> findAllAdmins() {
        List<Admins> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_Admins_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admins adminsToAdd = new Admins();
                adminsToAdd.setId(resultSet.getInt("id"));
                adminsToAdd.setFirstName(resultSet.getString("firstName"));
                adminsToAdd.setLastName(resultSet.getString("lastName"));
                adminsToAdd.setEmail(resultSet.getString("email"));
                adminsToAdd.setPassword(resultSet.getString("password"));
                adminsToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminsToAdd.setEnable(resultSet.getInt("enable"));
                adminsList.add(adminsToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;

    }

    /**
     * Create admins
     *
     * @param admins
     * @return
     */
    public Admins create(Admins admins) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_Admins_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admins.getFirstName());
            insertStm.setString(2, admins.getLastName());
            insertStm.setString(3, admins.getEmail());
            insertStm.setString(4, admins.getPassword());
            insertStm.setInt(5, admins.getSuperadmin());
            insertStm.setInt(6, admins.getEnable());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admins.setId(generatedKeys.getInt(1));
                    return admins;
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
     * Remove admins by id
     *
     * @param adminsId
     */
    public void delete(Integer adminsId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_Admins_QUERY)) {
            statement.setInt(1, adminsId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update admins
     *
     * @param admins
     */
    public void update(Admins admins) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_Admins_QUERY)) {
            statement.setInt(4, admins.getId());
            statement.setString(1, admins.getFirstName());
            statement.setString(2, admins.getLastName());
            statement.setString(3, admins.getEmail());
            statement.setString(4, admins.getPassword());
            statement.setInt(5, admins.getSuperadmin());
            statement.setInt(6, admins.getEnable());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}