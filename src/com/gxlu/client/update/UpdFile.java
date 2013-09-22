package com.gxlu.client.update;

public class UpdFile {
	private String name = "";// ����
	private String path = "";// ·��
	private int type = 0;// ���� 0.�ļ� 1.Ŀ¼
	private String version = "";// �汾��

	public UpdFile() {
		super();
	}

	public UpdFile(String name) {
		this();
		this.name = name;
	}

	public UpdFile(String name, String path, int type, String version) {
		this(name);
		this.path = path;
		this.type = type;
		this.version = version;
	}

	/** */
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/** */
	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** */
	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}

	/** */
	/**
	 * @param path
	 *            The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/** */
	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}

	/** */
	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}

	/** */
	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}

	/** */
	/**
	 * @param version
	 *            The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public String toString() {
		return "Name:" + getName() + ",Path:" + getPath() + ",Type:"
				+ getType() + ",Version:" + getVersion();
	}
}