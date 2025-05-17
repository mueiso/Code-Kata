/*
* PRICE / 10000 : 가격을 10,000으로 나누면 몇 만원대인지 계산 기능
  예) PRICE = 25,000 → 25000 / 10000 = 2.5

* FLOOR(2.5) : 소수점을 버리니까 2로 변환 (25,000원은 2만 원대에 속한다는 의미)
  2 * 10000: 다시 10,000을 곱해서 구간의 최소값인 20000을 만듦
*/

SELECT floor(PRICE/10000) * 10000 as PRICE_GROUP,
       COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP;