package todo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.DesktopIconUI;



public class Task {
    String name;
    String priority;
    String startDate;
    String description;
    public Task(){
        
    }

    public Task(String name, String priority, String startDate, String description) {
        this.name = name;
        this.priority = priority;
        this.startDate = startDate;
        this.description = description;
    }

    public static Connection connect(){
        final String URL = "jdbc:sqlite:src\\sqllite\\todo.db";
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
                    + "	task_id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "	task_name text NOT NULL,\n"
                    + "	st_date date,\n"
                    + "	st_time time,\n"
                    + "	end_date date,\n"
                    + "	end_time time,\n"
                    + "	iscompleted varchar(20),\n"
                    + "	priority String(10),\n"
                    + "	task_des varchar(400)\n"
                    + ");";
            System.out.println(sql);
            try {
                st.execute(sql);
                System.out.println("Table is created : " + table_name);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void insertValues(String table_name, String task_name, String st_date, String st_time, String end_date, String end_time, String isCompleted, String priority, String task_des){
        String sql = "INSERT INTO " + table_name + "( task_name, st_date, st_time, end_date, end_time, iscompleted, priority, task_des) VALUES(?,?,?,?,?,?,?,?)";

        try (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task_name);
            pstmt.setString(2, st_date);
            pstmt.setString(3, st_time);
            pstmt.setString(4, end_date);
            pstmt.setString(5, end_time);
            pstmt.setString(6, isCompleted);
            pstmt.setString(7, priority);
            pstmt.setString(8, task_des);
            pstmt.executeUpdate(); 
            System.out.println("Inserted!");
        } catch (SQLException e) {    
            System.err.println(e.getMessage());
        }
    }

    public static void delRow(String taskName){
        String sql = "DELETE FROM task WHERE task_name = '" + taskName + "'";
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

    public static void updateRow(String updated, String task_name, String desc, String stDate, String stTime, String endDate,String endTime, String pri, String status) {
        String sql = "UPDATE task SET task_name = '" + task_name + "', st_date = '" + stDate + "', st_time = '"+ stTime + "', end_date = '"+ endDate + "', end_time = '" + endTime + "', iscompleted = '" + status + "', priority = '" + pri + "', task_des = '" + desc + "' WHERE task_name = '" + updated + "'";

        
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
