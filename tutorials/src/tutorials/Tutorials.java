/*  Week 5 Iterative Sorting
 *  CSCI 112 ONLINE
 *  Last modified 2/25/16 @ 3:45 pm
 *  @author C. Herbert 
 *  @modified by Leo Vergnetti
 */

 /*
  * Sorry, felt weird putting my name as author since most of the program
  * is your work --
  */
/*  CSCI 111 - Fall 2013
 * reading, sorting and writing data in text files
 * this program reads data from a text file, sorst the data,
 * then writes the data bacxk to another text file.
 * last edited Oct 1, 2013 by C. Herbert
 * 
 * for this to work, the file "unsorted.txt" must be in the project folder
 * warning -- this will overwrite the file "tutorials.txt"
 * 
 * Thos program has methods to read lines from a text from a file into an array, 
 * display a text array on screen line-by-line, sort a text array, and write 
 * a text array to a data file line by line.
 * 
 * The program is limited to a file with 100 lines.   To change this, change the 
 * size of the array declared in the main mwthod.
 * 
 */
package tutorials;
import java.util.Scanner; 

public class Tutorials 
{

    // the main method call methods to perform each part of the program
public static void main(String[] args) throws Exception

    {
        String[] tutorials = new String[100];     // an array to hold a list of tutorials
        int count;		// the number of elements in the that are used
        
        
        // BUBBLE SORT 
        System.out.println("Demonstrating Bubble Sort\n");
        // read data into tutorials[] line by line and return count
        count = readLines(tutorials); 
        // print the array on the screen
        System.out.println("The original array:");    
        displayLines(tutorials, count);
        //use bubble sort
         bubbleSortStringArray(tutorials, count);
        //output
        System.out.println("\nThe bubble sorted array:");  
        displayLines(tutorials, count);
        // write the array to a data file line by line
        writeLines(tutorials, count);
        //to sort the array 
        
        // SELECTION SORT
        System.out.println("\nDemonstrating Selection Sort\n");
        // read data into tutorials[] line by line and return count
        count = readLines(tutorials); 
        // print the array on the screen
        System.out.println("The original array:");    
        displayLines(tutorials, count);
        //use selection sort
         selectionSortStringArray(tutorials, count);
        //output
        System.out.println("\nThe selection sorted array:");  
        displayLines(tutorials, count);
        // write the array to a data file line by line
        writeLines(tutorials, count);
        
        //INSERTION SORT
        System.out.println("\nDemonstrating Insertion Sort\n");
        // read data into tutorials[] line by line and return count
        count = readLines(tutorials); 
        // print the array on the screen
        System.out.println("The original array:");    
        displayLines(tutorials, count);
        //use insertion sort
         insertionSortStringArray(tutorials, count);
        //output
        System.out.println("\nThe Insertion sorted array:");  
        displayLines(tutorials, count);
        // write the array to a data file line by line
        writeLines(tutorials, count);
        
        

    } // end main()        
/*************************************************/
    
    /* This method reads data from the file into the array. 
     * We want our array to work with up to 100 elements  
     * Each line from the file will be one element in the array.
     * 
     * The parameter refers to the array in the main method.  
     * 
     * The method returns the number of elements it uses.
     */
     public static int readLines(String[] lines) throws Exception
    {
        int count = 0; // number of array elements with data
        
        // Create a File class object linked to the name of the file to read
        java.io.File unsorted = new java.io.File("unsorted.txt");

        // Create a Scanner named infile to read the input stream from the file
        Scanner infile  = new Scanner(unsorted);
   
        /* This while loop reads lines of text into an array. it uses a Scanner class 
         * boolean function hasNextLine() to see if there another line in the file.
         */
        
        while ( infile.hasNextLine() ) 
        {
            // read a line and put it in an array element
            lines[count] = infile.nextLine();
            count ++;  // increment the number of array elements with data
            
        } // end while
        
        infile.close();
        return count;    // returns the number of items used in the array.
    
    } // end readList()
/*************************************************/
    
     
    /* This method sorts an array of Strings line by line 
     * using a simple bubble sort. 
     * 
     * The first parameter refers to the array in the main method.  
     * The second parameter is the number of elements in the array that 
     * actually contain data
     */
    
    public static void bubbleSortStringArray(String[] a, int count)
    {
        boolean swapped;    //  keeps track of when array values are swapped 
        int i;              // a loop counter
        String temp;         // catalyst variable for String swapping
        
        // Each iteration of the outer do loop is is one pass through the loop. 
        // If anything was swapped, it makes another pass
        do     
        {
            // set swapped to false before each pass
            swapped = false;
            
            // the for loop is a pass through the array to the second to last element
            for( i=0 ; (i < count-1) ; i++ )    
            {
                // if the two items are out of order  see page 16 for String compareTo() 
                if(a[i+1].compareTo(a[i]) < 0)	 
                {
                    // swap the two items ans set swapped to true    
                    temp  = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    
                    swapped = true;    
		
		}  // end if
            } // end for
            
        // the outer loop will repeat if a swap was made  – another passs
        } while (swapped);	
        
    } // end sortStringArray
/******************************************************************/
    /*
     * This method sorts an array of strings line by line 
     * using a selection sort
     *
     * The first parameter refers to the array in the main method.  
     * The second parameter is the number of elements in the array that 
     * actually contain data
     */
    
    public static void selectionSortStringArray(String[] a, int count){
        String temp;    // catalyst variable for String swapping
        int spot;       //location in array where minimum will be inserted
        int minimum;   //location of minimum value in remainder
        
        for (spot=0; spot<count-1; spot++){     
            minimum = spot;
            for (int i = spot+1; i<count;i++){
                if ((a[i].compareTo(a[minimum])) < 0){
                    minimum = i; //if i is less than minimum, i is new minimum
                }
            }//end for i
            
            //swap a[spot] and a[minimum]
            temp = a[minimum];
            a[minimum] = a[spot];
            a[spot] = temp;
        }//end for spot
    }
/******************************************************************/
        /*
     * This method sorts an array of strings line by line 
     * using an insertion sort
     *
     * The first parameter refers to the array in the main method.  
     * The second parameter is the number of elements in the array that 
     * actually contain data
     */
    
    public static void insertionSortStringArray(String[] a, int count){
       
        int i;      //pointer to item in unsorted list
        int j;      //pointer to an item in sorted list
        String value;  //the next value to be inserted into sorted list
        
        for(i=1; i<count; i++){     // iterate for each item in unsorted list
            
            value = a[i];       //assigns value of element in list to be sorted
            j = i-1;            //assign j to be the last element in sorted list
            
            while (j >= 0 && (a[j].compareTo(value) >= 0)){
                //if there are still elements in unsorted list 
                //and if the value to be inserted is less than the the value at index
                a[j+1] = a[j];  //copy element to the right
                j--;            //increment to check value to the left
            }//end while --the array continues moving each element right
            a[j+1] = value;     //assign value to it's place 
        }//end for loop
    }
/******************************************************************/
        /*This method prints an array of Strings on the screen.  
         * The first parameter refers to the array in the main method.  The second 
         * parameter is the number of elements in the array that actually contain data
         */
        public static void displayLines(String[] lines, int count)
        {
            int i;  // loop counter
        
            // iterate the elements actually used
            for ( i=0; i < count; i++)    
                System.out.println(lines[i]);
        
        } // end displayLines()
/*************************************************/

/* This method writes an array of Strings to a text data file.  
 * The first parameter refers to the array in the main method. The second parameter
 * is the number of elements in the array that actually contain data
 */

        public static void  writeLines(String[] lines, int count) throws Exception
        {
            // create a File class object and give the file the name tutorials.txt
            java.io.File tut  = new java.io.File("tutorials.txt");
            // Create a PrintWriter text output stream and link it to the file x
            java.io.PrintWriter outfile  = new java.io.PrintWriter(tut);
            
            int i;  // loop counter
            
            // iterate the elements actually used
            for ( i=0; i < count; i++)    
                outfile.println(lines[i]);

            outfile.close();            
            
        } // end writeTextArray()
/*************************************************/

} // end class        
