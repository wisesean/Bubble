package com.gxlu.client.bubble.printbubble;

import java.util.Properties;

public class SysEnvironment {
	private String version;
	private String home;
	private String vmversion;
	private String jreversion;
	private String classpath;
	private String libpath;
	private String tmpdir;
	private String osname;
	private String osarch;
	private String userhome;
	private String userdir;
	private String fsp;

	public SysEnvironment() {
		Properties props = System.getProperties();
		version = props.getProperty("java.version");
		home = props.getProperty("java.home");
		vmversion = props.getProperty("java.vm.specification.version");
		jreversion = props.getProperty("java.specification.version");
		classpath = props.getProperty("java.class.path");
		libpath = props.getProperty("java.library.path");
		tmpdir = props.getProperty("java.io.tmpdir");
		osname = props.getProperty("os.name");
		userhome = props.getProperty("user.home");
		userdir = props.getProperty("user.dir");
		fsp = props.getProperty("file.separator");
	}

	public String getTelantdir() {
		return this.userhome + this.fsp + "TelantOrder";
	}

	public String getTelantfile() {
		return this.userhome + this.fsp + "TelantOrder" + this.fsp + "ExceptionOrderLog.mdd";
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getVmversion() {
		return vmversion;
	}

	public void setVmversion(String vmversion) {
		this.vmversion = vmversion;
	}

	public String getJreversion() {
		return jreversion;
	}

	public void setJreversion(String jreversion) {
		this.jreversion = jreversion;
	}

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public String getLibpath() {
		return libpath;
	}

	public void setLibpath(String libpath) {
		this.libpath = libpath;
	}

	public String getTmpdir() {
		return tmpdir;
	}

	public void setTmpdir(String tmpdir) {
		this.tmpdir = tmpdir;
	}

	public String getOsname() {
		return osname;
	}

	public void setOsname(String osname) {
		this.osname = osname;
	}

	public String getOsarch() {
		return osarch;
	}

	public void setOsarch(String osarch) {
		this.osarch = osarch;
	}

	public String getUserhome() {
		return userhome;
	}

	public void setUserhome(String userhome) {
		this.userhome = userhome;
	}

	public String getUserdir() {
		return userdir;
	}

	public void setUserdir(String userdir) {
		this.userdir = userdir;
	}

	public String getFsp() {
		return fsp;
	}

	public void setFsp(String fsp) {
		this.fsp = fsp;
	}
}
