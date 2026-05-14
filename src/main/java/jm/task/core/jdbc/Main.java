package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Slava", "Marushkin", (byte) 27);
        userService.saveUser("Drung", "Mellstroy", (byte) 87);
        userService.saveUser("Wraith", "King", (byte) 11);
        userService.saveUser("Snap", "Dragon", (byte) 16);
        userService.getAllUsers();
    }
}
