#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

char* message();

//int main(int argc, char *argv[]) {
////	char* strr  = message();
////	printf(strr);
//	
//	int *arr;
//	int i;
//	arr = (int*) calloc(10, sizeof(int));
////	for(i =0; i<10;i++)
////		printf("%d\n", arr[i]);	
//	for(i=0; i<10; i++)
//		arr[i] = i;
//	arr = (int*) realloc(arr, 20*sizeof(int));
//	for(i =0; i<20;i++)
//		printf("%d\n", arr[i]);	
//	free(arr);
//	printf("print free");
//	printf(arr);
//	return 0;
//}
void swap1(intx, inty);
void swap2(int *p, int *q);
int main(int argc, char *argv[]) {
	int x, y;
	x = 3;
	y = 4;
	printf("main: (x, y) = (%d, %d)\n", x, y);
	swap1(x, y);
	printf("main: (x, y) = (%d, %d)\n\n", x, y);	
	
	printf("main: (x, y) = (%d, %d)\n", x, y);
	swap2(&x, &y);
	printf("main: (x, y) = (%d, %d)\n\n", x, y);
	return 0;
}

void swap1(int x, int y){
	int temp;
	temp = x;
	x = y;
	y = temp;
	printf("swap1: (x, y) = (%d, %d)\n", x, y);
}
void swap2(int *p, int *q){
	int temp;
	temp = *p;
	*p = *q;
	*q = temp;
	printf("swap2: (x, y) = (%d, %d)\n", *p, *q);
}

//int main(int argc, char *argv[]) {
//	int x, y;
//	int *p, *q;
//	x = 5;
//	y= 10;
//	p = &x;
//	q = &y;
//	printf("x = %d\n", x);
//	printf("y = %d\n", y);
//	printf("p = %d\n", p);
//	printf("q = %d\n", q);
//	*p = 10;
//	*q = 20;
//	printf("x = %d\n", x);
//	printf("y = %d\n", y);
//	printf("p = %d\n", p);
//	printf("q = %d\n", q);
//	*p += 5;
//	*q -= 5;
//	printf("x = %d\n", x);
//	printf("y = %d\n", y);
//	printf("p = %d\n", p);
//	printf("q = %d\n", q);
//	return 0;
//}


char* message() {
	char str[] = "Hello";
	
	char *dynamic_mem;
	dynamic_mem = (char*) malloc(strlen(str)+1);
	strcpy(dynamic_mem, str);
	return dynamic_mem;
}
