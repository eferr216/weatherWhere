<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Weather Where</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Weather Where</h1>
<jsp:include page="navigation.jsp" />
<main>
    <div class="mainContent">
        <h2>This is the Results.jsp page</h2>
        ${apiResponse}
    </div>
</main>
</body>
</html>
