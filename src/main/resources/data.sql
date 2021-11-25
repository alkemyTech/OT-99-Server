INSERT IGNORE INTO organization(id,
ORG_NAME, 
ORG_IMAGE_URL, 
ORG_ADDRESS, 
ORG_PHONE, 
ORG_EMAIL, 
ORG_WELCOME_TEXT, 
ORG_ABOUT_US_TEXT, 
IS_DELETED, 
LAST_UPDATE_DATE, 
CREATION_DATE )
 VALUES(1,
'Somos Más',
 'https://drive.google.com/drive/u/2/folders/1q-t5pTX3FA7bf-2kDnGq-99EUfkUQ7iZ',
 'La Cava, San Isidro, Provincia de Buenos Aires', 
 1160112988, 
 'somosfundacionmas@gmail.com',
 'Somos Más es una organización sin fines de lucro que trabaja desde 1997 junto a los vecinos del barrio La Cava para mejorar las vidas de aquellos en situación de vulnerabilidad, garantizando un proceso de crecimiento e inserción social.',
 'Desde 1997 trabajamos con los chicos y chicas, mamás y papás, abuelos y vecinos del barrio La Cava para garantizar la mejora de la situación de aquellos en vulnerabilidad. 
Uniendo las manos de las familias tanto del barrio como fuera de él, hemos mejorado nuestra capacidad de trabajo y nuestra organización que inicialmente fue creada con la intención de dar alimento a aquellos que lo necesitaban, 
hoy es un centro comunitario que acompaña a más de 700 personas a través de las áreas de educación, deportes, primera infancia, salud, alimentación y trabajo social.
 Nuestra visión es mejorar la calidad de vida de niños y familias en situación de vulnerabilidad en el barrio La Cava, otorgando un cambio de rumbo en cada individuo a través de la educación, salud, trabajo, deporte, responsabilidad y compromiso.
 Estamos comprometidos a trabajar articuladamente con los distintos aspectos de la vida de las familias,
generando espacios de desarrollo personal y familiar, brindando herramientas que
logren mejorar la calidad de vida a través de su propio esfuerzo.',
FALSE,
current_timestamp(),
current_timestamp())
;

INSERT IGNORE INTO roles (
        id,
        creation_date,
        role_description,
        role_name,last_update_date
    )
    VALUES
	(1, current_timestamp(), "rol de administrador", "ROLE_ADMIN", current_timestamp()),
    (2, current_timestamp(), "rol de usuario","USER", current_timestamp());

INSERT IGNORE INTO users (
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
    (1,current_timestamp(),NULL,0,"mariano@gmail.com","Mariano","Forte",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/1.jpg",1),
    (2,current_timestamp(),NULL,0,"carolina@gmail.com","Carolina","Flash",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/2.jpg",1),
    (3,current_timestamp(),NULL,0,"elena@gmail.com","Elena","Mendez",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/3.jpg",1),
    (4,current_timestamp(),NULL,0,"Aitor@gmail.com","Aitor","Liri",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/4.jpg",1),
    (5,current_timestamp(),NULL,0,"Andres@gmail.com","Andres","Benegas",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/5.jpg",1),
    (6,current_timestamp(),NULL,0,"Elba@gmail.com","Elba","Klun",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/6.jpg",1),
    (7,current_timestamp(),NULL,0,"Jazmin@gmail.com","Jazmin","Flor",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/7.jpg",1),
    (8,current_timestamp(),NULL,0,"Maira@gmail.com","Maira","Falcon",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/8.jpg",1),
    (9,current_timestamp(),NULL,0,"Nicolas@gmail.com","Nicolas","Amarilla",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/9.jpg",1),
    (10,current_timestamp(),NULL,0,"Federico@gmail.com","Federico","Lopez",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/10.jpg",1),

    (11,current_timestamp(),NULL,0,"Belen@gmail.com","Belen","Milanesi",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/11.jpg",2),
    (12,current_timestamp(),NULL,0,"Florencia@gmail.com","Florencia","Perez",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/12.jpg",2),
    (13,current_timestamp(),NULL,0,"Jessica@gmail.com","Jessica","Artaud",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/13.jpg",2),
    (14,current_timestamp(),NULL,0,"Cecilio@gmail.com","Cecilio","Principe",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/14.jpg",2),
    (15,current_timestamp(),NULL,0,"Beatriz@gmail.com","Beatriz","Almo",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/15.jpg",2),
    (16,current_timestamp(),NULL,0,"Juliana@gmail.com","Juliana","Chavez",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/16.jpg",2),
    (17,current_timestamp(),NULL,0,"Pedro@gmail.com","Pedro","Escamozo",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/17.jpg",2),
    (18,current_timestamp(),NULL,0,"Selena@gmail.com","Selena","Gomez",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/18.jpg",2),
    (19,current_timestamp(),NULL,0,"Luis@gmail.com","Luis","Spinetta",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/19.jpg",2),
    (20,current_timestamp(),NULL,0,"Abril@gmail.com","Abril","Sierra",current_timestamp(),"$2a$10$GceBTMx5xPcFs8skaMTH2.taB0mzHt7U3fY2J0Mjevj0ARq81XK0i","images/20.jpg",2);
