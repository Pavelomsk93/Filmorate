DELETE FROM GENRES;
INSERT INTO GENRES (NAME)
VALUES('Комедия'),
      ('Драма'),
      ('Мультфильм'),
      ('Триллер'),
      ('Документальный'),
      ('Боевик');

DELETE FROM MPA_RATINGS;
INSERT INTO MPA_RATINGS (MPA_NAME)
VALUES('G'),
      ('PG'),
      ('PG-13'),
      ('R'),
      ('NC-17');
