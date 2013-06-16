package org.data.support.tool.xml.grid.mgr;

public class GridMetadataBuilder {

	public static final String xsd_file = "/grid/gridmeta.xsd";
	
	public static final String grid_tag = "grid";
	public static final String grid_attr_name = "gridName";
	public static final String grid_attr_metaClass = "metaClass";
	
	public static final String grid_dataFetcher_tag = "dataFetcher";
	public static final String grid_dataFetcher_attr_id = "id";
	public static final String grid_dataFetcher_attr_class = "className";
	public static final String grid_dataFetcher_parameters_tag = "parameters";
	public static final String grid_dataFetcher_parameters_param_tag = "param";
	public static final String grid_dataFetcher_parameters_param_attr_name = "name";
	public static final String grid_dataFetcher_parameters_param_attr_value = "value";
	
	public static final String grid_columns_tag = "columns";
	public static final String grid_columns_column_tag = "colName";
	public static final String grid_columns_column_attr_metaType = "metaType";
	public static final String grid_columns_column_attr_dataType = "dataType";
	
	public static final String grid_views_tag = "views";
	public static final String grid_views_view_tag = "view";
	public static final String grid_views_view_columnRef_tag = "columnRef";
	public static final String grid_views_view_columnRef_attr_name = "name";
	public static final String grid_views_view_columnRef_attr_label = "label";
	
	

}
