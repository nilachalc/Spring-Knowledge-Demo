CREATE TABLE Course 
  ( 
     courseid   VARCHAR2(10), 
     coursename VARCHAR2(40) NOT NULL, 
     instructorname  VARCHAR2(40) NOT NULL,
	 startdate TIMESTAMP DEFAULT null,
	 numberofstudents Number(5, 0),
     CONSTRAINT pk_course PRIMARY KEY (courseid)
  );
  
INSERT INTO Course 
            (courseid,
			 coursename, 
             instructorname,
             numberofstudents,
			 startdate) 
VALUES     ('101', 
            'ECE', 
            'Subhashree',
            40,
			CURRENT_DATE()); 

INSERT INTO Course 
            (courseid,
			 coursename, 
             instructorname,
             numberofstudents,
			 startdate) 
VALUES     ('102', 
            'EE', 
            'PKD',
            45,
			CURRENT_DATE());
INSERT INTO Course 
            (courseid,
			 coursename, 
             instructorname,
             numberofstudents,
			 startdate) 
VALUES     ('103', 
            'ICE', 
            'KeuEkta',
            50,
			CURRENT_DATE());
INSERT INTO Course 
            (courseid,
			 coursename, 
             instructorname,
             numberofstudents,
			 startdate) 
VALUES     ('104', 
            'CSE', 
            'Abhishek',
            55,
			CURRENT_DATE());
			
INSERT INTO Course 
            (courseid,
			 coursename, 
             instructorname,
             numberofstudents,
			 startdate) 
VALUES     ('105', 
            'IT', 
            'Bijayini',
            60,
			CURRENT_DATE());