package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.model.Car;
import online.quar.application.model.User;

import java.util.ArrayList;

public class UserManager {
    ArrayList<User> users = new ArrayList<>();

    public User addUser(User userToAdd) {
        users.add(userToAdd);

        //Save the user that was just added
        Singleton.getApplicationManager().getDatabaseManager().save(userToAdd);
        return userToAdd;
    }


    public boolean removeUser(User userToRemove) {
        users.remove(userToRemove);

        //to "remove" from saved
        userToRemove.setActive(false);
        Singleton.getApplicationManager().getDatabaseManager().save(userToRemove);

        if (findUser(userToRemove.getId()) == null) {
            return true;
        }
        return false;
    }

    public User findUser(String userName) {
        //First check for user in cache
        for (User user : users) {
            if (user.getUsername() == userName && user.isActive()) {
                return user;
            }
        }

        //User was not found in memory, check database
        DatabaseManager databaseManager = Singleton.getApplicationManager().getDatabaseManager();
        User user = databaseManager.getUserByUsername(userName, true);

        if(user != null) {
            users.add(user);
        }

        //If the user is not found in the database, null will be returned
        return user;
    }

    public User findUser(long userId) {
        //First check for user in cache
        for (User user : users) {
            if (user.getId() == userId && user.isActive()) {
                return user;
            }
        }

        //User was not found in memory, check database
        DatabaseManager databaseManager = Singleton.getApplicationManager().getDatabaseManager();
        User user = databaseManager.getUser(userId, true);

        if(user != null) {
            users.add(user);
        }

        //If the user is not found in the database, null will be returned
        return user;
    }


}
