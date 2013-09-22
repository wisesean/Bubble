package com.gxlu.client.bubble.printbubble;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.gxlu.client.res.string.StrConstants;
import com.rabbitmq.client.ConnectionFactory;

public class BubbleEntrance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties props=System.getProperties();
		System.out.println("Java\u7684\u8FD0\u884C\u73AF\u5883\u7248\u672C\uFF1A"+props.getProperty("java.version")); 
		System.out.println("Java\u7684\u8FD0\u884C\u73AF\u5883\u4F9B\u5E94\u5546\uFF1A"+props.getProperty("java.vendor")); 
		System.out.println("Java\u7684\u5B89\u88C5\u8DEF\u5F84\uFF1A"+props.getProperty("java.home")); 
		System.out.println("Java\u7684\u865A\u62DF\u673A\u89C4\u8303\u7248\u672C\uFF1A"+props.getProperty("java.vm.specification.version")); 
		System.out.println("Java\u7684\u865A\u62DF\u673A\u89C4\u8303\u4F9B\u5E94\u5546\uFF1A"+props.getProperty("java.vm.specification.vendor")); 
		System.out.println("Java\u7684\u865A\u62DF\u673A\u89C4\u8303\u540D\u79F0\uFF1A"+props.getProperty("java.vm.specification.name")); 
		System.out.println("Java\u7684\u865A\u62DF\u673A\u5B9E\u73B0\u7248\u672C\uFF1A"+props.getProperty("java.vm.version"));
		System.out.println("Java\u7684\u865A\u62DF\u673A\u5B9E\u73B0\u540D\u79F0\uFF1A"+props.getProperty("java.vm.name")); 
		System.out.println("Java\u8FD0\u884C\u65F6\u73AF\u5883\u89C4\u8303\u7248\u672C\uFF1A"+props.getProperty("java.specification.version")); 
		System.out.println("Java\u8FD0\u884C\u65F6\u73AF\u5883\u89C4\u8303\u4F9B\u5E94\u5546\uFF1A"+props.getProperty("java.specification.vender")); 
		System.out.println("Java\u8FD0\u884C\u65F6\u73AF\u5883\u89C4\u8303\u540D\u79F0\uFF1A"+props.getProperty("java.specification.name")); 
		System.out.println("Java\u7684\u7C7B\u683C\u5F0F\u7248\u672C\u53F7\uFF1A"+props.getProperty("java.class.version")); 
		System.out.println("Java\u7684\u7C7B\u8DEF\u5F84\uFF1A"+props.getProperty("java.class.path")); 
		System.out.println("\u52A0\u8F7D\u5E93\u65F6\u641C\u7D22\u7684\u8DEF\u5F84\u5217\u8868\uFF1A"+props.getProperty("java.library.path")); 
		System.out.println("\u9ED8\u8BA4\u7684\u4E34\u65F6\u6587\u4EF6\u8DEF\u5F84\uFF1A"+props.getProperty("java.io.tmpdir")); 
		System.out.println("\u4E00\u4E2A\u6216\u591A\u4E2A\u6269\u5C55\u76EE\u5F55\u7684\u8DEF\u5F84\uFF1A"+props.getProperty("java.ext.dirs")); 
		System.out.println("\u64CD\u4F5C\u7CFB\u7EDF\u7684\u540D\u79F0\uFF1A"+props.getProperty("os.name")); 
		System.out.println("\u64CD\u4F5C\u7CFB\u7EDF\u7684\u6784\u67B6\uFF1A"+props.getProperty("os.arch")); 
		System.out.println("\u64CD\u4F5C\u7CFB\u7EDF\u7684\u7248\u672C\uFF1A"+props.getProperty("os.version")); 
		System.out.println("\u6587\u4EF6\u5206\u9694\u7B26\uFF1A"+props.getProperty("file.separator"));
		System.out.println("\u8DEF\u5F84\u5206\u9694\u7B26\uFF1A"+props.getProperty("path.separator")); 
		System.out.println("\u884C\u5206\u9694\u7B26\uFF1A"+props.getProperty("line.separator")); 
		System.out.println("\u7528\u6237\u7684\u8D26\u6237\u540D\u79F0\uFF1A"+props.getProperty("user.name")); 
		System.out.println("\u7528\u6237\u7684\u4E3B\u76EE\u5F55\uFF1A"+props.getProperty("user.home")); 
		System.out.println("\u7528\u6237\u7684\u5F53\u524D\u5DE5\u4F5C\u76EE\u5F55\uFF1A"+props.getProperty("user.dir"));
		SysEnvironment se = new SysEnvironment();
		String dir = se.getTelantdir();
		System.out.println(dir);
		String path = se.getTelantfile();
		System.out.println(path);
		File dbdir = new File(dir);
		File db = new File(path);
		if(!dbdir.isDirectory()) {
			System.out.println("\u4E0D\u5B58\u5728\u76EE\u5F55");
			dbdir.mkdir();
			try {
				db.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				DialogBox.CreateErrorDialog(null, "\u521B\u5EFA\u6587\u4EF6\u5931\u8D25,\u65E0\u6CD5\u542F\u52A8\u7CFB\u7EDF\u3002"+e.getMessage(), StrConstants.b_ui_dialog_waring_error_title);
				return;
			}
		}else {
			if(!db.isFile()) {
				System.out.println("\u4E0D\u5B58\u5728\u6587\u4EF6");
				try {
					db.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					DialogBox.CreateErrorDialog(null, StrConstants.b_ui_back_filecreate_fail+e.getMessage(), StrConstants.b_ui_dialog_waring_error_title);
					return;
				}
			}
		}
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("gxlu-cq1");
		try {
			factory.newConnection();
		} catch (IOException e) {
			System.out.println("hosts file does not contain the necessary content:136.6.192.211 gxlu-cq1");
			FileOpera fo = new FileOpera();
			try {
				if(se.getOsname().contains("Window")) {
					fo.setContent(new File("C:/Windows/System32/drivers/etc/hosts"), fo.getContent(new File("./../configuration/HOSTS")), true);
					System.out.println("modify hosts was successful!");
					DialogBox.CreateInfoDialog(null, StrConstants.b_sys_restart, StrConstants.b_ui_dialog_waring_error_title);
					if(DialogBox.CreateWarningDialog(null, StrConstants.b_sys_exitornot, StrConstants.b_ui_dialog_waring_comfirm_title) == 0) {
						System.exit(1);
					}
				}else {
					fo.setContent(new File("/etc/hosts"), "136.6.192.211 gxlu-cq1", true);
					System.out.println("modify hosts was successful!");
					if(DialogBox.CreateWarningDialog(null, StrConstants.b_sys_exitornot, StrConstants.b_ui_dialog_waring_comfirm_title) == 0) {
						System.exit(1);
					}
				}
			} catch (IOException e1) {
				DialogBox.CreateErrorDialog(null, "Can not Modify HOSTS File.Please modify permissions granted to the file.", StrConstants.b_ui_dialog_waring_error_title);
				e1.printStackTrace();
			}
		}
		new BubbleUi();
	}

}
