/*
 *Week 2 - Recursion
 *CSCI 112
 *Last modified 2/2 @ 9:45 PM
 *@author Leo Vergnetti
 */


public class FibonacciCalculator {
/*This class briefly compares the runTimes of 2 methods of calculating fibonacci numbers
 * method 1 uses iteration, method 2 uses recursion*/
	public static void main(String[] args) {
		//var declarations
		long startTime; // 
		long runTime; //endTime - startTime
		long fibNumber=0;
		long sumTime;//sum of run times;
		long avgTime;//this will be sum
		int[] testCase = {1,2,5,10,15,20,25,30,35,45};//different values of test cases -
													  //could go higher, but already takes awhile
		
		//for statement to run each testCase.
			for (int i = 0; i <10; i++){
				sumTime = 0;//reset sum of times;
				//for statement, test each testCase  5 times and take the avg runTime for iterativeCalc
				for(int j = 0; j<5; j++){//
					startTime = System.nanoTime();
					fibNumber = iterativeCalc(testCase[i]);
					runTime = System.nanoTime() - startTime;
					sumTime += runTime;
				}
				avgTime = (sumTime/5);//calc avg time
				System.out.println("Case "+i+" Iterative Calculation: "+ fibNumber + " in average "+avgTime +" nanoseconds");
				
				sumTime =0;//reset sumTime
				//test each testCase 5 times and take the avg runTime for recursiveCalc
				for (int j=0; j<5; j++){
					startTime = System.nanoTime();
					fibNumber = recursiveCalc(testCase[i]);
					runTime = System.nanoTime() - startTime;
					sumTime += runTime;
				}
				avgTime = (sumTime/5);  //calc avg time
				System.out.println("Case " +i+ " Recursive Calculation: "+fibNumber+" in average " +avgTime + " nanoseconds");
			}
	}
	/****calculate fibonacci(a) using recursion*****/
	public static long recursiveCalc(int a){
		if(a==0){
			return 0;
		}
		else if(a==1){
			return 1;
		}
		else {
			return recursiveCalc(a-1) + (recursiveCalc(a-2));
		}
	}
	/*****calculate fibonacci(a) in iteratively ******/
	public static long iterativeCalc(int a){
		long first = 0;
		long second = 1;
		long copy;
		for(int i=1; i < a; i++){
		copy = second;
		second += first;
		first = copy;
		}
		
		return second;
	}
}
