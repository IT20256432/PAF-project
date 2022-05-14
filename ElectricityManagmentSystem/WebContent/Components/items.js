$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
 // Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true) {
    $("#alertError").text(status);
    $("#alertError").show();
    return;
}
// If valid------------------------
$("#formItem").submit(); 
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
    {
        url: "ItemsAPI",
        type: type,
        data: $("#formItem").serialize(),
        dataType: "text",
        complete: function (response, status) {
            onItemSaveComplete(response.responseText, status);
        }
    }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function (event) {
   $("#hidItemIDSave").val($(this).data("id")); 
    $("#unit").val($(this).closest("tr").find('td:eq(0)').text());
    $("#unifee").val($(this).closest("tr").find('td:eq(1)').text());
    $("#total").val($(this).closest("tr").find('td:eq(2)').text());
    
});
// CLIENT-MODEL================================================================
function validateItemForm() {
    // CODE
    if ($("#unit").val().trim() == "") {
        return "Insert no of unit.";
    }
    // NAME
    if ($("#unifee").val().trim() == "") {
        return "Insert Unit Fee";
    }
    
    // PRICE-------------------------------
    if ($("#total").val().trim() == "") {
        return "Insert Item Price.";
    }
    // is numerical value
    var tmpPrice = $("#total").val().trim();
    if (!$.isNumeric(tmpPrice)) {
        return "Insert a numerical value for Item Price.";
    }
    // convert to decimal price
    $("#total").val(parseFloat(tmpPrice).toFixed(2));
    
    return true;
}



function onItemSaveComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    $("#hidItemIDSave").val("");
    $("#formItem")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "ItemsAPI", 
 type : "DELETE", 
 data : "id=" + $(this).data("itemid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}

















 document.getElementById("cp_btn").addEventListener("click", copy_password);

		function copy_password() {
    var copyText = document.getElementById("pwd_spn");
    var textArea = document.createElement("textarea");
    textArea.value = copyText.textContent;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("Copy");
    textArea.remove();
}