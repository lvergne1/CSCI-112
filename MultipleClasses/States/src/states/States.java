/*
 *Week 1 - States
 *CSCI 112
 *Last modified 1/26 @ 5:45 PM
 *@author Leo Vergnetti
 */

package states;

import java.io.File;
import java.util.Scanner;

/**
 * This is the main executable class for the states assignment. The main method
 * first opens scanner to get input. a file object is created where state data is
 * contained. Then a new instance of StateList is created . State objects are
 * added by using the addState method with file parameter.  Next a formatted list
 * is printed.  A pair of prompt and captures allow the user to search for 1 state.
 * Then the user is prompted to pick a state for deletion.  once removed, the 
 * list is printed again to verify state is removed from list. 
 * scanner object is closed.
 */
public class States {
    
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        File file = new File ("statedata.txt");
        StateList stateList = new StateList();
        stateList.addState(file);
        stateList.print();
        
        System.out.println("Enter a State name ");
        stateList.searchState(kb.nextLine());
        System.out.println("Enter a state to delete");
        stateList.deleteState(kb.nextLine());
        stateList.print();
        kb.close();
     } 
    
}
