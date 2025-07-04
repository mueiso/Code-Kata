SELECT COUNT(*) COUNT
FROM ECOLI_DATA
WHERE TRUNCATE(MOD(CONV(GENOTYPE, 10, 2), 100) / 10, 0) = 0
   AND (TRUNCATE(MOD(CONV(GENOTYPE, 10, 2), 1000) / 100, 0) = 1
    OR MOD(CONV(GENOTYPE, 10, 2), 10) = 1);