package org.alvin.cishan.sys.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @功能描述 上传的公用代码
 */
public class UploadUtil {
	private static Log logger = LogFactory.getLog(UploadUtil.class);

	/**
	 * @功能描述 默认缩略图大小
	 */

	public static UploadVo doUpFile(MultipartFile image, String rootPath, String imgUrl, String model_path) {
		return saveFile(image, rootPath, imgUrl, model_path, 400, 400);
	}

	/**
	 * @功能描述 指定缩略图大小
	 */
	public static UploadVo doUpFile(MultipartFile image, String rootPath, String imgUrl, String model_path, int width, int height) {
		return saveFile(image, rootPath, imgUrl, model_path, width, height);
	}

	private static UploadVo saveFile(MultipartFile image, String rootPath, String imgUrl, String model_path, int width, int height) {
		UploadVo vo = new UploadVo();
		try {
			BufferedImage bi = ImageIO.read(image.getInputStream());
			String fileName = UUIDGenerator.getUUID() + "_" + bi.getWidth() + "X" + bi.getHeight();// 生成主文件名
			String oriName = image.getOriginalFilename();// 原始文件名
			String extName = oriName.substring(oriName.lastIndexOf("."));// 扩展名
			String fullPath = rootPath + model_path + fileName + extName;// 完成路径
			FileUtil.createDir(fullPath);// 如果目录,不存在创建这个目录
			File file = new File(fullPath);
			image.transferTo(file);// 保存文件
			changeFilePermission(file);
			vo.setImgUrl(imgUrl + model_path + fileName + extName);
			vo.setImgPath(model_path + fileName + extName);
			String newPath = rootPath + model_path + fileName + "_" + width + "X" + height + extName;
			Thumbnails.of(fullPath).size(width, height).toFile(newPath);
		} catch (IllegalStateException e) {
			logger.error("保文件时出现IllegalStateException异常!!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("保文件时出现IOException异常!!");
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * @功能描述 头像:无缩略图
	 */
	public static UploadVo saveFile(MultipartFile image, String rootPath, String imgUrl, String model_path) {
		UploadVo vo = new UploadVo();
		String uuid = UUIDGenerator.getUUID();// 生成主文件名
		String oriName = image.getOriginalFilename();// 原始文件名
		String extName = oriName.substring(oriName.lastIndexOf("."));// 扩展名
		String fullPath = rootPath + model_path + uuid + extName;// 完成路径
		FileUtil.createDir(fullPath);// 如果目录,不存在创建这个目录
		try {
			File file = new File(fullPath);
			image.transferTo(file);// 保存文件
			changeFilePermission(file);
			vo.setFile(file);
		} catch (IllegalStateException e) {
			logger.error("保文件时出现IllegalStateException异常!!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("保文件时出现IOException异常!!");
			e.printStackTrace();
		}
		// logger.info(fullPath);
		// logger.info(model_path + uuid + extName);
		vo.setImgUrl(imgUrl + model_path + uuid + extName);
		vo.setImgPath(model_path + uuid + extName);
		return vo;
	}

	/**
	 * @功能描述 头像:无缩略图
	 */
	public static Map<String, String> saveFile(String image, String rootPath, String imgUrl, String model_path) {
		byte[] buff = Base64Utils.decodeFromString(image);
		String uuid = UUIDGenerator.getUUID();// 生成主文件名
		String extName = ".png";
		String fullPath = rootPath + model_path + uuid + extName;// 完成路径
		FileUtil.createDir(fullPath);// 如果目录,不存在创建这个目录
		try {
			Files.write(Paths.get(fullPath), buff);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<>();
		map.put("relativePath", model_path + uuid + extName);// 数据库记录的相对路径
		map.put("absolutePath", rootPath + model_path + uuid + extName);
		map.put("absoluteUrl", imgUrl + model_path + uuid + extName);// 展示路径
		return map;

	}

	/**
	 * 修改文件权限
	 *
	 * @param dirFile
	 * @throws IOException
	 */
	public static void changeFilePermission(File dirFile) {
		Set<PosixFilePermission> perms = new HashSet<>();
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);
		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);
		perms.add(PosixFilePermission.OTHERS_WRITE);
		try {
			Path path = Paths.get(dirFile.getAbsolutePath());
			String os = System.getProperty("os.name");
			if (!os.toLowerCase().startsWith("windows")) {
				Files.setPosixFilePermissions(path, perms);
			}
		} catch (UnsupportedOperationException e) {
			logger.error("修改文件权限时出现UnsupportedOperationException异常!!", e);
		} catch (Exception e) {
			logger.error("修改文件权限时时出现Exception异常!!", e);
			e.printStackTrace();
		}
	}


}
