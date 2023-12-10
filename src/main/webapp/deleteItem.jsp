<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/styles.css">
<body>
<jsp:include page="navigation.jsp" />
<h1>Delete Confirmation</h1>
<main>
    <div class="mainContent">

        <div>
            <h2 style="margin-top: 50px; margin-bottom: 20px;">Are you sure you want to delete this item?:</h2>
            <form action="searchItem?delete_id=${item.id}" method="post">
                <label for="cancel">Cancel:</label>
                <input type="submit" name="submitButton" id="cancel" value="Cancel">
                <label for="delete">Delete:</label>
                <input type="submit" name="submitButton" id="delete" value="Delete">
            </form>
        </div>


    </div>
</main>
</body>
</html>
