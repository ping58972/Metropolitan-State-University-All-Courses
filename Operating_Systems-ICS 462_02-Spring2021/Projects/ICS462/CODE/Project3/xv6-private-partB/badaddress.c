#include "types.h"
#include "user.h"

int
main(int argc, char *argv[])
{
	// between heap and stack
	char *p = (char *) (10 * 1024);
    printf(1, "address between heap and stack : %d\n", p);
	exit();
}
