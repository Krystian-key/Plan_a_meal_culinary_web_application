package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admins;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password,superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_ADMIN_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_BY_ID_QUERY = "SELECT * from admins where id = ?;";
    private static final String READ_ADMIN_BY_EMAIL_QUERY = "SELECT * from admins where email = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE   admins SET first_name = ? , last_name = ?, email = ? WHERE id = ?;";
    private static final String UPDATE_ADMIN_PASSWORD_QUERY = "UPDATE   admins SET password = ? WHERE id = ?;";
    private static final String UPDATE_ADMIN_STATUS_QUERY = "UPDATE   admins SET enable = ? WHERE id = ?;";
    private static final String UPDATE_ADMIN_ENABLE_QUERY = "UPDATE    admins SET enable = ? WHERE id = ?;";


    /**
     * Get admin by id
     *
     * @param adminId
     * @return
     */
    public Admins readAdmins(Integer adminId) {
        Admins admins = new Admins();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_BY_ID_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admins.setId(resultSet.getInt("id"));
                    admins.setFirstName(resultSet.getString("first_name"));
                    admins.setLastName(resultSet.getString("last_name"));
                    admins.setEmail(resultSet.getString("email"));
                    admins.setPasswordString(resultSet.getString("password"));
                    admins.setSuperAdmin(resultSet.getInt("superadmin"));
                    admins.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }

    /**
     * Get admin by email
     * @param email
     * @return
     */
    public Admins readAdminsByEmail(String email) {
        Admins admins = new Admins();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_BY_EMAIL_QUERY)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admins.setId(resultSet.getInt("id"));
                    admins.setFirstName(resultSet.getString("first_name"));
                    admins.setLastName(resultSet.getString("last_name"));
                    admins.setEmail(resultSet.getString("email"));
                    admins.setPasswordString(resultSet.getString("password"));
                    admins.setSuperAdmin(resultSet.getInt("superadmin"));
                    admins.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;}

    /**
     * Return all admins
     *
     * @return
     */
    public List<Admins> findAllAdmins() {
        List<Admins> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admins adminsToAdd = new Admins();
                adminsToAdd.setId(resultSet.getInt("id"));
                adminsToAdd.setFirstName(resultSet.getString("first_name"));
                adminsToAdd.setLastName(resultSet.getString("last_name"));
                adminsToAdd.setEmail(resultSet.getString("email"));
                adminsToAdd.setPasswordString(resultSet.getString("password"));
                adminsToAdd.setSuperAdmin(resultSet.getInt("superadmin"));
                adminsToAdd.setEnable(resultSet.getInt("enable"));
                adminsList.add(adminsToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;

    }

    /**
     * Create admin
     *
     * @param admin
     * @return
     */
    public Admins createAdmin(Admins admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, admin.getPassword());
            insertStm.setInt(5, admin.getSuperAdmin());
            insertStm.setInt(6, admin.getEnable());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
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
    public void deleteAdmin(Integer adminsId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminsId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update admin
     *
     * @param admin
     */
    public void updateAdmin(Admins admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setInt(4, admin.getId());
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Update password for admin
     *
     * @param admin
     */
    public void updateAdminPassword(Admins admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_PASSWORD_QUERY)) {
            statement.setInt(2, admin.getId());
            statement.setString(1, admin.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Update status online/offline
     *
     * @param id
     * @param enable
     */
    public void updateAdminEnable(int id, int enable) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_ENABLE_QUERY)) {
            statement.setInt(2, id);
            statement.setInt(1, enable);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Checking email and password
     * @param admin
     * @return
     */
    public boolean validationAdminData(Admins admin) {
        List<Admins> allAdmins = findAllAdmins();
        for (Admins admins : allAdmins) {
            if (admins.getEmail().equals(admin.getEmail())) {
                if (admins.checkPassword(admin.getPassword(), admins.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checking if user is blocked by SuperAdmin
     * @param adminId
     * @return
     */
    public boolean validationStatus(int adminId){
        if (readAdmins(adminId).getEnable() == 1){
            return true;
        }
        return false;
    }

    /**
     * Return all emails
     * @return
     */
    public List<String> readEmails() {
        List<String> emails = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                emails.add(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }
}