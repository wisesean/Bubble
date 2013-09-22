package com.gxlu.client.update;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.simpleframework.xml.core.Persister;

import com.gxlu.client.res.string.StrConstants;

public class Config {
	private static Config config = null;
	AutoUpdate autoUpdate = null;

	public static Config getInstance() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

	private Config() {
		try {
			Persister persister = new Persister();
			autoUpdate = persister.read(AutoUpdate.class, new File(StrConstants.b_update_client_autoupdate_xml));
			persister.write(autoUpdate, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refresh() {
		config = new Config();
	}

	public String getVerstion() {
		return autoUpdate.getVersion();
	}

	public String getServerIp() {
		return autoUpdate.getUpdateServer().getIp();
	}

	public UpdFile[] getFiles() {
		if (config == null) {
			return null;
		}
		ArrayList<com.gxlu.client.update.File> lst = autoUpdate.getFilelist();
		if (lst.size() == 0) {
			return null;
		}
		UpdFile files[] = new UpdFile[lst.size()];
		for (int i = 0; i < lst.size(); i++) {
			com.gxlu.client.update.File file = autoUpdate.getFilelist().get(i);
			files[i] = new UpdFile(file.getName());
			if ("File".equals(file.getName())) {
				files[i].setType(0);// 文件
			} else {
				files[i].setType(1);// 目录
			}
			files[i].setPath(file.getPath());
			files[i].setVersion(file.getSubver());
		}
		return files;
	}

	public String getServerPort() {
		if (config == null) {
			return "";
		}
		return autoUpdate.getUpdateServer().getPort();
	}

	public static void print(String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss->>");
		String str = sdf.format(new Date());
		System.out.println(str + msg);
	}

	public static void main(String args[]) {
		Config cfg = Config.getInstance();
		UpdFile files[] = cfg.getFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i]);
		}
		Config.print("test");
	}

	/** */
	/**
	 * 格式化路径，增加其尾部的目录分隔符
	 * 
	 * @param p
	 *            要格式化的目录字符串
	 */
	public static String formatPath(String p) {
		if (!p.endsWith(File.separator))
			return (p + File.separator);
		return p;
	}

	/** */
	/**
	 * 格式化路径，去除其尾部的目录分隔符
	 * 
	 * @param p
	 *            要格式化的目录字符串
	 */
	public static String unformatPath(String p) {
		if (p.endsWith(File.separator))
			return (p.substring(0, p.length() - 1));
		return p;
	}

	public static byte[] getCmd(String cmd) {
		// 第一位用于标识是命令，后面8位为命令名
		byte cmdb[] = new byte[9];
		cmdb[0] = AUPD.CMD_DATA_SECT;
		byte[] tmp = cmd.getBytes();
		if (tmp.length != 8) {
			Config.print("命令有误:" + cmd + "<<");
			return null;
		}
		for (int i = 0; i < 8; i++) {
			cmdb[i + 1] = tmp[i];
		}
		return cmdb;
	}

	public static String parseCmd(byte cmd[]) {
		return new String(cmd, 0, 8);
	}

	public static byte[] getLen(int len) {
		String slen = String.valueOf(len);
		while (slen.length() < 4) {
			slen = "0" + slen;
		}
		return slen.getBytes();
	}

	public static void copyArray(byte objary[], byte souary[], int o_begin,
			int s_begin, int len) {
		for (int i = 0; i < len; i++) {
			objary[o_begin + i] = souary[s_begin + i];
		}
	}
}
