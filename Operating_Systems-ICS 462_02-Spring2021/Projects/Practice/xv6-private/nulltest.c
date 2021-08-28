#include "types.h"
#include "stat.h"
#include "user.h"

int main(int argc, char *argv[])
{
int *a = 0;
printf(1,"Accessing uninitialized pointer %d\n", *a);
exit();
}