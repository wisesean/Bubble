/**
 * ���ļ���ز���
 * */
package com.gxlu.client.bubble.printbubble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOpera {
	public final static boolean CONTENT_ADD = true;
	public final static boolean CONTENT_REVERT = false;
	/**
	 * ��ȡ�ļ�����
	 * @param f �ļ�
	 * @return content
	 * */
	public String getContent(File f) {
		FileReader fin = null;
		BufferedReader br = null;
		String content="";
		try {
			fin = new FileReader(f);
			br = new BufferedReader(fin);
			content = br.readLine();
			String message = null;
			while((message = br.readLine()) != null) {
				if("".equals(content)) {
					content = content + message;
				}else {
					content = content + "\n" + message;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fin.close();
				br.close();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content;
	}
	
	/**
	 *���ļ���д������
	 *@param f �ļ�
	 *@param content Ҫд������� 
	 * @throws IOException 
	 * */
	public void setContent(File f,String content,boolean isAdd) throws IOException {
		FileWriter fos = null;
		try {
			fos = new FileWriter(f,isAdd);
			if(f.length() > 0 && isAdd) {
				fos.write("\n"+content);
			}else {
				fos.write(content);
			}
		} finally {
			try {
				if(fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
