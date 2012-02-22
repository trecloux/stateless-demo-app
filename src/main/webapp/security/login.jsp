<!DOCTYPE html>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Authentication</title>
		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    	<!--[if lt IE 9]>
      		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    	<![endif]-->
    	<script src="<c:url value="/scripts/jquery-1.7.1.min.js"/>"></script>
		<link rel="stylesheet" href="<c:url value="/styles/bootstrap.css"/>" type="text/css">		
		<link rel="stylesheet" href="<c:url value="/styles/main.css"/>" type="text/css">
		<script type="text/javascript">
	$(document).ready(function(){
		$('#submit').click(function(){
			$('#form').submit();
		});
	});
		</script>
	</head>
	<body>
	    <div class="container">
			<div class="content">
        		<div class="page-header">
          			<h1>Demo App<small> Session less starter kit</small></h1>
        		</div>
        		<div class="row">
        			<div class="span2">&nbsp;</div>
        			<div class="span10">
        		<% if (request.getParameter("login_error") != null) { %>
						<div class="alert-message error">
	  						<p><strong>Login failure, please retry</strong></p>
	  					</div>
	  				
				<% } %>
					</div>
					<div class="span2">&nbsp;</div>
        		</div>
        		<div class="row">
        			<div class="span2">&nbsp;</div>
          			<div class="span10">
            	    	<form id='form' action="<c:url value="/j_spring_security_check"/>" method="POST">
            	    		<input type="hidden" value="true" name="_spring_security_remember_me" />
				    		<fieldset>
				    			<legend>Authentication</legend>					    			
				    			<div class="clearfix">
									<label for="j_username"><b>Login</b></label>
									<div class="input">
										<input class="large" type="text" name="j_username" id="j_username"/>
									</div>
								</div>
								<div class="clearfix">
									<label for="j_password"><b>Password</b></label>
									<div class="input">
										<input class="large" type="password" name="j_password" id="j_password"/>
									</div>
								</div>
								<div class="form-actions">
			            			<button type="submit" class="btn btn-primary">Login</button>
			            			<button type="reset" class="btn">Cancel</button>
			          			</div>					
							</fieldset>
						</form>
          			</div>
          			<div class="span2">&nbsp;</div>
	        	</div> <!-- /row -->
	      	</div> <!-- /content -->
	    </div> <!-- /container -->
	</body>
</html>
