function loadDataIntoTable(id, headerColumns, action){
	$('#'+id).datagrid({
		url: action,
		columns: [headerColumns],
		loadMsg: 'Message Loading...',
		width: 750,
		height: 350,
        pagination: true,
		rownumbers: true,
		singleSelect: true,
		pageNumber: 1,
		pageSize: 20,
		pageList: [10,20,30,50]				
	});
}

function castDataGrid(tableID, action){
	$('#'+tableID).datagrid({
		url:          action,
		loadMsg:      'Data is Loading...',
		width:        750,
		height:       350,
		pagination:   true,
		rownumbers:   true,
		singleSelect: true,
		pageNumber:   1,
		pageSize:     20,
		pageList:     [10, 20, 30, 50]
	});
}

function loadData(){
	castDataGrid('scenario', 'show.action');
}

function loadLeadingTable(viewID){
	$("#mainContent").empty();
	$.getJSON("loadLeadingTable", {"viewid", viewID}, function(data){
		$('<table id="' + data.viewName + '"/>').appendTo("mainContent");
		loadDataIntoTable(data.viewName, data.headerColumns, "show.action");
	})
}

function writeHeader(viewID){
	$("#mainContent").empty();
	$.getJSON("getheader",{"viewid":viewID}, function(data){
		$('<table id="scenario"/>').appendTo("#mainContent");
		regrid("scenario", data.headerColumns, "show.action");
	});
	
}

function refresh(view){
	$('#mainContent').empty();
	$('<table id="' + view + '"/>').appendTo('#mainContent');
	regrid(view, [  
	                    {field:'id',title:'id',width:100},  
	                    {field:'name',title:'name',width:100},  
	                    {field:'sex',title:'sex',width:100},
	                    {field:'age',title:'age',width:100},
	                    {field:'birthday',title:'birthday',width:100},
	                    {field:'classname',title:'classname',width:100}
	                ], 'show.action')
}