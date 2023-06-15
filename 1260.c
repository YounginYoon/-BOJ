#include <stdio.h>
#include <stdlib.h>
#define MAX 1001

int visit[MAX];
int edge[MAX][MAX];

typedef struct _node {
  int v;
  struct _node *next;
} Node;

typedef struct _queue {
  Node *front, *rear;
  int len;
} Queue;


typedef struct _stack {
  Node *top;
  int len;
} Stack;

void initVisit() {
  for (int i = 0; i < MAX; i++) {
    visit[i] = 0;
  }
}

Node *makeNode(int v) {
  Node *newNode = (Node *)malloc(sizeof(Node));
  newNode->v = v;
  newNode->next = NULL;

  return newNode;
}

Queue *makeQueue() {
  Queue *q = (Queue*) malloc(sizeof(Queue));
  q->front = NULL;
  q->rear = NULL;
  q->len = 0;

  return q;
}

Stack *makeStack() {
  Stack *s = (Stack *)malloc(sizeof(Stack));
  s->top = NULL;
  s->len = 0;

  return s;
}

int isQueueEmpty(Queue* q) {
  if(q->len == 0)
    return 1;
  else
    return 0;
}

int isStackEmpty(Stack *s) {
  if(s->len == 0)
    return 1;
  else
    return 0;
}

void pushQueue(Queue *q, Node *newNode) {
  if(isQueueEmpty(q)) {
    q->front = newNode;
    q->rear = newNode;
  }
  else {
    q->rear->next = newNode;
    q->rear = newNode;
  }
  q->len++;
}

Node *popQueue(Queue *q) {
  if(isQueueEmpty(q))
    return NULL;
  else {
    Node *del = q->front;
    Node *ret = makeNode(del->v);
    if(q->front == q->rear) {
      q->front = NULL;
      q->rear = NULL;
    }
    else {
      q->front = q->front->next;
    }
    q->len--;
    del->next = NULL;
    free(del);
    return ret;
  }
}
void pushStack(Stack *s, Node *newNode) {
  if(isStackEmpty(s)) {
    s->top = newNode;
  }
  else {
    newNode->next = s->top;
    s->top = newNode;
  }
  s->len++;
}

void *popStack(Stack* s) {
  if(isStackEmpty(s))
    return NULL;
  else {
    Node *del = s->top;
    s->top = s->top->next;
    del->next = NULL;
    free(del);
    s->len--;
  }
}

void BFS(int v, int n) {
  Queue *q = makeQueue();
  Node *start = makeNode(v);

  pushQueue(q, start);
  visit[v] = 1;

  while(!isQueueEmpty(q)) {
    Node *cur = popQueue(q);
    if(cur->v) {
      printf("%d ", cur->v);

      for (int i = 1;i <= n;i++) {
        if(i != cur->v && edge[cur->v][i]==1 && !visit[i]) {
          Node *newNode = makeNode(i);
          pushQueue(q, newNode);
          visit[i] = 1;
        }
      }
    }
  }
  free(q);
}


void DFS(int v, int n) {
  Stack *s = makeStack();
  Node *start = makeNode(v);

  pushStack(s, start);
  visit[v] = 1;
  int find = 0;
  printf("%d ", v);
  while(!isStackEmpty(s)) {
    Node *cur = s->top;
    if(cur->v) {
      find = 0;
      for (int i = 1; i <= n; i++) {
        if(i != cur->v && edge[cur->v][i]==1 && !visit[i]) {
          Node *newNode = makeNode(i);
          pushStack(s, newNode);
          visit[i] = 1;
          find = 1;
          printf("%d ", i);
          break;
        }
      }
      if(!find)
        popStack(s);
    }
  }
  free(s);
}

int main() {
  int n,m,v;
  int v1, v2;

  scanf("%d %d %d", &n, &m, &v);

  for (int i = 0; i < m; i++) {
    scanf("%d %d", &v1, &v2);
    edge[v1][v2] = 1;
    edge[v2][v1] = 1;
  }

  DFS(v, n);
  printf("\n");
  initVisit(n);
  BFS(v, n);

  return 0;
}