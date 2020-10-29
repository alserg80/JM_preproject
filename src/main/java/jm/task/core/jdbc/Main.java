package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String testName = "Ivan";
        final String testLastName = "Ivanov";

        UserServiceImpl userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser(testName + "1", testLastName + "1", (byte) 20);
        userService.saveUser(testName + "2", testLastName + "2", (byte) 25);
        userService.saveUser("Serg", "Sergg", (byte) 20);
        userService.saveUser("Serg1", "Sergg1", (byte) 30);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.closeConnection();
    }
}
