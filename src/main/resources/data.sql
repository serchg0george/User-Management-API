TRUNCATE t_addresses CASCADE;
TRUNCATE t_mails CASCADE;
TRUNCATE t_people CASCADE;

INSERT INTO t_addresses VALUES (1, '123 Main Street, Apt 4B, Springfield, USA', 'HOME');
INSERT INTO t_addresses VALUES (2, '456 Elm Avenue, Suite 201, Oakville, Canada', 'WORK');
INSERT INTO t_addresses VALUES (3, '789 Oak Lane, Unit 10, Manchester, UK', 'STUDY');
INSERT INTO t_addresses VALUES (4, '321 Pine Road, Flat 7, Melbourne, Australia', 'HOME');
INSERT INTO t_addresses VALUES (5, '567 Maple Street, Floor 3, Berlin, Germany', 'WORK');
INSERT INTO t_addresses VALUES (6, '890 Birch Avenue, Apartment 12C, Paris, France', 'STUDY');

INSERT INTO t_people VALUES (1, 'Emily Anderson', '983-745-68', 1);
INSERT INTO t_people VALUES (2, 'Benjamin Martinez', '521-307-45', 2);
INSERT INTO t_people VALUES (3, 'Sophia Johnson', '840-213-59', 3);
INSERT INTO t_people VALUES (4, 'Liam Thompson', '124-568-90', 4);
INSERT INTO t_people VALUES (5, 'Olivia Ramirez', '643-210-87', 5);
INSERT INTO t_people VALUES (6, 'Ethan Davis', '986-542-10', 6);

INSERT INTO t_mails VALUES (1, 'john.doe@work.com','WORK',1);
INSERT INTO t_mails VALUES (2, 'john.doe@uni.com','UNI',1);
INSERT INTO t_mails VALUES (3, 'sarah.smith@work.com','WORK',2);
INSERT INTO t_mails VALUES (4, 'sarah.smith@uni.com','UNI',2);
INSERT INTO t_mails VALUES (5, 'robert.wilson@uni.com','WORK',3);
INSERT INTO t_mails VALUES (6, 'robert.wilson@work.com','UNI',3);
INSERT INTO t_mails VALUES (7, 'emma.jones@uni.com','UNI',4);
INSERT INTO t_mails VALUES (8, 'emma.jones@work.com','WORK',4);
INSERT INTO t_mails VALUES (9, 'amy.miller@persn.com','PERSN',5);
INSERT INTO t_mails VALUES (10, 'amy.miller@work.com','WORK',5);
INSERT INTO t_mails VALUES (11, 'david.brown@persn.com','PERSN',6);
INSERT INTO t_mails VALUES (12, 'david.brown@uni.com','UNI',6);