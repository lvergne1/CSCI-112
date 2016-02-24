
package states;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StateList {
    //datamembers
    private int size;
    private State[] states;
    
    //constructor inits size to 0 and creates state array with 100 elements
    public StateList(){
        size = 0;
        states = new State[100];
    }
    //getter for size of list (different than array length)
    public int getSize(){
        return size;
    }
    //two methods to add state 
    //method1 takes a state object and adds it to array, size is incremented
    public void addState(State state){
        states[size] = state;
        size++;        
    }
    //method2 overloads method1, calls method 1 by iterating fileobject arg
    public void addState(File file){
        try{
            Scanner readFile = new Scanner(file);
            for(int i=0; i<50; i++){
                State state = new State();
                state.setName(readFile.nextLine());
                state.setCapital(readFile.nextLine());
                state.setPopulation(readFile.nextLine());
                addState(state);
                }
            readFile.close(); // close scanner
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
    }
    //getIndex searches array for string in state name returns index if found
    //else returns -1.  Separated pending removeState code;
    private int getIndex(String name){
        int index = -1;//initialized to -1, if no matches -1 is returned
        for (int i=0; i<size; i++){
            if (name.equalsIgnoreCase(states[i].getName())){
                index = i;//if the state name matches the search criteria, the index is set to i
                break;
            }    
        }
        return index;
    }
    //search state will attempt to get the index of the state with string value equal to the name of state
    //if index returns -1, prints not found message, otherwise it will print state data at index;
    public void searchState(String name){
        int searchIndex = getIndex(name);
        if(searchIndex == -1){
            System.out.println("State not found!");
        }
        else{
            printState(states[searchIndex]);
        }
    }
    //prints formatted single state data
    public void printState(State state){
        System.out.printf("%-15s%-20s%-10s%n",  state.getName(),state.getCapital(),
                            state.getPopulation());       
    }
    //prints whole state list.  will simply call printState for every state object
    //in the array.
    public void print(){
        for(int i = 0; i < size; i++){
            printState(states[i]);
        }
    }
    
    //a hypothetical deleteState name.
    //1.verify and get index of state to be deleted;
    //2.copy index+1 to index marked for deletion
    //3.continue copying until states[size] is reached
    //4.decrement size to account for missing model
    public void deleteState(String name){
        int deleteIndex = getIndex(name);
        if (deleteIndex != -1){
            for (int i=deleteIndex; i<size; i++){
                states[i] = states[i+1];
            }
            size--;
            System.out.println(name + " deleted");
        }
        else {
            System.out.println("No state Found");
        }
    }
}
