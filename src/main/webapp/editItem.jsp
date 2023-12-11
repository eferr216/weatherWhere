<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/styles.css">
<body>
<jsp:include page="navigation.jsp" />
<h1>Edit Page</h1>
<main>
    <div class="mainContent">

        <div>
            <h2 style="margin-top: 50px; margin-bottom: 20px;">Edit:</h2>
            <form action="searchItem?edit_id=${item.id}" method="post">
                <label for="delete">Edit:</label>
                <input type="submit" name="submitButton" id="delete" value="Delete">
                <label for="cancel">Cancel:</label>
                <input type="submit" name="submitButton" id="cancel" value="Cancel">
            </form>
        </div>


    </div>
</main>
</body>
</html>
