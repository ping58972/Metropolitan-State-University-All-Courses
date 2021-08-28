Author: Nalongsone Danddank	
Student ID : 14958950	StarID: jf3893pd
Email: nalongsone.danddank@my.metrostate.edu
Metropolitan State University, St. Paul
Department of Information and Computer Science
ICS 462 Operating Systems
Spring 2021
Project 1: Introduction to xv6

Developing Environment:
    CPU: intel 86X
    OS: window 10 (64bit)
    Virtual OS: VM Box 6.1.2
        Ubunto 18.04.5 LTS (64 bit)
            GCC :(Ubuntu 7.5.0-3ubuntu1~18.04) 7.5.0
            GNU gdb (Ubuntu 8.1.1-0ubuntu1) 8.1.1
            qemu-system-x86 Version: 1:2.11+dfsg-1ubuntu7.34

Original source from:
    Github clone: https://github.com/mit-pdos/xv6-public.git

Following the project assignment 1 requirment, 
    has modified some files from original xv6-public to 
    new xv6-private folder below:
    1. syscall.h
        -> at line 23 add: #define SYS_getsyscallinfo	22
    2. defs.h
        -> below line 105: // proc.c 
        -> at line 123 add: int	getsyscallinfo(void);
    3. user.h
        -> below line 4: // system calls
        -> at line 26 add: int	getsyscallinfo(void);
    4. sysproc.c
        -> at line 93 to 97 add:
            int
            sys_getsyscallinfo(void)
            {
                return getsyscallinfo();
            }
    5. usys.S
        -> at line 32 add: SYSCALL(getsyscallinfo)
    6. syscall.c
        -> at line 106 add: extern int sys_getsyscallinfo(void);
        -> at line 107 add: extern int syscallcount;
        -> at line 131 add: [SYS_getsyscallinfo]	sys_getsyscallinfo,
        -> at line 142 add: syscallcount++;
        -> at line 147 add: syscallcount = -1;
    7. proc.c
        -> create global variable for counting a system call.
            by at line 11 add: int syscallcount = 0;
        -> then add system call function for print out the number of counter.
            at line 540 to 545 add:
                int
                getsyscallinfo(void)
                {
                    cprintf("Total number of system call is made till now: %d\n", syscallcount);
                    return 22;
                }
    8. getsyscallinfo.c 
        -> create a new c file name: getsyscallinfo.c
            for invoke the new system call which we want.
            adding contant below into the file:
                #include "types.h"
                #include "stat.h"
                #include "user.h"
                #include "fcntl.h"
                int
                main(int argc, char *argv[])
                {
                    getsyscallinfo();
                    exit();
                }
    9. Makefile
        -> in UPROGS=\ at line 183 add: _getsyscallinfo\
        -> in EXTRA=\ at line 253 add: getsyscallinfo.c

To run the OS in xv6-private folder:
    -> cd enter in the folder xv6-private.
    -> type input command line "make" 
    -> after finished running type command line 
        "make qemu-nox"
    -> then we get in the OS.
    -> type commandline "getsyscallinfo" which we created.
    -> there will return the message: 
        "Total number of system call is made till now ##"