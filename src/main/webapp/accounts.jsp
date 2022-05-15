<%@ page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/accounts.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container mt-3">
		<h2>Account Management</h2>
		<div class="row">
			<div class="col-sm">
				<form id="formAccount" name="formAccount">
					
					City: 
					<input id="city" name="city" type="text" class="form-control form-control-sm"  placeholder="Enter your City"> 
					<br /> 
					Building Number: 
					<input id="buildingNo" name="buildingNo" type="text" class="form-control form-control-sm" placeholder="Enter your Building Number"> 
					<br /> 
					Total Amount To Pay: 
					<input id="totalAmtToPay" name="totalAmtToPay" type="number" class="form-control form-control-sm" min="0" placeholder="Enter your total amount"> 
					<br />
					User ID: 
					<input id="userID" name="userID" type="number" class="form-control form-control-sm" min="0" placeholder="Enter User ID"> 
					<br />
					<input id="btnReset" name="btnReset" type="reset" value="Reset" class="btn btn-secondary"> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-success"> 
					<input type="hidden" id="hidAccountIDSave" name="hidAccountIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success my-3"></div>
				<div id="alertError" class="alert alert-danger my-3"></div>
			</div>
			<div class="col-sm container">
				<div id="divAccountsGrid">
			 		<%
			 			Account accountObj = new Account();
			 			out.print(accountObj.readAccounts());
			 		%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>