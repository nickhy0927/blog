package com.cako.platform.utils;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

import com.orm.commons.utils.IdEntity;
import com.orm.enums.SysEnum;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public class BaseEntity extends IdEntity {

	protected SysEnum.DeleteStatus deleteStatus = SysEnum.DeleteStatus.NO;
	protected SysEnum.Display displayStatus = SysEnum.Display.DISPLAY;

	public SysEnum.DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public SysEnum.Display getDisplayStatus() {
		return displayStatus;
	}

	public void setDeleteStatus(SysEnum.DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public void setDisplayStatus(SysEnum.Display displayStatus) {
		this.displayStatus = displayStatus;
	}

}
