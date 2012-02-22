<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title><c:if test="${!booking.new}">Booking View</c:if> <c:if test="${booking.new}">New Booking</c:if>
</title>
</head>
<body>
	<script type="text/javascript">
		 $(document).ready(function(){	 
			 $('#close-confirm').click(function (event) {
				 event.preventDefault();
				 $('#modal-confirm').modal('hide');
			 });
			 $('#confirm-delete').click(function (event) {
				 event.preventDefault();
				 $('#form').append('<input type="hidden" name="_method" value="delete"/>').submit();
			 });
			 $('#add-item').click(function (event) {
				 event.preventDefault();
				 var items = $('#items').children();
				 var itemId = 0;
				 if (items.size() > 0 ) {
					 itemId = items.last().data("item") + 1;
				 } 				 
				 var itemData = { id: itemId };
				$("#itemTemplate").tmpl(itemData).appendTo( "#items" );				 
			 });
			 $('.delete-item').live("click", function (event) {
				 event.preventDefault();
				 var item = $(this).data("item");
				 $("#items").find('tr[data-item="'+ item +'"]').remove();		 
			 });
			 $('#submit-btn').click(function(){
					$('#form').submit();
				});			 
		 });
		</script>
	<c:url value="/booking" var="formUrl" />
	<form:form commandName="booking" id="form" action="${formUrl}" method="POST" class="form-horizontal">
		<fieldset">
			<legend>Booking</legend>
			<div class="control-group">
				<label>ID</label>
				<div class="controls">
					<input type="hidden" name="id" id="id" value="${booking.id}"/>
					<span class="uneditable-input">${booking.id}</span>
				</div>
			</div>
			<spring:bind path="booking.customerEmail">
				<div class="control-group <c:if test="${status.error}">error</c:if>">
					<label for="customerEmail">Customer Email</label>
					<div class="controls">
						<input class="input-large " id="customerEmail" name="customerEmail" size="30" type="text" value="${booking.customerEmail}">
						<form:errors cssClass="help-inline" path="customerEmail" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="booking.bookingDate">
				<div class="control-group <c:if test="${status.error}">error</c:if>">
					<label for="bookingDate">Date</label>
					<div class="controls">
						<input class="span2" data-datepicker="datepicker" type="text" 
							   value="<fmt:formatDate value="${booking.bookingDate}" pattern="dd/MM/yyyy"/>" 
							   id="bookingDate" name="bookingDate" />
						<form:errors cssClass="help-inline" path="bookingDate" />
					</div>
				</div>
			</spring:bind>
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Label</th>
						<th>Unit price</th>
						<th>Quantity</th>
						<th><button class="btn btn-primary" id="add-item">Add</button></th>
					</tr>
				</thead> 
				<tbody id="items"> 
				<c:forEach items="${booking.items}" var="item" varStatus="loopStatus">
					<tr id="item" data-item="${loopStatus.index}">
						<td>
						<spring:bind path="items[${loopStatus.index}].itemLabel">
						  <div class="control-group <c:if test="${status.error}">error</c:if>">
				            <div class="control nolabel">
				              <input type="text" name="items[${loopStatus.index}].itemLabel" value="${item.itemLabel}" />
				              <form:errors element="div" cssClass="help-inline" path="items[${loopStatus.index}].itemLabel" />
				            </div>
				          </div>						
						</spring:bind>						
						</td>
						<td>
						<spring:bind path="items[${loopStatus.index}].itemUnitPrice">
						  <div class="control-group <c:if test="${status.error}">error</c:if>">
				            <div class="control nolabel">
				              <input type="text" name="items[${loopStatus.index}].itemUnitPrice" value="${item.itemUnitPrice}" />
				              <form:errors element="div" cssClass="help-inline" path="items[${loopStatus.index}].itemUnitPrice" />
				            </div>
				          </div>						
						</spring:bind>						
						</td>
						<td>
						<spring:bind path="items[${loopStatus.index}].quantity">
						  <div class="control-group <c:if test="${status.error}">error</c:if>">
				            <div class="control nolabel">
				              <input class="<c:if test="${status.error}">error</c:if>" type="text" name="items[${loopStatus.index}].quantity" value="${item.quantity}" />
				              <form:errors element="div" cssClass="help-inline" path="items[${loopStatus.index}].quantity" />
				            </div>
				          </div>						
						</spring:bind>						
						</td>
						<td><button class="btn btn-danger delete-item" data-item="${loopStatus.index}">Delete</button></td>
					</tr>					
				</c:forEach>
				</tbody>		
			</table>			
			<div class="form-actions" style="padding: 14px 19px;">
				<a href="#" class="btn btn-primary" id="submit-btn">Save</a>
				<c:if test="${!booking.new}">
					<a href="#modal-confirm" data-toggle="modal" data-backdrop="true" data-keyboard="true" class="btn btn-danger" id="delete">Delete</a>
				</c:if>				
				<a class="btn" href="<c:url value="/bookings" />" id="cancel">Cancel</a>
			</div>
			
		</fieldset>
	</form:form>
	<div id="modal-confirm" class="modal hide">
		<div class="modal-header">
			<a href="#" class="close">&times;</a>
			<h3>Confirm</h3>
		</div>
		<div class="modal-body">
			<p>This booking will be permanently deleted and cannot be recovered. Are you sure?</p>
		</div>
		<div class="modal-footer">
			<a href="#" id="confirm-delete" class="btn btn-primary">Confirm</a> <a href="#" id="close-confirm" class="btn btn-secondary">Cancel</a>
		</div>
	</div>
	<script id="itemTemplate" type="text/html">
    	<tr id="item" data-item="\${id}">
			<td>
				<input type="text" name="items[\${id}].itemLabel" />
			</td>
			<td>
				<input type="text" name="items[\${id}].itemUnitPrice" />
			</td>
			<td><input type="text" name="items[\${id}].quantity" /></td>
			<td><button class="btn danger delete-item" data-item="\${id}">Delete</button></td>
		</tr>	
	</script>
</body>
</html>
