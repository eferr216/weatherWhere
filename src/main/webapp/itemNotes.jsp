<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Weather Where</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<nav>
    <ul>
        <li><a href="index.jsp">Check Weather</a></li>
        <li><a href="searchItem?link=clothing">Clothes</a></li>
        <li><a href="#">Sign In</a></li>
        <li><a href="#">Sign Up</a></li>
    </ul>
</nav>
<h1>Notes</h1>
<main>
    <div class="mainContent">
        <div class="row">
            <c:forEach items="${itemNotes}" var="itemNote">
                <div class="clothingDivs col-6-xs col-4-sm col-4-md">
                    <form action="searchItem?selected_item_id=${selectedItem.id}&item_note_id=${itemNote.id}" method="post">
                        <div class="itemAttributesDiv">
                            <p><span class="clothingAttributeNameStyles">Note Text: </span>${itemNote.noteText}</p>
                            <input type="submit" name="deleteItemNote" value="Delete" class="deleteSubmitButton">
                            <input type="submit" name="editItemNote" value="Edit">
                        </div>
                    </form>
                </div>
            </c:forEach>
        </div>
        <div>
            <h2 style="margin-top: 50px; margin-bottom: 20px;">Add new note:</h2>
            <form action="searchItem?selected_item_id=${selectedItem.id}" method="post">
                <label for="noteTextInput">Note Text:</label>
                <input id="noteTextInput" name="noteText" type="text">
                <input type="submit" name="insertNoteSubmitButton" value="Submit">
            </form>
        </div>


    </div>
</main>
</body>
</html>
