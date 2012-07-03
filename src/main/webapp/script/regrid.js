function regrid(idValue, headerColumnsValue, actionValue){
	$('#'+idValue).datagrid({
		url: actionValue,
		columns: [headerColumnsValue],
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