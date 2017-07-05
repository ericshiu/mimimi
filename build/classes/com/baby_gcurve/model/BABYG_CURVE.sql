DROP TABLE BABYG_CURVE;
DROP SEQUENCE babyg_curve_seq;


CREATE TABLE BABYG_CURVE (
 
BGC_NO		VARCHAR2(10) NOT NULL,
BGC_SEX		VARCHAR2(20) NOT NULL,
BGC_AGE		NUMBER	NOT NULL,
BGC_HEIGHT_97	NUMBER(6,1)	,
BGC_HEIGHT_85	NUMBER(6,1)	,
BGC_HEIGHT_50	NUMBER(6,1)	,
BGC_HEIGHT_15	NUMBER(6,1)	,
BGC_HEIGHT_3	NUMBER(6,1)	,
BGC_WEIGHT_97	NUMBER(6,1)	,
BGC_WEIGHT_85	NUMBER(6,1)	,
BGC_WEIGHT_50	NUMBER(6,1)	,
BGC_WEIGHT_15	NUMBER(6,1)	,
BGC_WEIGHT_3	NUMBER(6,1)	,
BGC_HEAD_97		NUMBER(6,1)	,
BGC_HEAD_85		NUMBER(6,1)	,
BGC_HEAD_50		NUMBER(6,1)	,
BGC_HEAD_15 	NUMBER(6,1)	,
BGC_HEAD_3 		NUMBER(6,1)	,



CONSTRAINT BABYG_CURVE_PK PRIMARY KEY (BGC_NO));

CREATE SEQUENCE babyg_curve_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;


INSERT INTO BABYG_CURVE VALUES('BGC'||LPAD(to_char(babyg_curve_seq.NEXTVAL),7,'0'),'男',10,10,10,10,10,10,10,10,10,10,10,10,10,140,10,51);
INSERT INTO BABYG_CURVE VALUES('BGC'||LPAD(to_char(babyg_curve_seq.NEXTVAL),7,'0'),'女',10,10,10,10,10,10,10,10,10,10,10,10,10,140,10,51);

commit;