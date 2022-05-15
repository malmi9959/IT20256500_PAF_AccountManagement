package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;

/**
 * Servlet implementation class AccountsAPI
 */
@WebServlet("/AccountsAPI")
public class AccountsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Account accountObj = new Account();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountsAPI() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output =accountObj.insertAccount(request.getParameter("city"), 
											request.getParameter("buildingNo"),
											request.getParameter("totalAmtToPay"),
											request.getParameter("userID"));
		response.getWriter().write(output);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		Map paras = getParasMap(request);
		String output = accountObj.updateAccount(paras.get("hidAccountIDSave").toString(),
										   paras.get("city").toString(),
										   paras.get("buildingNo").toString(),
										   paras.get("totalAmtToPay").toString(),
										   paras.get("userID").toString());
										
			
		response.getWriter().write(output);
	}
	
			
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
		Map paras = getParasMap(request);
		String output = accountObj.deleteAccount(paras.get("accountID").toString());
		response.getWriter().write(output);
	}
	

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{ 
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		
		catch (Exception e)
		{
		}
		
		return map;
	}

}
