insert into albums(naam, artiestId)
values ('test',(select id from artiesten where naam= 'test'));
insert into tracks(naam, tijd, albumId)
values ('test','0:10:0',(select id from albums where naam = 'test'));