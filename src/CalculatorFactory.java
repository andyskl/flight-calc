package server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

public class CalculatorFactory {
	
	private static CalculatorFactory instance;
	
	private static Map<String, Calculator> calculatorMap;
	
	private CalculatorFactory() {
		calculatorMap=new HashMap<String, Calculator>();
		Map<String, Calculator> initMap = new HashMap<String, Calculator>();
		 initMap.put("Crowflight", new CrowflightCalculator());
		 initMap.put("Distance Matrix", new DistanceCalculator());
		 calculatorMap = Collections.unmodifiableMap(initMap);
	}
	
	public static CalculatorFactory getInstance() {
		if(instance == null) {
			instance=new CalculatorFactory();
		}
		return instance;
	}
	
	public Set<String> getFactoryList(){
		return  Collections.unmodifiableSet(calculatorMap.keySet());
	}
	
	public Calculator get(String name){
		return calculatorMap.get(name);
	}
}
