create table square (
    id INTEGER NOT NULL AUTO_INCREMENT,
    latitude DECIMAL(20,10) NOT NULL,
    longitude DECIMAL(20,10) NOT NULL,
    environment_type INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY PK_SQUARE (id)
);

create table place (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(40) NOT NULL,
    map_url VARCHAR(200),
    place_type INTEGER NOT NULL,
    square_id INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY PK_PLACE (id),
    CONSTRAINT FOREIGN KEY FK_PLACE_SQUARE (square_id) REFERENCES square (id) ON DELETE NO ACTION
);

create table building (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(40) NOT NULL,
    map_url VARCHAR(200),
    building_type INTEGER NOT NULL,
    place_id INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY PK_BUILDING (id),
    CONSTRAINT FOREIGN KEY FK_BUILDING_PLACE (place_id) REFERENCES place (id) ON DELETE NO ACTION
);

create table quest (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(40) NOT NULL,
    place_id INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY PK_QUEST (id),
    CONSTRAINT FOREIGN KEY FK_QUEST_QUEST (place_id) REFERENCES place (id) ON DELETE NO ACTION
);

create table rolcharacter (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(40) NOT NULL,
    portrait VARCHAR(200) NOT NULL,
    CONSTRAINT PRIMARY KEY PK_CHARACTER (id)
);