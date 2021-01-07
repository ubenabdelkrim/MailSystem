package part1.mailServiceElements;

import java.io.Serializable;

/**
 * This class is the class that contains the different information about the user.
 *
 * @author Usama Benabdelkrim Zakan
 */
public class User implements Serializable {
    /**
     * Serializable Class to read and write it
     */
    private static final long serialVersionUID = 1L;

    private String username;
    private String name;
    private int birthYear;

    /**
     * User Constructor
     *
     * @param name      User's name
     * @param username  Username
     * @param birthYear Birth year
     */
    public User(String name, String username, int birthYear) {
        this.username=username;
        this.name=name;
        this.birthYear=birthYear;
    }

    /**
     * User Constructor without parameters
     */
    public User() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Username Getter
     *
     * @return username Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username Setter
     *
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Name Getter
     *
     * @return name Name
     */
    public String getName() {
        return name;
    }

    /**
     * Name Setter
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * BirthYear Getter
     *
     * @return birthYear Birth Year
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * BirthYear Setter
     *
     * @param birthYear Birth Year
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    /**
     * toString method
     * @return String string
     */
    public String toString(){
        return "\nName: "+name
                +" Username "+username
                +" Birth Year "+birthYear;
    }
}
