package com.taikang.udp.common.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class CommUtil {

	private static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public static String first2low(String str) {
		String s = "";
		s = str.substring(0, 1).toLowerCase() + str.substring(1);
		return s;
	}

	public static String first2upper(String str) {
		String s = "";
		s = str.substring(0, 1).toUpperCase() + str.substring(1);
		return s;
	}

	/**
	 * 用来处理一行一条数据
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static List<String> str2list(String s) throws IOException {
		List<String> list = new ArrayList<String>();
		if (s != null && !s.equals("")) {
			StringReader fr = new StringReader(s);
			BufferedReader br = new BufferedReader(fr);
			String aline = "";
			while ((aline = br.readLine()) != null) {
				list.add(aline);
			}
		}
		return list;
	}

	public static java.util.Date formatDate(String s) {
		java.util.Date d = null;
		try {
			d = dateFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static java.util.Date formatDate(String s, String format) {
		java.util.Date d = null;
		try {
			SimpleDateFormat dFormat = new java.text.SimpleDateFormat(format);
			d = dFormat.parse(s);
		} catch (Exception e) {
		}
		return d;
	}

	public static String formatTime(String format, Object v) {
		if (v == null)
			return null;
		if (v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	public static String formatLongDate(Object v) {
		if (v == null || v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(v);
	}

	public static String formatShortDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(v);
	}

	public static String decode(String s) {
		String ret = s;
		try {
			ret = URLDecoder.decode(s.trim(), "UTF-8");
		} catch (Exception e) {
		}
		return ret;
	}

	public static String encode(String s) {
		String ret = s;
		try {
			ret = URLEncoder.encode(s.trim(), "UTF-8");
		} catch (Exception e) {
		}
		return ret;
	}

	public static String convert(String str, String coding) {
		String newStr = "";
		if (str != null)
			try {
				newStr = new String(str.getBytes("ISO-8859-1"), coding);
			} catch (Exception e) {
				return newStr;
			}
		return newStr;
	}

	public static boolean isImg(String extend) {
		boolean ret = false;
		List<String> list = new java.util.ArrayList<String>();
		list.add("jpg");
		list.add("jpeg");
		list.add("bmp");
		list.add("gif");
		list.add("png");
		list.add("tif");
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;
	}

	/**
	 * 图片水印，一般使用gif png格式，其中png质量较好
	 * 
	 * @param pressImg
	 *            水印文件
	 * @param targetImg
	 *            目标文件
	 * @param pos
	 *            水印位置，使用九宫格控制
	 * @param alpha
	 *            水印图片透明度
	 */
	/*
	 * public final static void waterMarkWithImage(String pressImg, String
	 * targetImg, int pos, float alpha) { try { // 目标文件 File _file = new
	 * File(targetImg); Image src = ImageIO.read(_file); int width =
	 * src.getWidth(null); int height = src.getHeight(null); BufferedImage image
	 * = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	 * Graphics2D g = image.createGraphics(); g.drawImage(src, 0, 0, width,
	 * height, null);
	 * 
	 * // 水印文件 File _filebiao = new File(pressImg); Image src_biao =
	 * ImageIO.read(_filebiao);
	 * g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha
	 * / 100)); int width_biao = src_biao.getWidth(null); int height_biao =
	 * src_biao.getHeight(null); int x = 0; int y = 0; if (pos == 1) {
	 * 
	 * } if (pos == 2) { x = (width - width_biao) / 2; y = 0; } if (pos == 3) {
	 * x = width - width_biao; y = 0; } if (pos == 4) { x = width - width_biao;
	 * y = (height - height_biao) / 2; } if (pos == 5) { x = width - width_biao;
	 * y = height - height_biao; } if (pos == 6) { x = (width - width_biao) / 2;
	 * y = height - height_biao; } if (pos == 7) { x = 0; y = height -
	 * height_biao; } if (pos == 8) { x = 0; y = (height - height_biao) / 2; }
	 * if (pos == 9) { x = (width - width_biao) / 2; y = (height - height_biao)
	 * / 2; } g.drawImage(src_biao, x, y, width_biao, height_biao, null); //
	 * 水印文件结束 g.dispose(); FileOutputStream out = new
	 * FileOutputStream(targetImg); com.sun.image.codec.jpeg.JPEGImageEncoder
	 * encoder = com.sun.image.codec.jpeg.JPEGCodec .createJPEGEncoder(out);
	 * encoder.encode(image); out.close(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

	public static boolean createFolder(String folderPath) {
		boolean ret = true;
		try {
			java.io.File myFilePath = new java.io.File(folderPath);
			if (!myFilePath.exists() && !myFilePath.isDirectory()) {
				ret = myFilePath.mkdirs();
				if (!ret) {
					System.out.println("创建文件夹出错");
				}
			}
		} catch (Exception e) {
			System.out.println("创建文件夹出错");
			ret = false;
		}
		return ret;
	}

	public static List toRowChildList(List list, int perNum) {
		// System.out.println("执行toRowChildList");
		List l = new java.util.ArrayList();
		if (list == null)
			return l;
		// System.out.println("照片："+list.size());
		// System.out.println("perNum:"+perNum);
		for (int i = 0; i < list.size(); i += perNum) {
			List cList = new ArrayList();
			for (int j = 0; j < perNum; j++)
				if (i + j < list.size())
					cList.add(list.get(i + j));
			l.add(cList);
		}
		return l;
	}

	public static List copyList(List list, int begin, int end) {
		List l = new ArrayList();
		if (list == null)
			return l;
		if (end > list.size())
			end = list.size();
		for (int i = begin; i < end; i++) {
			l.add(list.get(i));
		}
		return l;
	}

	public static boolean isNotNull(Object obj) {
		if (obj != null && !obj.toString().equals("")) {
			return true;
		} else
			return false;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();
		}
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param path
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean deleteFolder(String path) {
		boolean flag = false;
		File file = new File(path);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(path);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(path);
			}
		}
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

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param path
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String path) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		File dirFile = new File(path);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 静态分页，结合urlwriter使用
	 * 
	 * @param url
	 * @param currentPage
	 * @param pages
	 * @return
	 */
	public static String showPageStaticHtml(String url, int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s += "<a href='" + url + "_1.htm'>首页</a> ";
				if (currentPage > 1)
					s += "<a href='" + url + "_" + (currentPage - 1) + ".htm'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s += "第　";
				for (int i = beginPage, j = 0; i <= pages && j < 6; i++, j++)
					if (i == currentPage)
						s += "<a class='this' href='" + url + "_" + i + ".htm'>" + i + "</a> ";
					else
						s += "<a href='" + url + "_" + i + ".htm'>" + i + "</a> ";
				s += "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s += "<a href='" + url + "_" + (currentPage + 1) + ".htm'>下一页</a> ";
				}
				s += "<a href='" + url + "_" + pages + ".htm'>末页</a> ";
			}
		}
		return s;
	}

	/**
	 * 常规的分页信息，使用get传递参数
	 * 
	 * @param url
	 * @param params
	 * @param currentPage
	 * @param pages
	 * @return
	 */
	public static String showPageHtml(String url, String params, int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s += "<a href='" + url + "?currentPage=1" + params + "'>首页</a> ";
				if (currentPage > 1)
					s += "<a href='" + url + "?currentPage=" + (currentPage - 1) + params + "'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s += "第　";
				for (int i = beginPage, j = 0; i <= pages && j < 6; i++, j++)
					if (i == currentPage)
						s += "<a class='this' href='" + url + "?currentPage=" + i + params + "'>" + i + "</a> ";
					else
						s += "<a href='" + url + "?currentPage=" + i + params + "'>" + i + "</a> ";
				s += "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s += "<a href='" + url + "?currentPage=" + (currentPage + 1) + params + "'>下一页</a> ";
				}
				s += "<a href='" + url + "?currentPage=" + pages + params + "'>末页</a> ";
			}
		}
		// s+=" 转到<input type=text size=2>页";
		return s;
	}

	/**
	 * 使用表单分页，前台需要给数据放到form里，适合多参数查询分页
	 * 
	 * @param currentPage
	 * @param pages
	 * @return
	 */
	public static String showPageFormHtml(int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s += "<a href='javascript:void(0);' onclick='return gotoPage(1)'>首页</a> ";
				if (currentPage > 1)
					s += "<a href='javascript:void(0);' onclick='return gotoPage(" + (currentPage - 1) + ")'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s += "第　";
				for (int i = beginPage, j = 0; i <= pages && j < 6; i++, j++)
					if (i == currentPage)
						s += "<a class='this' href='javascript:void(0);' onclick='return gotoPage(" + i + ")'>" + i
								+ "</a> ";
					else
						s += "<a href='javascript:void(0);' onclick='return gotoPage(" + i +

						")'>" + i + "</a> ";
				s += "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s += "<a href='javascript:void(0);' onclick='return gotoPage(" + (currentPage + 1) + ")'>下一页</a> ";
				}
				s += "<a href='javascript:void(0);' onclick='return gotoPage(" + pages + ")'>末页</a> ";
			}
		}
		// s+=" 转到<input type=text size=2>页";
		return s;
	}

	/**
	 * ajax动态分页，使用json管理数据
	 * 
	 * @param url
	 * @param params
	 * @param currentPage
	 * @param pages
	 * @return
	 */
	public static String showPageAjaxHtml(String url, String params, int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			String address = url + "?1=1" + params;
			if (currentPage >= 1) {
				s += "<a href='javascript:void(0);' onclick='return ajaxPage(\"" + address + "\",1,this)'>首页</a> ";
				s += "<a href='javascript:void(0);' onclick='return ajaxPage(\"" + address + "\"," + (currentPage - 1)
						+ ",this)'>上一页</a> ";
			}

			int beginPage = currentPage - 3 < 1 ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s += "第　";
				for (int i = beginPage, j = 0; i <= pages && j < 6; i++, j++)
					if (i == currentPage)
						s += "<a class='this' href='javascript:void(0);' onclick='return ajaxPage(\"" + address + "\","
								+ i + ",this)'>" + i + "</a> ";
					else
						s += "<a href='javascript:void(0);' onclick='return ajaxPage(\"" + address + "\"," + i +

						",this)'>" + i + "</a> ";
				s += "页　";
			}
			if (currentPage <= pages) {
				s += "<a href='javascript:void(0);' onclick='return ajaxPage(\"" + address + "\"," + (currentPage + 1)
						+ ",this)'>下一页</a> ";
				s += "<a href='javascript:void(0);' onclick='return ajaxPage(\"" + address + "\"," + pages
						+ ",this)'>末页</a> ";
			}
			// s+=" 转到<input type=text size=2>页";
		}
		return s;
	}

	/**
	 * 计算两个时间间隔
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long getDateDistance(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 浮点数除法运算，保证数据的精确度
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double div(Object a, Object b) {
		double ret = 0.0;
		if (!null2String(a).equals("") && !null2String(b).equals("")) {
			BigDecimal e = new BigDecimal(null2String(a));
			BigDecimal f = new BigDecimal(null2String(b));
			if (null2Double(f) > 0)
				ret = e.divide(f, 3, BigDecimal.ROUND_DOWN).doubleValue();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	/**
	 * 浮点数据减法运算，保证数据的精确度
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double subtract(Object a, Object b) {
		double ret = 0.0;
		BigDecimal e = new BigDecimal(CommUtil.null2Double(a));
		BigDecimal f = new BigDecimal(CommUtil.null2Double(b));
		ret = e.subtract(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	/**
	 * 浮点数据加法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double add(Object a, Object b) {
		double ret = 0.0;
		BigDecimal e = new BigDecimal(CommUtil.null2Double(a));
		BigDecimal f = new BigDecimal(CommUtil.null2Double(b));
		ret = e.add(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	/**
	 * 浮点数乘法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double mul(Object a, Object b) {// 乘法
		BigDecimal e = new BigDecimal(CommUtil.null2Double(a));
		BigDecimal f = new BigDecimal(CommUtil.null2Double(b));
		double ret = e.multiply(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret));
	}

	public static double formatMoney(Object money) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(money));
	}

	public static int M2byte(float m) {
		float a = m * 1024 * 1024;
		return (int) a;
	}

	public static boolean convertIntToBoolean(int intValue) {
		return (intValue != 0);
	}

	public static double null2Double(Object s) {
		double v = 0.0;
		if (s != null)
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception e) {
			}
		return v;
	}

	public static String null2String(Object s) {
		return s == null ? "" : s.toString().trim();
	}

	public static float null2Float(Object s) {
		float v = 0.0f;
		if (s != null)
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null)
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	/**
	 * 定义过滤信息 使用Jsoup过滤数据，保护网站安全
	 */
	private final static Whitelist user_content_filter = Whitelist.relaxed();

	static {
		user_content_filter.addTags("embed", "object", "param", "span", "div", "font");
		user_content_filter.addAttributes(":all", "style", "class", "id", "name");
		user_content_filter.addAttributes("object", "width", "height", "classid", "codebase");
		user_content_filter.addAttributes("param", "name", "value");
		user_content_filter.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen",
				"allowScriptAccess", "flashvars", "name", "type", "pluginspage");
	}

	public static String filterHTML(String content) {
		Whitelist whiteList = new Whitelist();
		String s = Jsoup.clean(content, user_content_filter);
		return s;
	}

	public static int parseDate(String type, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("y")) {
			return cal.get(Calendar.YEAR);
		}
		if (type.equals("M")) {
			return cal.get(Calendar.MONTH) + 1;
		}
		if (type.equals("d")) {
			return cal.get(Calendar.DAY_OF_MONTH);
		}
		if (type.equals("H")) {
			return cal.get(Calendar.HOUR_OF_DAY);
		}
		if (type.equals("m")) {
			return cal.get(Calendar.MINUTE);
		}
		if (type.equals("s")) {
			return cal.get(Calendar.SECOND);
		}
		return 0;
	}

	// 读取远程url图片,得到宽高
	public static int[] readImgWH(String imgurl) {
		boolean b = false;
		try {
			// 实例化url
			URL url = new URL(imgurl);
			// 载入图片到输入流
			java.io.BufferedInputStream bis = new BufferedInputStream(url.openStream());
			// 实例化存储字节数组
			byte[] bytes = new byte[100];
			// 设置写入路径以及图片名称
			OutputStream bos = new FileOutputStream(new File("C:\\thetempimg.gif"));
			int len;
			while ((len = bis.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
			bis.close();
			bos.flush();
			bos.close();
			// 关闭输出流
			b = true;
		} catch (Exception e) {
			// 如果图片未找到
			b = false;
		}
		int[] a = new int[2];
		if (b) {// 图片存在
			// 得到文件
			java.io.File file = new java.io.File("C:\\thetempimg.gif");
			BufferedImage bi = null;
			boolean imgwrong = false;
			try {
				// 读取图片
				bi = javax.imageio.ImageIO.read(file);
				try {
					// 判断文件图片是否能正常显示,有些图片编码不正确
					int i = bi.getType();
					imgwrong = true;
				} catch (Exception e) {
					imgwrong = false;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			if (imgwrong) {
				a[0] = bi.getWidth(); // 获得 宽度
				a[1] = bi.getHeight(); // 获得 高度
			} else {
				a = null;
			}
			// 删除文件
			file.delete();
		} else {// 图片不存在
			a = null;
		}
		return a;
	}

	/**
	 * 前台判定是否存在文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean fileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * 计算分割后的数组长度
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	public static int splitLength(String s, String c) {
		int v = 0;
		if (!s.trim().equals("")) {
			v = s.split(c).length;
		}
		return v;
	}

	/**
	 * 计算file文件大小，可以是单个文件也可以是文件夹
	 * 
	 * @param file
	 * @return
	 */
	static int totalFolder = 0;
	static int totalFile = 0;

	public static double fileSize(File folder) {
		if (folder.exists()) {
			totalFolder++;
			// System.out.println("Folder: " + folder.getName());
			long foldersize = 0;
			File[] filelist = folder.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					foldersize += fileSize(filelist[i]);
				} else {
					totalFile++;
					foldersize += filelist[i].length();
				}
			}
			return div(foldersize, 1024);
		} else
			return 0;
	}

	/**
	 * 计算文件夹下文件数量
	 * 
	 * @param file
	 * @return
	 */
	public static int fileCount(File file) {
		if (file == null) {
			return 0;
		}
		if (!file.isDirectory()) {
			return 1;
		}
		int fileCount = 0;
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				fileCount++;
			} else if (f.isDirectory()) {
				fileCount++;
				fileCount += fileCount(file); // 如果遇到目录则通过递归调用继续统计
			}
		}
		return fileCount;
	}

	/**
	 * 根据html颜色代码返回java Color
	 * 
	 * @param color
	 * @return
	 */
	public static Color getColor(String color) {
		if (color.charAt(0) == '#') {
			color = color.substring(1);
		}
		if (color.length() != 6) {
			return null;
		}
		try {
			int r = Integer.parseInt(color.substring(0, 2), 16);
			int g = Integer.parseInt(color.substring(2, 4), 16);
			int b = Integer.parseInt(color.substring(4), 16);
			return new Color(r, g, b);
		} catch (NumberFormatException nfe) {
			return null;
		}
	}

	/**
	 * 根据种子a随机出一组长度为length不重复的整型数组
	 * 
	 * @param a
	 * @param length
	 * @return
	 */
	public static Set<Integer> randomInt(int a, int length) {
		Set<Integer> list = new TreeSet<Integer>();
		int size = length;
		if (length > a) {
			size = a;
		}
		while (list.size() < size) {
			Random random = new Random();
			int b = random.nextInt(a);
			list.add(b);
		}
		return list;
	}

	public static final String randomInt(int length) {
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = ("0123456789").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	/**
	 * 格式化数字，保留对应的小数位
	 * 
	 * @param obj
	 * @param len
	 * @return
	 */
	public static Double formatDouble(Object obj, int len) {
		Double ret = 0.0;
		String format = "0.0";
		for (int i = 1; i < len; i++) {
			format = format + "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		return Double.valueOf(df.format(obj));
	}

	/**
	 * 判断字符是否为中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为乱码
	 * 
	 * @param strName
	 * @return
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {

				if (!isChinese(c)) {
					count = count + 1;
					System.out.print(c);
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 去掉IP字符串前后所有的空格
	 * 
	 * @param IP
	 * @return
	 */
	public static String trimSpaces(String IP) {//
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	/**
	 * 判断是否是一个IP
	 * 
	 * @param IP
	 * @return
	 */
	public static boolean isIp(String IP) {//
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		return b;
	}

	/**
	 * 邮箱验证
	 * 
	 * @param s
	 * @return
	 */
	public static boolean checkEmail(String s) {
		String regex = "[a-zA-Z][\\w_]+@\\w+(\\.\\w+)+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	public static Long null2Long(Object s) {
		Long v = -1l;
		if (s != null)
			try {
				v = Long.parseLong(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	public static int null2Int(Object s) {
		int v = 0;
		if (s != null)
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception e) {
			}
		return v;
	}

	// 加密
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		long i=Long.parseLong(randomInt(10));
		System.out.println(i);
		
		int j=Integer.parseInt(randomInt(10));
		System.out.println(j);
		
	}
}
