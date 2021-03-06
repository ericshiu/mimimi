DROP TABLE BABY_GROWTH;
DROP SEQUENCE baby_growth_seq;


CREATE TABLE BABY_GROWTH (
 
BG_NO		VARCHAR2(10)	NOT NULL,
BD_NO		VARCHAR2(10)	NOT NULL,
BG_HEIGHT	NUMBER	NOT NULL,
BG_WEIGHT	NUMBER	NOT NULL,
BG_HEAD  	NUMBER	NOT NULL,
BG_DATE     DATE    NOT NULL,


CONSTRAINT BABY_GROWTH_PRIMARY_KEY PRIMARY KEY (BG_NO));

CREATE SEQUENCE baby_growth_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;



INSERT INTO BABY_GROWTH VALUES ('BG'||LPAD(to_char(baby_growth_seq.NEXTVAL),8,'0'),'BD00000001',10,10,10,TO_DATE('2017-4-22','YYYY-MM-DD'));

commit;