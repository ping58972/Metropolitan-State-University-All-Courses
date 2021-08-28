#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

/*NOTES:
Additional resources outside the class materials I used for this programming assignment:
https://www.youtube.com/watch?v=1joffYDrIlA
https://www.tutorialspoint.com/cprogramming/c_input_output.htm
https://www.tutorialspoint.com/c_standard_library/c_function_free.htm
https://www.geeksforgeeks.org/c-program-bubble-sort-linked-list/
*/

//TYPEDEF: so I don't have to type "struct char_node" everytime.
typedef struct char_node {
    char data;
    struct char_node *next;
}char_node;

//FUNCTIONS:
char_node *insert(char_node *previous, char *input);
void print(char_node * head);
void delete(char_node *head);
void auto_sort(char_node *head);
void swap(char_node *a, char_node *b);

int main () {
	//VARIABLE DECLARATIONS:
	bool running = false;
	char command[16];
	char input;
	char_node * head = NULL;
	char_node * newest = NULL;
	
	//USER INSTRUCTION:
	printf("This C program implements a sorted linked list. \n\nINSTRUCTIONS: \nType 'insert' to add a char to the list, 'print' to print the linked list to the screen, \n'remove' to delete the list, or 'exit' to quit the program.\n");

	//BEGIN PROGRAM:
	running = true;
	while(running == true) {
		
		printf("> ");
		scanf("%s", command);
		
		//PROCESS INSERT COMMAND:
		if (strcmp(command, "insert") == 0) {
			
			//PROMPT USER:
			printf("  Enter a char: ");
			scanf("%c");
			input = getchar();
			
			//CHECK THAT INPUT IS LOWERCASE:
			if (islower(input)) {
				
				if (head == NULL) {
					head = insert(NULL, input);
					newest = head;
					;
				}
				
				else {
					newest = insert(newest, input);
				}
				
				auto_sort(head);
			}
			
			else {
				printf("  Error: the char must be lowercase, please try the 'insert' command again.\n");
			}
				
		}
		
		//PROCESS PRINT COMMAND:
		else if (strcmp(command, "print") == 0) {
			
			//CHECK FOR EMPTY LIST:
			if (head == NULL) {
				printf("  Error: there are no elements within the linked list.\n");
			}
			else {
				print(head);
			}
		}
		
		//PROCESS REMOVE COMMAND:
		else if (strcmp(command, "remove") == 0) {
			
			//CHECK FOR EMPTY LIST:
			if (head == NULL) {
				printf("Error: there are no elements within the linked list.");
			}
			else {
				delete(head);
				//Set 'head' to NULL after freeing linked list.
				head = NULL;
			}
		}
		
		//PROCESS EXIT COMMAND:
		else if (strcmp(command, "exit") == 0) {
			if (head != NULL) {
				printf("  Error: you must delete the linked list using the 'remove' command \n  before you are able to exit the program.\n");
			}
			
			else {
				running = false;	
			}
			
		}
		
		//PROCESS UNRECOGNIZED COMMAND:
		else {
			printf("  Error: unknown request '%s'.\n", command);
		}
	}
	
	return 0;
}

char_node *insert(char_node *previous, char *input) {
	
	char_node *new_node = malloc(sizeof(char_node));
	new_node->data = input;
	//printf("Added: %c", new_node->data);//testing
	
	new_node->next = NULL;
	
	if (previous != NULL) {
		previous->next = new_node;
	}
	
	return new_node;
}

void print(char_node *head) {
	
	char_node *current = head;
	printf("  "); //formatting purposes.
    while (current != NULL) {
        printf("%c ", current->data);
        current = current->next;
    }
    printf("\n"); //formatting purposes.
}

void delete(char_node *head) {
	
	char_node *current = head;
	char_node *next = NULL;	
	
	while(current != NULL) {
		next = current->next;
		free(current);
		current->data = NULL;
		current->next = NULL;
		current = next;
	}	

}

void auto_sort(char_node *head) { 
	//source: https://www.geeksforgeeks.org/c-program-bubble-sort-linked-list/
    int swapped, i; 
    char_node *current; 
    char_node *lptr = NULL; 
  
  	//CHECK FOR EMPTY LIST:
    if (head == NULL) 
        return; 
  
    do
    { 
        swapped = 0; 
        current = head; 
  
        while (current->next != lptr) 
        { 
            if (current->data > current->next->data) 
            {  
                swap(current, current->next); 
                swapped = 1; 
            } 
            current = current->next; 
        } 
        lptr = current; 
    } 
    while (swapped); 
} 
  

void swap(char_node *a, char_node *b) 
{ 
	//source: https://www.geeksforgeeks.org/c-program-bubble-sort-linked-list/
    char temp = a->data; 
    a->data = b->data; 
    b->data = temp; 
} 



