package model;

public class User {

    private int id;
    private int UserId;
    private String firstName;
    private String lastName;
    private int deptId;

    public User(int id, int UserId, String firstName, String lastName, int deptId) {
        super();
        this.id = id;
        this.UserId = UserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deptId = deptId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }


    public String toString(){

        return "[" + "id=" + id + ", " + "UserId=" + UserId + ", " + "firstName=" + firstName + ", " + "lastName=" + lastName + ", " + "deptId=" + deptId + "]";

    }
    

}
