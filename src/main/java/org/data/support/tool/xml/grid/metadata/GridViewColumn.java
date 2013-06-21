package org.data.support.tool.xml.grid.metadata;

public class GridViewColumn {
	
	private String name;
	private String label;
	
	private boolean visible = false;
	
	public GridViewColumn(String name, String label, boolean visible)
	{
		this.name = name;
		this.label = label;
		this.visible = visible;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
