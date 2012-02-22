<!DOCTYPE html>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
	<head>
		<title><decorator:title/></title>
		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    	<!--[if lt IE 9]>
      		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    	<![endif]-->
		<link rel="stylesheet" href="<c:url value="/styles/bootstrap.css"/>" type="text/css">		
		<link rel="stylesheet" href="<c:url value="/styles/main.css"/>" type="text/css">
		
		<script src="<c:url value="/scripts/jquery-1.7.1.min.js"/>"></script>
		<script src="<c:url value="/scripts/bootstrap-datepicker.js"/>"></script>
		<script src="<c:url value="/scripts/bootstrap-datepicker-custom.js"/>"></script>
		<script src="<c:url value="/scripts/jquery.tmpl.min.js"/>"></script>
		<decorator:head />
	</head>
	<body>
		<div class="navbar navbar-fixed-top">
		  <div class="navbar-inner">
	        <div class="container">
	          <a class="brand" href="#">Sample App</a>
	          <ul class="nav">
	            <li><a href="<c:url value="/" />">Home</a></li>
	          </ul>
	          <ul class="nav secondary-nav pull-right">
            	<li><a href="<c:url value="/j_spring_security_logout" />"><sec:authentication property="principal.username" />Logout</a></li>
              </ul>
	        </div>
	      </div>
	    </div>

	    <div class="container">
	    	<div class="content">
        		<div class="page-header">
          			<h1><decorator:title/></h1>
        		</div>
       			<div>
       				<decorator:body />
       			</div>
      		</div>
	      <footer>
	      </footer>
	    </div> <!-- /container -->
	</body>
</html>