package com.gxlu.client.update;

public class AUPD {
    /** *//**
     * ���������
     */
    public static final String NONE = "NONE    ";
    
    /** *//**
     * ���Ϳͻ��˰汾��Ϣ
     */
    public static final String SEND_CLIENT_VERSION = "SENDCVER";
    
    /** *//**
     * ���տͻ��˰汾��Ϣ
     */
    public static final String RECEIVED_CLIENT_VERSION = "RECDCVER";
    
    /** *//**
     * �����ļ�ȫ·��
     */
    public static final String SEND_FILE_ABSOULT = "SENDFILE";
    
    /** *//**
     * �����ļ�ȫ·��
     */
    public static final String RECEIVED_FILE_ABSOULT = "RECDFILE";
    
    /** *//**
     * ��ʼ�ļ�����
     */
    public static final String START_TRANSMIT = "STARTTSM";
    
    /** *//**
     * �����ļ�����
     */
    public static final String TERMINATE_TRANSMIT = "TERMTSMT";
    
    /** *//**
     * ����ʧ��
     */
    public static final String UPDATED_FAILURE = "UPDEFAIL";
    
    /** *//**
     * ���³ɹ�
     */
    public static final String UPDATED_SUCCESSFUL = "UPDESUCC";
   
    /** *//**
     * �������
     */
    public static final String NOTNEED_UPDATED = "NNEEDUPD";
    
    /** *//**
     * �Ѿ�׼���ý��ո����ļ�
     */
    public static final String READY_TO_UPDATE = "READYTUP";
    
    /** *//**
     * ��������
     */
    public static final String BYE = "BYEBYEOK";
    
    /** *//**
     * ������OFFSET
     */
    public static final int DATA_OFFSET = 5;    

    /** *//**
     * �ļ����ݿ��С
     */
    public static final int DATA_SIZE = 1024;

    /** *//**
     * ���ͻ�������С
     */
    public static final int BUFFER_SIZE = DATA_SIZE + 1 + 4; // [0]λ�Ǳ�־λ���������ݺ����� + 4λ����

    /** *//**
     * ���ݶα�ʶ
    */
   public static final int MARK_DATA_SECT = 0;
   /** *//**
    * ����α�ʶ
    */
   public static final int CMD_DATA_SECT = 1;

   /** *//**
    * ���ݶν�����ʶ
    */
   public static final int MARK_DATA_END = 127;
}