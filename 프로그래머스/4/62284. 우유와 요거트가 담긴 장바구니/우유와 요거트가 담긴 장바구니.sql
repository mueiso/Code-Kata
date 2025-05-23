SELECT DISTINCT CART_ID
FROM (
    SELECT *
    FROM CART_PRODUCTS
    GROUP BY NAME, CART_ID
    HAVING NAME IN ('Yogurt', 'Milk')
     ) a
GROUP BY CART_ID
HAVING COUNT(CART_ID) > 1
ORDER BY CART_ID;