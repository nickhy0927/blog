package com.cako.platform.utils;

/**
 * 平台
 * 
 * @author isea533
 */
public enum EPlatform {
	AIX("AIX"), Any("any"), Digital_Unix("Digital Unix"), FreeBSD("FreeBSD"), HP_UX("HP-UX"), Irix("Irix"), Linux(
			"Linux"), Mac_OS("Mac OS"), Mac_OS_X("Mac OS X"), MPEiX("MPE/iX"), NetWare_411("NetWare"), OpenVMS(
					"OpenVMS"), OS2("OS/2"), OS390("OS/390"), OSF1("OSF1"), Others("Others"), Solaris("Solaris"), SunOS(
							"SunOS"), Windows("Windows");

	private String description;

	private EPlatform(String desc) {
		this.description = desc;
	}

	@Override
	public String toString(){
		return description;
	}
}