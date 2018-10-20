package org.alvin.cishan.sys.util;

import java.io.File;

public class UploadVo extends MessageInfo {

	public UploadVo() {
	}

	private String imgUrl;// 展示路径

	private String imgPath;// 储存路径

	private File file;// 存储的文件

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
