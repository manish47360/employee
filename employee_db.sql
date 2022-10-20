create database employee_db;
use employee_db;

create table employees(
   id bigint Primary key auto_increment,
   name varchar(200),
   address varchar(255),
   salary double
);

select * from employees;