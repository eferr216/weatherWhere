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
<h1>Delete Confirmation</h1>
<main>
    <div class="mainContent">

        <div>
            <h2 style="margin-top: 50px; margin-bottom: 20px;">Are you sure you want to delete this item?</h2>
            <form action="searchItem?id_to_delete=${idToDelete}" method="post">
                <input type="submit" id="deleteItem" name="confirmDeleteButton" value="Delete">
                <input type="submit" id="cancelDeleteItem" name="cancelDeleteButton" value="Cancel">
            </form>
        </div>


    </div>
</main>
</body>
</html>
