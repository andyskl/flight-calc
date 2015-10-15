
package server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;


public class DatabaseManager {	
	
	public static Double getLatitude(String cityName) throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex){
			return 0.0;
		}
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try{
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "");
			pst = c.prepareStatement("select latitude from city where city.name=?");
			pst.setString(1, cityName);
			rs = pst.executeQuery();
            rs.next();
			return rs.getDouble(1);
		}		
		catch(SQLException ex){ throw ex; }
		finally{ c.close(); }
		//return "";
	}
	
	public static Double getLongitude(String cityName) throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex){
			return 0.0;
		}
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try{
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "");
			pst = c.prepareStatement("select longitude from city where city.name=?");
			pst.setString(1, cityName);
			rs = pst.executeQuery();
            rs.next();
			return rs.getDouble(1);
		}		
		catch(SQLException ex){ throw ex;}
		finally{ c.close(); }
		//return "";
	}
	
	public static Double getDistance(String cityFrom, String cityTo) throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex){
			return 0.0;
		}
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;	
		try{
                    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "");
                    pst = c.prepareStatement("select distance from distance " +
                                             "where distance.id_from_city in " +
                                             "(select id_city from city where city.name=?) and " +
                                             "distance.id_to_city in" +
                                             "(select id_city from city where city.name=?)" );
                    pst.setString(1, cityFrom);
                    pst.setString(2, cityTo);
                    rs = pst.executeQuery();
                    if(rs.next()) {
                    	return rs.getDouble(1);
                    } else {
                    	pst.setString(1, cityTo);
        				pst.setString(2, cityFrom);
        	            rs = pst.executeQuery();
        	            rs.next();
        	            return rs.getDouble(1);
                    }
		}		
		catch(SQLException ex) {
			throw ex;
        }                                    
        
		finally {
			c.close();
		}
		//return "";
	}
	
	public static void addCity(int id, String name, double latitude, double longitude) throws SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex){			
		}
		Connection c = null;
		Statement st = null;
		DecimalFormat df = new DecimalFormat("#.##");
		try{
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "");
            st=c.createStatement();
            st.executeUpdate("INSERT INTO city " +
            				 "VALUES (" + id + ", " + name + ", " + 
            		         df.format(latitude) + ", " + df.format(longitude) + ")");
		}		
		catch(SQLException ex) {
			throw ex;
        }
		finally{ c.close(); }
	}
	
	public static void addDistance(int from, int to, double distance) throws SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException ex){			
		}
		Connection c = null;
		Statement st = null;
		DecimalFormat df = new DecimalFormat("#.##");
		try{
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/calc", "root", "");
            st=c.createStatement();
            st.executeUpdate("INSERT INTO distance " +
            				 "VALUES (" + from + ", " + to + ", " + df.format(distance) + ")");
		}		
		catch(SQLException ex) {
			throw ex;
        }
		finally{ c.close(); }
	}
}
