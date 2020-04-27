package online.quar.application.model;

import online.quar.application.Singleton;

public class User {
    private long id = -1;
    private String username;
    private byte[] password;
    private String fullname;
    private boolean active = true;

    public User() {
    }

    public User(long id, String username, byte[] password, String fullname, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.active = active;
    }

    public void save() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
