package vo;

public class UsersVO {
    private String userID;
    private String password;
    private String name;
    private int age;
    private String email;
    private int grade;

    public UsersVO(){}

    public UsersVO(String userID, String password, String name, int age, String email, int grade) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.grade = grade;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getGrade() {
        return grade;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
