INSERT INTO roles (creation_date,role_description, role_name,last_update_date) values
(current_timestamp(),"rol de administrador","ROLE_ADMIN",current_timestamp()),
(current_timestamp(),"rol de usuario","USER",current_timestamp());

INSERT INTO users (creation_date,delete_date,deleted,email,first_name,last_name,last_update,password,photo, id_role) values
(current_timestamp(),NULL,0,"mariano@gmail.com","Mariano","Forte",current_timestamp(),"123456","images/1.jpg",1),
(current_timestamp(),NULL,0,"carolina@gmail.com","Carolina","Flash",current_timestamp(),"123456","images/2.jpg",1),
(current_timestamp(),NULL,0,"elena@gmail.com","Elena","Nito",current_timestamp(),"123456","images/3.jpg",1),
(current_timestamp(),NULL,0,"Aitor@gmail.com","Aitor","Tilla",current_timestamp(),"123456","images/4.jpg",1),
(current_timestamp(),NULL,0,"Andres@gmail.com","Andres","Trozado",current_timestamp(),"123456","images/5.jpg",1),
(current_timestamp(),NULL,0,"Elba@gmail.com","Elba","Surero",current_timestamp(),"123456","images/6.jpg",1),
(current_timestamp(),NULL,0,"Jazmin@gmail.com","Jazmin","Flor",current_timestamp(),"123456","images/7.jpg",1),
(current_timestamp(),NULL,0,"Maira@gmail.com","Maira","Verde",current_timestamp(),"123456","images/8.jpg",1),
(current_timestamp(),NULL,0,"Nicolas@gmail.com","Nicolas","Amarilla",current_timestamp(),"123456","images/9.jpg",1),
(current_timestamp(),NULL,0,"Federico@gmail.com","Federico","Lopez",current_timestamp(),"123456","images/10.jpg",1),

(current_timestamp(),NULL,0,"Belen@gmail.com","Belen","Milanesi",current_timestamp(),"123456","images/11.jpg",2),
(current_timestamp(),NULL,0,"Florencia@gmail.com","Florencia","Perez",current_timestamp(),"123456","images/12.jpg",2),
(current_timestamp(),NULL,0,"Jessica@gmail.com","Jessica","Artaud",current_timestamp(),"123456","images/13.jpg",2),
(current_timestamp(),NULL,0,"Cecilio@gmail.com","Cecilio","Principe",current_timestamp(),"123456","images/14.jpg",2),
(current_timestamp(),NULL,0,"Beatriz@gmail.com","Beatriz","Almo",current_timestamp(),"123456","images/15.jpg",2),
(current_timestamp(),NULL,0,"Juliana@gmail.com","Juliana","Chavez",current_timestamp(),"123456","images/16.jpg",2),
(current_timestamp(),NULL,0,"Pedro@gmail.com","Pedro","Escamozo",current_timestamp(),"123456","images/17.jpg",2),
(current_timestamp(),NULL,0,"Selena@gmail.com","Selena","Gomez",current_timestamp(),"123456","images/18.jpg",2),
(current_timestamp(),NULL,0,"Luis@gmail.com","Luis","Spinetta",current_timestamp(),"123456","images/19.jpg",2),
(current_timestamp(),NULL,0,"Abril@gmail.com","Abril","Sierra",current_timestamp(),"123456","images/20.jpg",2);