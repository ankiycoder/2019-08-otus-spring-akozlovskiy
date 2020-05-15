insert into AUTHOR (ID, NAME, BIRTHDATE) values (1, 'Маршак', '1887-11-03');
insert into AUTHOR (ID, NAME, BIRTHDATE) values (2, 'Пушкин', '1799-06-06');
insert into GENRE (ID, DESCRIPTION) values (1, 'Детский');
insert into GENRE (ID, DESCRIPTION) values (2, 'Сказки');
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (1, 'Багаж', 1, 1);
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (2, 'Вот какой рассеянный', 1, 1);
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (3, 'Усатый-полосатый', 1, 1);
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (4, 'Сказка о золотом петушке', 2, 2);