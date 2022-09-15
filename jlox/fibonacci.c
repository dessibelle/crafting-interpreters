#include <stdio.h>

int fib(int n) {
  if (n <= 1) return n;
  return fib(n - 2) + fib(n - 1);
}

int main(int argc, char *argv[]) {
  for (int i = 0; i < 40; i = i + 1) {
    printf("%d\n", fib(i));
  }
}
