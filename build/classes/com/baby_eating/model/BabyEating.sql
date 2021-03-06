DROP TABLE BABY_EATING;
DROP SEQUENCE baby_eating_seq;


CREATE TABLE BABY_EATING (
 
BE_NO		VARCHAR2(10)	NOT NULL,
BD_NO		VARCHAR2(10)	NOT NULL,
BE_DATE		DATE	NOT NULL,
BE_SORT		VARCHAR2(50)	NOT NULL,
BE_METE  	NUMBER	NOT NULL,


CONSTRAINT BABY_EATING_PK PRIMARY KEY (BE_NO));

CREATE SEQUENCE baby_eating_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;




INSERT INTO BABY_EATING VALUES ('BE'||LPAD(to_char(baby_eating_seq.NEXTVAL),8,'0'),'BD00000001',TO_DATE('2017-4-19 10:10:00','YYYY-MM-DD hh24:mi:ss'),'母奶',100);
INSERT INTO BABY_EATING VALUES ('BE'||LPAD(to_char(baby_eating_seq.NEXTVAL),8,'0'),'BD00000002',TO_DATE('2017-4-19 18:10:00','YYYY-MM-DD hh24:mi:ss'),'奶粉',100);
INSERT INTO BABY_EATING VALUES ('BE'||LPAD(to_char(baby_eating_seq.NEXTVAL),8,'0'),'BD00000003',TO_DATE('2017-4-19 21:10:00','YYYY-MM-DD hh24:mi:ss'),'三路漸漸奶',100);

commit;