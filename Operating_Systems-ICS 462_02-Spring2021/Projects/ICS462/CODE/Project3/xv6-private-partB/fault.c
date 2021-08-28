#include "types.h"
#include "user.h"

int
main(int argc, char *argv[])
{
	char *p = (char *) (300 * 1024);
	// try get in address in between heap and stack 
        // *p = 'a';
    printf(1, "address between heap and stack : %d\n", p);
	exit();
}
