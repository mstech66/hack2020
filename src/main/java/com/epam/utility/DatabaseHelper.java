package com.epam.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    private static Connection connection;

    static {
        connection = DatabaseConnectionManager.getConnection();
    }

    public static boolean isCartEmpty() {
        boolean isCartEmpty = false;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cart");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            isCartEmpty = !resultSet.next();
        } catch (SQLException e) {
            LoggerUtil.warn("Error in SELECT query of Cart Table");
        }
        return isCartEmpty;
    }

    public static boolean isProductCheckedOut(String productName, int quantity) {
        boolean isProductCheckedOut = false;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM product where name = '" + productName + "'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                try (PreparedStatement ordersPreparedStatement = connection
                        .prepareStatement("SELECT * FROM orders WHERE product_id = " + productId + " AND quantity = " + quantity);
                     ResultSet ordersResultSet = ordersPreparedStatement.executeQuery();
                ) {
                    isProductCheckedOut = ordersResultSet.next();
                }
            }
        } catch (SQLException e) {
            LoggerUtil.warn("Error in SELECT query while checking products in Orders Table");
        }
        return isProductCheckedOut;
    }

    public static int getCategoryId(String categoryName) {
        int categoryId = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM category where name = '" + categoryName + "'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.first();
            categoryId = resultSet.getInt("id");
        } catch (SQLException sqlException) {
            LoggerUtil.error("Error in SELECT query while getting categoryid from category Table");
        }
        return categoryId;
    }

    public static int getSubcategoryId(String subcategoryName) {
        int subcategoryId = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM category where name = '" + subcategoryName + "'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.first();
            subcategoryId = resultSet.getInt("id");
        } catch (SQLException sqlException) {
            LoggerUtil.warn("Error in SELECT query while getting subcategoryId from category Table");
        }
        return subcategoryId;
    }

    public static int getProductId(String productName) {
        int productId = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM product where name = '" + productName + "'");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            resultSet.first();
            productId = resultSet.getInt("id");
        } catch (SQLException sqlException) {
            LoggerUtil.warn("Error in SELECT query while getting productId from category Table");
        }
        return productId;
    }
}
