SELECT ao.ANIMAL_ID,
       ao.ANIMAL_TYPE,
       ao.NAME
FROM ANIMAL_INS AS ai JOIN ANIMAL_OUTS AS ao ON ai.ANIMAL_ID = ao.ANIMAL_ID
WHERE SEX_UPON_INTAKE LIKE '%Intact%' AND (SEX_UPON_OUTCOME LIKE '%Spayed%' OR SEX_UPON_OUTCOME LIKE '%Neutered%')
ORDER BY ao.ANIMAL_ID;