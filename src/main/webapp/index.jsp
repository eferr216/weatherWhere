<html>
<head>
    <title>Weather Where</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<jsp:include page="navigation.jsp" />
<main>
<div class="mainContent">
    <h1>Check the weather in your area</h1>

    <p style="text-align: center; margin-bottom: 5px;">Enter your zip code:</p>
    <form action="searchItem" method="get">
        <input name="zipCode" type="text">
        <input type="submit" value="Search">
    </form>
</div>
</main>
</body>
</html>
