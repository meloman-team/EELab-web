INSERT INTO department(ID, NAME, LVL, LOCATION, ID_PARENT)
  VALUES(1,'NC',1,'Samara',null);

INSERT INTO department(ID, NAME, LVL, LOCATION, ID_PARENT)
  VALUES(2,'HR',2,'Samara',1);

INSERT INTO department(ID, NAME, LVL, LOCATION, ID_PARENT)
  VALUES(3,'MacDak',1,'Samara',null);

INSERT INTO JOB (JOB)
  VALUES('Водонос');

INSERT INTO JOB (JOB)
  VALUES('Грузчик');

INSERT INTO JOB (JOB)
  VALUES('Холоп');

INSERT INTO JOB (JOB)
  VALUES('Царь');

INSERT INTO WORKER(ID, NAME, MIDDLE_NAME, LAST_NAME, JOB, ID_DEPARTMENTS)
  VALUES(1,'Сан','Саныч','Бомбилин','Водонос',1);

INSERT INTO WORKER(ID, NAME, MIDDLE_NAME, LAST_NAME, JOB, ID_DEPARTMENTS)
  VALUES(2,'Василий','Васильевич','Васильев','Холоп',3);

INSERT INTO WORKER(ID, NAME, MIDDLE_NAME, LAST_NAME, JOB, ID_DEPARTMENTS)
  VALUES(3,'Дарт ','Вейдер','Ситх','Царь',2);