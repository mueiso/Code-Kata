#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(int n, int k) {
    int service = n / 10;                 // 서비스 음료수 개수
    int real_k = k - service;             // 실제 지불해야 하는 음료수 개수
    int answer = (n * 12000) + (real_k * 2000);
    return answer;
}
