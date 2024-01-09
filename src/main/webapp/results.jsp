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
<main>
    <div class="mainContent">
        <h1>Weather Results</h1>
        <div id="zipCodeWeatherResultsDiv">
            <h2>${city}</h2>
            <br>
            <p><span class="weatherResultsPropertyLabel">Temperature:</span> ${temperature}</p>
            <p><span class="weatherResultsPropertyLabel">Wind Speed:</span> ${windSpeed}</p>
        </div>
        <br>
        ${mainObject}
        <br>
    </div>
</main>
</body>
</html>
