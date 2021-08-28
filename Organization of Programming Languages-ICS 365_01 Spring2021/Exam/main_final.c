#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int i = 100; 
void g(void) 
{ 
 printf("%d\n", i); 
 i++; 
 printf("%d\n", i); 
} 

int f(void) 
{ 
  int i=200; 
  printf("%d\n", i); 
  g(); 
  printf("%d\n", i); 
  return(i); 
} 

int main(int argc, char *argv[]) {
//  int i[2]={5,6};
//  int *i_ptr;
//  i_ptr=&i[1];
//  (*i_ptr)++;
//  printf("%d ",*i_ptr);
//  i_ptr--;
//  printf("%d \n",*i_ptr);

 printf("%d\n", f()); 
  printf("%d\n", i);
	return 0;
}
