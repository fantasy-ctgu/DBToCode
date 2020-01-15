package com.ctgu.springbootbase.utils;
/**  
* @Title: FileUtil.java  
*
* @Package com.ctgu.collegeservice.utils  
*
* @Description: 文件处理基本工具
*
* @author Fantasy  
*
* @date 2018年12月20日  
*
* @version V1.0  
*/

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;


public class UploadUtil {
	// 获取操作系统文件分隔符
	private static String seperator = System.getProperty("file.separator");
	// 时间格式化
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random random = new Random();
	
	// 定义文件保存路径
	public static String imgDir = "img/";
	public static String fileDir = "file/";

	/**
	 * 获取文件上传根路径
	 * @return 用户上传文件根路径
	 */
	public static String getUploadBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/recruitment/upload/";
		} else {
			basePath = "/home/recruitment/upload/";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	/**
	 * 格式化路径
	 * @param path 需要格式化的路径
	 * @return 格式化(文件分隔符匹配)后路径
	 */
	public static String pathFormat(String path) {
		return path.replace("/", seperator);
	}
	
	/**
	 * 获取 根路径和path拼接 后的路径
	 * @param path 附加目录
	 * @return 生成文件保存路径
	 */
	public static String getFilePath(String path) {
		String filePath = getUploadBasePath() + path;
		filePath = pathFormat(filePath);
		return filePath;
	}
	
	/**
	 * 产生随机文件名(当前年月日时分秒+五位随机数)
	 * @return 获取随机文件名
	 */
	public static String getRandomFileName() {
		//随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		//获取随机数
		int rannum = (int)(random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		String nowTimeStr = sdf.format(new Date());
		return nowTimeStr+rannum;
	}
	
	/**
	 * 获取文件后缀名
	 * @param file 目标文件
	 * @return 文件后缀
	 */
	public static String getFileExtension(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	/**
	 * 创建文件夹(递归创建)
	 * @param targetAddr 
	 */
	private static void makeDirPath(String targetAddr) {
		File dirPath = new File(targetAddr);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	
	/**
	 * 
	 * @param path 删除路径下文件
	 */
	public static void deleteFile(String path) {
		File file = new File(getUploadBasePath()+path);
		if(file.exists()) {
			if(file.isDirectory()) {
				File files[] = file.listFiles();
				for(int i = 0;i < file.length();i++) {
					files[i].delete();
				}
				file.delete();
			}
		}
	}
	
	/**
	 * 保存文件
	 * @param multipartFile 目标文件
	 * @return 文件保存后的相对地址
	 */
	public static String generateFile(MultipartFile multipartFile) {
		String fileName = UploadUtil.getRandomFileName();
		String extension = UploadUtil.getFileExtension(multipartFile);
		String filePath = UploadUtil.getFilePath(fileDir);
		makeDirPath(filePath);
		String fileFile = filePath + fileName + extension;
		File dest = new File(fileFile);
		try {
			multipartFile.transferTo(dest);
		} catch (IOException e) {
			System.out.println("保存文件失败");
			return null;
		}
		return UploadUtil.pathFormat(fileDir + "/" + fileName + extension);
	}

	/**
	 * 保存图片
	 * @param multipartFile 目标图片
	 * @return 图片的相对路径
	 */
	public static String generateNormalImg(MultipartFile multipartFile) {
		String fileName = UploadUtil.getRandomFileName();
		String extension = UploadUtil.getFileExtension(multipartFile);
		String imgPath = UploadUtil.getFilePath(imgDir);
		makeDirPath(imgPath);
		String imgFile = imgPath + fileName + extension;
		File dest = new File(imgFile);
		try {
			Thumbnails.of(multipartFile.getInputStream()).size(200, 200).outputQuality(0.5f).toFile(dest);
		} catch (IOException e) {
			System.out.println("创建缩略图失败");
			e.printStackTrace();
			return null;
		}
		return UploadUtil.pathFormat(imgDir + "/" + fileName + extension);
	}
	
}
