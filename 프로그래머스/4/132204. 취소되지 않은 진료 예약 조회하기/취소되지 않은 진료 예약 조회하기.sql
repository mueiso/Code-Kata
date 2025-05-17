SELECT DISTINCT APNT_NO,
                PT_NAME,
                p.PT_NO,
                a.MCDP_CD,
                DR_NAME,
                APNT_YMD
FROM PATIENT p
    JOIN APPOINTMENT a ON p.PT_NO = a.PT_NO
    JOIN DOCTOR d ON a.MDDR_ID = d.DR_ID
WHERE  a.MCDP_CD = 'CS'
    AND APNT_CNCL_YN = 'N'
    AND date_format(APNT_YMD, '%Y-%m-%d') = '2022-04-13'
ORDER BY APNT_YMD;