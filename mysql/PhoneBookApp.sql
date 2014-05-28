drop database 
if exists phonebookapp;
create database phonebookapp;

use phonebookapp;

create table contact(
contact_id int not null primary key auto_increment,
firstname varchar(200) not null,
lastname varchar(200) not null,
birthdate date not null,
street varchar(200),
number int,
city varchar(200),
country varchar(200)
);

create table contact_details(
contact_detail_id int not null primary key auto_increment,
contact_id int,
prefix varchar(10),
phone_number varchar(20),
network_name varchar(100),
foreign key Contact_Details_fk_Contact (contact_id) references contact(contact_id) on delete cascade
);

insert into contact
	(firstname,lastname,birthdate,street,number,city,country)
	values ('Maria','Igescu','2000-04-03','Viitorului',23,'Brasov','RO');
