SELECT CONCAT (FLOOR((MONTH(DIFFERENTIATION_DATE)-1)/3 + 1), 'Q') AS QUARTER,
       COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER
ORDER BY QUARTER