#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
void foo(char w[]){
	w++;
	printf("%c", *w);
	w++;
	printf("%c", *w);
}

int main(int argc, char *argv[]) {
	char a[100];
	a[0] = 'a';
	a[1] = 'b';
	a[2] = 'c';
	a[4] = 'd';
	foo(a);
	return 0;
}
