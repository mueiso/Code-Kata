SELECT ii.ITEM_ID,
       ITEM_NAME
FROM ITEM_INFO ii
JOIN ITEM_TREE it ON ii.ITEM_ID = it.ITEM_ID
WHERE PARENT_ITEM_ID IS NULL
ORDER BY ii.ITEM_ID;
