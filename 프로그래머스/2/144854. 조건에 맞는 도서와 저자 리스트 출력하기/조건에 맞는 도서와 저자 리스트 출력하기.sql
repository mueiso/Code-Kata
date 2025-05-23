SELECT bo.BOOK_ID,
       au.AUTHOR_NAME,
       DATE_FORMAT(bo.PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM BOOK AS bo JOIN AUTHOR AS au ON bo.AUTHOR_ID = au.AUTHOR_ID
WHERE bo.CATEGORY = '경제'
ORDER BY bo.PUBLISHED_DATE;