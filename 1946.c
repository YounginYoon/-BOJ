#include <stdio.h>
#include <stdlib.h>

typedef struct _person {
  int test, interview;
} Person;

int ans[21];

int compare(const void* x, const void* y) {
	Person A = *(Person*)x;
	Person B = *(Person*)y;

	if (A.test > B.test)
		return 1;
	else if (A.test < B.test)
		return -1;
	else return 0;
}


int check(Person p[], int n) {
  int cnt = 0;
  int maxInterview = p[0].interview;

  for (int i = 0; i < n;i++) {
    if(p[i].interview <= maxInterview) {
      cnt++;
      maxInterview = p[i].interview;
    }
  }

  return cnt;
}

int main() {
  int t, n;
  
  scanf("%d", &t);
  for (int i = 0;i<t;i++) {
    scanf("%d", &n);
    Person *people = (Person *)malloc(sizeof(Person) * n);
    for (int j = 0; j < n;j++) {
      scanf("%d %d", &people[j].test, &people[j].interview);
    }
    qsort(people, n, sizeof(Person), compare);
    ans[i] = check(people, n);

    free(people);
  }

  for (int i = 0; i < t;i++)
    printf("%d\n", ans[i]);
}