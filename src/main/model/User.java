public class User {
    int id_user;
    String name;
    String email;

    @Override
    public String toString() {
        return "User [id_user=" + id_user + ", name=" + name + ", email=" + email + ", password=" + password + "]";
    }

    String password;

    public User(int id_user, String name, String email, String password) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
