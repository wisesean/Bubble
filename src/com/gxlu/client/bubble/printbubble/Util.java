/**
 * һ���������һЩ������Ҫ�õ��Ĺ��ܺ���
 * @author WiseSean
 * */
package com.gxlu.client.bubble.printbubble;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class Util {
	public final static int NORMAL_MODEL = 0;
	public final static int SILENT_MODEL = 1;
	public final static String image_t_path = "/com/gxlu/client/res/images/1_t.png";
	public final static String image_path = "/com/gxlu/client/res/images/1.png";
	/**
     * �õ���Ӧ���ļ�ѡ���,��ΪҪ��һ��һ��
     * @param filter 
     * @param mode
     * @return
     */
    public static JFileChooser getFileChooser(FileNameExtensionFilter filter, int mode) {
    	JFileChooser jfc = new JFileChooser(new File("."));
    	jfc.setFileFilter(filter);
    	jfc.setFileSelectionMode(mode);
    	return jfc;
    }
    
    /**
     * ͳ���ļ�����ĳһ�鼮Ŀ¼�µ��ļ�����
     * @param filelist
     * @return �ļ���Ŀ
     * */
    public static int countFileNumber(String filelist) {
    	File d = new File(filelist); 
    	File[] subdirs = d.listFiles(new FileFilter() { 
    		public boolean accept(File filelist)   { 
    	        return   filelist.isFile(); 
    		} 
    	}); 
    	return   subdirs.length;
    }
    
    /**
     * ����һ���ļ���
     * @param path Ҫ����Ŀ¼�ĸ�Ŀ¼  dirname Ŀ¼���
     * @return true or false
     * */
    public static boolean mkDir(String path,String dirname) {
    	File fb = new File(path+dirname);
		if(fb.exists()) {
			infoOut("directory already exist");
		}else {
			fb.mkdir();
			return true;
		}
		infoOut("make directory false");
    	return false;
    	 
    }
    
    /**
     * ����ַ�
     * @param s
     * @return 
     * */
    public static void infoOut(String s) {
    	System.out.println(":       "+s);
    }
    
    /**
     * �õ�ϵͳʱ��
     * @return datetime
     * */
    public static String getTime() {
    	SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String datetime = tempDate.format(new java.util.Date());
    	infoOut(datetime);
    	return datetime;
    }
    
    /**
     * ��ȡ��ǰĿ¼·��
     * @return curr_path
     * */
    public static String getCurrpath() {
    	File   directory =   new   File(".");//�õ������б����ڵ�Ŀ¼   
		String curr_path = null;
		try {
			curr_path = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curr_path;
    	
    }
    
    /**
     * ����ת��00:00�ַ�
     * @param d ����
     * @return �ַ�
     */
    public static String secondToString(int d) {
        DecimalFormat df = new DecimalFormat("00");
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(d / 60)).append(":").append(df.format(d % 60));
        return sb.toString();
    }
    /**
     * ����ǰ�̹߳�����ʱ ָ���ĺ�����
     * @param time
     * */
    public static void delay(int time) {
    	try 
    	{ 
    		Thread.currentThread();
    		Thread.sleep(time);//���� 
    	} 
    	catch(Exception e){
    		
    	}
    }
  
	
    /**
     * ��ȡ�ļ���
     * @param filename
     * */
    public static StringBuffer daoxu(String fileName) {
    	String mc = "";
		for (int i = fileName.length(); i >= 1; i--) {
			if (fileName.charAt(i - 1) == '\\')
				break;
		    mc += fileName.charAt(i - 1);
		}
		StringBuffer buffer = new StringBuffer(mc);
		StringBuffer mm = buffer.reverse();
		return mm;
	} 
    /**
     * ȥ���ļ���չ��
     * @param filename
     * @return String
     * */
    public static String removeExtension(String filename) {
    	
    	if(filename.contains(".")) {
    		String removed = filename.substring(0, filename.lastIndexOf("."));
    		return removed;
    	}
    	return filename;
    }
    
    /**
     * ���һ���ļ���ȫ·���õ������չ��
     * @param path �ļ��ľ��·��
     * @return  ��չ��
     */
    public static String getExName(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
    
    public static void InitList() {    	SysEnvironment se = new SysEnvironment();
    	FileReader fir;
		try {
			fir = new FileReader(new File(se.getTelantfile()));
	    	@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fir);
			String message = null;
			while((message = br.readLine()) != null) {
				BubbleUi.d_message_lm.addElement(message);
			}
			System.out.println("init succ!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
	 * ��ȡ���Ƶ�FRAME
	 * @param title
	 * @param d
	 * @param isCenter
	 * @return
	 */
	public static JFrame getMyJFrame(String title,Dimension d,boolean isCenter,boolean reSizAble) {
		JFrame jf = new JFrame(title);
		jf.setSize(d);
		jf.setResizable(reSizAble);
		if(isCenter) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = jf.getSize();
			if (frameSize.width > screenSize.width) {
				frameSize.width = screenSize.width;
			}
			if (frameSize.height > screenSize.height) {
				frameSize.height = screenSize.height;
			}
			jf.setLocation((screenSize.width - frameSize.width) / 2,
					(screenSize.height - frameSize.height) / 2);
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(jf);
		} catch (Exception exe) {
			exe.printStackTrace();
		}	
		return jf;
	}
   
    public static File getResFile(String filename) {
    	String path = Util.class.getResource(filename).getFile();
		try {
			return new File(URLDecoder.decode(path,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("��Ϣ�ļ���ȡʧ�ܡ�");
		}
    }
}


                                                                