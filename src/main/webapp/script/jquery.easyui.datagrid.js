

/**
 * jQuery.getJSON( url [, data ] [, success(data, textStatus, jqXHR) ] )
 */

function loadDataToView(headerAction, dataAction, viewId){
	$.getJSON(headerAction, {"viewId" : viewId}, function( data ){
		$('#' + viewId).datagrid({
			title: data.title,
			url: dataAction,
			fitColumns: true,
			striped: true,
			columns: [data.headerColumns],
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
	} );		
}