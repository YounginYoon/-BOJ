#include <stdio.h>
#define MAX_SIZE 100000

int main() {
    int N,S;
    int array[MAX_SIZE];
    int start = 0, end = 0, sum = 0, min = MAX_SIZE;
    int check = 0;

    scanf("%d %d", &N, &S);

    for(int i=0; i<N; i++) {
        scanf("%d", &array[i]);
    }

    while(end<=N) {
        if(sum < S) {
            sum += array[end++];
        }
        else { // sum <= S
            check = 1;
            int arr_size = end-start;
            if(start == end) arr_size = 1;
            if(arr_size < min) {min = arr_size;}
            sum -= array[start++];
        }
    }

    if(check) printf("%d\n",min);
    else printf("0\n");

}