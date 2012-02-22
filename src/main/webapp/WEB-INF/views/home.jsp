<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Demo App</title>
	</head>
	<body>
		<h1>Welcome on the demo App!</h1>
		<a href="<c:url value="/bookings/1" />" class="btn btn-large btn-primary">Bookings CRUD</a>
	</body>
</html>