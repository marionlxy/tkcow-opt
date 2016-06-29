/**
 * 
 */
package com.taikang.udp.common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.taikang.udp.common.constant.UploadVertor;

/**
 *TODO
 *FileHelper.java
 * @author itw_lixy02
 * @date 2016年1月22日上午10:54:39
 */
public class FileHelper {
	/**
	 * 生成文件名  20160122_111042_381.jpg
	 * @author itw_lixy02
	 * generateFileName
	 * FileHelper
	 * @param folder
	 * @param suffix
	 * @return
	 */
    public static String generateFileName(File folder, String suffix) {
        String filename;
        File file;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        String base = format.format(date);
        filename = base + "." + suffix;
        file = new File(filename);
        int i = 1;
        while (file.exists()) {
            filename = String.format("%s_%d.%s", base, i, suffix);
            i++;
        }
        return filename;
    }
    
    
    /**
     * 获取文件名称
     * @param str
     * @return
     */
    public static String getFileName(String str){
        int index = str.lastIndexOf("//");
        if(-1 != index){
            return str.substring(index);
        } else {
            return str;
        }
    }
    
    
    /**
     * 创建文件目录
     * 
     * @return
     */
    public static String buildFolder(HttpServletRequest request) {
       
        String realPath = request.getSession().getServletContext()
                .getRealPath(UploadVertor.FILE_UPLOAD_DIR);//"/upload"

        //logger.error(realPath);

        // 一级目录，如果不存在，创建
        File firstFolder = new File(realPath);//;
        if (!firstFolder.exists()) {
        	firstFolder = new File(realPath);
        }

        // 二级目录，如果不存在，创建
        String folderdir = realPath +  UploadVertor.FILE_UPLOAD_SUB_IMG_DIR;///img
        //if (logger.isDebugEnabled()) {
        //   logger.debug("folderdir" + folderdir);
        //}

        if (StringUtils.isBlank(folderdir)) {
            //logger.error("路径错误:" + folderdir);
            return null;
        }
        File floder = new File(folderdir);
        if (!floder.exists()) {
            if (!floder.mkdir()) {
                //logger.error("创建文件夹出错！path=" + folderdir);
                return null;
            }

        }
        // 再往下的文件夹都是以时间字符串来命名的，所以获取最新时间的文件夹即可
        String[] files = floder.list();
        if (null != files && 0 < files.length) {
            // 含有子文件夹，则获取最新的一个
            Date oldDate = null;
            int index = -1;
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i];

                try {
                    Date thisDate = DateUtils.parseDate(fileName, new String[] {
                    		UploadVertor.DEFAULT_SUB_FOLDER_FORMAT_AUTO, UploadVertor.DEFAULT_SUB_FOLDER_FORMAT_NO_AUTO });
                    if (oldDate == null) {
                        oldDate = thisDate;
                        index = i;
                    } else {
                        if (thisDate.after(oldDate)) {
                            // 保存最新的时间和数组中的下标
                            oldDate = thisDate;
                            index = i;
                        }
                    }
                } catch (ParseException e) {
                    // 这里异常吃掉，不用做什么，如果解析失败，会建立新的文件夹，防止人为的建立文件夹导致的异常。
                }
            }// for

            // 判断当前最新的文件夹下是否已经存在了最大数目的图片
            if (null != oldDate && -1 != index) {
                File pointfloder = new File(folderdir + File.separator
                        + files[index]);
                if (!pointfloder.exists()) {
                    if (!pointfloder.mkdir()) {
                    	pointfloder.mkdirs();
                        //logger.error("创建文件夹出错！path=" + folderdir);
                        return null;
                    }
                }

                // 如果文件夹下的文件超过了最大值，那么也需要新建一个文件夹
                String[] pointfloderFiles = pointfloder.list();
                if (null != pointfloderFiles
                        && UploadVertor.MAX_NUM_PER_UPLOAD_SUB_DIR < pointfloderFiles.length) {
                    return buildNewFile(folderdir);
                }

                return pointfloder.getAbsolutePath();
            }
            
            // 查找当前子文件夹失败，新建一个
            return buildNewFile(folderdir);
        } else {
            // 不含有子文件夹，新建一个，通常系统首次上传会有这个情况
            return buildNewFile(folderdir);
        }
    }  
        /**
         * 创建一个新文件
         * @param path
         * @return
         */
        private static String buildNewFile(String path){
            // 不含有子文件夹，新建一个，通常系统首次上传会有这个情况
            String newFile = buildFileBySysTime(path);
            if (null == newFile) {
                //logger.error("创建文件夹失败！newFile=" + newFile);
            }

            return newFile;
        }
        
        /**
         * 根据当前的时间建立文件夹，时间格式yyyyMMdd
         * 
         * @param path
         * @return
         */
        private static String buildFileBySysTime(String path) {
            DateFormat df = new SimpleDateFormat(UploadVertor.DEFAULT_SUB_FOLDER_FORMAT_AUTO);
            String fileName = df.format(new Date());
            String file = path + File.separator + fileName;
            if (!file.isEmpty()) {
                return null;
            }
            return file;
        }
        /**
         * 
         * @author itw_lixy02
         * buildFileBySysTime
         * FileHelper
         * @param path
         * @return
         */
        private static String escapeShoudle(String path) {
           /* if(path.contains("\\")){
            	path.replace("\\", "/");
            }*/
            String str=path.replaceAll("\\\\", "/");
//            path.replace("\\", "//");
            return str;
        }
        
        public static void main(String[] args) {
        	/*File folder=null;
			System.out.println(generateFileName(folder, "jpg"));
			System.out.println(getFileName("D://a/20160122_111042_381.jpg"));*/
        	
        	//String path="\\"+"test"+"\\"+"banner";
        	String path="\test\banner";
        	String str=path.replaceAll("\\\\", "/");
        	/*try {
				str=new String(path.getBytes(),"gbk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	System.out.println(str);
			System.out.println(escapeShoudle(str));*/
        	
        	System.out.println(str);
			System.out.println(escapeShoudle(str));
		}
}
