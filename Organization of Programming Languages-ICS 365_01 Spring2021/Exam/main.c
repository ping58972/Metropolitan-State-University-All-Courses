
/* @Author: Nalongsone Danddank	Student ID :14958950	StarID: jf3893pd
*	Email: nalongsone.danddank@my.metrostate.edu\
*	ICS 365. Organization of Programming Languages. 
*	Exam #1
*/
#include <stdio.h>
#include <stdlib.h>

// declaration  function.
int smallest_num(int, int, int);

int main(int argc, char *argv[]) {
	// initilize valiables for user input
	int x,y,z;
	//show infomation to user to input the 3 number.
	printf("Input the 3 three different positive integer numbers:\n");
	printf("Input the first positive integer:");
	scanf("%d", &x);
	printf("Input the second positive integer:");
	scanf("%d", &y);
	printf("Input the thirst positive integer:");
	scanf("%d", &z);
	//display the result.
	printf("Minimum of 3 three different positive integer numbers that you input is %d\n", smallest_num(x, y, z));
	printf("We have Done! Bye!");
	return 0;
}

/* for calculating the minimum of the 3 numbers. 
* @params a - int
*         b - int
*		  c - int
* @return smallest of 3 number - int.
*/
int smallest_num(int a, int b, int c){
	if (!(b / a))  
        return (!(b / c)) ? b : c; 
    return (!(a / c)) ? a : c; 
}
