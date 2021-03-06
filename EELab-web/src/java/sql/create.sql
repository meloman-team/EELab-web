﻿CREATE TABLE NCWORCSPACE.DEPARTMENT (
  ID        NUMBER,
  NAME      VARCHAR2(50 CHAR),
  LVL       NUMBER,
  LOCATION  VARCHAR2(50 CHAR),
  ID_PARENT NUMBER,
  CONSTRAINT PK_OFFICES PRIMARY KEY (ID),
  CONSTRAINT FK_DEPARTMENT_DEPARTMENT_ID FOREIGN KEY (ID_PARENT)
  REFERENCES NCWORCSPACE.DEPARTMENT (ID)
)
TABLESPACE USERS
STORAGE (INITIAL 64 K
         NEXT 1 M
         MAXEXTENTS UNLIMITED)
LOGGING;

CREATE TABLE NCWORCSPACE.JOB (
  JOB NVARCHAR2(50),
  CONSTRAINT PK_JOB PRIMARY KEY (JOB)
)
TABLESPACE USERS
STORAGE (INITIAL 64 K
         NEXT 1 M
         MAXEXTENTS UNLIMITED)
LOGGING;

CREATE TABLE NCWORCSPACE.WORKER (
  ID             NUMBER,
  NAME           VARCHAR2(50 BYTE),
  MIDDLE_NAME    NVARCHAR2(50),
  LAST_NAME      NVARCHAR2(50),
  JOB            NVARCHAR2(50),
  ID_DEPARTMENTS NUMBER(10, 0),
  CONSTRAINT PK_WORKER PRIMARY KEY (ID),
  CONSTRAINT FK_WORKER_JOB_JOB FOREIGN KEY (JOB)
  REFERENCES NCWORCSPACE.JOB (JOB)
)
TABLESPACE USERS
STORAGE (INITIAL 64 K
         NEXT 1 M
         MAXEXTENTS UNLIMITED)
LOGGING;