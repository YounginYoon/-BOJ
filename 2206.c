#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define WALL 1
#define ROOM 0
#define MAX 1001

int visit[MAX][MAX][2];
int **maze;

typedef struct _node
{
    int r, c;
    int w;
    int dist;
    struct _node *next;
} Node;

typedef struct _queue
{
    Node *front, *rear;
    int len;
} Queue;

Node *makeNode(int r, int c, int w, int dist)
{
    Node *ret = (Node *)malloc(sizeof(Node));
    ret->c = c;
    ret->r = r;
    ret->w = w;
    ret->dist = dist;
    ret->next = NULL;

    return ret;
}

Queue *makeQueue()
{
    Queue *q = (Queue *)malloc(sizeof(Queue));
    q->front = NULL;
    q->rear = NULL;
    q->len = 0;

    return q;
}
int isEmpty(Queue *q)
{
    if (q->len == 0)
        return 1;
    return 0;
}

void pushQueue(Queue *q, Node *newNode)
{
    if (isEmpty(q))
    {
        q->front = newNode;
        q->rear = newNode;
    }
    else
    {
        q->rear->next = newNode;
        q->rear = newNode;
    }
    q->len++;
}

Node *popQueue(Queue *q)
{
    if (!isEmpty(q))
    {
        Node *del = q->front;
        Node *ret = makeNode(del->r, del->c, del->w, del->dist);
        q->front = q->front->next;

        free(del);
        q->len--;
        return ret;
    }
    else
        return NULL;
}

int BFS(int hx, int hy, int ex, int ey, int n, int m)
{
    Queue *q = makeQueue();
    Node *start = makeNode(hx, hy, 0, 0);
    int dr[4] = {1, 0, -1, 0};
    int dc[4] = {0, 1, 0, -1};

    pushQueue(q, start);
    visit[start->r][start->c][0] = 1;

    while (!isEmpty(q))
    {
        Node *cur = popQueue(q);

        if (cur->r == ex && cur->c == ey)
        {
            return cur->dist + 1;
        }

        for (int i = 0; i < 4; i++)
        {
            int nr = cur->r + dr[i];
            int nc = cur->c + dc[i];

            if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1)
                continue;

            if (maze[nr][nc] == ROOM && !visit[nr][nc][cur->w])
            {
                int dist = cur->dist + 1;
                pushQueue(q, makeNode(nr, nc, cur->w, dist));
                visit[nr][nc][cur->w] = 1;
            }
            else if (maze[nr][nc] == WALL)
            {
                if (cur->w == 0 && !visit[nr][nc][cur->w])
                {
                    visit[nr][nc][cur->w + 1] = 1;
                    pushQueue(q, makeNode(nr, nc, cur->w + 1, cur->dist + 1));
                }
            }
        }
    }

    return -1;
}

int main()
{
    int n, m, hx, hy, ex, ey;

    scanf("%d %d", &n, &m);

    hx = 0;
    hy = 0;
    ex = n - 1;
    ey = m - 1;

    maze = (int **)malloc(sizeof(int *) * n);

    for (int i = 0; i < n; i++)
    {
        maze[i] = (int *)malloc(sizeof(int) * m);
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            scanf("%1d", &maze[i][j]);
            visit[i][j][0] = 0;
            visit[i][j][1] = 0;
        }
    }

    int ans = BFS(hx, hy, ex, ey, n, m);
    printf("%d\n", ans);

    for (int i = 0; i < n; i++)
    {
        free(maze[i]);
    }
    free(maze);
}
