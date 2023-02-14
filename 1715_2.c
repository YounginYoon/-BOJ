#include <stdio.h>
#include <stdlib.h>

#define MAX_N 100001


typedef struct _heap {
  int data[MAX_N];
  int heap_size;
} Heap;

//heap 초기화
Heap *init(Heap *h) {
  h = (Heap *) malloc(sizeof(Heap));
  h->heap_size = 0;

  return h;
}

// 최소 힙 : 부모 <= 자식
void add(Heap *h, int data) {
  h->heap_size++;
  h->data[h->heap_size] = data;

  int child = h->heap_size;
  int parent = child / 2;

  while (parent) {
    if(h->data[parent] > h->data[child]) {
      int tmp = h->data[child];
      h->data[child] = h->data[parent];
      h->data[parent] = tmp;
      child = parent;
      parent = child / 2;
    }
    else return;
  }
  return ;
}

int isEmpty(Heap *h) {
  return (h->heap_size ? 0 : 1);
}

int delete(Heap *h) {
  //삭제할 가장 작은 값 저장
  int tmp = h->data[1];
  //가장 마지막 데이터를 맨 위로 올리고 크기 1 감소
  h->data[1] = h->data[h->heap_size];
  h->heap_size--;

  int parent = 1;
  int child = 2;
  //자식의 인덱스가 힙의 크기보다 작거나 같을 동안 반복
  while (child <= h->heap_size) {
    if ((child < h->heap_size) && (h->data[child] > h->data[child + 1]))
      child++;
    if (h->data[parent] > h->data[child]) {
      int tmp = h->data[parent];
      h->data[parent] = h->data[child];
      h->data[child] = tmp;

      parent = child;
      child = parent * 2;
    }
    else break;
  }
  return tmp;
}

int main() {
  int N, num, sum = 0;
  Heap *h;
  
  h = init(h);

  scanf("%d", &N);
  for(int i = 0; i < N; i++) {
    scanf("%d", &num);
    add(h, num);
  }

  while(h->heap_size > 1) {
    int tmp1 = delete(h);
    int tmp2 = delete(h);
    int tmp = tmp1 + tmp2;
    sum += tmp;
    add(h, tmp);
  }
  printf("%d\n", sum);
}