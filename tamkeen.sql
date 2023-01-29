/*
constraint pk_tableName 
constraint mainTable_referencesTable_FK
date syntax: 'yyyy-mm-dd'
*/

/* create database*/
create database insurance;

use insurance;

/* Create table of Client*/
create table client(
ssn int,
first_name varchar(30),
second_name varchar(20),
third_name varchar(20),
fourth_name varchar(20),
phone_1 int,
phone_2 int,
dob date,
ssn_image blob,
driving_license blob,
constraint pk_client primary key(ssn)
);

/* add some data*/
insert into client (ssn,first_name, second_name, third_name, fourth_name,dob,phone_1,phone_2) values
(101234567,'Nora', 'Liam', 'Ethan', 'Olivia','1979-08-11',0590123456,0560123457),
(551818404,'Madison', 'Matthew', 'Joseph', 'Madison','1993-10-07',0592624440,null),
(238456790,'Isabella', 'Benjamin', 'Alexander', 'Sophia','1980-06-24',0598765432,0568765431),
(998686604,'Emily', 'Samuel', 'Ethan', 'Avery','1992-11-22',0592624442,null),
(404818155,'Charlotte', 'Noah', 'Benjamin', 'Natalie','1991-01-16',0592624448,null),
(907654832,'Avery', 'William', 'Michael', 'Abigail','1981-05-07',0593456789,0560456789),
(456789012,'Madison', 'Jacob', 'Daniel', 'Elizabeth','1982-03-20',0598654321,0567654389),
(482956575,'Charlotte', 'Noah', 'Benjamin', 'Isabella','1999-12-19',0562345671,null),
(901234567,'Harper', 'Elijah', 'Daniel', 'Olivia','1998-02-14',0593456772,null),
(765432109,'Olivia', 'Liam', 'Ethan', 'Sophia','1997-03-29',0563456773,null),
(678901235,'Harper', 'Matthew', 'Joseph', 'Mia','1983-02-02',0591234567,0561234568),
(531209876,'Emily', 'Samuel', 'Ethan', 'Avery','1984-12-16',0592345678,0562345679),
(532109876,'Charlotte', 'Noah', 'Benjamin', 'Natalie','1985-10-30',0593456780,0563456781),
(423098765,'Harper', 'Elijah', 'Daniel', 'Isabella','1990-03-05',0599757665,null),
(789012345,'Harper', 'Elijah', 'Daniel', 'Isabella','1986-09-12',0594567890,0564567891),
(123456789,'Sophia', 'Benjamin', 'Alexander', 'Abigail','1996-05-17',0594567874,null),
(543210987,'Olivia', 'Liam', 'Ethan', 'Madison','1987-07-26',0590123450,0560123451),
(890123456,'Sophia', 'Benjamin', 'Alexander', 'Avery','1988-06-09',0598765430,0568765439),
(406686899,'Abigail', 'William', 'Michael', 'Elizabeth','1995-07-03',0564567875,null),
(345678901,'Elizabeth', 'Jacob', 'Daniel', 'Harper','1994-08-20',0569757661,null),
(103456789,'Abigail', 'William', 'Michael', 'Harper','2003-06-25',0593456788,0560456787),
(987654301,'Elizabeth', 'Jacob', 'Daniel', 'Mia','2002-08-12',0598654320,0567654388),
(234567890,'Madison', 'Matthew', 'Joseph', 'Emily','2001-09-21',0591234560,0561234569),
(575659284,'Emily', 'Samuel', 'Ethan', 'Natalie','2000-11-07',0592345670,null),
(567890234,'Olivia', 'Liam', 'Ethan', 'Sophia','1989-04-22',059531424,null);


/* Create table of car*/
create table car(
car_id varchar(255) not null,
client_id int not null,
model text,
engin_size int,
color varchar(20),
car_license blob,
license_end  date,
model_date year,
price double,
constraint  pk_car primary key (car_id),
constraint car_client_FK foreign key(client_id) references client(ssn) on delete CASCADE ON update CASCADE
);

/*add some data to car client*/
insert into car(car_id,client_id,model,engin_size,color,model_date,price,license_end) values
('C89B364A701D52',101234567,'Ford Mustang',2300,'black','2017',130000,'2024-01-15'),
('E8D109473G62F5',551818404,'Volkswagen Jetta',1400,'red','2015',110000,'2023-12-27'),
('H4505916G83I7J',551818404,'Chevrolet Impala',2500,'white','2018',120000,'2023-8-03'),
('T43JM672LK8059',238456790,'Kia Soul',1600,'Gray','2019',90000,'2024-01-29'),
('CO562943781MNP',238456790,'Hyundai Sonata',2400,'Blue','2023',115000,'2024-01-02'),
('V28SR6QT109357',238456790,'Toyota Corolla',1800,'black','2016',100000,'2023-04-30'),
('U015897W246V3X',998686604,'Honda Civic',2000,'white','2001',105000,'2023-06-01'),
('B7Y58A103694Z2',404818155,'Subaru Outback',2500,'Gold','2002',120000,'2023-06-15'),
('M95403EDC0F728',404818155,'Nissan Altima',2500,'black','2004',135000,'2023-05-15'),
('N5F9HI0418736G',907654832,'Ram',1500,'green','2013',170000,'2024-02-02'),
('J14836L5270MK9',456789012,'Jeep Cherokee',2400,'black','2020',140000,'2024-01-26'),
('CN7009O4P315M6',456789012,'Ford Edge',2000,'Blue','2010',160000,'2023-08-03'),
('Z12R89643QT05S',456789012,'Nissan Rogue',2500,'black','2012',120000,'2023-05-28'),
('J2463V7W91X85U',482956575,'Jeep Wrangler',3600,'red','2015',100000,'2023-07-29'),
('G65182B09Z74AY',482956575,'Ford Explorer',1300,'sliver','2008',90000,'2023-03-23'),
('A618D95B7C43A0',901234567,'Ford Fusion',1200,'sliver','2006',45000,'2024-02-02'),
('W379D6GEF20541',765432109,'Volkswagen Golf',1400,'black','2001',35000,'2023-07-15'),
('P38925GH704J6I',765432109,'Nissan Kicks',1600,'red','2011',75000,'2024-01-29'),
('JK9M24675810L3',678901235,'Nissan Sentra',1200,'white','2004',50000,'2023-01-19'),
('O5MP16043978N2',531209876,'Jeep Compass',2400,'balck','2022',150000,'2024-01-25'),
('K659T2R8SQ1730',532109876,'Honda CR-V',1500,'blue','2006',130000,'2023-09-11'),
('B842U3V0W7516X',532109876,'Ford Escape',1500,'white','2013',120000,'2024-01-28'),
('E2095YB76Z8A14',532109876,'Jeep Gladiator',3600,'red','2019',180000,'2023-07-30'),
('FD9523617804CE',423098765,'Toyota 4Runner',4000,'Brown','2017',120000,'2023-08-01'),
('R58264G0I391FH',789012345,'Nissan Titan',2500,'black','2015',115000,'2023-10-04'),
('ABC123456789',123456789,'Ford F-150',1450,'white','2010',70000,'2023-11-18'),
('DEF098765432',543210987,'Nissan Titan',2500,'red','2012',190000,'2024-01-15'),
('GHI246801357',543210987,'Nissan Kicks',1600,'Purple','2014',115000,'2023-08-06'),
('JKL369258147',890123456,'Nissan Titan',1350,'black','2000',190000,'2023-02-15'),
('MNO547896123',406686899,'Jeep Gladiator',2500,'red','2008',135000,'2023-09-29'),
('UVW741852963',345678901,'Ford Mustang',2300,'black','2021',150000,'2023-09-15'),
('YZA852963014',345678901,'Jeep Wrangler',3600,'red','2003',45000,'2023-07-25'),
('CDE908273645',103456789,'Nissan Kicks',1600,'red','2016',85000,'2024-01-18'),
('FGH283746519',987654301,'Subaru Outback',2500,'Gold','2002',50000,'2023-06-27'),
('JKL746283015',234567890,'Volkswagen Jetta',1400,'red','2020',180000,'2024-01-27'),
('MNO903174856',575659284,'Ford Fusion',1200,'sliver','2005',24000,'2024-01-01'),
('QRS675823490',567890234,'Nissan Rogue',2500,'black','2018',195000,'2023-07-22');


/*Create table of insurance */
/*ins_id : last 5 number of client_id and first 5 number of car_id*/
/*end date of inurance is the end date of car_license*/
/*insutance_type: A shamel, B not shmael*/
create table insurance(
ins_id varchar(10),
start_date date,
end_date date,
insutance_type  varchar(30),
car_id varchar(255) not null,
client_id int not null,
price decimal,
constraint pk_insurance primary key(ins_id),
constraint car_insurance_FK foreign key(car_id) references car(car_id) on delete CASCADE ON update CASCADE,
constraint client_insurance_FK foreign key(client_id) references client(ssn) on delete CASCADE ON update CASCADE
);


/*add some data to insurance*/
insert into insurance(ins_id,start_date,end_date,insutance_type,car_id,client_id,price) values
('34567C89B3', '2023-01-15' , '2024-01-15','A','C89B364A701D52',101234567,3965),
('18404E8D10', '2022-12-27' , '2023-12-27','B','E8D109473G62F5',551818404,1035),
('18404H4505', '2022-08-03' , '2023-8-03','A','H4505916G83I7J',551818404,3790),
('56790T43JM', '2023-01-29' , '2024-01-29','B','T43JM672LK8059',238456790,1340),
('56790CO562', '2023-01-02' , '2024-01-02','A','CO562943781MNP',238456790,3702),
('56790V28SR', '2022-04-30' , '2023-04-30','A','V28SR6QT109357',238456790,3090),
('86604U0158', '2023-01-01' , '2023-06-01','B','U015897W246V3X',998686604,1340),
('18155B7Y58', '2022-06-15' , '2023-06-15','B','B7Y58A103694Z2',404818155,1690),
('18155M9540', '2022-12-15' , '2023-05-15','B','M95403EDC0F728',404818155,1690),
('54832N5F9H', '2023-02-02' , '2024-02-02','A','N5F9HI0418736G',907654832,4010),
('89012J1483', '2023-01-26' , '2024-01-26','A','J14836L5270MK9',456789012,4140),
('89012CN700', '2022-08-03' , '2023-08-03','A','CN7009O4P315M6',456789012,4140),
('89012Z12R8', '2022-05-28' , '2023-05-28','B','Z12R89643QT05S',456789012,1690),
('56575J2463', '2022-07-29' , '2023-07-29','A','J2463V7W91X85U',482956575,3440),
('56575G6518', '2022-03-23' , '2023-03-23','B','G65182B09Z74AY',482956575,1035),
('34567A618D', '2023-02-02' , '2024-02-02','A','A618D95B7C43A0',901234567,2035),
('32109W379D', '2023-02-15' , '2023-07-15','B','W379D6GEF20541',765432109,1035),
('32109P3892', '2023-01-29' , '2024-01-29','A','P38925GH704J6I',765432109,2652),
('01235JK9M2', '2022-08-19' , '2023-01-19','B','JK9M24675810L3',678901235,1035),
('09876O5MP1', '2023-01-25' , '2024-01-25','A','O5MP16043978N2',531209876,4315),
('09876K659T', '2022-09-11' , '2023-09-11','A','K659T2R8SQ1730',532109876,3310),
('09876B842U', '2023-01-28' , '2024-01-28','A','B842U3V0W7516X',532109876,3135),
('09876E2095', '2022-07-30' , '2023-07-30','B','E2095YB76Z8A14',532109876,1690),
('98765FD952', '2022-08-01' , '2023-08-01','A','FD9523617804CE',423098765,3790),
('12345R5826', '2022-10-04' , '2023-10-04','A','R58264G0I391FH',789012345,3702),
('56789ABC12', '2022-11-18' , '2023-11-18','B','ABC123456789',123456789,1035),
('10987DEF09', '2023-01-15' , '2024-01-15','A','DEF098765432',543210987,5015),
('10987GHI24', '2022-08-06' , '2023-08-06','A','GHI246801357',543210987,3352),
('23456JKL36', '2022-09-15' , '2023-02-15','A','JKL369258147',890123456,4360),
('86899MNO54', '2022-09-29' , '2023-09-29','A','MNO547896123',406686899,4052),
('78901UVW74', '2022-09-15' , '2023-09-15','A','UVW741852963',345678901,4315),
('78901YZA85', '2023-02-25' , '2023-07-25','B','YZA852963014',345678901,1690),
('56789CDE90', '2023-01-18' , '2024-01-18','A','CDE908273645',103456789,2827),
('54301FGH28', '2023-01-27' , '2023-06-27','B','FGH283746519',987654301,1690),
('67890JKL74', '2023-01-27' , '2024-01-27','A','JKL746283015',234567890,4185),
('59284MNO90', '2023-01-01' , '2024-01-01','B','MNO903174856',575659284,1035),
('90234QRS67', '2022-07-22' , '2023-07-22','A','QRS675823490',567890234,5102);


/* Create table of employee*/
/*defualt password id ssn id*/
create table employee(
emp_id varchar(10),
ssn int,
first_name varchar(20),
second_name varchar(20),
third_name varchar(20),
fourth_name varchar(20),
phone_1 int,
phone_2 int,
dob date,
ssn_image  blob,
password text,
constraint pk_employee primary key(emp_id)
);

/*Add some data*/
insert into employee(emp_id,ssn,first_name,second_name,third_name,fourth_name,phone_1,phone_2,dob,password) values
('4545AAA',48471557,'ahmad','burhan','khalil','aburayya',0568188407,null,'2000-07-30',48471557),
('4585AAB',14836985,'mohammad','manhal','hasan','abusaleh',0597880687,null,'2002-03-17',14836985),
('4545AAC',32189495,'ameer','mohammad','mahmmod','karaja',056188207,null,'2003-9-11',32189495);
 
select * from employee where emp_id='4545AAA' and password =48471557
/*create table of drivers */
create table driver(
ssn int,
first_name varchar(20),
second_name varchar(20),
third_name varchar(20),
fourth_name varchar(20),
dob date,
driving_license blob,
constraint driver_pk primary key(ssn)
);

/*create table of driver_insurance*/
/*id : last 5 number of driver_id and first 5 char of insurance_id*/
create table driver_insurance(
driver_ins_id  varchar(10) not null,
driver_id int not null,
ins_id varchar(10) not null,
constraint driver_insurance_driver_FK foreign key(driver_id) references driver(ssn) on delete CASCADE ON update CASCADE,
constraint driver_insurance_insurance_FK foreign key(ins_id) references insurance(ins_id) on delete CASCADE ON update CASCADE,
constraint driver_insurance_pk primary key(driver_id,ins_id) 
);






