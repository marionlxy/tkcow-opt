package com.taikang.udp.common.util;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.taikang.udp.accessory.model.AccessoryBO;

public class WebUtil {
	
	public static String getURL(HttpServletRequest request) {
		String contextPath = request.getContextPath().equals("/") ? ""
				: request.getContextPath();
		String url = "http://" + request.getServerName();
		if (CommUtil.null2Int(request.getServerPort()) != 80) {
			url = url + ":" + CommUtil.null2Int(request.getServerPort()) + contextPath;
		} else {
			url = url + contextPath;
		}
		return url;
	}
	
	public static String generic_domain(HttpServletRequest request) {
		// System.out.println(request.getServerName());
		String system_domain = "localhost";
		String serverName = request.getServerName();
		if (CommUtil.isIp(serverName)) {
			system_domain = serverName;
		} else {
			system_domain = serverName.substring(serverName.indexOf(".") + 1);
		}
		// System.out.println(system_domain);
		return system_domain;
	}
	
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            java.net.InetAddress addr = null;
            try {
                addr = java.net.InetAddress.getLocalHost();
            } catch (java.net.UnknownHostException e) {
                e.printStackTrace();
            }
            ip = CommUtil.null2String(addr.getHostAddress());// 获得本机IP
        }
        return ip;
    }
	
	
	/**
     * saveFileToServer 上传文件保存到服务器
     * 
     * @param filePath为上传文件的名称
     *            ，
     * @param saveFilePathName为文件保存全路径
     * @param saveFileName为保存的文件
     * @param extendes为允许的文件扩展名
     *            , *
     * @return 返回一个map，map中有4个值，第一个为保存的文件名fileName,第二个为保存的文件大小fileSize,,
     *         第三个为保存文件时错误信息errors,如果生成缩略图则map中保存smallFileName，表示缩略图的全路径
     */
    public static Map<String,Object> saveFileToServer(HttpServletRequest request,
            String filePath, String saveFilePathName, String saveFileName,
            String[] extendes) throws IOException {     
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
                .getFile(filePath);
        Map<String,Object> map = new HashMap<String,Object>();
        if (file != null && !file.isEmpty()) {
            // System.out.println("文件名为：" + file.getOriginalFilename());
            String extend = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
            if (saveFileName == null || saveFileName.trim().equals("")) {
                saveFileName = UUID.randomUUID().toString() + "." + extend;
            }
            if (saveFileName.lastIndexOf(".") < 0) {
                saveFileName = saveFileName + "." + extend;
            }
            float fileSize = Float.valueOf(file.getSize());// 返回文件大小，单位为k
            List<String> errors = new java.util.ArrayList<String>();
            boolean flag = true;
            if (extendes != null) {
                for (String s : extendes) {
                    if (extend.toLowerCase().equals(s))
                        flag = true;
                }
            }
            if (flag) {
                File path = new File(saveFilePathName);
                if (!path.exists()) {
                    path.mkdir();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                DataOutputStream out = new DataOutputStream(
                        new FileOutputStream(saveFilePathName + File.separator
                                + saveFileName));
                InputStream is = null;
                try {
                    is = file.getInputStream();
                    int size = (int) (fileSize);
                    byte[] buffer = new byte[size];
                    while (is.read(buffer) > 0) {
                        out.write(buffer);
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
                if (CommUtil.isImg(extend)) {
                    File img = new File(saveFilePathName + File.separator
                            + saveFileName);
                    try {
                        BufferedImage bis = ImageIO.read(img);
                        int w = bis.getWidth();
                        int h = bis.getHeight();
                        map.put("width", w);
                        map.put("height", h);
                    } catch (Exception e) {
                        // map.put("width", 200);
                        // map.put("heigh", 100);
                    }
                }
                map.put("mime", extend);
                map.put("fileName", saveFileName);
                map.put("fileSize", fileSize);
                map.put("error", errors);
                map.put("oldName", file.getOriginalFilename());
            } else {
                errors.add("不允许的扩展名");
            }
        } else {
            map.put("width", 0);
            map.put("height", 0);
            map.put("mime", "");
            map.put("fileName", "");
            map.put("fileSize", 0.0f);
            map.put("oldName", "");
        }
        return map;
    }
    
    /**
     * 物理删除附件方法
     * 
     * @param request
     * @param acc
     * @return
     */
    public static boolean del_acc(HttpServletRequest request, AccessoryBO acc) {
        boolean ret = true;
        boolean ret1 = true;
        if (acc != null) {
            String path = request.getRealPath("/") + acc.getPath()
                    + File.separator + acc.getName();
            ret = deleteFile(path);
        }
        return ret && ret1;
    }
    
    /**
     * 删除单个文件
     * 
     * @param path
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String path) {
        boolean flag = false;
        File file = new File(path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
