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
    freelancer_disc NTEXT,
    user_register_time DATETIME2(0) DEFAULT CURRENT_TIMESTAMP
);


INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (101, N'John Doe', N'john.doe@example.com', N'e99a18c428cb38d5f260853678922e03', N'0912-345-678', N'台北市', 1, N'https://example.com/john.jpg', 0, N'台北市+新北市+基隆市', N'5 years', N'個人兼職', 1, N'Experienced full-stack developer.', NULL)
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (102, N'Jane Smith', N'jane.smith@example.com', N'abcde1234567890fghijk9876543210', N'0923-456-789', N'新北市', 2, N'https://example.com/jane.jpg', 0, N'新北市+桃園市+台中市', N'3 years', N'專職SOHO', 1, N'Creative designer with a passion for UI/UX.', NULL)
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (103, N'Alice Brown', N'alice.brown@example.com', N'5d41402abc4b2a76b9719d911017c592', N'0934-567-890', N'桃園市', 1, N'https://example.com/alice.jpg', 0, N'桃園市+新竹市+新竹縣', N'4 years', N'工作室', 1, N'Professional photographer with experience in portraits.', NULL)
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (104, N'Bob Johnson', N'bob.johnson@example.com', N'098f6bcd4621d373cade4e832627b4f6', N'0945-678-901', N'新竹市', 2, N'https://example.com/bob.jpg', 0, N'新竹市+新竹縣+台北市', N'6 years', N'兼職上班族', 1, N'Expert in video production and editing.', NULL)
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (105, N'Charlie Davis', N'charlie.davis@example.com', N'c4ca4238a0b923820dcc509a6f75849b', N'0956-789-012', N'新竹縣', 1, N'https://example.com/charlie.jpg', 0, N'新竹縣+基隆市+台中市', N'2 years', N'公司', 1, N'Freelance writer specializing in tech articles.', NULL)
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (149, N'游峻翰', N'johnyu91308@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'0917-276358', N'花蓮縣', 3, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=b8ced6a1-0b56-404c-b99a-dfd5df3041c4', 0, N'新北市', N'7-8年工作經驗', NULL, 0, N'', CAST(N'2024-08-30T11:15:46.0000000' AS DateTime2))
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (150, N'測試', N'test@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'0917-276358', N'臺北市', 2, N'150/馬邦德.jpeg', 0, N'臺北市', N'無工作經驗', N'工作室', 0, N'hhhhhhh', CAST(N'2024-08-30T11:52:29.0000000' AS DateTime2))
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (1145, N'測試', N'aaaaa@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'0917-278888', N'花蓮縣', 1, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=8103fba3-03a0-4a9e-9e40-2c108cc157cc', 4, N'臺中市', N'0-1年工作經驗', N'學生', 0, N'asdasdasdasd', CAST(N'2024-09-02T10:11:12.0000000' AS DateTime2))
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (2146, N'aaaaaaa', N'aaaaaaa@gmail.com', N'4b179810791a19af3bfeafbdcf0aca274d9ac7d5f063b8e0b75189360c0cf3a3', N'0917-278888', N'基隆市', 1, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=f6b449df-f640-4616-8142-da53a632ec92', 0, N'花蓮縣', N'10年以上年工作經驗', N'工作室', 0, N'aaaaaaa', CAST(N'2024-09-09T15:59:50.0000000' AS DateTime2))
INSERT [dbo].[users] ([user_id], [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_disc], [user_register_time]) VALUES (3145, N'游峻翰', N'test111@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'0917-276358', N'臺北市', 3, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=d1d95a04-2bb6-4989-8186-c22287f77799', 0, N'臺北市', N'無工作經驗', NULL, 0, N'', CAST(N'2024-09-11T10:29:02.0000000' AS DateTime2))


CREATE TABLE employer_application(
    employer_application_id INT IDENTITY(100, 1) NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    company_name NVARCHAR(30), 
    company_address NVARCHAR(60),
    company_category NVARCHAR(20),
    company_phoneNumber VARCHAR(20),
    company_taxID VARCHAR(100),
    company_taxID_docURL NVARCHAR(255),
    user_national_id VARCHAR(20),
    idCard_pictureURL_1 NVARCHAR(255),
    idCard_pictureURL_2 NVARCHAR(255),
    application_check TINYINT,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT [dbo].[employer_application] ([employer_application_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES (101, 101, N'ABC Corp', N'123 Taipei Road', N'IT', N'0223456789', N'A123456789', N'http://example.com/taxdoc1.jpg', N'A123456789', N'http://example.com/id1_front.jpg', N'http://example.com/id1_back.jpg', 1)
INSERT [dbo].[employer_application] ([employer_application_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES (102, 102, N'XYZ Inc', N'456 Kaohsiung Street', N'Finance', N'0223456790', N'B123456789', N'http://example.com/taxdoc2.jpg', N'B123456789', N'http://example.com/id2_front.jpg', N'http://example.com/id2_back.jpg', 1)
INSERT [dbo].[employer_application] ([employer_application_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES (2116, 1145, N'測試', N'澎湖縣測試測試', N'礦業及土石採取業', N'1111111', N'5454654646', N'', N'H123456789', N'', N'', 0)
INSERT [dbo].[employer_application] ([employer_application_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES (2118, 150, N'55555', N'嘉義縣55555', N'礦業及土石採取業', N'55555', N'55555', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=188ab1e6-c2b3-4578-92d1-93ae204dfc5e', N'H123456789', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=4e2da6ae-dc3e-4931-a4a5-61a1ac116582', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=188ab1e6-c2b3-4578-92d1-93ae204dfc5e', NULL)

CREATE TABLE employer_profile(
    employer_profile_id INT IDENTITY(100, 1) NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    company_name NVARCHAR(30),
    company_address NVARCHAR(60),
    company_category NVARCHAR(20),
    company_phoneNumber VARCHAR(20),
    company_taxID VARCHAR(100),
    company_numberOfemployee NVARCHAR(30),
    company_captital NVARCHAR(30),
    company_description NVARCHAR(200),
    company_photoURL NVARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

INSERT [dbo].[employer_profile] ([employer_profile_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_captital], [company_description], [company_photoURL]) VALUES (100, 101, N'ABC Corp', N'123 Taipei Road', N'IT', N'0223456789', N'A123456789', N'200', N'10000000', N'Leading IT company in Taiwan', N'http://example.com/abccorp.jpg')
INSERT [dbo].[employer_profile] ([employer_profile_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_captital], [company_description], [company_photoURL]) VALUES (101, 102, N'XYZ Inc', N'臺北市 Kaohsiung Street', N'農、林、漁、牧業', N'0223456790', N'B123456789', N'1-10', N'8000000', N'Top finance company', N'http://example.com/xyzinc.jpg')
INSERT [dbo].[employer_profile] ([employer_profile_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_captital], [company_description], [company_photoURL]) VALUES (1112, 1145, N'資展國際', N'桃園市中壢區新生路二段421號', N'其他', N'03123456789', N'123456789', N'1-10', N'464646464464', N'', N'1145/1965770a560615a908f98b80e558752e-65960.jpg')
INSERT [dbo].[employer_profile] ([employer_profile_id], [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_captital], [company_description], [company_photoURL]) VALUES (2113, 150, N'66666', N'花蓮縣66666', N'專業、科學及技術服務業', N'66666', N'66666', N'5000以上', N'66666', N'66666', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F1965770a560615a908f98b80e558752e-65960.jpg?alt=media&token=7783bf37-e935-4cb2-bd07-ec7a05d96a06')


CREATE TABLE password_reset_tokens(
    token_id INT IDENTITY(100, 1) NOT NULL PRIMARY KEY,
    user_id INT NOT NULL,
    user_tokenHash VARCHAR(150) UNIQUE,
    expiration_stime TIMESTAMP ,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT [dbo].[password_reset_tokens] ([token_id], [user_id], [user_tokenHash], [expiration_time]) VALUES (101, 101, N'tokenhash100', CAST(N'2024-08-26T09:39:23.0000000' AS DateTime2))
INSERT [dbo].[password_reset_tokens] ([token_id], [user_id], [user_tokenHash], [expiration_time]) VALUES (102, 102, N'tokenhash101', CAST(N'2024-08-26T09:39:23.0000000' AS DateTime2))


CREATE TABLE jobs (
    jobs_id INT PRIMARY KEY IDENTITY(1,1), 
    jobs_users_id INT, 
    jobs_title NVARCHAR(100), 
    jobs_posting_date DATETIME, 
    jobs_application_deadline DATETIME, 
    jobs_description NVARCHAR(MAX), 
    jobs_status TINYINT, 
    jobs_required_skills NVARCHAR(500), 
    jobs_location NVARCHAR(100), 
    jobs_max_salary INT, 
    jobs_min_salary INT, 
    jobs_worktime TIME,
    jobs_number_of_openings INT,
    FOREIGN KEY (jobs_users_id) REFERENCES users(user_id)
);

CREATE TABLE jobs_application (
    jobs_application_id INT PRIMARY KEY IDENTITY(1,1),
    jobs_application_posting_id INT,
    jobs_application_member_id INT,
    jobs_application_date DATETIME,
    jobs_application_status TINYINT, 
    jobs_application_contract VARBINARY(MAX), 
    FOREIGN KEY (jobs_application_posting_id) REFERENCES jobs(jobs_id), 
    FOREIGN KEY (jobs_application_member_id) REFERENCES users(user_id )
);

CREATE TABLE jobs_application_project (
    jobs_application_project_id INT PRIMARY KEY IDENTITY(1,1),
    jobs_application_id INT,
    jobs_application_status TINYINT,
    jobs_project VARCHAR(50),
    jobs_amount INT,
    FOREIGN KEY (jobs_application_id) REFERENCES jobs_application(jobs_application_id) 
);

    -- 專業技能的分類, 一個技能只能屬於一種分類
	CREATE TABLE major_category (
	major_category_id INT primary key,
	category_name NVARCHAR(24) not null,
	);

	-- 一列major代表一個專業技能
	CREATE TABLE major (
	major_id INT PRIMARY KEY,
	major_name NVARCHAR(24) not null,
	major_category_id INT,
	major_description NVARCHAR(48),
	
	FOREIGN KEY (major_category_id) REFERENCES major_category(major_category_id)
	);
	-- user新增的技能(中介table)
	CREATE TABLE user_major (
	user_id INT NOT NULL,
	major_id INT NOT NULL,

	PRIMARY KEY (user_id, major_id),
	FOREIGN KEY(user_id) REFERENCES users(user_id),
	FOREIGN KEY(major_id) REFERENCES major(major_id) 
	);


	-- 服務報價與概述
	-- 一個人可以在一個專業下新增多筆服務
	CREATE TABLE service (
	service_id INT PRIMARY KEY IDENTITY(1,1),
	user_id INT NOT NULL,
	major_id INT NOT NULL,
	service_title NVARCHAR(50),
	service_content NVARCHAR(1000),
	service_price int NOT NULL,
	service_unit_name NVARCHAR(8),
	service_duration DECIMAL(10,1),
	service_createdate DATETIME2,
	service_updatedate DATETIME2,
	-- 建立服務可以存3張圖片當範例
	service_pictureURL_1 VARCHAR(300),
	service_pictureURL_2 VARCHAR(300),
	service_pictureURL_3 VARCHAR(300),
	-- 服務狀態(審核是否通過、)
	service_status int not null default 0,
	
	FOREIGN KEY (user_id,major_id) REFERENCES user_major(user_id,major_id)
	);
	

	CREATE TABLE collection (
	collection_id INT PRIMARY KEY IDENTITY(1,1),
	user_id INT,
	major_id INT,
	collection_cover_img_id INT, 
	collection_name NVARCHAR(24),
	
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (major_id) REFERENCES major(major_id)
	);
	
	
	CREATE TABLE image(
	image_id INT PRIMARY KEY IDENTITY(1,1),
	collection_id INT,
	image_pictureURL VARCHAR(300),
	
	FOREIGN KEY (collection_id) REFERENCES collection(collection_id)
	);
	
	CREATE TABLE video(
	video_id INT PRIMARY KEY IDENTITY(1,1),
	collection_id INT,
	video_url NVARCHAR(128),
	video_disc  NVARCHAR(128)
	
	FOREIGN KEY (collection_id) REFERENCES collection(collection_id)
	);

--建立 events 表格
CREATE TABLE events (
    event_id NVARCHAR(255) PRIMARY KEY,
    event_name NVARCHAR(255) NOT NULL,
    is_event_active INT NOT NULL,
    event_major INT,
    event_start_date DATETIME2,
    event_end_date DATETIME2,
    event_part_start_date DATETIME2,
    event_part_end_date DATETIME2,
    event_amount INT,
    event_location NVARCHAR(255),
    event_participant_maximum INT,
    event_description NVARCHAR(255),
    event_note NVARCHAR(255),
    FOREIGN KEY (event_major) REFERENCES major(major_id)
);

--建立 event_host 表格
CREATE TABLE event_host (
    event_id NVARCHAR(255), 
    event_host_id INT, 
    PRIMARY KEY (event_id, event_host_id), 
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (event_host_id) REFERENCES users(user_id)
);

--建立 event_order 表格
CREATE TABLE event_order (
    event_order_id NVARCHAR(50) PRIMARY KEY,
    event_order_amount INT,
    is_event_order_active BIT,
    event_id NVARCHAR(255),
    event_participant_id INT,
    event_participant_date DATETIME2,
    event_participant_note NVARCHAR(255),
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (event_participant_id) REFERENCES users(user_id)
);



-- 插入 events 表格的測試數據
INSERT INTO events (event_id, event_name, is_event_active, event_major, event_start_date, event_end_date, event_part_start_date, event_part_end_date, event_amount, event_location, event_participant_maximum, event_description, event_note)
VALUES
('EV100', 'Tech Conference', 1, 100, GETDATE(), DATEADD(day, 1, GETDATE()), DATEADD(day, 2, GETDATE()), DATEADD(day, 3, GETDATE()), 500, 'Taipei', 300, 'Annual technology conference', 'Registration required'),
('EV101', 'Finance Summit', 1, 100, GETDATE(), DATEADD(day, 1, GETDATE()), DATEADD(day, 2, GETDATE()), DATEADD(day, 3, GETDATE()), 700, 'Kaohsiung', 400, 'Leading finance summit', 'Register online');

-- 插入 event_host 表格的測試數據
INSERT INTO event_host (event_id, event_host_id)
VALUES
('EV100', 100),
('EV101', 101);

-- 插入 event_order 表格的測試數據
INSERT INTO event_order (event_order_id, event_order_amount, is_event_order_active, event_id, event_participant_id, event_participant_date, event_participant_note)
VALUES
('EO100', 500, 1, 'EV100', 100, GETDATE(), 'Confirmed'),
('EO101', 700, 1, 'EV101', 101, GETDATE(), 'Confirmed');

-- courses table
CREATE TABLE [dbo].[courses] (
    [course_id]              NVARCHAR (50)  NOT NULL,
    [course_name]            NVARCHAR (50)  NOT NULL,
    [course_create_user_id]  INT            NOT NULL,
    [course_category]        INT            NOT NULL,
    [course_information]     NVARCHAR (255) NULL,
    [course_description]     NVARCHAR (MAX) NULL,
    [course_enrollment_date] DATETIME2 (7)  NOT NULL,
    [course_start_date]      DATETIME2 (7)  NULL,
    [course_end_date]        DATETIME2 (7)  NULL,
    [course_price]           INT            NULL,
    [course_status]          NVARCHAR (20)  NOT NULL,
    PRIMARY KEY CLUSTERED ([course_id] ASC),
    FOREIGN KEY ([course_category]) REFERENCES [dbo].[major] ([major_id]),
    FOREIGN KEY ([course_create_user_id]) REFERENCES [dbo].[users] ([user_id])
);


CREATE TABLE [dbo].[course_order] (
    [course_order_id]          NVARCHAR (50)  NOT NULL,
    [course_id]                NVARCHAR (50)  NOT NULL,
    [student_id]               INT            NOT NULL,
    [course_order_price]       INT            NULL,
    [course_order_create_date] DATETIME2 (7)  NOT NULL,
    [course_order_remark]      NVARCHAR (255) NULL,
    [course_order_status]      NVARCHAR (50)  DEFAULT ('Pending') NOT NULL,
    PRIMARY KEY CLUSTERED ([course_order_id] ASC),
    FOREIGN KEY ([course_id]) REFERENCES [dbo].[courses] ([course_id]),
    FOREIGN KEY ([student_id]) REFERENCES [dbo].[users] ([user_id])
);

CREATE TABLE [dbo].[course_grade_content] (
    [course_grade_id]      INT           IDENTITY (100, 1) NOT NULL,
    [course_id]            NVARCHAR (50) NOT NULL,
    [student_id]           NVARCHAR (50) NOT NULL,
    [course_grade_score]   INT           NULL,
    [course_grade_comment] NVARCHAR (MAX) NULL,
    CONSTRAINT [PK_course_grade_content] PRIMARY KEY CLUSTERED ([course_grade_id] ASC),
    FOREIGN KEY ([course_id]) REFERENCES [dbo].[courses] ([course_id])
);

CREATE TABLE [dbo].[course_module] (
    [course_module_id]   INT           IDENTITY (1, 1) NOT NULL,
    [course_id]          NVARCHAR (50) NOT NULL,
    [course_module_name] NVARCHAR (50) NOT NULL,
    PRIMARY KEY CLUSTERED ([course_module_id] ASC),
    FOREIGN KEY ([course_id]) REFERENCES [dbo].[courses] ([course_id])
);

CREATE TABLE [dbo].[course_lessons] (
    [course_lesson_id]      INT            IDENTITY (1, 1) NOT NULL,
    [course_module_id]      INT            NOT NULL,
    [course_id]             NVARCHAR (50)  NOT NULL,
    [course_lesson_name]    NVARCHAR (50)  NOT NULL,
    [course_lesson_sort]    NVARCHAR (50)  NULL,
    [lesson_media_url]      NVARCHAR (255) NULL,
    [lesson_media_type]     NVARCHAR (20)  NULL,
    [lesson_media_duration] INT            NULL,
    PRIMARY KEY CLUSTERED ([course_lesson_id] ASC),
    FOREIGN KEY ([course_id]) REFERENCES [dbo].[courses] ([course_id]),
    FOREIGN KEY ([course_module_id]) REFERENCES [dbo].[course_module] ([course_module_id])
);

-- transaction table
-- 1. 建立 job_orders 表
CREATE TABLE job_orders (
    job_orders_id NVARCHAR(50) PRIMARY KEY,  -- 職缺訂單申請ID，主鍵（PK）
    job_application_id INT,                  -- 職缺申請ID，外鍵（FK）
    job_order_date DATETIME2 NULL,           -- 申請訂單日期，允許 NULL
    job_order_status VARCHAR(10) CHECK (job_order_status IN ('Processing', 'Completed', 'Canceled')) NOT NULL,  -- 申請訂單狀態
    job_notes TEXT,                          -- 訂單備註
    job_amount INT NOT NULL,               -- 訂單總金額，不允許 NULL
    FOREIGN KEY (job_application_id) REFERENCES jobs_application(jobs_application_id)  -- 外鍵約束
);


-- 2. 建立 user_transactions 表
CREATE TABLE user_transactions (
    transaction_id NVARCHAR(50) PRIMARY KEY,   -- 交易ID
    user_id INT NOT NULL,                           -- 用戶ID
    transaction_type VARCHAR(10) CHECK (transaction_type IN ('deposit', 'withdrawal', 'payment', 'refund')) NOT NULL, -- 交易類型
    transaction_amount int NOT NULL,                 -- 交易金額
    transaction_status VARCHAR(10) CHECK (transaction_status IN ('pending', 'completed', 'failed')) NOT NULL, -- 交易狀態
    created_at DATETIME2 NOT NULL,          -- 創建時間
	completion_at DATETIME2                 --完成時間
    FOREIGN KEY (user_id) REFERENCES users(user_id), -- 外鍵關聯到用戶表
);

-- 3. 建立 invoices 表
CREATE TABLE invoices (
    invoice_number NVARCHAR(50) PRIMARY KEY,   -- 發票號碼，同時作為主鍵（PK）
    transaction_id NVARCHAR(50),               -- 交易ID，與交易一對一關聯
    job_order_id NVARCHAR(50),                 -- 職缺訂單ID，新增關聯字段
    course_order_id NVARCHAR(50),              -- 課程訂單ID
    event_order_id NVARCHAR(50),               -- 活動訂單ID
    invoice_amount int NOT NULL,               -- 發票金額
    issued_date DATETIME2 NOT NULL,            -- 發票開具日期
    invoice_status VARCHAR(10) CHECK (invoice_status IN ('open', 'canceled')) NOT NULL, -- 發票狀態
    FOREIGN KEY (transaction_id) REFERENCES user_transactions(transaction_id), -- 外鍵關聯到交易表
    FOREIGN KEY (job_order_id) REFERENCES job_orders(job_orders_id),           -- 外鍵關聯到職缺訂單表
    FOREIGN KEY (course_order_id) REFERENCES course_order(course_order_id),    -- 外鍵關聯到課程訂單表
    FOREIGN KEY (event_order_id) REFERENCES event_order(event_order_id)        -- 外鍵關聯到活動訂單表
);
