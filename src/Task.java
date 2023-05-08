import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Task {
    public Task(){
        
    }

    
    public Connection connect(){
        final String URL = "jdbc:sqlite:src\\sqllite\\todo.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL);
            System.out.println("Created!");
            
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
                    + "	iscompleted varchar(3),\n"
                    + "	priority String(3),\n"
                    + "	task_type varchar(20)\n"
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

    void insertValues(String table_name, String task_name, String st_date, String st_time, String end_date, String end_time, String isCompleted, String priority, String task_type){
        String sql = "INSERT INTO " + table_name + "( task_name, st_date, st_time, end_date, end_time, iscompleted, priority, task_type) VALUES(?,?,?,?,?,?,?,?)";

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
            pstmt.setString(8, task_type);
            pstmt.executeUpdate(); 
            System.out.println("Values are inserted seccessfully!");
        } catch (SQLException e) {
            if( e.getMessage() == "[SQLITE_CONSTRAINT_PRIMARYKEY] A PRIMARY KEY constraint failed (UNIQUE constraint failed: task.task_id)")
                System.out.println("row repetation");
            else
                System.err.println(e.getMessage());
        }
    }

    public void retrievData(String table_name){
        String sql = "SELECT * FROM " + table_name;
        try(
            Connection con = this.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
        ){  
            List<String> task_name = new ArrayList<>();
            List<String> st_time = new ArrayList<>();
            List<String> pri = new ArrayList<>();
            System.out.println("Task      Time                    Priority");
            int i = 0;
            while(rs.next()){
                task_name.add(rs.getString(2)) ;
                st_time.add(rs.getString(4)) ;
                pri.add(rs.getString(8));
                System.out.println(task_name.get(i) + "      " + st_time.get(i) + "     " + pri.get(i));
                i++;
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    void updateValues(){

    }

    void createView(String view_name){

    }

    void delRow(){

    }

    void delTable(){

    }
}
