#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) 
{
int *a = 0;
printf("Accessing uninitialized pointer %d\n", *a);
return 0;
}