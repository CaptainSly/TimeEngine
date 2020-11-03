package com.reignleif.io;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import com.badlogic.gdx.utils.Array;

public class FileManager {

	private FileSystemManager fsManager;
	private Array<DataFile> dataArray;
	
	public FileManager() {
		
		dataArray = new Array<DataFile>();
				
		try {
			fsManager = VFS.getManager();
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

}