/**
 * 
 */
package com.taikang.udp.common.constant;

/**
 *TODO
 *UploadVertor.java
 * @author itw_lixy02
 * @date 2016年1月22日上午10:52:58
 */
public class UploadVertor {
	public static final String FILE_UPLOAD_DIR = "/upload";
	public static final String FILE_UPLOAD_SUB_IMG_DIR = "/img";
    public static final String FOR_RESOURCES_LOAD_DIR = "D:/resources";
    //每个上传子目录保存的文件的最大数目
    public static final int MAX_NUM_PER_UPLOAD_SUB_DIR = 500;
    //上传文件的最大文件大小
    public static final long MAX_FILE_SIZE = 1024 * 1024 * 2;
    //系统默认建立和使用的以时间字符串作为文件名称的时间格式
    public static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMdd";
    //这里扩充一下格式，防止手动建立的不统一
    public static final String DEFAULT_SUB_FOLDER_FORMAT_NO_AUTO = "yyyy-MM-dd";
	public static final String FILE_UPLOAD_BANNER = "/upload/banner";
	public static final String FILE_UPLOAD_SMALIMAGE = "/upload/smalImage";

}
