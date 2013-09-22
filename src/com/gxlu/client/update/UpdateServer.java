package com.gxlu.client.update;

import org.simpleframework.xml.Element;

public class UpdateServer {
	@Element(name = "ip")
	private String ip;
	@Element(name = "port")
	private String port;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
