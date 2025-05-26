SELECT HISTORY_ID,
       ROUND(DATE_DIFF * (1 - D_RATE / 100) * DAILY_FEE) AS FEE
FROM (
      SELECT HISTORY_ID,
             c.CAR_TYPE,
             DATEDIFF(END_DATE, START_DATE) + 1 AS DATE_DIFF,
             DURATION_TYPE,
             DAILY_FEE,
             IF (DISCOUNT_RATE IS NULL, 0, DISCOUNT_RATE) AS D_RATE
      FROM (
            SELECT *,
            CASE WHEN DATEDIFF(END_DATE,START_DATE) + 1 >= 90 THEN '90일 이상'
                        WHEN DATEDIFF(END_DATE,START_DATE) + 1 >= 30 THEN '30일 이상'
                        WHEN DATEDIFF(END_DATE,START_DATE) + 1 >= 7 THEN '7일 이상'
                        END AS DURATION
            FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY) h
                        LEFT JOIN CAR_RENTAL_COMPANY_CAR c ON h.CAR_ID = c.CAR_ID
                        LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN d ON c.CAR_TYPE = d.CAR_TYPE
                        AND h.DURATION = d.DURATION_TYPE
                        WHERE c.CAR_TYPE = '트럭'
    ) a
ORDER BY 2 DESC, 1 DESC