#include <stdio.h>
#include <string.h>
#define MAX_N 1002
#define max(a, b) (a > b ? a : b)

int LCS[MAX_N][MAX_N];
char result[MAX_N];

int LCS_len(char *str1, char *str2) {
    int len_1 = strlen(str1);
    int len_2 = strlen(str2);

    int i, j;
    //문자열은 인덱싱이 0부터 시작!
    for(i = 0; i<=len_1; i++) {
        for(j = 0; j <= len_2; j++) {
            if (i == 0 || j == 0) // margin 설정
                LCS[i][j] = 0;
            else if (str1[i - 1] == str2[j - 1])
                LCS[i][j] = LCS[i - 1][j - 1] + 1;
            else
                LCS[i][j] = max(LCS[i - 1][j], LCS[i][j - 1]);
        }
    }

    return LCS[len_1][len_2];
}

void find_LCS(char *str1, char *str2)
{
    int len_1 = strlen(str1);
    int len_2 = strlen(str2);

    int i = len_1;
    int j = len_2;
    int k = 0;

    while (i > 0 && j > 0)
    {
        if (LCS[i][j] == LCS[i][j-1]) {
            j--;
        }
        else if (LCS[i][j] == LCS[i-1][j]) {
            i--;
        }
        else {
            result[k++] = str2[j - 1];
            i--;
            j--;
        }
    }
}

int main() {
    char s1[MAX_N];
    char s2[MAX_N];

    scanf("%s", s1);
    scanf("%s", s2);

    printf("%d\n", LCS_len(s1, s2));
    find_LCS(s1, s2);
    for(int i=strlen(result) - 1; i >= 0; i--)
        printf("%c", result[i]);
}