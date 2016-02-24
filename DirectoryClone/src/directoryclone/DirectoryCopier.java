package directoryclone;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/***
 * This class copies a source directory and all of it's subdirectory's files. 
 * 
 * */
public class DirectoryCopier  {
	//path vars
	
	//status vars
	private int filesCopied;
	private int directoriesCopied;
	private int errors;
	//*********************************************************************************
	/**
	 * cloneDirectory is the main method users will interact with when using this class. 
	 * it is the only method available to them.  The user calls this method, which in turn
	 * passes off functionality to the appropriate validation / analysis methods before 
	 * calling copyDirectory. 
	 **/
	public void cloneDirectory(String source, String target){
		//calls validate is true
		//calls analyze(for transfer info, optional)
		if (validate(source, target)){
			filesCopied = 0;
			directoriesCopied = 0;
			copyDirectory(source, target);
		}
		else{
			output("File is in source");
		}
	}//end cloneDirectory method
	//**************************************************************************
	//public for user access
	public boolean validate(String source, String target){
		if (target.contains(source)|| target.equals(source)){
			return false;
		}
		else return true;
	}//end validate method
	//****************************************************************************
	private void copyFile(String source, String target) throws IOException{
		//copy file
			//file and stream declarations
		
			File sourceFile = new File(source);
			File targetFile = new File(target);
			FileInputStream sourceStream = null;
			FileOutputStream targetStream = null;
			BufferedInputStream bufferedSource = null;
			BufferedOutputStream bufferedTarget = null;
			output("Beginning copy : " + sourceFile.getName());
		try{
			//IO stream inits
			sourceStream = new FileInputStream(sourceFile);
			targetStream = new FileOutputStream(targetFile);
			bufferedSource = new BufferedInputStream(sourceStream, 8192);
			bufferedTarget = new BufferedOutputStream(targetStream, 8192);
			
			int transfer;
			
			while ((transfer = bufferedSource.read()) != -1){
				bufferedTarget.write(transfer);
			}			
			filesCopied++;
			output(sourceFile.getName() + " copied successfully!");
		}catch(IOException e){
			//catch this better later
			errors++;
			output("IO ERROR");
		}finally{
			if (bufferedSource != null){
				bufferedSource.close();
			}
			if (bufferedTarget != null){
				bufferedTarget.close();
			}
		}//end try-catch-finally block
	}//end copyFile method
	//****************************************************************************
	private void copyDirectory(String source, String target) {
		//copy directory
		File sourceDirectory = new File(source); 
		File targetDirectory = new File(target +"/"+ sourceDirectory.getName());
		targetDirectory.mkdir();
		directoriesCopied++;
		File[] fileList = sourceDirectory.listFiles();
		for (File currentFile : fileList){
			if (currentFile.isFile()){
				try{
					//because copyFile technically throws IOException if buffers can't close
					copyFile(currentFile.getAbsolutePath(), targetDirectory.getAbsolutePath() +"/"+ currentFile.getName());
				}catch(IOException e){
					output("error closing buffer");;
				}
			}
			else if (currentFile.isDirectory()){
				output(currentFile.getAbsolutePath());
				output(targetDirectory.getAbsolutePath());
				copyDirectory(currentFile.getAbsolutePath(), targetDirectory.getAbsolutePath());
			}
		}//end for 
	}//end copyDirectory method
	//*******************************************************************************
	//output method
	/**
	 * This method doesn't do much currently, but the idea of it is to separate the output from being 
	 * permanently tied to the console.  That is, i could use this with console, swing components, javaFx etc...
	 * for now, 
	 * without further ado
	 * lame output method*/
	private void output(String message){
		System.out.println(message);
	}
	public void printFinished(){
		System.out.println("Files Copied: " + filesCopied);
		System.out.println("Directories Copied: "+ directoriesCopied);
		System.out.println("Errors: "+ errors);
	}
}//end directoryCopier class
