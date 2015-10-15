package server;

import java.sql.SQLException;

public class CrowflightCalculator implements Calculator{

	@Override
	public double ñalculate(String fromCity, String toCity) throws SQLException {
		double from_lat;
		double from_lon;
		double to_lat;
		double to_lon;
		try{
			from_lat=DatabaseManager.getLatitude(fromCity)*Math.PI/180;
			from_lon=DatabaseManager.getLongitude(fromCity)*Math.PI/180;
			to_lat=DatabaseManager.getLatitude(toCity)*Math.PI/180;
			to_lon=DatabaseManager.getLongitude(toCity)*Math.PI/180;		
		}
		catch(SQLException ex) {
			throw ex;
		}
		return Math.acos(Math.sin(from_lat) * Math.sin(to_lat) + Math.cos(from_lat)
			      * Math.cos(to_lat) * Math.cos(from_lon - to_lon))*6378.1;
	}	
	
}
