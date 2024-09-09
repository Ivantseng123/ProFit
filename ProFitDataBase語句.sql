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
	service_pictureURL_1 VARCHAR(200),
	service_pictureURL_2 VARCHAR(200),
	service_pictureURL_3 VARCHAR(200),
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
	image_pictureURL VARCHAR(200),
	
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
    [course_id]          NVARCHAR (50) NOT NULL,
    [student_id]         NVARCHAR (50) NOT NULL,
    [course_grade_score] INT           NULL,
    [course_grade_id]    INT           IDENTITY (100, 1) NOT NULL,
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
    created_at DATETIME2 NOT NULL,          -- 交易時間
    FOREIGN KEY (user_id) REFERENCES users(user_id), -- 外鍵關聯到用戶表
);

-- 3. 建立 invoices 表
CREATE TABLE invoices (
    invoice_id NVARCHAR(50) PRIMARY KEY,	   -- 發票ID
    transaction_id NVARCHAR(50),               -- 交易ID，與交易一對一關聯
    job_order_id NVARCHAR(50),                 -- 職缺訂單ID，新增關聯字段
	course_order_id NVARCHAR(50),			   -- 課程訂單ID
	event_order_id NVARCHAR(50),			   -- 活動訂單ID
    invoice_number VARCHAR(10) NOT NULL,       -- 發票號碼
    invoice_amount int NOT NULL,					   -- 發票金額
    issued_date DATETIME2 NOT NULL,            -- 發票開具日期
    invoice_status VARCHAR(10) CHECK (invoice_status IN ('open', 'canceled')) NOT NULL, -- 發票狀態
    FOREIGN KEY (transaction_id) REFERENCES user_transactions(transaction_id), -- 外鍵關聯到交易表
    FOREIGN KEY (job_order_id) REFERENCES job_orders(job_orders_id), -- 外鍵關聯到職缺訂單表
	FOREIGN KEY (course_order_id) REFERENCES course_order(course_order_id), -- 外鍵關聯到職缺訂單表
	FOREIGN KEY (event_order_id) REFERENCES event_order(event_order_id) -- 外鍵關聯到職缺訂單表
);
