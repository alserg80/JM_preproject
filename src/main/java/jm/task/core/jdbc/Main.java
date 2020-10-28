package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String testName = "Ivan";
        final String testLastName = "Ivanov";

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        userServiceImpl.dropUsersTable();
        userServiceImpl.createUsersTable();

        userServiceImpl.saveUser(testName + "1", testLastName + "1", (byte) 20);
        userServiceImpl.saveUser(testName + "2", testLastName + "2", (byte) 25);
        userServiceImpl.saveUser("Serg", "Sergg", (byte) 20);
        userServiceImpl.saveUser("Serg1", "Sergg1", (byte) 30);

        List<User> users = userServiceImpl.getAllUsers();
        for (User user: users) {
            System.out.println(user.toString());
        }
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
