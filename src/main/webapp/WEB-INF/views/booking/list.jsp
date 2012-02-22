<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<html>
	<head>
		<title>Booking List</title>
	</head>
	<body>
		<c:if test="${info != null}">
		<div class="alert alert-success fade in" >
		 	<a class="close" data-dismiss="alert" href="#">&times;</a>
  			<p><spring:message code="${info}"/></p>
		</div>
		</c:if>	
		<a class="btn btn-primary" href="<c:url value="/booking/new" />">Create</a>
		<table class="table table-striped" style="margin-bottom: 0px;">
			<thead>
				<tr>
					<th>#</th>
					<th>Date</th>
					<th>Email</th>
					<th>Item count</th>
				</tr>
			</thead> 
			<tbody> 
			<c:forEach items="${bookings}" var="booking">
				<c:url value="/booking/${booking.id}" var="url" />
				<tr>
					<td><a href="${url}">${booking.id}</a></td>
					<td>${booking.bookingDate}</td>
					<td>${booking.customerEmail}</td>
					<td>${fn:length(booking.items)}</td>
				</tr>
			</c:forEach>
			</tbody>		
		</table>
		<c:if test="${pager.pagingNeeded}">
		<div class="pagination center">
		  <ul>
		  	<li class="prev <c:if test="pager.firstPage">disabled</c:if>"><a href="<c:url value="/bookings/${pager.firstPageIndex}"/>">&larr; First</a></li>
		    <li class="prev <c:if test="pager.firstPage">disabled</c:if>"><a href="<c:url value="/bookings/${pager.previousPageIndex}"/>">&larr; Prev.</a></li>
			<c:forEach items="${pager.controlPages}" var="page">
		 	<li><a class="<c:if test="${pager.currentPageIndex == page}">active</c:if>"href="<c:url value="/bookings/${page}"/>">${page}</a></li>
		 	</c:forEach>
		 	<li class="prev <c:if test="pager.lastPage">disabled</c:if>"><a href="<c:url value="/bookings/${pager.nextPageIndex}"/>">Next &rarr;</a></li>
		    <li class="next <c:if test="pager.lastPage">disabled</c:if>"><a href="<c:url value="/bookings/${pager.lastPageIndex}"/>">Last &rarr;</a></li>
		  </ul>
		</div>
		</c:if>		
	</body>
</html>