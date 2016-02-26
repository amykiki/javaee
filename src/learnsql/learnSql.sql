INSERT INTO customer_tbl VALUE
  ('615', 'FRED WOLF', '109 MEMORY LANE', 'PLAINFIELD', 'IN', 46113, '3175555555',
   NULL);
INSERT INTO customer_tbl VALUE
  ('559', 'RITA THOMPSON', '125 PEASE TREE', 'INDIANAPOLIS', 'IN', 46248,
   '3171111111',
   NULL);
INSERT INTO customer_tbl VALUE
  ('715', 'BOB DIGGLER', '1102 HUNTINGTONST', 'SHELBY', 'IN', 41234, '3172222222',
   NULL);
SAVEPOINT INSERT1;
RELEASE SAVEPOINT INSERT1;

UPDATE customer_tbl
SET CUST_NAME = 'RITA THOMPSON'
WHERE CUST_ID = '559';
UPDATE customer_tbl
SET CUST_ADDRESS = 'APT C 4556 WATERWAY'
WHERE CUST_ID = '615';
UPDATE customer_tbl
SET CUST_CITY = 'CHICAGO'
WHERE CUST_ID = '715';
ROLLBACK TO INSERT1;
COMMIT;
SELECT *
FROM customer_tbl;

DELETE FROM customer_tbl
WHERE CUST_ID = '615';
DELETE FROM customer_tbl
WHERE CUST_ID = '559';
DELETE FROM customer_tbl
WHERE CUST_ID = '715';

UPDATE products_tbl
SET COST = 1.45
WHERE PROD_ID = '9';
COMMIT;
SELECT
  PROD_DESC,
  COST,
  PROD_ID
FROM products_tbl
ORDER BY COST DESC, PROD_DESC ASC;

SELECT
  EMP_ID,
  LAST_NAME
FROM employee_tbl
ORDER BY EMP_ID, LAST_NAME DESC;
SELECT count(*)
FROM employee_tbl;
SELECT COUNT(DISTINCT CUST_CITY)
FROM customer_tbl;
SELECT
  CUST_CITY,
  count(*)
FROM customer_tbl
GROUP BY CUST_CITY;
SELECT count(*)
FROM customer_tbl
WHERE CUST_CITY = 'INDIANAPOLIS';

SELECT
  PROD_DESC,
  COST
FROM products_tbl
WHERE COST BETWEEN 1.1 AND 5.95;

SELECT
  CUST_ID,
  CUST_NAME,
  CUST_CITY,
  CUST_ZIP
FROM customer_tbl
WHERE CUST_ID <= '43';

SELECT
  EMP_ID,
  LAST_NAME,
  FIRST_NAME,
  PAGER
FROM employee_tbl
WHERE PAGER IS NOT NULL;
SELECT
  EMP_ID,
  LAST_NAME,
  FIRST_NAME,
  PAGER
FROM employee_tbl;

SELECT
  ORD_NUM,
  CUST_ID,
  ORDER_DATE
FROM orders_tbl
WHERE ORDER_DATE IN ('2009-10-17', '2009-10-15');

SELECT
  CUST_ID,
  CUST_NAME
FROM customer_tbl
WHERE CUST_NAME LIKE '_A%';
SELECT
  CUST_ID,
  CUST_NAME
FROM customer_tbl
WHERE CUST_NAME LIKE '_A%S';

SELECT *
FROM products_tbl;
SELECT *
FROM products_tbl
WHERE COST > ALL (SELECT COST
                  FROM products_tbl
                  WHERE PROD_ID > '222');

INSERT INTO products_tbl VALUES ('1234', 'KEY CHAIN', 5.95);

SELECT count(*)
FROM employee_pay_tbl;
SELECT count(SALARY)
FROM employee_pay_tbl;

SELECT *
FROM orders_tbl;
SELECT count(PROD_ID)
FROM orders_tbl;
SELECT count(DISTINCT PROD_ID)
FROM orders_tbl;

INSERT INTO orders_tbl VALUES ('90C461', '560', '1234', 2, '2010-1-12');

SELECT SUM(SALARY)
FROM employee_pay_tbl;
SELECT AVG(SALARY)
FROM employee_pay_tbl;
SELECT AVG(DISTINCT SALARY)
FROM employee_pay_tbl;
SELECT SUM(COST)
FROM products_tbl;

SELECT
  AVG(PAY_RATE),
  AVG(SALARY)
FROM employee_pay_tbl;

SELECT MAX(PROD_DESC)
FROM products_tbl;

SELECT
  COUNT(ORD_NUM),
  SUM(QTY),
  SUM(QTY) / COUNT(ORD_NUM) AVG_QTY
FROM orders_tbl;
SELECT
  COUNT(EMP_ID),
  SALARY
FROM employee_pay_tbl
GROUP BY SALARY;
UPDATE employee_pay_tbl
SET BONUS = '500'
WHERE EMP_ID = '443679012';
SELECT
  MIN(BONUS),
  MAX(SALARY)
FROM employee_pay_tbl
WHERE SALARY > 20000;

SELECT AVG(PAGER)
FROM employee_tbl;

# CHAPTER9 -- EXERSIZE
SELECT AVG(SALARY)
FROM employee_pay_tbl;
SELECT MAX(BONUS)
FROM employee_pay_tbl;
SELECT SUM(SALARY)
FROM employee_pay_tbl;
SELECT MIN(PAY_RATE)
FROM employee_pay_tbl;
SELECT COUNT(*)
FROM employee_pay_tbl;
SELECT COUNT(*)
FROM employee_tbl
WHERE LAST_NAME LIKE 'G%';
SELECT SUM(COST * orders_tbl.QTY)
FROM products_tbl, orders_tbl
WHERE orders_tbl.PROD_ID = products_tbl.PROD_ID;

SELECT
  FIRST_NAME,
  MIDDLE_NAME,
  LAST_NAME
FROM employee_tbl
ORDER BY LAST_NAME, FIRST_NAME;

# CHAPTER 10
SELECT
  EMP_ID,
  CITY
FROM employee_tbl
GROUP BY CITY, EMP_ID;

SELECT
  EMP_ID,
  SUM(SALARY)
FROM employee_pay_tbl
GROUP BY SALARY;
SELECT SUM(SALARY)
FROM employee_pay_tbl
GROUP BY SALARY;

SELECT
  CITY,
  COUNT(*)
FROM employee_tbl
GROUP BY CITY;

SELECT
  YEAR(DATE_HIRE) AS YEAR_HIRED,
  SUM(SALARY)
FROM employee_pay_tbl
GROUP BY 1;
SELECT
  YEAR(DATE_HIRE) AS YEAR_HIRED,
  SUM(SALARY)
FROM employee_pay_tbl
GROUP BY 1
ORDER BY 2;

SELECT
  CITY,
  ZIP,
  AVG(PAY_RATE),
  AVG(SALARY)
FROM employee_tbl E INNER JOIN employee_pay_tbl P ON E.EMP_ID = P.EMP_ID
GROUP BY CITY, ZIP
ORDER BY CITY, ZIP;

SELECT
  CITY,
  ZIP,
  AVG(PAY_RATE),
  AVG(SALARY)
FROM employee_tbl E
  INNER JOIN employee_pay_tbl P
    ON E.EMP_ID = P.EMP_ID
GROUP BY CITY, ZIP WITH ROLLUP;

SELECT
  CITY,
  AVG(PAY_RATE),
  AVG(SALARY)
FROM employee_tbl e
  INNER JOIN employee_pay_tbl P
    ON E.EMP_ID = P.EMP_ID
GROUP BY CITY;

SELECT
  CITY,
  AVG(PAY_RATE),
  AVG(SALARY)
FROM employee_tbl E
  INNER JOIN employee_pay_tbl P
    ON E.EMP_ID = P.EMP_ID
GROUP BY CITY
HAVING AVG(SALARY) > 20000;

SELECT
  EMP_ID,
  MAX(SALARY)
FROM employee_pay_tbl
GROUP BY SALARY, EMP_ID;

SELECT
  CITY,
  COUNT(*)
FROM employee_tbl
GROUP BY CITY
HAVING COUNT(*) > 1;

SELECT
  POSITION,
  AVG(SALARY)
FROM employee_pay_tbl
GROUP BY POSITION
HAVING AVG(SALARY) > 20000;

# CHAPTER 11

SELECT CONCAT(LAST_NAME, ', ', FIRST_NAME)
FROM employee_tbl;

SELECT
  CITY,
  REPLACE(CITY, 'I', 'M')
FROM employee_tbl;
SELECT LOWER(CITY)
FROM employee_tbl;

SELECT
  EMP_ID,
  SUBSTR(EMP_ID, 4, 3)
FROM employee_tbl;

SELECT
  PROD_DESC,
  INSTR(PROD_DESC, 'PLASTIC')
FROM products_tbl;
SELECT INSTR(PROD_DESC, 'PLASTIC')
FROM products_tbl;

SELECT LTRIM(POSITION)
FROM employee_pay_tbl;

SELECT
  PROD_DESC,
  LENGTH(PROD_DESC)
FROM products_tbl;
SELECT
  PAGER,
  IFNULL(PAGER, 'NO PAGER')
FROM employee_tbl;

SELECT LPAD(PROD_DESC, 30, '.') PRODUCT
FROM products_tbl;
SELECT RPAD(PROD_DESC, 30, '.') PRODUCT
FROM products_tbl;
SELECT *
FROM products_tbl;

SELECT
  CONCAT(LAST_NAME, ', ', FIRST_NAME) NAME,
  CONCAT(SUBSTR(EMP_ID, 1, 3), '-', SUBSTR(EMP_ID, 4, 2), '-',
         SUBSTR(EMP_ID, 6, 4))        ID
FROM employee_tbl;

SELECT CONCAT(FIRST_NAME, '.', LAST_NAME, '@PERPTECH.COM') EMAIL
FROM employee_tbl;

SELECT
  CONCAT(LAST_NAME, ', ', FIRST_NAME) NAME,
  CONCAT(SUBSTR(EMP_ID, 1, 3), '-', SUBSTR(EMP_ID, 4, 2), '-',
         SUBSTR(EMP_ID, 6, 4))        ID,
  CONCAT('(', SUBSTR(PHONE, 1, 3), ')', SUBSTR(PHONE, 4, 2), '-',
         SUBSTR(PHONE, 6, 4))         PHONE
FROM employee_tbl;

# CHAPTER 12
SELECT NOW();
SELECT DAYNAME(NOW());
SELECT DAYOFMONTH(NOW());
SELECT DAYOFWEEK(NOW());
SELECT DAYOFYEAR(NOW());

UPDATE employee_pay_tbl
SET DATE_HIRE = '1999-05-30'
WHERE EMP_ID LIKE '4436%';
UPDATE employee_pay_tbl
SET DATE_HIRE = '2001-02-28'
WHERE EMP_ID LIKE '4423%';
SELECT
  EMP_ID,
  DATE_HIRE,
  DATE_ADD(DATE_HIRE, INTERVAL 1 DAY)
FROM employee_pay_tbl;
SELECT
  DATE_HIRE,
  DATE_FORMAT(DATE_HIRE, '%D %M %Y')
FROM employee_pay_tbl;
SELECT
  DATE_HIRE,
  DATE_FORMAT(DATE_HIRE, '%d %b %y')
FROM employee_pay_tbl;

SELECT STR_TO_DATE('01/01/2016 01:30:59 PM', '%m/%d/%Y %h:%i:%s %p') AS FORMAT_DATE
FROM employee_pay_tbl;

SELECT
  EMP_ID,
  DATE_HIRE,
  EXTRACT(YEAR FROM DATE_HIRE)
FROM employee_pay_tbl;
SELECT
  EMP_ID,
  DATE_HIRE,
  EXTRACT(MONTH FROM DATE_HIRE)
FROM employee_pay_tbl;

SELECT
  EMP_ID,
  DATE_HIRE,
  YEAR(NOW()) - YEAR(DATE_HIRE)
FROM employee_pay_tbl;
SELECT
  EMP_ID,
  DATE_HIRE,
  WEEKDAY(DATE_HIRE)
FROM employee_pay_tbl;
SELECT
  EMP_ID,
  DATE_HIRE,
  DAYNAME(DATE_HIRE),
  DAYOFWEEK(DATE_HIRE),
  WEEKDAY(DATE_HIRE)
FROM employee_pay_tbl;

SELECT CURRENT_DATE;
SELECT CURRENT_TIME;
SELECT NOW();

# CHAPTER 13
SELECT
  E.EMP_ID,
  CONCAT(E.FIRST_NAME, ' ', E.FIRST_NAME),
  EP.POSITION,
  EP.SALARY
FROM employee_tbl E INNER JOIN employee_pay_tbl EP
    ON E.EMP_ID = EP.EMP_ID AND EP.SALARY IS NOT NULL;
SELECT
  E.EMP_ID,
  CONCAT(E.FIRST_NAME, ' ', E.FIRST_NAME),
  EP.POSITION,
  EP.SALARY
FROM employee_tbl E INNER JOIN employee_pay_tbl EP ON E.EMP_ID = EP.EMP_ID;

SELECT
  P.PROD_DESC,
  O.QTY
FROM products_tbl P JOIN orders_tbl O ON P.PROD_ID = O.PROD_ID;
SELECT
  P.PROD_DESC,
  O.QTY
FROM products_tbl P LEFT JOIN orders_tbl O ON P.PROD_ID = O.PROD_ID;
SELECT
  P.PROD_DESC,
  O.QTY
FROM products_tbl P RIGHT JOIN orders_tbl O ON P.PROD_ID = O.PROD_ID;
SELECT
  P.PROD_DESC,
  O.QTY
FROM products_tbl P LEFT JOIN orders_tbl O ON P.PROD_ID = O.PROD_ID
UNION
SELECT
  P.PROD_DESC,
  O.QTY
FROM products_tbl P RIGHT JOIN orders_tbl O ON P.PROD_ID = O.PROD_ID;

SELECT
  P.PROD_DESC,
  O.QTY
FROM products_tbl P LEFT JOIN orders_tbl O ON P.PROD_ID = O.PROD_ID
UNION ALL
SELECT
  P.PROD_DESC,
  O.QTY
FROM products_tbl P RIGHT JOIN orders_tbl O ON P.PROD_ID = O.PROD_ID;

INSERT INTO orders_tbl VALUES ('9898EA', '9999', '119', 5, '2012-02-21');

SELECT
  P.PROD_DESC,
  O.QTY,
  C.CUST_ID
FROM products_tbl P
  RIGHT JOIN orders_tbl O
    ON P.PROD_ID = O.PROD_ID
  LEFT JOIN customer_tbl C
    ON O.CUST_ID = C.CUST_ID;

SELECT *
FROM employee_tbl;
SELECT E1.EMP_ID, E1.FIRST_NAME, E1.LAST_NAME, E2.LAST_NAME
FROM employee_tbl E1
  INNER JOIN employee_tbl E2 ON E1.LAST_NAME = E2.LAST_NAME
WHERE E1.FIRST_NAME <> E2.FIRST_NAME;

SELECT E1.EMP_ID, E1.FIRST_NAME, E1.LAST_NAME, E2.LAST_NAME
FROM employee_tbl E1, employee_tbl E2 WHERE E1.LAST_NAME = E2.LAST_NAME;

SELECT DISTINCT  C1.CUST_ID, C1.CUST_NAME, C1.CUST_CITY
FROM customer_tbl C1
INNER JOIN customer_tbl C2
ON C1.CUST_CITY = C2.CUST_CITY
  WHERE C2.CUST_CITY = 'INDIANAPOLIS';

SELECT E.EMP_ID, CONCAT(E.FIRST_NAME, ' ', E.LAST_NAME) NAME, E.CITY, E2.SALARY, E2.BONUS
FROM employee_tbl E
LEFT JOIN employee_pay_tbl E2
ON E.EMP_ID = E2.EMP_ID;

SELECT E.CITY, AVG(E2.SALARY)
FROM employee_tbl E
  LEFT JOIN employee_pay_tbl E2
    ON E.EMP_ID = E2.EMP_ID
GROUP BY E.CITY;

SELECT E.CITY, E2.SALARY
FROM employee_tbl E
  LEFT JOIN employee_pay_tbl E2
    ON E.EMP_ID = E2.EMP_ID;