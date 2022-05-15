package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.DBConnect;

public class Account {
	
	private DBConnect connection = new DBConnect();
	
	public String insertAccount (String city, String buildingNo, String totalAmtToPay, String userID) {

		String output ="";
		
		try {
			
			Connection con = connection.getConnection();
			
			if(con == null) {
				return "Error while connecting to database";
			}
			
			//create a prepared statement
			String query = "INSERT INTO accounts (`accountID`,`city`, `buildingNo`, `totalAmtToPay`, `userID`)"
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, city);
			preparedStmt.setString(3, buildingNo);
			preparedStmt.setDouble(4, Double.parseDouble(totalAmtToPay));
			preparedStmt.setInt(5, Integer.parseInt(userID));
			
			//execute statement
			preparedStmt.execute();
			con.close();
			
			String newAccounts = readAccounts();
			output = "{\"status\":\"success\", \"data\": \"" + newAccounts + "\"}";
			
		} catch (Exception e) {
			
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the account.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	public String readAccounts() {

		String output = "";
		
		try {
			Connection con = connection.getConnection();
			
			if(con == null) {
				return "Error while connecting to database";
			}
			
			//prepare the html table to be displayed
			output= "<table border='1' class='container mx-2 text-center table' >"
					+ "<thead class='thead-dark'>" 
					+ "<tr>"
					+ "<th scope='col'>Account ID</th>"
					+ "<th scope='col'>City</th>"
					+ "<th scope='col'>Building Number</th>"
					+ "<th scope='col'>Total Amount To Pay</th>"
					+ "<th scope='col'>User ID</th>"
					+ "<th scope='col'>Update</th>" 
					+ "<th scope='col'>Remove</th>" 
					+ "</tr>"
					+ "</thead>";
			
			String query = "SELECT * FROM accounts";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String accountID = Integer.toString(rs.getInt("accountID"));
				String city = rs.getString("city");
				String buildingNo = rs.getString("buildingNo");
				String totalAmtToPay = Double.toString(rs.getDouble("totalAmtToPay"));
				String userID = Integer.toString(rs.getInt("userID"));
				
				//add a row into html table
				output += "<tbody>";
				output += "<tr>";
				output += "<th><input id='hidAccountIDUpdate' name='hidAccountIDUpdate' type='hidden' value='" + accountID
						+ "'>" + accountID + "</th>";
				output += 	"<td>" + city +  "</td>";
				output += 	"<td>" + buildingNo +  "</td>";
				output += 	"<td>" + totalAmtToPay +  "</td>";
				output += 	"<td>" + userID +  "</td>";
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-warning mx-2' data-accountid='"
						+ accountID + "'></td>";
				output += "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger mx-2' data-accountid='"
						+ accountID + "'></td>";
				output += "</tr>";
				output += "</tbody>";
				
			}
			
			con.close();
			
			//completing html table
			output += 	"</table>";
			
		} 
		catch (Exception e) 
		{
			output="Error while reading the accounts";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
public String updateAccount(String ID, String city, String buildingNo, String totalAmtToPay, String userID) {
		
		String output = "";
		
		try {
			
			
			Connection con = connection.getConnection();
			if(con == null) {
				
				return "Error connecting to database";
			}
			
			String query = "UPDATE accounts SET city=?, buildingNo=?, totalAmtToPay=?, userID=? where accountID=? ";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1, city);
			preparedStmt.setString(2, buildingNo);
			preparedStmt.setDouble(3, Double.parseDouble(totalAmtToPay));
			preparedStmt.setInt(4, Integer.parseInt(userID));
			preparedStmt.setInt(5, Integer.parseInt(ID));
			
			 // execute the statement
			 preparedStmt.execute();
			 
			 con.close();
			 
				String newAccounts = readAccounts();
				output = "{\"status\":\"success\", \"data\": \"" + newAccounts + "\"}";
			
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\":\"Error while updating the account.\"}";
			System.err.println(e.getMessage());
			 
		}
		
		
		return output;
	}

	public String deleteAccount(String accountID) {
		String output="";
		
		try {
			
			Connection con = connection.getConnection();
			if(con == null) {
				return "Error while connecting to database for deleting";
			}
			
			//create a prepared statement
			String query = "DELETE FROM accounts WHERE accountID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding value
			preparedStmt.setInt(1, Integer.parseInt(accountID));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newAccounts = readAccounts();
			output = "{\"status\":\"success\", \"data\": \"" + newAccounts + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the accounts.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	


	

}
