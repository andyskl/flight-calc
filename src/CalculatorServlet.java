package server;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

public class CalculatorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {      
		String method = req.getParameter("calc_method");
		String from = req.getParameter("from_city");
		String to = req.getParameter("to_city");
		double distance=0.0;
    	DecimalFormat df = new DecimalFormat("#.##");
    	List<Result> resultList=new ArrayList<Result>();
    	CalculatorFactory calculatorFactory=CalculatorFactory.getInstance();
    	if (!method.equals("All")) {
    		Calculator calculator = calculatorFactory.get(method);
    		try{
    			distance = calculator.ñalculate(from, to);
    			resultList.add(new Result(method, df.format(distance) + " km"));
    		}
    		catch(SQLException ex){
    			resultList.add(new Result(method, "No Data"));
    		}
    	} else {
    		String currentMethod;
    		Set<String> calcSet = calculatorFactory.getFactoryList(); 
    		Iterator<String> calcSetIterator = calcSet.iterator();
    		while(calcSetIterator.hasNext()) { 
    			currentMethod = calcSetIterator.next();
    			Calculator calculator = calculatorFactory.get(currentMethod);
    			try{
    				distance = calculator.ñalculate(from, to);
    				resultList.add(new Result(currentMethod, df.format(distance) + " km"));
    			}
    			catch(SQLException ex){
    				resultList.add(new Result(currentMethod, "No Data"));
        		}
    		}
    	}
    	req.setAttribute("from", from);
    	req.setAttribute("to", to);
    	req.setAttribute("method", method);
    	req.setAttribute("resultList", resultList);
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
    	rd.forward(req, res);
	}
	
	@Override
    public String getServletInfo() {
        return "";
    }
	
}
