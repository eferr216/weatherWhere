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
<h1>Edit Item Note</h1>
<main>
    <div class="mainContent">

        <div>
            <form action="searchItem?note_id_to_edit=${idToEdit}" method="post" style="margin-top: 50px; margin-bottom: 20px;">
                <label for="noteText">Note Text:</label>
                <input type="text" name="noteText" id="noteText" value="${itemNoteToEdit.noteText}">
                <div class="submissionButtonsDiv">
                    <input type="submit" name="confirmEditNoteButton" value="Submit">
                    <input type="submit" name="cancelEditNoteButton" value="Cancel">
                </div>
            </form>
        </div>


    </div>
</main>
</body>
</html>
