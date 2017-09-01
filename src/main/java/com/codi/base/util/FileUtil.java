package com.codi.base.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shangdu Lin
 * 
 * @date:2011-10-17 下午10:23:05
 * @version :
 * 
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	

	/**
	 * 筛选出指定后缀名称文件
	 * 
	 * @param fileDir
	 *            文件
	 * @param suffix
	 *            后缀
	 * @return
	 */
	public static File[] filter(File fileDir, final String suffix) {
		if (fileDir.exists() && fileDir.isDirectory()) {
			File[] files = fileDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (pathname.isFile() && pathname.getName().endsWith(suffix)) {
						try {
							logger.debug("properties file={}", pathname.getCanonicalPath());
						} catch (IOException e) {
							logger.error("io exception", e);
							return false;
						}
						return true;
					}
					return false;
				}
			});
			return files;
		}

		return null;
	}

}
