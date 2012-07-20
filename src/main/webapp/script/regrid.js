function loadDataIntoTable(viewID, subviewID, title, headerColumns, action){
	$('#'+subviewID).datagrid({
		title: title,
		url: action,
		fitColumns: true,
		striped: true,
		columns: [headerColumns],
		loadMsg: 'Message Loading...',
		width: 750,
		height: 350,
        pagination: true,
		rownumbers: true,
		singleSelect: true,
		pageNumber: 1,
		pageSize: 20,
		pageList: [10,20,30,50],
		toolbar: [{
			id: "btnQuery",
			text: "Query",
			iconCls: "icon-search",
			handler: function(){
				alert("Query");
			}
		},'-',{
			id: "btnModifyQuery",
			text: "Modify Query",
			iconCls: "icon-search",
			handler: function() {
				alert("Modify Query");
			}
		},'-'],
		onClickRow: function(rowIndex, rowData) {
			loadInheritingTable(viewID, subviewID);
		}			
	});
}

function loadLeadingTable(viewID){
	$("#mainContent").empty();
	$.getJSON("headers", {"viewid":viewID}, function(data){
		$('<div><table id="' + data.subViewID + '"/></div>').appendTo("#mainContent");
		loadDataIntoTable(data.viewID, data.subViewID, data.subViewTitle, data.headerColumns, "show.action");
	});
}


function loadInheritingTable(viewID, subviewID){
	$.getJSON("headers", {"viewid":viewID, "subviewid":subviewID}, function(data){
		$('<div id="' + data.subViewID +  '"><table id="' + data.subViewID + '"/></div>').appendTo("#mainContent");
		loadDataIntoTable(data.viewID, data.subViewID, data.subViewTitle, data.headerColumns, "show.action");
	});
}