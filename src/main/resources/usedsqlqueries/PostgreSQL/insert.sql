INSERT INTO USER_PROFILE (type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE (type)
VALUES ('CUSTOMER');

INSERT INTO APP_USER (sso_id, password, first_name, last_name, email)
VALUES ('admin', 'admin', 'Vova', 'Ternovyi', 'vovaternovy@gmail.com');

/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT
    u.id,
    p.id
  FROM app_user u, user_profile p
  WHERE u.sso_id = 'admin' AND p.type = 'ADMIN';

INSERT INTO ITEMS (id, item_Name, price, stock, description)
    VALUES (1, 'Some item', 10, 5, 'This is a test item');

INSERT INTO ITEMS (id, item_Name, price, stock, description)
    VALUES (2, 'Apple', 5, 100, 'This is an apple');

INSERT INTO ITEMS (id, item_Name, price, stock, description)
    VALUES (3, 'Orange', 5, 100, 'This is an orange');

INSERT INTO ITEMS (id, item_Name, price, stock, description)
    VALUES (4, 'Banana', 5, 5, 'This is a banana');

INSERT INTO ITEMS (id, item_Name, price, stock, description)
    VALUES (5, 'Raspberry', 10, 5, 'This is a raspberry');

INSERT INTO ITEMS (id, item_Name, price, stock, description)
    VALUES (6, 'Blackberry', 10, 5, 'This is a blackberry');