<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Weather Where</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<jsp:include page="navigation.jsp" />
<h1>Edit Item</h1>
<main>
    <div class="mainContent">

        <div>
            <form action="searchItem?id_to_edit=${idToEdit}" method="post" style="margin-top: 50px; margin-bottom: 20px;">
                <label for="itemName">Item Name:</label>
                <input type="text" name="itemName" id="itemName" value="${itemToEdit.itemName}">
                <label for="itemDescription">Item Description:</label>
                <input type="text" name="itemDescription" id="itemDescription" value="${itemToEdit.itemDescription}">
                <label for="itemCategory">Item Category:</label>
                <input type="text" name="itemCategory" id="itemCategory" value="${itemToEdit.itemCategory}">
                <div class="submissionButtonsDiv">
                    <input type="submit" name="confirmEditButton" id="editItem" value="Submit">
                    <input type="submit" name="cancelEditButton" id="cancelEditItem" value="Cancel">
                </div>
            </form>
        </div>


    </div>
</main>
</body>
</html>
