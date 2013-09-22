package com.gxlu.client.update;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.gxlu.client.res.string.StrConstants;

public class AutoUpdateClient {
	private Socket socket = null;
    private OutputStream socketOut;
    private InputStream socketIn;
    private Config config = Config.getInstance();//�����ļ�����
    private String currFileAbs = "";//��ǰ�����ļ���ȫ·��
    public AutoUpdateClient()
    {
        try
        {
            socket = new Socket(config.getServerIp(),Integer.parseInt(config.getServerPort()));
            socket.setSoTimeout(30000);//30��
        }catch(Exception e)
        {
            Config.print("�������Զ�����������֮�������ʧ��,"+e);
            e.printStackTrace();
        }
    }
    public void update()
    {
        if(socket == null)
        {
            Config.print("�޷�����������ӣ�����ʧ��");
            return;
        }
        try
        {
            socketOut = socket.getOutputStream();
            socketIn = socket.getInputStream();      
            
            //��ʼ��������
            byte flag [] = new byte[1];
            byte cmd[] = new byte[8];//����
//            byte datahead [] = new byte[5];//����ͷ������һλ���ڱ�ʶ���ݣ�����λΪ����
//            byte buffer[] = new byte[AUPD.BUFFER_SIZE];//�������ͷ��������ͷ��
//            byte data[] = new byte[AUPD.DATA_SIZE];//��ž������������            
            //���ͱ��ذ汾��Ϣ��������                
            socketOut.write(Config.getCmd(AUPD.SEND_CLIENT_VERSION));//���ʹ���汾��Ϣ����
            sendClientVer();//���Ͱ汾��Ϣ
            while(true)
            {
                //��ȡ��Ϣ
                int len = socketIn.read(flag,0,1);
                if(len!=1)
                {
                    Config.print("��ȡ��ʶλʧ��");
                    socketOut.write(Config.getCmd(AUPD.BYE));//����
                   break;
                }
                if(flag[0]==AUPD.CMD_DATA_SECT)//������
                {
                    len = socketIn.read(cmd,0,8);
                    if(len!=8)
                    {
                        Config.print("��ȡ����ʧ��");
                        socketOut.write(Config.getCmd(AUPD.BYE));//����
                        break;
                    }
                    if(Config.parseCmd(cmd).equals(AUPD.RECEIVED_CLIENT_VERSION))//�յ��汾��Ϣ
                    {
                        Config.print("�������ɹ��յ��汾��Ϣ");
                        socketOut.write(Config.getCmd(AUPD.READY_TO_UPDATE));
                        continue;
                    }else if(Config.parseCmd(cmd).equals(AUPD.SEND_FILE_ABSOULT))//�����ļ�ȫ·��
                    {
                        Config.print("��ʼ�����ļ�·����");                      
                    }else if(Config.parseCmd(cmd).equals(AUPD.UPDATED_FAILURE))//����ʧ��
                    {
                        Config.print("�汾����ʧ��");
                        socketOut.write(Config.getCmd(AUPD.BYE));//����
                        break;
                   }else if(Config.parseCmd(cmd).equals(AUPD.UPDATED_SUCCESSFUL))//���³ɹ�
                   {
                       Config.print("�汾���³ɹ�");
                       socketOut.write(Config.getCmd(AUPD.BYE));//����
                       //��������Ϣ
                       openFile(".\\config\\history.htm");
                       break;
                   }else if(Config.parseCmd(cmd).equals(AUPD.NOTNEED_UPDATED))//�������
                   {
                       Config.print("�Ѿ������°汾���������");
                       socketOut.write(Config.getCmd(AUPD.BYE));//����
                      break;
                   }else if(Config.parseCmd(cmd).equals(AUPD.BYE))//��������
                   {
                      socketOut.write(Config.getCmd(AUPD.BYE));//����
                       break;
                   }
               }else if(flag[0]==AUPD.MARK_DATA_SECT || flag[0]==AUPD.MARK_DATA_END)//��������
               {
                   if(Config.parseCmd(cmd).equals(AUPD.SEND_FILE_ABSOULT))//�����ļ�ȫ·��
                    {
                       currFileAbs = receiveFileAbsPath(flag[0]);
                       if(currFileAbs!=null && !currFileAbs.equals(""))//�ɹ�
                       {
                           socketOut.write(Config.getCmd(AUPD.RECEIVED_FILE_ABSOULT));
                           Config.print("�����ļ�ȫ·����"+currFileAbs+"���ɹ�");
                       }else
                       {
                           Config.print("�����ļ�ȫ·��ʧ��");
                           socketOut.write(Config.getCmd(AUPD.BYE));//����
                           break;
                       }
                  }else if(Config.parseCmd(cmd).equals(AUPD.START_TRANSMIT))//�����ļ�
                   {
                       if(receiveFile(flag[0]))
                       {
                           socketOut.write(Config.getCmd(AUPD.TERMINATE_TRANSMIT));
                       }else
                       {
                           socketOut.write(Config.getCmd(AUPD.BYE));
                       }
                   }else
                   {
                       Config.print("���ַ���������,"+new String(cmd));
                      socketOut.write(Config.getCmd(AUPD.BYE));//����
                      break;
                   }
               }else
               {
                   Config.print("��������ʶλ,"+flag[0]);
                   socketOut.write(Config.getCmd(AUPD.BYE));//����
                   break;
               }
           }//END while(true)
           
          //�ر���Դ������
           socketOut.close();
           socketIn.close();
           socket.close();
           Config.print("�Զ������������");
       }catch(Exception e)
       {
          Config.print("��������ʧ��,"+e);
           e.printStackTrace();
       }
   }
   private void openFile(String file)
   {
       try
      {
           Runtime.getRuntime().exec("cmd /c "+file);
       }catch(Exception e)
       {
           e.printStackTrace();
       }
   }
   private String receiveFileAbsPath(byte flag)
   {
       String absPath = "";
       //�����ļ�ȫ·��
       try
      {
           //�������ݻ�����
           byte flagb[] = new byte[1];//��־
           byte lenb [] = new byte[4];//����
           //�����ļ�ȫ·��
           StringBuffer strBuf = new StringBuffer();//���ڽ�����Ϣ
          int len = -1;
           boolean isFirst = true;
           boolean isOk = false;        
           flagb[0] = flag;
           while(true)
           {
               //��һ��
              if(isFirst)
               {
                   isFirst = false;        
               }else
               {
                   len = socketIn.read(flagb,0,1);//��ȡ��ʶλ
                   if(len != 1)
                   {
                       Config.print(socket.getInetAddress() + ":��ȡ���ݱ�ʶλʧ��");
                       break;
                   }
               }
              //��ȡ���ݳ���
               if(flagb[0]==AUPD.MARK_DATA_SECT)
               {
                   len = socketIn.read(lenb, 0, 4);
                   if (len != 4)
                   {
                       Config.print(socket.getInetAddress() + ":��ȡ����ͷ��ʧ��");
                  break;
                   }
               }
               if (flagb[0] == AUPD.MARK_DATA_SECT)//��������
               {
                   int cLen = Integer.parseInt(new String(lenb, 0, 4));//�������ݳ���
                   byte data[] = new byte[cLen];
                   len = socketIn.read(data, 0, cLen);
                   System.out.println("len:"+len+"cLen="+cLen+">>"+new String(data,0,len));
                   int totLen = len;
                  while (totLen < cLen)//����λҪ���ض�ȡ
                   {
                       strBuf.append(new String(data, 0, len));
                       len = socketIn.read(data, 0, cLen - totLen);
                       totLen = totLen + len;
                       System.out.println("len:"+len+"cLen="+cLen);
                   }
                   strBuf.append(new String(data, 0, len));
               }else if(flagb[0]==AUPD.MARK_DATA_END)//���ݽ���
             {
                   isOk = true;
                   break;
               }else
               {
                   Config.print(socket.getInetAddress()+":�յ�����������,"+new String(flagb,0,1)+"<<");
                   break;
               }
           }//END while(true)
           if(isOk)//�ɹ�
          {            
               absPath = strBuf.toString();
         }else//ʧ��
           {
               socketOut.write(Config.getCmd(AUPD.BYE));//����
           }
     }catch(Exception e)
       {
           Config.print("�����ļ�ȫ·������ʧ��,"+e);
       }
       return absPath;
  }
   private boolean receiveFile(byte flag)
  {
      try
       {
          if(currFileAbs==null||currFileAbs.equals(""))
           {
               Config.print("�޷���ȡ�����ļ���Ϣ������ʧ��");
              return false;
           }
           File file;
          //�ȼ��Ŀ¼�Ƿ����
           //�õ�Ŀ¼
           int idx = currFileAbs.lastIndexOf(File.separator);
          String path = currFileAbs.substring(0,idx);
           file = new File(path);
           if(!file.isDirectory() || !file.exists())
           {
               Config.print("�´���Ŀ¼:"+path);
               file.mkdir();
           }
          file = new File(currFileAbs);
           FileOutputStream fout = new FileOutputStream(file);
           //�������ݻ�����
           byte flagb[] = new byte[1];//��־
           byte lenb [] = new byte[4];//����
           int len = -1;
           boolean isFirst = true;
           boolean isOk = false;
           flagb[0] = flag;
           //�����ϴ����ļ�����
           while (true)
           {
               //��һ��
               if(isFirst)
               {
                   isFirst = false;        
               }else
             {
                   len = socketIn.read(flagb,0,1);//��ȡ��ʶλ
                   if(len != 1)
                   {
                       Config.print(socket.getInetAddress() + ":��ȡ���ݱ�ʶλʧ��");
                      break;
                   }
             }
               //��ȡ���ݳ���
               if(flagb[0]==AUPD.MARK_DATA_SECT)
               {
                   len = socketIn.read(lenb, 0, 4);
                   if (len != 4)
                   {
                       Config.print(socket.getInetAddress() + ":��ȡ����ͷ��ʧ��");
                       break;
                   }
               }
               if (flagb[0] == AUPD.MARK_DATA_SECT)//��������
              {
                   int cLen = Integer.parseInt(new String(lenb, 0, 4));//�������ݳ���
                  byte data[] = new byte[cLen];
                   len = socketIn.read(data, 0, cLen);
                   int totLen = len;
                  while (totLen < cLen)//����λҪ���ض�ȡ
                   {
                      fout.write(data,0,len);
                       len = socketIn.read(data, 0, cLen - totLen);
                       totLen = totLen + len;
                 }
                   fout.write(data,0,len);
               }else if(flagb[0]==AUPD.MARK_DATA_END)//���ݽ���
               {
                   isOk = true;
                   break;
               }else
               {
                   Config.print(socket.getInetAddress()+":�յ�����������,"+new String(flagb,0,1)+"<<");
                   break;
               }
           }//END while
           fout.flush();
          fout.close();
           if(isOk)
           {
               Config.print("�ɹ������ļ���"+file.getAbsolutePath());
               return true;
           }else
           {
               Config.print("�����ļ���"+file.getAbsolutePath()+"ʧ��");                
               return false;
           }
       }catch(Exception e)
       {
           Config.print("���ظ����ļ�'"+currFileAbs+"'ʧ��,"+e);
           e.printStackTrace();
           return false;
       }
   }
   //���Ϳͻ��˰汾��Ϣ
   private void sendClientVer()
   {
       try
     {
           File verFile = new File(StrConstants.b_update_client_autoupdate_xml);
           if(!verFile.isFile() || !verFile.exists())
           {
               Config.print("�汾��Ϣ�ļ�������");
               return;
           }
           //��ʼ����
           FileInputStream fis = new FileInputStream(verFile);
          byte buffer[] = new byte[AUPD.BUFFER_SIZE];
          byte data[] = new byte[AUPD.DATA_SIZE];
          int len = 0;
           while((len=fis.read(data))!=-1)
           {
              //��ʶΪ���ݶ�
               buffer[0] = AUPD.MARK_DATA_SECT;
              Config.copyArray(buffer,Config.getLen(len),1,0,4);//4λ����
               //������ݰ�
               for (int i=0; i<len; i++)
                   buffer[i+5] = data[i];//ǰ��λΪͷ��1λ��ʶ+4λ����
               socketOut.write(buffer,0,len+5);//��������
           }//END while
           //��ʶΪ���ݶ��ѽ�������������������
           buffer[0] = AUPD.MARK_DATA_END;
           socketOut.write(buffer,0,1);
           socketOut.flush();
           fis.close();
           Config.print("�汾��Ϣ�������");
     }catch(Exception e)
       {
           Config.print("���Ͱ汾��Ϣ��������ʧ��,"+e);
           e.printStackTrace();
      }
   }    
   //����������
   public static void main(String args[])
   {
       AutoUpdateClient client = new AutoUpdateClient();
       client.update();
   }
}
