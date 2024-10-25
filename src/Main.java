import dao.MenuListDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        MenuListDAO menuListDAO = new MenuListDAO();
        menuListDAO.LoginMenu();
    }

}