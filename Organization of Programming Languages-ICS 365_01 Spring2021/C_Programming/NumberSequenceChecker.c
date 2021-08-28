/*
*	Assignment: Program 2
*	Class: ICS 365
*/

#include<stdio.h>
#include<stdbool.h>


/* Declare functions */ 
int inputSequence(int tempArray[], int maxSequenceSize, int min_Value, int* userSequenceSize);
char checkSequence(int tempnArray[], int maxSequenceSize, int* userSequenceSize);

int main(void)
{
	int MIN_VALUE = 0, MAX_SEQUENCE_SIZE = 100;
	int j, i = 0, userSequenceSize = 0;
	int arrayOfInts[MAX_SEQUENCE_SIZE]; 
	
    printf("Please enter a sequence of integer numbers.  Enter Zero to end the sequence.\n");
    printf("The sequence must be an even number of integers entered, and cannot exceed 100 numbers.\n");
	inputSequence(arrayOfInts, MAX_SEQUENCE_SIZE, MIN_VALUE, &userSequenceSize); 
	
	char res = checkSequence(arrayOfInts, MAX_SEQUENCE_SIZE, &userSequenceSize);
	
	if(res == 'Y')
	{
		printf("Sequence of integers is equal.\n");
	}
	else
	{
		printf("Sequence of integers is not equal.\n");
	}
	
	printf("Sequence: { ");
	for(i=0; i<userSequenceSize; i++)
	{
		printf("%d ", arrayOfInts[i]);
	}
	printf("}\n");
	
	return 0;
	
}


int inputSequence(int sequenceArray[], int maxSequenceSize, int min_Value, int* userSequenceSize)
{
	int i, j;
		
	for(i=0; i< maxSequenceSize; i++)
	{
		printf("Please enter the sequence number (value must be an integer between 1 - 99): ");
		scanf("%d", &sequenceArray[i]);
		
		if((sequenceArray[i] >= maxSequenceSize) || (sequenceArray[i] < min_Value))
		{
			printf("Error: The input %d is not a valid option. \n\n", sequenceArray[i]);			
			printf("************************************************\n");
			printf("*     The input value must be between 1-99     *\n");
			printf("*     Enter 0 to end sequence.                 *\n");
			printf("************************************************\n");
			i--;
		}
		else
		{
			if(sequenceArray[i] == 0)
			{
				printf("User pressed 0 the input sequence is complete.\n");
				break;				
			}
			
		}

	}
	
	*userSequenceSize = i; 
	
	return sequenceArray;
}


char checkSequence(int inputArray[], int maxSequenceSize, int* userSequenceSize)
{

	int i, j;
	int size = *userSequenceSize;
		
	if((size>0) && (((size)%2)!=0))
	{
		printf("The number of integers entered is odd.\n");
		printf("Sequence must be even number of integers.\n");
		return 'N';
	}
	else
	{
		int firstHalf = size/2;
		int count = 0;
		for(j=0;j<size;j++)
		{
			if(inputArray[j] == inputArray[j+firstHalf])
			{
				count++;
			}
			if(count == firstHalf)
			{
				return 'Y';
			}		
		}
		return 'N';
	}
}

