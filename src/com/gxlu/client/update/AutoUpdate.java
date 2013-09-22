package com.gxlu.client.update;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "info")
public class AutoUpdate {
	@Element(name = "version")
	private String version;
	@Element(name = "updateserver")
	private UpdateServer updateServer;
	@ElementList(required=false,entry = "file",type = File.class)
	private ArrayList<File> files;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public UpdateServer getUpdateServer() {
		return updateServer;
	}
	public void setUpdateServer(UpdateServer updateServer) {
		this.updateServer = updateServer;
	}
	public ArrayList<File> getFilelist() {
		return files;
	}
	public void setFilelist(ArrayList<File> files) {
		this.files = files;
	}
}
