<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Editable DataGrid - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="css/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="script/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="script/regrid.js"></script>
	
	<script type="text/javascript">
	    $(function() {
	    	regrid('scenario', [  
	    	                    {field:'id',title:'id',width:100},  
	    	                    {field:'name',title:'name',width:100},  
	    	                    {field:'sex',title:'sex',width:100},
	    	                    {field:'age',title:'age',width:100},
	    	                    {field:'birthday',title:'birthday',width:100},
	    	                    {field:'classname',title:'classname',width:100}
	    	                ], 'show.action')
	    });
	</script>
	
</head>
<body>
	<h2>Editable DataGrid</h2>
	
	<!-- <table id="scenario" title="Scenario" url="show.action">
		<thead>
			<tr>
				<th field="id" width="80">ID</th>
				<th field="name" width="100">NAME</th>
				<th field="sex" width="80">SEX</th>
				<th field="age" width="80">AGE</th>
				<th field="birthday" width="250">BIRTHDAY</th>
				<th field="classname" width="60">CLASS</th>
			</tr>
		</thead>
	</table> -->
	
	<table id="scenario"/>
	
</body>
</html>