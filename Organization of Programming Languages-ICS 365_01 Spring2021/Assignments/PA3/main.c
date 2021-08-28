/* @Author: Nalongsone Danddank	
*	Student ID :14958950	StarID: jf3893pd
*	Email: nalongsone.danddank@my.metrostate.edu\
*	ICS 365. Organization of Programming Languages. 
*	Programming Assignment 3
*/
#include <stdio.h>
#include <stdlib.h>
// declaration structors Node for linked list.
struct Node {
	char *ch;
	struct Node *next;
};
// decalration structor new node for creation.
struct Node *newNode(char new_ch){
	struct Node *new_node = (struct Node*)malloc(sizeof(struct Node));
	new_node->ch = new_ch;
	new_node->next = NULL;
	return new_node;
}
//declaration functions.
void removes(struct Node **);
void insert(struct Node **, struct Node *);
void print(struct Node *);

int main(int argc, char *argv[]) {
	
    struct Node* head = NULL; 
    struct Node* new_node;
	char str[100];
	char exitStr[] = "exit";
	char insertStr[] = "insert";
	char printStr[] = "print";
	char removeStr[] = "remove";
	char ch;
	printf("Enter one of the following commandline: insert, print, remove, or exit.\n");
	scanf("%s", str);
	getchar();
	// loop until user input 'exit'. 
	while(strcmp(str,exitStr) != 0){
		// checking, allow user only input following the requestment key worlds.
		if((strcmp(str,insertStr) != 0) && (strcmp(str,printStr) != 0)
			&&(strcmp(str,removeStr) != 0)){
				printf("Error: unknown request \'%s\'\n", str);	
		} else if (strcmp(str,insertStr) == 0){
			
			printf("enter a char: ");
			ch = getchar();
			// checking, user only input low case letter.
			if(ch <97 || ch > 122){
				printf("Please enter only low case letters 'a' to 'z'.\n");
				ch = NULL;
			} else {
				new_node = newNode(ch);
				insert(&head, new_node); 	
			}
			
		} else if (strcmp(str,printStr) == 0){
			print(head);
		} else if (strcmp(str,removeStr) == 0){
			removes(&head);
		} 
		scanf("%s", str);
		getchar();
	}
	// free memory allocate.
    if(head){
    	free(head);
		head = NULL;
		free(new_node);
		new_node = NULL;
	}

	return 0;
}
/* for insert character to the linked list and also sorted.
* @params head_node - struct Node
* 	new_node - struct Node
*/
void insert(struct Node **head_node, struct Node *new_node){
	struct Node *curr_node;
	if(*head_node == NULL || (*head_node)->ch >= new_node ->ch){
		new_node->next = *head_node;
		*head_node = new_node;
	} else {
		curr_node = *head_node;
		while(curr_node->next != NULL && curr_node->next->ch < new_node->ch){
			curr_node = curr_node->next;
		}
		new_node->next = curr_node->next;
		curr_node->next = new_node;
	}	
};
/* for printing sorted linked list of characters.
* @params head_node - struct Node
*/
void print(struct Node *head_node){
	if(head_node == NULL)
	printf("Linked list is now empty");
	struct Node *temp = head_node;
	while(temp != NULL){
		printf("%c ", temp->ch);
		temp = temp->next;
	}
	printf("\n");
}
/* remove all characters in linked list and 
*  free the linked list memory allocate.
* @params head_node - struct Node
*/
void removes(struct Node **head_node){
	if(*head_node){
		free(*head_node);
		*head_node = NULL;	
	}	
	printf("Linked list is now empty\n");
}
