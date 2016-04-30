package com.cako.platform.attachment.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cako.platform.utils.BaseEntity;

/**
 * 附件的大小
 * 
 * @author HUANGYUAN
 *
 */
@Entity
@Table(name = "t_platform_attachment")
public class Attachment extends BaseEntity {

	/**
	 * 附件的名称
	 */
	private String name;

	/**
	 * 附件上传的路径
	 */
	private String path;
	/**
	 * 附件的类型
	 */
	private String fileType;

	/**
	 * 附件的大小
	 */
	private String fileSize;

	/**
	 * 文件后缀名
	 */
	private String suffix;

	public Attachment() {

	}

	public Attachment(String name, String path, String type, String fileSize, String suffix) {
		super();
		this.name = name;
		this.path = path;
		this.fileType = type;
		this.fileSize = fileSize;
		this.suffix = suffix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
