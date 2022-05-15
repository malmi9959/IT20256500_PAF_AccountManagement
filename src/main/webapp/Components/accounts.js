$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateAccountForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid-------------------------
	var type = ($("#hidAccountIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
		{
			url: "AccountsAPI",
			type: type,
			data: decodeURIComponent($("#formAccount").serialize()),
			dataType: "text",
			complete: function(response, status) {
				onAccountSaveComplete(response.responseText, status);
			}
		});
});

function onAccountSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divAccountsGrid").html(resultSet.data);
		}
		else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}
	else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	}
	else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidAccountIDSave").val("");
	$("#formAccount")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidAccountIDSave").val($(this).data("accountid"));
	$("#city").val($(this).closest("tr").find('td:eq(0)').text());
	$("#buildingNo").val(decodeURIComponent(($(this).closest("tr").find('td:eq(1)').text())));
	$("#totalAmtToPay").val($(this).closest("tr").find('td:eq(2)').text());
	$("#userID").val($(this).closest("tr").find('td:eq(3)').text());
	
});



$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "AccountsAPI",
			type: "DELETE",
			data: "accountID=" + $(this).data("accountid"),
			dataType: "text",
			complete: function(response, status) {
				onAccountDeleteComplete(response.responseText, status);
			}
		});
});


function onAccountDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		console.log(resultSet);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAccountsGrid").html(resultSet.data);
		}
		else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}
	else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}
	else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// CLIENT-MODEL================================================================
function validateAccountForm() {
	
	// city
	if ($("#city").val().trim() == "") {
		return "Insert city!";
	}

	// buildingNo
	if ($("#buildingNo").val().trim() == "") {
		return "Insert builing number!";
	}
	

	// totalAmtToPay
	if ($("#totalAmtToPay").val().trim() == "") {
		return "Insert total bill amount!";
	}
	
	// is numerical value
	var tmpPrice = $("#totalAmtToPay").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for bill amount!";
	}
	
	// convert to decimal price
	$("#totalAmtToPay").val(parseFloat(tmpPrice).toFixed(2));
	
	// year
	if ($("#userID").val().trim() == "") {
		return "Insert user id!";
	}

	return true;
}




