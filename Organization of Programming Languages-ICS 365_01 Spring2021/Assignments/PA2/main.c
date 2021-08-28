
/* @Author: Nalongsone Danddank	Student ID : 14958950	StarID: jf3893pd
*	Email: nalongsone.danddank@my.metrostate.edu\
*	ICS 365. Organization of Programming Languages. 
*	Programming Assignment 2
*/
#include <stdio.h>
#include <stdlib.h>

// declaration  function.
int accept_sequence(int []);
char check_equivalence(int, int []);

int main(int argc, char *argv[]) {
	// initilize valiables 	
	int sequence [100];
	int i;
	int size;
	char answer;	
	// ask user to input the sequence by correctly.
	printf("Input sequence number: ");
	size = accept_sequence(sequence);
	if (size == -1){
		printf("You have to put number 0 to the end of Sequence!\n");
		return 0;
	}		
	if (size == -2){
		printf("Each value must be in range 1 to 99.\n");
		return 0;
	}			
	if (size%2 != 0){
		printf("The number of integers entered is odd!\n");
		printf("You must enter an even number of integers.\n");
		return 0;
	}	
	// check sequence is equivalence or not.
	// then get the answer and show the result. 
	answer = check_equivalence(size/2, sequence);
	if (answer == 'Y')
		printf("The first half is same as the second half!\n");
	else 
		printf("The first half does not match the second half!\n");	 	 
	return 0;
}
/* for accept the input string from user, 
* then convert to integer and put in to sequence array.
* @params sequence - array type: int
* @return size of sequence - int.
*/
int accept_sequence(int sequence[]) {
	int j = 0;
	char *input[200];
	// get one line input string from user.
	scanf("%[^\n]s",&input);
	// split input string by empty space,
	// then convert each string to integer, 
	// assign to sequence array and end by 0 .
	char *p = strtok(input, " ");
	while(p){
		// convert each string to integer.
		int element = atoi(p);
		sequence[j] = element;
		// if an element is not in range 1 to 99 return -2.
		if(element <0 || element >99) return -2;
		// stop by number 0 that mean the end 
		// and return a size of sequence array.
		if(element == 0) return j;	
		p = strtok(NULL, " ");
		j++;
	}
	return -1;
}
/* for check the sequence integer array, 
* then return the result it is The first half 
* is same as the second half or not.
* @params: 
*           sequence - array type: int
*           size     -  int
* @return answer of result - char.
*/
char check_equivalence(int half_size, int sequence[]){
	int k;
	for (k=0; k< half_size; k++)
		if(sequence[k] != sequence[k+half_size])
			// if a element in the first half is not same 
			// the last half of sequence array then answer is N. 
			return 'N';
	return 'Y';	
}
