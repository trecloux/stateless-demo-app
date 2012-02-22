<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Sample App : Not Found</title>
		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    	<!--[if lt IE 9]>
      		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    	<![endif]-->
		<link rel="stylesheet" href="<c:url value="/styles/bootstrap.min.css"/>" type="text/css">		
		<link rel="stylesheet" href="<c:url value="/styles/main.css"/>" type="text/css">
		<script type="text/javascript">
	$(document).ready(function(){
		$('#submit').button().click(function(){
			$('#form').submit();
		});
		$('#form').submit(function(){
			$('#j_username').val($('#j_username').val().toUpperCase());	
		});
	});
		</script>
		<style type="text/css">
      /* Override some defaults */
      html, body {
        background-color: #eee;
      }
      body {
        padding-top: 40px; /* 40px to make the container go all the way to the bottom of the topbar */
      }
      .container > footer p {
        text-align: center; /* center align it with the container */
      }
      .container {
        width: 820px; /* downsize our container to make the content feel a bit tighter and more cohesive. NOTE: this removes two full columns from the grid, meaning you only go to 14 columns and not 16. */
      }

      /* The white background content wrapper */
      .content {
        background-color: #fff;
        padding: 20px;
        margin: 0 -20px; /* negative indent the amount of the padding to maintain the grid system */
        -webkit-border-radius: 0 0 6px 6px;
           -moz-border-radius: 0 0 6px 6px;
                border-radius: 0 0 6px 6px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
      }

      /* Page header tweaks */
      .page-header {
        background-color: #f5f5f5;
        padding: 20px 20px 10px;
        margin: -20px -20px 20px;
      }

    </style>		
	</head>
	<body>
	    <div class="container">
			<div class="content">
        		<div class="page-header">
          			<h1>Blank App<small> Spring / MVC / Hibernate starter kit</small></h1>
        		</div>
        		<div class="row">
        			<div class="span2">&nbsp;</div>
        			<div class="span10">
        				<h2>404 - Not found</h2>				    
        			</div>
					<div class="span2">&nbsp;</div>
	        	</div> <!-- /row -->
	      	</div> <!-- /content -->
	    </div> <!-- /container -->
	</body>
</html>
