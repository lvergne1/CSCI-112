package directoryclone;

/**
 * Week 4 - Using API
 * CSCI 112- online
 * Last Modified 2/18/2016 11:00 PM
 * @author Leo Vergnetti
 * */
import java.util.Scanner;

public class DirectoryClone {

	/**
	 * this class tests the capabilities of the DirectoryCopy class. 
	 * */
	public static void main(String[] args){
		
		String srcPath; //directoryto be copied 
		String tgtPath; // directory to copy to
		Scanner scanner = new Scanner(System.in); //open scanner for user input
		System.out.println("Enter a source");
		srcPath = scanner.nextLine();
		System.out.println("Enter a target directory");
		tgtPath = scanner.nextLine();
		scanner.close();  //close scanner
		
		//INSTANTIATES directory copier class (made to be used with varying GUIS)
		DirectoryCopier dc = new DirectoryCopier();    
		dc.cloneDirectory(srcPath, tgtPath);  //call public cloneDirectory method
		dc.printFinished(); //print finished report
		
	}
}


