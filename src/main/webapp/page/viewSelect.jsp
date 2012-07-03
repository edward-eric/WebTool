<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="css/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="script/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="script/regrid.js"></script>
		
	<title>Welcome to Support Tool</title>
</head>
<body>

   <div>
       <h2>Please select a view instance:</h2>
       <s:select list="viewList"
                 label="views"
                 headerKey="-1"
                 headerValue="Select View"
                 size="1"
                 onchange="refresh(this.value)"
                 />
       
   </div>
   
   <div id="mainContent">
      <h2>this is main content</h2>
   </div>
   
   
	
</body>
</html>