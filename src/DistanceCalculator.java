package server;

import java.sql.SQLException;

public class DistanceCalculator implements Calculator {

	@Override
	public double ñalculate(String fromCity, String toCity) throws SQLException {
		try {
			return DatabaseManager.getDistance(fromCity, toCity);
		}
		catch(SQLException ex) {
			throw ex;
		}
	}

	

}
