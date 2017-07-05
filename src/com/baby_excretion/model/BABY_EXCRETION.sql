DROP TABLE BABY_EXCRETION;
DROP SEQUENCE baby_excretion_seq;


CREATE TABLE BABY_EXCRETION
 (
 
BEX_NO		VARCHAR2(10)	NOT NULL,
BD_NO		VARCHAR2(10)	NOT NULL,
BEX_DATE	DATE	NOT NULL,
BEX_DETAILS	VARCHAR2(20)	NOT NULL,



CONSTRAINT BABY_EXCRETION_PK PRIMARY KEY (BEX_NO));

CREATE SEQUENCE baby_excretion_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

INSERT INTO BABY_EXCRETION VALUES ('BEX'||LPAD(to_char(baby_excretion_seq.NEXTVAL),7,'0'),'BD00000001',TO_DATE('2017-4-19 8:10:00','YYYY-MM-DD hh24:mi:ss'),'便便硬硬的');
INSERT INTO BABY_EXCRETION VALUES ('BEX'||LPAD(to_char(baby_excretion_seq.NEXTVAL),7,'0'),'BD00000002',TO_DATE('2017-4-19 10:10:00','YYYY-MM-DD hh24:mi:ss'),'便便軟軟的');

commit;