//  @Author: Nalongsone Danddank	Student ID : 14958950	StarID: jf3893pd
//  Email: nalongsone.danddank@my.metrostate.edu
//  ICS 462-02 Operating Systems. 
//  xv6 - Project 2.

#include "types.h"
#include "stat.h"
#include "user.h"
#include "pstat.h"

// define check function for checking the system call work or.
#define check(exp, msg) if(exp) {} else {\
   printf(1, "%s:%d check (" #exp ") failed: %s\n", __FILE__, __LINE__, msg);\
   exit();}

int
main(int argc, char *argv[])
{
   struct pstat st;

   sleep(10);
   //after sleeping check getpinfo system call.
   check(getpinfo(&st) == 0, "getpinfo");

   int count = 0;
   int i;
   for(i = 0; i < NPROC; i++) {
      if (st.inuse[i]) {
         count++;
         // show the result.
         printf(1, "pid: %d hticks: %d lticks:%d, tickets: %d\n", st.pid[i], st.hticks[i], st.lticks[i], st.tickets[i]);
         check(st.hticks[i] > 0, "all processes must have run at least once");
      }
   }
   
   //show the test passed.
   printf(1, "TEST PASSED\n");
   exit();
}
