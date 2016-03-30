
/*
 *Week 6 - Benchmarking Sorting Algorithms
 *CSCI 112
 *Last modified 3/14 @ 9:45 PM
 *@author Leo Vergnetti
 */
package algorithmbenchmark;

public class AlgorithmBenchmark {

    /*
     * This class benchmarks the various sorting algorithms covered in class. It
     * generates random arrays, and calls each sorting algorithm 100 times,
     * taking the average for each one. The arrays will vary in size from 10000,
     * 20000, 100,000, 200,000, 1x10^6, and 2x10^6 In the event a sorting
     * attempt takes longer than 1 minute, the value will be indicated and no
     * further attempts will be made to test it.
     *
     * note   : bubbleSort, selectionSort, and insertionSort were adapted from
     * methods to sort String arrays, to sort int arrays instead. selectionSort
     * and insertionSort methods were developed by me as outlined in ch 19 for
     * the tutorials assignment. bubbleSortStringArray along with the methods
     * for quickSort and mergeSort were written by c.herbert
     *
     */
    
    //these CONSTANT values will be used as values to indicate and iterate the 
    //sort modes.  
    final static int BUBBLE_SORT = 4;
    final static int SELECTION_SORT = 3;
    final static int INSERTION_SORT = 2;
    final static int MERGE_SORT = 1;
    final static int QUICK_SORT = 0;

    //static int sortMode;
    public static void main(String[] args) {
        // an array containing array SIZES to be tested.  actual arrays will be
        //created and populated during the test. if additional tests are desired, 
        //add or remove array lengths here (test should run regardless)
        int[] arraySizes = {10000, 20000, 100000, 200000, 1000000, 2000000};
        int timeOutLength = 60; //length of time to exit array in seconds (modifiable)
       // int sortMode;//will indicate sorting mode when assigned declared constants
        
        /*
         * loop through each sorting mode.  It will begin with quick sort since this
         * almost always successfully completes and does so in shortest amount of time, 
         * and will increment based on projected run time
         */
        for (int sortMode = QUICK_SORT; sortMode <= BUBBLE_SORT; sortMode++) {
            //print centered title of sorting mode to be tested
            System.out.printf("%n%37s%n", modeName(sortMode));
            //print a formatted table header with labels Elements, AverageCase, Best Case, Worst Case
            System.out.printf("%15s%15s%15s%15s", "Array Length", "Average Case", "Best Case", "Worst Case");
            //iterate each array size, and call testsort on each one
            //if test sort returns false, exit inner loop and move to next method
            for (int j = 0; j < arraySizes.length; j++) {
                System.out.printf("%n%15d", arraySizes[j]); 
                
                //call testSort method, if it returns false, it timed out on run
                if (!testSort(sortMode, arraySizes[j], timeOutLength)) {
                    break; // too long, move to next method
                }
            }//END FOR
        }//end outer for

    }//end main
//****************************************************************************
/* 
 * testSort method.  this method accepts the current sorting mode, the number
 * of elements to be sorted during test, and how long to run the test before
 *  failing.   it will run 100 times unless an instance
 * takes longer than 60 seconds, in which case it will exit the loop and report a 
 * timeout. each pass through, a new array is randomly generated .
 * testSort returns a boolean value to indicate whether the full test was 
 * successful.  If false, the test was not completed due to a timeout value.  
 * currently timeout value is set to 60 seconds due to assignment restrictions, 
 * however, this can be adusted in main method if desired.
 */
    public static boolean testSort(int sortMode, int arraySize, int timeOutLength) {
        long totalTime = 0;
        long bestCase = 0;
        long worstCase = 0;
        boolean lengthOk = true;

        for (int i = 0; i < 100; i++) {
            //make array of arraySize
            long startTime = 0;
            long duration;
            int[] a = generateRandomArray(arraySize);//generate and populate a random array 
            //switch statement to choose sorting method. 
            //timer starts AFTER method is chosen to avoid timing issues
            switch (sortMode) {
                case BUBBLE_SORT:
                    startTime = System.nanoTime();
                    bubbleSort(a);
                    break;
                case INSERTION_SORT:
                    startTime = System.nanoTime();
                    insertionSort(a);
                    break;
                case SELECTION_SORT:
                    startTime = System.nanoTime();
                    selectionSort(a);
                    break;
                case MERGE_SORT:
                    int[] temp = new int[arraySize];
                    startTime = System.nanoTime();
                    mergeSort(a, temp, 0, arraySize - 1);
                    break;
                case QUICK_SORT:
                    startTime = System.nanoTime();
                    quickSort(a, 0, arraySize - 1);
                    break;
            }//end switch

            duration = System.nanoTime() - startTime; //current time minus start time = run time
            totalTime += duration;//add run time to total run Times (will be averaged later)
            if (bestCase == 0 || duration < bestCase) {
                bestCase = duration; //sets sbest case method
            }
            if (worstCase == 0 || worstCase < duration) {
                worstCase = duration;//sets worst case method
            }
            //typecaste to double, for division.  if runtime > timeout value, exit loop
            if ((double) totalTime / 1000000000 > timeOutLength) {
                lengthOk = false;//set boolean to false
                break;//exit for statement! this will take too long
            }//end if tooLong

        }//end for 
        //if operation timed out boolean will be false
        if (!lengthOk) {
           System.out.printf("%40s", "Test Aborted: Run Time > " +timeOutLength );
        } //end if
        else { //successfully completed
            //print formatted output: 
            System.out.printf("%15.5f", ((double) totalTime / 100000000000f));
            System.out.printf("%15.5f", ((double) bestCase / 1000000000f));
            System.out.printf("%15.5f", ((double) worstCase / 1000000000f));
        }//end else
        //return boolean indicating success or timeout
        return lengthOk;
    }//end testSort method

    //******************************************************


    //*********************************************************
    //to String method for integer sortMode
    public static String modeName(int sortMode) {
        String mode = "";
        switch (sortMode) {
            case BUBBLE_SORT:
                mode = "Bubble Sort";
                break;

            case INSERTION_SORT:
                mode = "Insertion Sort";
                break;

            case SELECTION_SORT:
                mode = "Selection Sort";
                break;

            case MERGE_SORT:
                mode = "Merge Sort";
                break;

            case QUICK_SORT:
                mode = "Quick Sort";
                break;
        }//end switch
        return mode;
    }//end modeName

    //**************************************************
    public static int[] generateRandomArray(int n) {
        int[] a = new int[n]; //int array with n elements
        //for statement will populate array with random integers
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100000 + 1);
        }//end for
        return a; //return array
    }//end generateRandomArray

    /*
     * **********************************************
     */
    /* This method sorts an array of Strings line by line 
     * using a simple bubble sort. 
     * 
     * The first parameter refers to the array in the main method.  
     * The second parameter is the number of elements in the array that 
     * actually contain data
     */
    public static void bubbleSort(int[] a) {
        boolean swapped;    //  keeps track of when array values are swapped 
        int i;              // a loop counter
        int temp;         // catalyst variable for String swapping

        // Each iteration of the outer do loop is is one pass through the loop. 
        // If anything was swapped, it makes another pass
        do {
            // set swapped to false before each pass
            swapped = false;

            // the for loop is a pass through the array to the second to last element
            for (i = 0; (i < a.length - 1); i++) {
                // if the two items are out of order  see page 16 for String compareTo() 
                if (a[i + 1] < (a[i])) {
                    // swap the two items ans set swapped to true    
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;

                    swapped = true;

                }  // end if
            } // end for

            // the outer loop will repeat if a swap was made  – another passs
        } while (swapped);

    } // end sortStringArray

    /*
     * ***************************************************************
     */
    /*
     * This method sorts an array of strings line by line 
     * using a selection sort
     *
     * The first parameter refers to the array in the main method.  
     * The second parameter is the number of elements in the array that 
     * actually contain data
     */
    public static void selectionSort(int[] a) {
        int temp;    // catalyst variable for String swapping
        int spot;       //location in array where minimum will be inserted
        int minimum;   //location of minimum value in remainder

        for (spot = 0; spot < a.length - 1; spot++) {
            minimum = spot;
            for (int i = spot + 1; i < a.length; i++) {
                if (a[i] < a[minimum]) {
                    minimum = i; //if i is less than minimum, i is new minimum
                }
            }//end for i

            //swap a[spot] and a[minimum]
            temp = a[minimum];
            a[minimum] = a[spot];
            a[spot] = temp;
        }//end for spot
    }//end selection sort

    /*
     * ***************************************************************
     */
    /*
     * This method sorts an array of strings line by line 
     * using an insertion sort as outlined in book
     *
     * The first parameter refers to the array in the main method.  
     * The second parameter is the number of elements in the array that 
     * actually contain data
     */
    public static void insertionSort(int[] a) {

        int i;      //pointer to item in unsorted list
        int j;      //pointer to an item in sorted list
        int value;  //the next value to be inserted into sorted list

        for (i = 1; i < a.length; i++) {     // iterate for each item in unsorted list

            value = a[i];       //assigns value of element in list to be sorted
            j = i - 1;            //assign j to be the last element in sorted list

            while (j >= 0 && (a[j] >= value)) {
                //if there are still elements in unsorted list 
                //and if the value to be inserted is less than the the value at index
                a[j + 1] = a[j];  //copy element to the right
                j--;            //increment to check value to the left
            }//end while --the array continues moving each element right
            a[j + 1] = value;     //assign value to it's place 
        }//end for loop
    }//end selection sort
    //************************************************************************
    // the recursive quicksort method, which calls the partition method

    public static void quickSort(int[] a, int startIndex, int endIndex) {
        int pivotIndex; // the index of pivot returned by the quicksort partition
        // if the set has more than one element, then partition
        if (startIndex < endIndex) {
            // partition and return the pivotIndex
            pivotIndex = partition(a, startIndex, endIndex);
            // quicksort the low set
            quickSort(a, startIndex, pivotIndex - 1);
            // quiclsort the high set
            quickSort(a, pivotIndex + 1, endIndex);
        } // end if
    } // end quickSort()
    //************************************************************************
    // This method performs quicksort partitioning on a set of elements in an array.

    public static int partition(int[] a, int startIndex, int endIndex) {
        int pivotIndex; // the index of the chosen pivot element
        int pivot; // the value of the chosen pivot
        int midIndex = startIndex; // boundary element between high and low sets
        // select the center element in the set as the pivot by integer averaging
        pivotIndex = (startIndex + endIndex) / 2;
        pivot = a[pivotIndex];
        // put the pivot at the end of the set so it is out of the way
        swap(a, pivotIndex, endIndex);
        // iterate the set, up to but not including last element
        for (int i = startIndex; i < endIndex; i++) {
            // if a[i] is less than the pivot
            if (a[i] < pivot) {
                // put a[i] in the low half and increment current Index
                swap(a, i, midIndex);
                midIndex = midIndex + 1;
            } // end if
        } // end for

        // partitioning complete -- move pivot from end to middle
        swap(a, midIndex, endIndex);
        // return index of pivot
        return midIndex;

    } // end partition
    //************************************************************************
    // This method swaps two elements in an integer array

    public static void swap(int[] a, int first, int second) {

        int c; // a catalyst variable used for the swap
        c = a[first];
        a[first] = a[second];
        a[second] = c;
    } // end Swap()

//************************************************************************
    public static void mergeSort(int[] a, int[] temp, int low, int high) {
        // low is the low index of the part of the array to be sorted
        // high is the high index of the part of the array to be sorted

        int mid; // the middle of the array – last item in low half

        // if high > low then there is more than one item in the list to be sorted
        if (high > low) {
            // split into two halves and mergeSort each part
            // find middle (last element in low half)
            mid = (low + high) / 2;
            mergeSort(a, temp, low, mid);
            mergeSort(a, temp, mid + 1, high);

            // merge the two halves back together, sorting while merging
            merge(a, temp, low, mid, high);
        } // end if
        return;
    }// end mergeSort()
    //********************************************************************


    /* This method merges the two halves of the set being sorted back together.
 * the low half goes from a[low] to a[mid]
 * the high half goes from a[mid+1] to a[high]
 * (High and low only refer to index numbers, not the values in the array.)
 *
 * The work of sorting occurs as the two halves are merged back into one
 * sorted set.
 *
 * This version of mergesort copies the set to be sorted into the same
 * locations in a temporary array, then sorts them as it puts them back.
 * Some versions of mergesort sort into the temporary array then copy it back.
     */
    public static void merge(int[] a, int[] temp, int low, int mid, int high) {
        // low is the low index of the part of the array to be sorted
        // high is the high index of the part of the array to be sorted
        // mid is the middle of the array – last item in low half

        // copy the two sets from a[] to the same locations in the temporary array
        for (int i = low; i <= high; i++) {
            temp[i] = a[i];
        }
        //set up necessary pointers
        int lowP = low; // pointer to current item in low half
        int highP = mid + 1; // pointer to current item in high half
        int aP = low; // pointer to where each item will be put back in a[]
        // while the pointers have not yet reached the end of either half
        while ((lowP <= mid) && (highP <= high)) {
            // if current item in low half <= current item in high half
            if (temp[lowP] <= temp[highP]) {
                // move item at lowP back to array a and increment low pointer
                a[aP] = temp[lowP];
                lowP++;
            } else {
                // move item at highP back to array a and increment high pointer
                a[aP] = temp[highP];
                highP++;
            } // end if..else

            // increment pointer for location in original array
            aP++;
        } // end while
        /* When the while loop is done, either the low half or the high half is done.
 * We now simply move back everything in the half not yet done.
 * Remember, each half is already in order itself.
         */
        // if lowP is at end of low half, then low half is done, move rest of high half.
        if (lowP > mid) {
            for (int i = highP; i <= high; i++) {
                a[aP] = temp[i];
                aP++;
            } // end for
        } else // high half is done, move rest of low half
        {
            for (int i = lowP; i <= mid; i++) {
                a[aP] = temp[i];
                aP++;
            }// end for
        }
        return;
    } // end merge()
    // *************************************************************
}//end class
