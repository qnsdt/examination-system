package com.hig.fms.service;

import java.io.File;
import java.io.IOException;

import com.hig.fms.common.NotSameFileExpection;
import com.hig.fms.domain.MultipartFileParam;


public interface ChunkService {
	public String chunkUploadByMappedByteBuffer(MultipartFileParam param, String filePath) throws IOException, NotSameFileExpection;
	
	public void renameFile(File toBeRenamed, String toFileNewName);
	
	public boolean checkUploadStatus(MultipartFileParam param, String fileName, String filePath) throws IOException;
}
