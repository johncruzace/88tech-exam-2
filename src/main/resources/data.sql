
delete from account_details;
delete from bank_user;

--INSERT INTO account_details (id, created_date, account_id, balance)
--VALUES (11001, NOW(), 12001, 10000);

INSERT INTO bank_user
(id, active, password, role, user_name, account_name)
VALUES (10001, true, 'password', 'TELLER', 'teller', 'Teller Name');

INSERT INTO bank_user
(id, active,  password, role, user_name, account_name)
VALUES (10002, true,  'password', 'ACCOUNT', 'user1', 'Account Holder 1');

INSERT INTO bank_user
(id, active, password, role, user_name, account_name)
VALUES (10003, true,  'password', 'ACCOUNT', 'user2', 'Account Holder 2');

