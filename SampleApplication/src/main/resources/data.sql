insert into TBL_USER(user_id,username,password) values(1,'Sasikala','$2a$10$IzktJJhD.IYvnEnGDCxIveEbpVEZI6vk.VBUjaksvPMDbcJOYkVpi');
insert into TBL_USER(user_id,username,password) values(2,'Satheesh','$2a$10$bf5H25GbjkIX8T2s1bmUtuuyF3T42W7qp2pMjqcWch91BjQuam/3e');
insert into TBL_TODO(todo_id,activity_name,assigned_staff) values (1,'Drawing','Sasikala');
insert into TBL_TODO(todo_id,activity_name,assigned_staff) values (2,'Skating','Sathish');
insert into TBL_TODO(todo_id,activity_name,assigned_staff) values (3,'Painting','Adhya');
insert into TBL_TODO(todo_id,activity_name,assigned_staff) values (4,'Running','Vishalini');
insert into TBL_TODO(todo_id,activity_name,assigned_staff) values (5,'Speech','Gokul');
insert into TBL_TODO(todo_id,activity_name,assigned_staff) values (6,'Drama','Ram');
insert into TBL_TODO(todo_id,activity_name,assigned_staff) values (7,'Games','Vishnu');
insert into TBL_ROLE(role_id,role_name) values(2,'ROLE_STUDENT');
insert into TBL_ROLE(role_id,role_name) values(1,'ROLE_ADMIN');
insert into TBL_USER_ROLES(user_id,role_id) values (1,1),(2,2);