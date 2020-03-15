package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admins {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int superadmin;
    private int enable;

    public Admins() {
    }

    public Admins(String firstName, String lastName, String email, String password, int superadmin, int enable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        this.superadmin = superadmin;
        this.enable = enable;
    }

    public Admins(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Admins(int id, String firstName, String lastName, String email, String password, int superadmin, int enable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        this.superadmin = superadmin;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Compare new password with new password,
     * if correct return true
     *
     * @param newPassword
     * @return
     */
    //mistake
    public boolean checkPassword(String newPassword, String oldPassword) {
        return BCrypt.checkpw(newPassword, oldPassword);
    }

    /**
     * Something do?
     *
     * @param newPassword
     * @return
     */
    public boolean checkPassword(String newPassword) {
        return BCrypt.checkpw(newPassword, this.password);
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", superadmin='" + superadmin + '\'' +
                ", enable=" + enable +
                '}';
    }
}



