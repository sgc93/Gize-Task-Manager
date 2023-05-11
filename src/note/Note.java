package note;
import java.sql.*;

public class Note {
    String name;
    String priority;
    String startDate;
    String description;
    public Note(){   
    }

    public Note(String name, String priority, String startDate, String description) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.description = description;
    }

    public static Connection connect(){
        final String URL = "jdbc:sqlite:src\\sqllite\\note.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL);
            System.out.println("Con Created!");
            
        } catch(SQLException e){
            System.out.println("Not Created!");
            System.err.println(e);
        }
        return con;
    }

    void createTable(String table_name ){
        try (Statement st = connect().createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + table_name + " (\n"
                    + "	note_id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "	note_name text NOT NULL,\n"
                    + "	note_detail varchar(2000),\n"
                    + "	st_date date,\n"
                    + "	st_time time,\n"
                    + "	ed_date date,\n"
                    + "	ed_time time,\n"
                    + "	isimportant varchar(20)\n"
                    + ");";
            System.out.println(sql);
            try {
                st.execute(sql);
                System.out.println("Table is created : " + table_name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void insertValues(String table_name, String note_name, String note_detail, String st_date, String st_time, String ed_date, String ed_time, String isimportant){
        String sql = "INSERT INTO " + table_name + "( note_name, note_detail, st_date, st_time, ed_date, ed_time, isimportant) VALUES(?,?,?,?,?,?,?)";

        try (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, note_name);
            pstmt.setString(2, note_detail);
            pstmt.setString(3, st_date);
            pstmt.setString(4, st_time);
            pstmt.setString(5, ed_date);
            pstmt.setString(6, ed_time);
            pstmt.setString(7, isimportant);
            pstmt.executeUpdate(); 
            System.out.println("Inserted!");
        } catch (SQLException e) {    
            System.err.println(e.getMessage());
        }
    }

    public static void delRow(String noteName){
        String sql = "DELETE FROM note WHERE note_name = '" + noteName + "'";
        try(
            Connection con = connect();
            Statement st = con.createStatement();
            
        ){
            st.executeUpdate(sql);
            System.out.println("Row Deleted!");
        } catch(SQLException e) {
            System.err.println(e);
        }
    }

    public static void updateRow(String sql) {
        try(
            Connection con = connect();
            Statement st = con.createStatement();
            
            ){
                st.executeUpdate(sql);
                System.out.println("Row Updated!");
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
}
