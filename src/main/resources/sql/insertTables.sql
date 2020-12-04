-- Square inserts

insert into square (id, latitude, longitude, environment_type)
values (1, 1, 1, 1);

insert into square (id, latitude, longitude, environment_type)
values (2, 1, -1, 1);

insert into square (id, latitude, longitude, environment_type)
values (3, -1, 1, 1);

insert into square (id, latitude, longitude, environment_type)
values (4, -1, -1, 1);


-- Place inserts

insert into place (id, name, description, map_url, place_type, square_id)
values (1, 'Vermidia', 'Capital city of Salendar', null, 1, 1);


-- Building inserts

insert into building (id, name, description, map_url, building_type, place_id)
values (1, 'Magic Window', 'Kithris magical item shop', null, 7, 1);


-- Quest inserts

insert into quest (id, name, description, place_id)
values (1, 'Winter games', 'Fighting tournament', 1);






