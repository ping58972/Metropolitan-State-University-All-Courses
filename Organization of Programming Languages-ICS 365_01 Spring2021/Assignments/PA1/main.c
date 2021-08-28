#include <stdio.h>
void fun(int*, int*);
int main() 
{
	int i=6, j=2;
	fun(&i, &j);
	printf("%d, %d\n", i, j);
	return 0;
}
void fun(int *i, int *j){
	*i = *i**i;
	*j = *j**j;
}
//#include <stdio.h>
//int main(int argc, char *argv[]) {
//	argv[1] = getchar();
//	printf("Hello World\n");
//	printf("%s\n", argv[0]);
//	printf("%s\n",argv[1]);
//}
