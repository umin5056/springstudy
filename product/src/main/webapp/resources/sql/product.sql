		SELECT DISTINCT E.FIRST_NAME
          FROM DEPARTMENTS D RIGHT OUTER JOIN EMPLOYEES E
            ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
         WHERE E.FIRST_NAME LIKE CONCAT('A', '%');