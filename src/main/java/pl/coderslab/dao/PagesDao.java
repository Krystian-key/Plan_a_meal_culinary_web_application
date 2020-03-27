package pl.coderslab.dao;

import pl.coderslab.model.Pages;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagesDao {
    // ZAPYTANIA SQL

    private static final String FIND_ALL_PAGES_QUERY = "SELECT * FROM pages;";


    /**
     * Return all Pages
     *
     * @return
     */
    public List<Pages> findAllPages() {
        List<Pages> pages = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PAGES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Pages pagesToAdd = new Pages();
                pagesToAdd.setId(resultSet.getInt("id"));
                pagesToAdd.setTitle(resultSet.getString("title"));
                pagesToAdd.setDescription(resultSet.getString("description"));
                pagesToAdd.setSlug(resultSet.getString("slug"));

                pages.add(pagesToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pages;

    }
}