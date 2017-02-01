package model.instances;

/**
 * Created by lexy on 28.11.16.
 */
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private boolean isTeacher;
    private boolean isAdmin;

    public UserDTO(int id, String firstName, String lastName, String login, String password, boolean isTeacher, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.isTeacher = isTeacher;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
