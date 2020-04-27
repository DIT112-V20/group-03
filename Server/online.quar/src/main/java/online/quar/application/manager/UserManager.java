package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.model.User;

import java.util.ArrayList;

public class UserManager {
    private long userId;

    ArrayList<User> users = new ArrayList<>();

    public User addUser(User user) {
        User userToAdd = findUser(user);
        users.add(userToAdd);

        //Save the user that was just added
        Singleton.getApplicationManager().getDatabaseManager().save(userToAdd);
        return userToAdd;
    }


    public boolean removeUser(User user) {
        User userToRemove = findUser(user);
        users.remove(userToRemove);

        //to "remove" from saved
        userToRemove.setActive(false);
        Singleton.getApplicationManager().getDatabaseManager().save(userToRemove);

        if (findUser(userToRemove) == null) {
            return true;
        }
        return false;
    }

    public User findUser(User user) {
        userId = user.getId();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                return users.get(i);
            }
        }
        return null;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
