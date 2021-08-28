#include <stdio.h>
#include <time.h>
//set target age aviable to be 70 by macro #define
#define TARGET_AGE 70
int calculate_age(int year);

int main(int argc, char *argv[]) {
	int year;
	printf("What year is your birth?");
	scanf("%d", &year);
	// change target age to the correct answer.
	#if defined(TARGET_AGE)
		#define TARGET_AGE calculate_age(year)
	#endif
	// then print out the correct age.
	printf("Your current age is %d", TARGET_AGE);
	return 0;
}

// function for calculating from a birth year to age.
// @params year - int
// @return age - int
int calculate_age(int year){
	time_t t = time(NULL);
	struct tm tm = *localtime(&t);
	return tm.tm_year + 1900 - year;
}
