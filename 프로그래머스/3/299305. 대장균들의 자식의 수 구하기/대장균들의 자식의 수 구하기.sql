SELECT ED.ID, IFNULL(TMP.CHILD_COUNT,0) AS CHILD_COUNT
FROM ECOLI_DATA ED LEFT JOIN
    (SELECT PARENT_ID,
     COUNT(*) AS CHILD_COUNT
     FROM ECOLI_DATA
     GROUP BY PARENT_ID) AS TMP
ON ED.ID = TMP.PARENT_ID
ORDER BY ED.ID ASC;