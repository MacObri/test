
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author sqlitetutorial.net
 */
public class Main {
    /**
     * Connect to a sample database
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/users/hp/desktop/test.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO cats (name, age, color)"
            +" VALUES (?, ?, ?)");
        /*ps.setString(1,""+System.currentTimeMillis());
        ps.setInt(2, (int)(Math.random()*5.99));
        ps.setString(3,Math.random()>.5 ? "black" : "blue");
        ps.executeUpdate();
         */
        for(int i = 0; i < 15; i++){
            ps.setString(1, syllableSmash());
            ps.setInt(2,  (int)(Math.random()*5.99));
            ps.setString(3,Math.random()>.5 ? "black" : "blue");
            ps.executeUpdate();
        }
        ps = con.prepareStatement("SELECT name, color FROM cats");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String name = rs.getString("name");
            String color = rs.getString("color");
            System.out.println(name+" is a "+color+" cat.");
        }
        rs.close();

        //ps = con.prepareStatement("DELETE FROM cats");
        //ps.setString(1,"blue");
        //ps.executeUpdate();
    }
    private static String syllableSmash(){
        String[] syllables = {"y","tabb","spot","spott","doug","las","cart","er","ish","fuzz","cat","dogg","min","mon","bing","bong","me","ow","kit","ia"};
        int sylNum = (int) (Math.random() * 4) + 1;
        String result = "";
        for(int i = 0; i < sylNum; i++){
            result = result + syllables[(int)(Math.random() * 20)];
        }
        return result;
    }
}