package DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.DriverManager;

public class XJdbcHelper {
 
    public static Connection CreateConnect() {
		Connection conn = null;
		
//		String username = "root";
//		String url = "jdbc:mysql://localhost:3306/fishShops";
//		String passowrd = "1234567890van";
		
		String username = "admin";
		String url = "jdbc:mysql://mysql-111501-0.cloudclusters.net:10304/fishshops";
		String passowrd = "bWOymuL2";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, passowrd);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
    
    
    public static PreparedStatement getStmt(String sql,Object...args) throws SQLException{
        Connection conn = XJdbcHelper.CreateConnect();
        PreparedStatement stmt = null;
        if(sql.trim().startsWith("{")){ 
            stmt = conn.prepareCall(sql); //PROC
        }else{
            stmt = conn.prepareStatement(sql); //SQL
        }
        for(int i = 0; i<args.length; i++){
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    
    public static boolean update(String sql,Object...args) throws SQLIntegrityConstraintViolationException {
        try {
            PreparedStatement stmt = XJdbcHelper.getStmt(sql, args);
            try {
                return stmt.executeUpdate() > 0;
            } finally {
                stmt.getConnection().close();
            }
        }catch(SQLIntegrityConstraintViolationException e) {
        	throw new SQLIntegrityConstraintViolationException();
        }
        catch (Exception e) {
            return false;
        }
        
    }
    
    
    public static ResultSet query(String sql,Object...args) throws SQLException{
        PreparedStatement stmt = XJdbcHelper.getStmt(sql, args);
        return stmt.executeQuery();
    }
    
    public static Object value(String sql,Object...args){
        
        try {
            ResultSet rs = XJdbcHelper.query(sql, args);
            if(rs.next()){
            	String d = String.valueOf(rs.getObject(0));
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
            
        } catch (Exception e) {
        	  return null;
        }
    }
}

