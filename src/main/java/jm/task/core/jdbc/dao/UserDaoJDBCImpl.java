package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    //    Connection connection;
    private Statement statement;

    public UserDaoJDBCImpl() {

    }

    /*public void createConnection() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            try {
                statement.executeUpdate("CREATE TABLE users1 (" +
                        "id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                        "name VARCHAR(40), " +
                        "lastName VARCHAR(40), " +
                        "age TINYINT UNSIGNED)");
            } catch (SQLSyntaxErrorException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            try {
                statement.executeUpdate("DROP TABLE users1");
            } catch (SQLSyntaxErrorException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
//            statement = connection.createStatement();
            try {
//                statement.executeUpdate("INSERT INTO users1(name, lastName, age) values ('" + name + "', '" +lastName+"', '"+age+"')");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users1(name, lastName, age) VALUES (?, ?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                System.out.println("User с именем – " + name + " добавлен в базу данных ");
            } catch (SQLSyntaxErrorException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            try {
                statement.executeUpdate("DELETE FROM users1 WHERE id = '" + id + "'");
            } catch (SQLSyntaxErrorException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            try {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users1");
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setAge(resultSet.getByte(4));
                    userList.add(user);
                }
            } catch (SQLSyntaxErrorException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            statement = connection.createStatement();
            try {
                statement.executeUpdate("DELETE FROM users1");
            } catch (SQLSyntaxErrorException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
