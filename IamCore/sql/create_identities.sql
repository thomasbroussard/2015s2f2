create table IDENTITIES (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT PEOPLE_PK PRIMARY KEY, 
	
	DISPLAY_NAME VARCHAR(55),
	EMAIL VARCHAR(255),
	UID VARCHAR(255),
	BIRTHDATE DATE
);
	