DROP TABLE BABY_DATA;
DROP SEQUENCE babydata_seq;


CREATE TABLE BABY_DATA (
 
BD_NO		VARCHAR2(10)	NOT NULL,
MEM_NO		VARCHAR2(10)	NOT NULL,
BD_ORDER 	NUMBER	NOT NULL,
BD_NAME		VARCHAR2(50)	NOT NULL,
BD_SEX 		VARCHAR2(50)	NOT NULL,
BD_PRE		DATE	NOT NULL,
BD_BIRTHDAY	DATE	NOT NULL,
BD_PICTURES	BLOB	null,


CONSTRAINT BABY_DATA_PRIMARY_KEY PRIMARY KEY (BD_NO));

CREATE SEQUENCE babydata_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;



INSERT INTO BABY_DATA VALUES ('BD'||LPAD(to_char(babydata_seq.NEXTVAL),8,'0'),'MEM0000001',1,'大寶','女',TO_DATE('2017-4-21','YYYY-MM-DD'),TO_DATE('2017-4-22','YYYY-MM-DD'),null); 

commit;