package com.errorLogSystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class FileUtil {
	
	private Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	@Value("${ErrorFilePath}")
	private String errorFileName;
	
	public void writeBeanToFile(Object object){
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + File.separator + errorFileName;
		
		File file = createFile(path);
		if(null!=file){
			try {
	        	FileOutputStream out;
	            out = new FileOutputStream(file);
	            ObjectOutputStream objOut=new ObjectOutputStream(out);
	            objOut.writeObject(object);
	            objOut.flush();
	            objOut.close();
	            logger.info("write object success!");
	        } catch (IOException e) {
	        	logger.info("write object failed");
	            e.printStackTrace();
	            return;
	        }
		}else{
			logger.info("创建文件出错");
		}
        
	}
	
	public Object readFileToBean(){
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + File.separator + errorFileName;
		File file = createFile(path);
		if(null != file){
			try {
				FileInputStream in;
				in = new FileInputStream(file);
				ObjectInputStream objIn = new ObjectInputStream(in);
				Object temp = objIn.readObject();
				objIn.close();
				logger.info("read object success!");
				return temp;
				
			} catch (IOException e) {
				logger.info("read object failed");
				e.printStackTrace();	
			} catch (ClassNotFoundException e) {
				logger.info("Class not found");
				e.printStackTrace();
			}
		}else{
			logger.info("创建文件出错");
		}
		return null;
	}
	
	public File createFile(String path){
		File file = new File(path);
		if(!file.exists()){
			try {
				file.createNewFile();
				return file;
			} catch (IOException e) {
				logger.error("创建错误信息数据文件出错");
				e.printStackTrace();
				return null;
			}
		}else{
			return file;
		}
	}

}
