package com.gxlu.client.update;

import org.simpleframework.xml.Element;

public class File {
	@Element(name = "name")
	private String name;
	@Element(name = "path")
	private String path;
	@Element(name = "subver")
	private String subver;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSubver() {
		return subver;
	}
	public void setSubver(String subver) {
		this.subver = subver;
	}
}
