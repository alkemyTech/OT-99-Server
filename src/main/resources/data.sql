INSERT IGNORE INTO roles (
        id,
        creation_date,
        role_description,
        role_name,last_update_date
    )
    VALUES
	(1, current_timestamp(), "rol de administrador", "ROLE_ADMIN", current_timestamp()),
    (2, current_timestamp(), "rol de usuario","USER", current_timestamp());

INSERT IGNORE INTO user (
        id,
        creation_date,
        delete_date,
        deleted,email,
        first_name,
        last_name,
        last_update,
        password,
        photo,
        id_role)
    VALUES
    (1,current_timestamp(),NULL,0,"mariano@gmail.com","Mariano","Forte",current_timestamp(),"123456","images/1.jpg",1),
    (2,current_timestamp(),NULL,0,"carolina@gmail.com","Carolina","Flash",current_timestamp(),"123456","images/2.jpg",1),
    (3,current_timestamp(),NULL,0,"elena@gmail.com","Elena","Mendez",current_timestamp(),"123456","images/3.jpg",1),
    (4,current_timestamp(),NULL,0,"Aitor@gmail.com","Aitor","Liri",current_timestamp(),"123456","images/4.jpg",1),
    (5,current_timestamp(),NULL,0,"Andres@gmail.com","Andres","Benegas",current_timestamp(),"123456","images/5.jpg",1),
    (6,current_timestamp(),NULL,0,"Elba@gmail.com","Elba","Klun",current_timestamp(),"123456","images/6.jpg",1),
    (7,current_timestamp(),NULL,0,"Jazmin@gmail.com","Jazmin","Flor",current_timestamp(),"123456","images/7.jpg",1),
    (8,current_timestamp(),NULL,0,"Maira@gmail.com","Maira","Falcon",current_timestamp(),"123456","images/8.jpg",1),
    (9,current_timestamp(),NULL,0,"Nicolas@gmail.com","Nicolas","Amarilla",current_timestamp(),"123456","images/9.jpg",1),
    (10,current_timestamp(),NULL,0,"Federico@gmail.com","Federico","Lopez",current_timestamp(),"123456","images/10.jpg",1),

    (11,current_timestamp(),NULL,0,"Belen@gmail.com","Belen","Milanesi",current_timestamp(),"123456","images/11.jpg",2),
    (12,current_timestamp(),NULL,0,"Florencia@gmail.com","Florencia","Perez",current_timestamp(),"123456","images/12.jpg",2),
    (13,current_timestamp(),NULL,0,"Jessica@gmail.com","Jessica","Artaud",current_timestamp(),"123456","images/13.jpg",2),
    (14,current_timestamp(),NULL,0,"Cecilio@gmail.com","Cecilio","Principe",current_timestamp(),"123456","images/14.jpg",2),
    (15,current_timestamp(),NULL,0,"Beatriz@gmail.com","Beatriz","Almo",current_timestamp(),"123456","images/15.jpg",2),
    (16,current_timestamp(),NULL,0,"Juliana@gmail.com","Juliana","Chavez",current_timestamp(),"123456","images/16.jpg",2),
    (17,current_timestamp(),NULL,0,"Pedro@gmail.com","Pedro","Escamozo",current_timestamp(),"123456","images/17.jpg",2),
    (18,current_timestamp(),NULL,0,"Selena@gmail.com","Selena","Gomez",current_timestamp(),"123456","images/18.jpg",2),
    (19,current_timestamp(),NULL,0,"Luis@gmail.com","Luis","Spinetta",current_timestamp(),"123456","images/19.jpg",2),
    (20,current_timestamp(),NULL,0,"Abril@gmail.com","Abril","Sierra",current_timestamp(),"123456","images/20.jpg",2);