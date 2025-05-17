SELECT p.product_code,
       SUM(p.price * os.sales_amount) AS SALES
FROM PRODUCT p LEFT JOIN OFFLINE_SALE os ON p.product_id = os.product_id
GROUP BY p.product_code
ORDER BY SALES DESC,
         p.product_code