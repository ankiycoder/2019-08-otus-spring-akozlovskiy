insert into AUTHOR (ID, NAME, BIRTHDATE) values (1, 'Самуил Яковлевич Маршак', '1887-10-22');
insert into AUTHOR (ID, NAME, BIRTHDATE) values (2, 'Александр Сергеевич Пушкин', '1799-06-06');
insert into GENRE (ID, DESCRIPTION) values (1, 'Детский');
insert into GENRE (ID, DESCRIPTION) values (2, 'Комедия');
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (1, 'Кошкин Дом', 1, 1);
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (2, 'Вот какой рассеянный', 1, 1);
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (3, 'Усатый полосатый', 1, 1);
insert into BOOK (ID, TITLE, AUTHORID, GENREID) values (4, 'Дом, который построил Джек', 1, 1);