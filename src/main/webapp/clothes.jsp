<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/styles.css">
<body>
<jsp:include page="navigation.jsp" />
<h1>Clothes</h1>
<main>
    <div class="mainContent">
        <div class="row">
        <c:forEach items="${items}" var="item">
                    <div class="clothingDivs col-6-xs col-4-sm col-4-md">
                        <form action="searchItem" method="post">
                            <div class="itemAttributesDiv">
                                <p><span class="clothingAttributeNameStyles">Item Name: </span>${item.itemName}</p>
                                <p><span class="clothingAttributeNameStyles">Item Description: </span>${item.itemDescription}</p>
                                <p><span class="clothingAttributeNameStyles">Item Category: </span>${item.itemCategory}</p>
                                <c:forEach items="${item.itemNotes}" var="itemNote">
                                    <p>Note: ${itemNote.noteText}</p>
                                </c:forEach>
                                <input type="submit" name="delete" value="Delete" class="deleteSubmitButton">
                                <input type="submit" name="edit" value="Edit">
                            </div>
                        </form>
                    </div>
        </c:forEach>
        </div>
        <div id="clothingFormDiv">
            <h2 style="margin-top: 50px; margin-bottom: 20px;">Add new item:</h2>
            <form action="searchItem" method="post">
                <label for="itemNameInput">Item Name:</label>
                <input id="itemNameInput" name="itemName" type="text">
                <label for="itemDescriptionInput">Item Description:</label>
                <input id="itemDescriptionInput" name="itemDescription" type="text">
                <label for="itemCategoryInput">Item Category:</label>
                <input id="itemCategoryInput" name="itemCategory" type="text">
                <input type="submit" value="Submit">
            </form>
        </div>


    </div>
</main>
</body>
</html>
