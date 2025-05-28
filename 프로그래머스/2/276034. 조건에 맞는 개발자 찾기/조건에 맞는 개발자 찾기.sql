SELECT id,
       email,
       first_name,
       last_name
FROM DEVELOPERS dev
WHERE dev.skill_code & 
        (
            SELECT BIT_OR(code)
            FROM SKILLCODES
            WHERE name IN ('Python', 'C#')
        ) > 0
ORDER BY id ASC;