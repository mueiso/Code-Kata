WITH RECURSIVE CTE AS (
    SELECT DISTINCT
        a.PARENT_ID, a.ID, 1 AS GENERATION
    FROM
        ECOLI_DATA a LEFT JOIN ECOLI_DATA b
        ON a.ID = b.PARENT_ID
    WHERE
        a.PARENT_ID IS NULL  

    UNION ALL

    SELECT
        c.ID AS PARENT_ID,
        d.ID AS ID,
        GENERATION + 1
    FROM
        CTE c 
        INNER JOIN ECOLI_DATA d
        ON c.ID = d.PARENT_ID),

        CTE2 AS (
        SELECT DISTINCT
            c1.ID AS WITH_CHILD_ID
        FROM
            CTE c1 INNER JOIN CTE c2 ON c1.ID = c2.PARENT_ID),

        CTE3 AS (
        SELECT 
            PARENT_ID, ID, GENERATION,
            CASE
                WHEN ID IN (SELECT WITH_CHILD_ID FROM CTE2) THEN 'WITH_CHILD'
                ELSE 'NONE_CHILD'
            END AS CHILD
        FROM CTE)

SELECT  COUNT(*) AS COUNT, GENERATION
FROM CTE3
WHERE CHILD = 'NONE_CHILD'
GROUP BY GENERATION, CHILD;