package online.quar.application.model;

import online.quar.application.Singleton;

public class User {
    private long id;
    private String username;
    private byte[] password;
    private String fullname;

    public User() {
    }

    public User(long id, String username, byte[] password, String fullname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public void save(){
        Singleton.getApplicationManager().getDatabaseManager().save(this);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
