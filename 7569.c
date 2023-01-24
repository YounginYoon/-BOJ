#include <stdlib.h>
#include <stdio.h>
#define MAX_SIZE 101

int tomatoes_map[MAX_SIZE][MAX_SIZE][MAX_SIZE];
int visited[MAX_SIZE][MAX_SIZE][MAX_SIZE];
int path[MAX_SIZE][MAX_SIZE][MAX_SIZE];

int dx[6] = {1, 0, -1, 0, 0, 0};
int dy[6] = {0, 1, 0, -1, 0, 0};
int dz[6] = {0, 0, 0, 0, 1, -1};

int M, N, H; // N: 세로, M: 가로, H: 높이

typedef struct _node {
    int x;
    int y;
    int z;
    struct _node *next;
} Node;

typedef struct queue {
    Node* front;
    Node* rear;
    int count;
} Queue;

Queue q;

void initQueue(Queue *q) { // 큐 초기화
    q->front = NULL;
    q->rear = NULL;
    q->count = 0;  
}

int isEmpty(Queue *q) {
    return (q->count == 0);
}

void pushQueue(Queue *q, int x, int y, int z) {
    Node* newNode = (Node*) malloc(sizeof(Node));
    newNode->x = x;
    newNode->y = y;
    newNode->z = z;
    newNode->next = NULL;

    if(isEmpty(q)) { // 큐가 비어있으면 front를 현재 삽입하는 노드로 설정
        q->front = newNode;
    }
    else { // 비어있지 않으면 현재 큐의 rear의 next에 newNode 삽입
        q->rear->next = newNode;
    }
    q->rear = newNode; // rear을 newNode로 설정하고 개수 증가
    q->count++;
}

int popQueue(Queue *q) { // 처음에 들어온 게 제일 먼저 나감
    //pop할 수 없으면 0을 리턴하고, 1 리턴
    Node* deleteNode;

    if(isEmpty(q)) return 0; // 비어있으면 0을 리턴하여 pop할 수 없음을 표시
    
    deleteNode = q->front;

    q->front = q->front->next;
    q->count--;
    free(deleteNode); // pop한 노드 할당 해제

    return 1;

}


void BFS() {
    while(!isEmpty(&q)) {
        Queue *cur = &q;
        int cur_x = cur->front->x;
        int cur_y = cur->front->y;
        int cur_z = cur->front->z;

        popQueue(&q);

        for(int i=0; i<6; i++) {
            int nx = cur_x + dx[i];
            int ny = cur_y + dy[i];
            int nz = cur_z + dz[i];

            if(0<=nx && nx<N && 0<=ny && ny<M && 0<=nz && nz<H) {
                if(!visited[nx][ny][nz] && tomatoes_map[nx][ny][nz] == 0) {
                    visited[nx][ny][nz] = 1;
                    tomatoes_map[nx][ny][nz] = 1;
                    path[nx][ny][nz] = path[cur_x][cur_y][cur_z] + 1;
                    pushQueue(&q, nx, ny, nz);
                }

            }
        }
    }



}

int main() {
    int max_days = 0;

    scanf("%d %d %d", &M, &N, &H);

    initQueue(&q);

    for(int height=0; height < H; height++) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                scanf("%d", &tomatoes_map[i][j][height]);
            }
        }
    }

    for(int height=0; height < H; height++) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j][height] && tomatoes_map[i][j][height] == 1) {
                    pushQueue(&q, i, j, height);
                }
            }
        }
    }

    BFS();

    for(int height=0; height < H; height++) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(tomatoes_map[i][j][height] == 0) {
                    printf("-1\n");
                    return 0;
                }
            }
        }
    }

    for(int height=0; height < H; height++) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(max_days < path[i][j][height]) {
                    max_days = path[i][j][height];
                }
            }
        }
    }

    printf("%d\n", max_days);
    return 0;


}
