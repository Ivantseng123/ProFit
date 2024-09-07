CREATE TABLE users(
    user_id INT IDENTITY(100, 1) NOT NULL PRIMARY KEY,
    user_name NVARCHAR(30),
    user_email VARCHAR(50) UNIQUE,
    user_passwordHash VARCHAR(128), 
    user_phoneNumber VARCHAR(20),
    user_city NVARCHAR(60),
    user_identity TINYINT,
    user_pictureURL VARCHAR(200),
    user_balance INT,
    freelancer_location_prefer NVARCHAR(50),
    freelancer_exprience NVARCHAR(30),
    freelancer_identity NVARCHAR(20),
    freelancer_profile_status TINYINT NOT NULL,
    freelancer_disc NTEXT
);

CREATE TABLE employer_application(
    employer_application_id INT IDENTITY(100, 1) NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    company_name NVARCHAR(30), 
    company_address NVARCHAR(60),
    company_category NVARCHAR(20),
    company_phoneNumber VARCHAR(20),
    company_taxID VARCHAR(100),
    company_taxID_docURL VARCHAR(100),
    user_national_id VARCHAR(20) UNIQUE,
    idCard_pictureURL_1 VARCHAR(200),
    idCard_pictureURL_2 VARCHAR(200),
    application_check TINYINT,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE employer_profile(
    employer_profile_id INT IDENTITY(100, 1) NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    company_name NVARCHAR(30),
    company_address NVARCHAR(60),
    company_category NVARCHAR(20),
    company_phoneNumber VARCHAR(20),
    company_taxID VARCHAR(100),
    company_numberOfemployee INT,
    company_captital INT,
    company_description NVARCHAR(200),
    company_photoURL VARCHAR(200),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE password_reset_tokens(
    token_id INT IDENTITY(100, 1) NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    user_tokenHash VARCHAR(150) UNIQUE,
    expiration_stime TIMESTAMP ,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);


-- �Ы�jobs¾�ʪ���
CREATE TABLE jobs (
    jobs_id INT PRIMARY KEY IDENTITY(1,1), -- ¾�ʪA��ID�APK�A�۰ʻ��W
    jobs_users_id INT, -- ¾�ʪA�ȷ|��ID�AFK
    jobs_title NVARCHAR(100), -- ¾�ʼ��D
    jobs_posting_date DATETIME, -- ¾�ʵo�����
    jobs_application_deadline DATETIME, -- ¾�ʥӽкI����
    jobs_description NVARCHAR(MAX), -- ¾�ʴy�z
    jobs_status TINYINT, -- ¾�ʪ��A
    jobs_required_skills NVARCHAR(500), -- ¾�ʩһݧޯ�
    jobs_location NVARCHAR(100), -- ¾�ʤu�@�a�I
    jobs_max_salary INT, -- ¾�ʳ̰��~��
    jobs_min_salary INT, -- ¾�ʳ̧C�~��
    jobs_worktime TIME, -- ¾�ʤu��
    jobs_number_of_openings INT, -- ¾�ʶ}��ƶq
    FOREIGN KEY (jobs_users_id) REFERENCES users(user_id) -- �~������A�s�bmembers��
);

-- �Ы�jobs_application�ӽЪ���
CREATE TABLE jobs_application (
    jobs_application_id INT PRIMARY KEY IDENTITY(1,1), -- ¾�ʥӽ�ID�APK�A
    jobs_application_posting_id INT, -- �ӽ�¾�ʪA��ID�AFK
    jobs_application_member_id INT, -- �ӽз|��ID�AFK
    jobs_application_date DATETIME, -- �ӽФ��
    jobs_application_status TINYINT, -- �ӽЪ��A
    jobs_application_contract VARBINARY(MAX), -- �ӽЦX��
    FOREIGN KEY (jobs_application_posting_id) REFERENCES jobs(jobs_id), -- �~�����
    FOREIGN KEY (jobs_application_member_id) REFERENCES users(user_id ) -- �~������A�s�bresumes��
);

-- �Ы�jobs_application_project¾�ʭq�����
CREATE TABLE jobs_application_project (
    jobs_application_project_id INT PRIMARY KEY IDENTITY(1,1), -- ¾�ʭq��ӽ�ID�APK
    jobs_application_id INT, -- ¾��ID�AFK
    jobs_application_status TINYINT, -- �ӽЭq�檬�A
    jobs_project VARCHAR(50), -- �q��Ƶ�
    jobs_amount INT,
    FOREIGN KEY (jobs_application_id) REFERENCES jobs_application(jobs_application_id) -- �~�����
);

    -- �M�~�ޯ઺����, �@�ӧޯ�u���ݩ�@�ؤ���
	CREATE TABLE major_category (
	major_category_id INT primary key,
	categoy_name NVARCHAR(24) not null,
	);

	-- �@�Cmajor�N���@�ӱM�~�ޯ�
	CREATE TABLE major (
	major_id INT PRIMARY KEY,
	major_name NVARCHAR(24) not null,
	major_category_id INT,
	major_description NVARCHAR(48),
	
	FOREIGN KEY (major_category_id) REFERENCES major_category(major_category_id)
	);
	-- user�s�W���ޯ�s�b�o
	CREATE TABLE user_major (
	user_id INT NOT NULL,
	major_id INT NOT NULL,

	PRIMARY KEY (user_id, major_id),
	FOREIGN KEY(user_id) REFERENCES users(user_id),
	FOREIGN KEY(major_id) REFERENCES major(major_id) 
	);


	-- �A�ȳ����P���z
	-- �@�ӤH�i�H�b�@�ӱM�~�U�s�W�h���A��
	CREATE TABLE service (
	service_id INT PRIMARY KEY IDENTITY(1,1),
	user_id INT NOT NULL,
	major_id INT NOT NULL,
	service_title NVARCHAR(50),
	service_content NVARCHAR(1000),
	service_price DECIMAL(10,2),
	service_unit_name NVARCHAR(8),
	service_duration DECIMAL(10,1),
	service_createdate DATETIME2,
	service_updatedate DATETIME2,
	-- �إߪA�ȥi�H�s3�i�Ϥ����d��
	service_sample1 varbinary,
	service_sample2 varbinary,
	service_sample3 varbinary,
	
	FOREIGN KEY (user_id,major_id) REFERENCES user_major(user_id,major_id)
	);
	

	CREATE TABLE collection (
	collection_id INT PRIMARY KEY IDENTITY(1,1),
	user_id INT,
	major_id INT,
	collection_cover_img_id INT, 
	collection_name NVARCHAR(24),
	
	FOREIGN KEY (user_id,major_id) REFERENCES user_major(user_id,major_id)
	);
	
	
	CREATE TABLE image(
	image_id INT PRIMARY KEY IDENTITY(1,1),
	collection_id INT,
	image_file VARBINARY
	
	FOREIGN KEY (collection_id) REFERENCES collection(collection_id)
	);
	
	CREATE TABLE video(
	video_id INT PRIMARY KEY IDENTITY(1,1),
	collection_id INT,
	video_url NVARCHAR(128),
	video_disc  NVARCHAR(128)
	
	FOREIGN KEY (collection_id) REFERENCES collection(collection_id)
	);

	-- Events table
	CREATE TABLE events (
    event_id NVARCHAR(255) PRIMARY KEY,
    event_name NVARCHAR(255) NOT NULL,
    is_event_active BIT NOT NULL,
    event_start_date DATETIME2 NOT NULL,
    event_end_date DATETIME2 NOT NULL,
    event_description NVARCHAR(255),
    event_amount INT,
    event_location NVARCHAR(255),
    event_participant_maximum INT,
    event_note NVARCHAR(255)
	);

CREATE TABLE event_host (
    event_id NVARCHAR(255), 
    event_host_id INT, 
    PRIMARY KEY (event_id, event_host_id), 
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (event_host_id) REFERENCES users(user_id)
);

CREATE TABLE event_order (
    event_order_id NVARCHAR(50) PRIMARY KEY,
    event_order_amount INT,
    is_event_order_active BIT,
    event_id NVARCHAR(255),
    event_participant_id INT,
    event_participant_date DATETIME2 NOT NULL,
    event_participant_data NVARCHAR(255),
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (event_participant_id) REFERENCES users(user_id)
);


-- courses table
CREATE TABLE courses(
    course_id NVARCHAR(50) PRIMARY KEY,
    course_name NVARCHAR(50) NOT NULL,
	course_create_user_id INT NOT NULL,
    course_category INT NOT NULL,
    course_information NVARCHAR(255),
    course_description TEXT,
    course_enrollment_date DATE NOT NULL,
    course_start_date DATE,
    course_end_date DATE,
    course_price INT ,
    course_status nvarchar(20) NOT NULL,
	FOREIGN KEY(course_create_user_id) REFERENCES users(user_id),
    FOREIGN KEY(course_category) REFERENCES major(major_id)
);


CREATE TABLE course_order(
    course_order_id NVARCHAR(50) PRIMARY KEY,
    course_id NVARCHAR(50) NOT NULL,
    student_id INT NOT NULL,
    course_order_price INT,
    course_order_create_date DATE NOT NULL,
    FOREIGN KEY(course_id) REFERENCES courses(course_id),
	FOREIGN KEY(student_id) REFERENCES users(user_id)
);

CREATE TABLE course_grade_content(
    course_grade_id INT PRIMARY KEY,
    course_id NVARCHAR(50) NOT NULL,
    student_id NVARCHAR(50) NOT NULL,
    course_grade_scroe INT,
    FOREIGN KEY(course_id) REFERENCES courses(course_id)
);

CREATE TABLE course_module(
    course_module_id INT PRIMARY KEY IDENTITY(1,1),
    course_id NVARCHAR(50) NOT NULL,
    course_module_name NVARCHAR(50) NOT NULL,
    FOREIGN KEY(course_id) REFERENCES courses(course_id)
);

CREATE TABLE course_lessons(
    course_lesson_id INT PRIMARY KEY IDENTITY(1,1),
    course_module_id INT NOT NULL,
    course_id NVARCHAR(50) NOT NULL,
    course_lesson_name NVARCHAR(50) NOT NULL,
    course_lesson_sort NVARCHAR(50),
    lesson_media_url NVARCHAR(255),
    lesson_media_type NVARCHAR(20),
    lesson_media_duration INT,
    FOREIGN KEY(course_module_id) REFERENCES course_module(course_module_id),
    FOREIGN KEY(course_id) REFERENCES courses(course_id),
);

-- transaction table
-- 1. �إ� job_orders ��
CREATE TABLE job_orders (
    job_orders_id NVARCHAR(50) PRIMARY KEY,      -- ¾�ʭq��ӽ�ID�APK
    job_application_id INT,                      -- ¾�ʥӽ�ID�AFK
    job_order_date DATETIME,                     -- �ӽЭq����
    job_order_status TINYINT,                    -- �ӽЭq�檬�A
    job_notes TEXT,                              -- �q��Ƶ�
    FOREIGN KEY (job_application_id) REFERENCES jobs_application(jobs_application_id) -- �~�����
);

-- 2. �إ� user_transactions ��
CREATE TABLE user_transactions (
    transaction_id INT PRIMARY KEY IDENTITY(1,1),   -- ���ID
    user_id INT NOT NULL,                           -- �Τ�ID
    transaction_type VARCHAR(50) CHECK (transaction_type IN ('deposit', 'withdrawal', 'payment', 'refund')) NOT NULL, -- �������
    amount DECIMAL(10, 2) NOT NULL,                 -- ������B
    transaction_status VARCHAR(50) CHECK (transaction_status IN ('pending', 'completed', 'failed')) NOT NULL, -- ������A
    job_order_id INT,                               -- ¾�ʭq��ID�A�s�W���p�r�q
    created_at DATETIME DEFAULT GETDATE(),          -- ����ɶ�
    FOREIGN KEY (user_id) REFERENCES users(user_id), -- �~�����p��Τ��
);

-- 3. �إ� invoices ��
CREATE TABLE invoices (
    invoice_id INT PRIMARY KEY IDENTITY(1,1), -- �o��ID
    transaction_id INT UNIQUE,                 -- ���ID�A�P����@��@���p
    job_order_id NVARCHAR(50),                          -- ¾�ʭq��ID�A�s�W���p�r�q
	course_order_id NVARCHAR(50),			   -- �ҵ{�q��ID
	event_order_id NVARCHAR(50),			   -- ���ʭq��ID
    invoice_number VARCHAR(50) NOT NULL,       -- �o�����X
    amount DECIMAL(10, 2) NOT NULL,            -- �o�����B
    issued_date DATE NOT NULL,                 -- �o���}����
    status VARCHAR(50) CHECK (status IN ('pending', 'paid', 'canceled')) NOT NULL, -- �o�����A
    FOREIGN KEY (transaction_id) REFERENCES user_transactions(transaction_id), -- �~�����p������
    FOREIGN KEY (job_order_id) REFERENCES job_orders(job_orders_id), -- �~�����p��¾�ʭq���
	FOREIGN KEY (course_order_id) REFERENCES course_order(course_order_id), -- �~�����p��¾�ʭq���
	FOREIGN KEY (event_order_id) REFERENCES event_order(event_order_id) -- �~�����p��¾�ʭq���
);