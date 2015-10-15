package server;

public class Result {
	String method;
	String distance;
	
	public Result(String method, String distance){		
		this.method=method;
		this.distance=distance;
	}
	
	public String getMethod(){
		return method;
	}
	
	public String getDistance(){
		return distance;
	}
}
