// HOW TO RUN: 
// <prompt>$ gcc threads.c -o threads -Wall -pthread
// <prompt>$ ./threads

#include <stdio.h>
#include <pthread.h>

static volatile int counter = 0;
pthread_mutex_t lock; //= PTHREAD_MUTEX_INITIALIZER;

void *mythread(void *arg){
  printf("++ :| %s : Begins ++ \n", (char *) arg);
  pthread_mutex_lock(&lock);
  int i;
  for(i = 0; i < 1e7; i++){
    counter = counter + 1;
  }
  pthread_mutex_unlock(&lock);
  printf("++ :| %s : End ++ \n", (char *) arg);
  return NULL;
}

int main(int argc, char *argv[]){
  pthread_t thread1;
  pthread_t thread2;

  printf("[++ MAIN: Begin ++] || counter = %d \n", counter);
  pthread_create(&thread1, NULL, mythread, "A");
  pthread_create(&thread2, NULL, mythread, "B");

  //Wait for the threads to finish
  pthread_join(thread1, NULL);
  pthread_join(thread2, NULL);

  printf("[++ MAIN: End ++] || counter = %d \n", counter);
  return 0;
}
