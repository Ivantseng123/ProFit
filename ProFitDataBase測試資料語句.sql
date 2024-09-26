	INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'John Doe', N'john.doe@example.com', N'e99a18c428cb38d5f260853678922e03', N'0912-345-678', N'台北市', 1, N'https://example.com/john.jpg', 0, N'台北市+新北市+基隆市', N'5 years', N'個人兼職', 1, N'Experienced full-stack developer.', NULL)
INSERT [dbo].[users] ([user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'Jane Smith', N'jane.smith@example.com', N'abcde1234567890fghijk9876543210', N'0923-456-789', N'新北市', 2, N'https://example.com/jane.jpg', 0, N'新北市+桃園市+台中市', N'3 years', N'專職SOHO', 1, N'Creative designer with a passion for UI/UX.', NULL)
INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'Alice Brown', N'alice.brown@example.com', N'5d41402abc4b2a76b9719d911017c592', N'0934-567-890', N'桃園市', 1, N'https://example.com/alice.jpg', 0, N'桃園市+新竹市+新竹縣', N'4 years', N'工作室', 1, N'Professional photographer with experience in portraits.', NULL)
INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'Bob Johnson', N'bob.johnson@example.com', N'098f6bcd4621d373cade4e832627b4f6', N'0945-678-901', N'新竹市', 2, N'https://example.com/bob.jpg', 0, N'新竹市+新竹縣+台北市', N'6 years', N'兼職上班族', 1, N'Expert in video production and editing.', NULL)
INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'Charlie Davis', N'charlie.davis@example.com', N'c4ca4238a0b923820dcc509a6f75849b', N'0956-789-012', N'新竹縣', 1, N'https://example.com/charlie.jpg', 0, N'新竹縣+基隆市+台中市', N'2 years', N'公司', 1, N'Freelance writer specializing in tech articles.', NULL)
INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'游峻翰', N'johnyu91308@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'0917-276358', N'花蓮縣', 3, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=b8ced6a1-0b56-404c-b99a-dfd5df3041c4', 0, N'新北市', N'7-8年工作經驗', NULL, 0, N'', CAST(N'2024-08-30T11:15:46.0000000' AS DateTime2))
INSERT [dbo].[users] ([user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'測試', N'test@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'0917-276358', N'臺北市', 2, N'150/馬邦德.jpeg', 0, N'臺北市', N'無工作經驗', N'工作室', 0, N'hhhhhhh', CAST(N'2024-08-30T11:52:29.0000000' AS DateTime2))
INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'測試', N'aaaaa@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'0917-278888', N'花蓮縣', 1, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=8103fba3-03a0-4a9e-9e40-2c108cc157cc', 4, N'臺中市', N'0-1年工作經驗', N'學生', 0, N'asdasdasdasd', CAST(N'2024-09-02T10:11:12.0000000' AS DateTime2))
INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'aaaaaaa', N'aaaaaaa@gmail.com', N'4b179810791a19af3bfeafbdcf0aca274d9ac7d5f063b8e0b75189360c0cf3a3', N'0917-278888', N'基隆市', 1, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=f6b449df-f640-4616-8142-da53a632ec92', 0, N'花蓮縣', N'10年以上年工作經驗', N'工作室', 0, N'aaaaaaa', CAST(N'2024-09-09T15:59:50.0000000' AS DateTime2))
INSERT [dbo].[users] ( [user_name], [user_email], [user_passwordHash], [user_phoneNumber], [user_city], [user_identity], [user_pictureURL], [user_balance], [freelancer_location_prefer], [freelancer_exprience], [freelancer_identity], [freelancer_profile_status], [freelancer_description], [user_register_time]) VALUES ( N'游峻翰', N'test111@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'0917-276358', N'臺北市', 3, N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=d1d95a04-2bb6-4989-8186-c22287f77799', 0, N'臺北市', N'無工作經驗', NULL, 0, N'', CAST(N'2024-09-11T10:29:02.0000000' AS DateTime2))

INSERT [dbo].[employer_application] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES ( 101, N'ABC Corp', N'123 Taipei Road', N'IT', N'0223456789', N'A123456789', N'http://example.com/taxdoc1.jpg', N'A123456789', N'http://example.com/id1_front.jpg', N'http://example.com/id1_back.jpg', 1)
INSERT [dbo].[employer_application] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES ( 102, N'XYZ Inc', N'456 Kaohsiung Street', N'Finance', N'0223456790', N'B123456789', N'http://example.com/taxdoc2.jpg', N'B123456789', N'http://example.com/id2_front.jpg', N'http://example.com/id2_back.jpg', 1)
INSERT [dbo].[employer_application] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES ( 103, N'測試', N'澎湖縣測試測試', N'礦業及土石採取業', N'1111111', N'5454654646', N'', N'H123456789', N'', N'', 0)
INSERT [dbo].[employer_application] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_taxID_docURL], [user_national_id], [idCard_pictureURL_1], [idCard_pictureURL_2], [application_check]) VALUES ( 104, N'55555', N'嘉義縣55555', N'礦業及土石採取業', N'55555', N'55555', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=188ab1e6-c2b3-4578-92d1-93ae204dfc5e', N'H123456789', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=4e2da6ae-dc3e-4931-a4a5-61a1ac116582', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F%E9%A6%AC%E9%82%A6%E5%BE%B7.jpeg?alt=media&token=188ab1e6-c2b3-4578-92d1-93ae204dfc5e', NULL)

INSERT [dbo].[employer_profile] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_capital], [company_description], [company_photoURL]) VALUES ( 101, N'ABC Corp', N'123 Taipei Road', N'IT', N'0223456789', N'A123456789', N'200', N'10000000', N'Leading IT company in Taiwan', N'http://example.com/abccorp.jpg')
INSERT [dbo].[employer_profile] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_capital], [company_description], [company_photoURL]) VALUES ( 102, N'XYZ Inc', N'臺北市 Kaohsiung Street', N'農、林、漁、牧業', N'0223456790', N'B123456789', N'1-10', N'8000000', N'Top finance company', N'http://example.com/xyzinc.jpg')
INSERT [dbo].[employer_profile] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_capital], [company_description], [company_photoURL]) VALUES ( 103, N'資展國際', N'桃園市中壢區新生路二段421號', N'其他', N'03123456789', N'123456789', N'1-10', N'464646464464', N'', N'1145/1965770a560615a908f98b80e558752e-65960.jpg')
INSERT [dbo].[employer_profile] ( [user_id], [company_name], [company_address], [company_category], [company_phoneNumber], [company_taxID], [company_numberOfemployee], [company_capital], [company_description], [company_photoURL]) VALUES ( 104, N'66666', N'花蓮縣66666', N'專業、科學及技術服務業', N'66666', N'66666', N'5000以上', N'66666', N'66666', N'https://firebasestorage.googleapis.com/v0/b/profit-e686b.appspot.com/o/userUpload%2F1965770a560615a908f98b80e558752e-65960.jpg?alt=media&token=7783bf37-e935-4cb2-bd07-ec7a05d96a06')

INSERT [dbo].[password_reset_tokens] ( [user_id], [user_tokenHash], [expiration_time]) VALUES ( 101, N'tokenhash100',default);
INSERT [dbo].[password_reset_tokens] ( [user_id], [user_tokenHash], [expiration_time]) VALUES ( 102, N'tokenhash101',default);

-- 1. jobs 表
INSERT INTO jobs (jobs_user_id, jobs_title, jobs_posting_date, jobs_application_deadline, jobs_description, jobs_status, jobs_required_skills, jobs_location, jobs_max_salary, jobs_min_salary, jobs_worktime, jobs_number_of_openings)
VALUES
(101, N'資深軟體工程師', GETDATE(), DATEADD(month, 1, GETDATE()), N'尋找有經驗的軟體工程師加入我們的團隊', 1, N'Java, Spring Boot, MySQL', N'台北市', 150000, 100000, '09:00:00', 2),
(102, N'UI/UX 設計師', DATEADD(day, -5, GETDATE()), DATEADD(month, 2, GETDATE()), N'創意UI/UX設計師，負責產品界面設計', 1, N'Figma, Adobe XD, Sketch', N'新北市', 120000, 80000, '09:30:00', 1),
(103, N'數據分析師', DATEADD(day, -10, GETDATE()), DATEADD(month, 1, GETDATE()), N'負責公司數據分析和報告', 1, N'Python, R, SQL, Tableau', N'台中市', 130000, 90000, '09:00:00', 3),
(104, N'行銷經理', DATEADD(day, -3, GETDATE()), DATEADD(month, 3, GETDATE()), N'負責公司整體行銷策略', 1, N'數位行銷, SEO, 社群媒體管理', N'高雄市', 140000, 100000, '09:00:00', 1),
(105, N'前端開發工程師', GETDATE(), DATEADD(month, 2, GETDATE()), N'開發responsive網站和web應用', 1, N'HTML, CSS, JavaScript, React', N'台北市', 110000, 80000, '09:30:00', 2);

-- 2. jobs_application 表
INSERT INTO jobs_application (jobs_application_posting_id, jobs_application_member_id, jobs_application_date, jobs_application_status, jobs_application_contract)
VALUES
(1, 101, DATEADD(day, -2, GETDATE()), 1, NULL),
(2, 102, DATEADD(day, -1, GETDATE()), 2, NULL),
(3, 103, GETDATE(), 1, NULL),
(4, 104, DATEADD(day, -3, GETDATE()), 3, NULL),
(5, 105, DATEADD(day, -1, GETDATE()), 1, NULL);

-- 3. jobs_application_project 表
INSERT INTO jobs_application_project (jobs_application_id, jobs_application_status, jobs_project, jobs_amount)
VALUES
(1, 1, N'網站重構專案', 50000),
(2, 2, N'手機APP UI設計', 30000),
(3, 1, N'客戶數據分析報告', 40000),
(4, 3, N'品牌推廣活動', 60000),
(5, 1, N'電商平台前端開發', 45000);

-- 4. major_category 表
INSERT INTO major_category (major_category_id, category_name)
VALUES
(100, N'軟體開發'),
(200, N'設計'),
(300, N'數據分析'),
(400, N'行銷'),
(500, N'專案管理');

-- 5. major 表
INSERT INTO major (major_id, major_name, major_category_id, major_description)
VALUES
(100, N'Java開發', 100, N'Java語言及相關框架開發'),
(101, N'UI/UX設計', 200, N'使用者介面和體驗設計'),
(102, N'數據科學', 300, N'大數據分析和機器學習'),
(103, N'數位行銷', 400, N'線上營銷策略和執行'),
(104, N'敏捷專案管理', 500, N'使用敏捷方法論管理專案');

-- 6. user_major 表
INSERT INTO user_major (user_id, major_id)
VALUES
(101, 100),
(102, 101),
(103, 102),
(104, 103),
(105, 104);

-- 7. service 表
INSERT INTO service (user_id, major_id, service_title, service_content, service_price, service_unit_name, service_duration, service_createdate, service_updatedate, service_pictureURL_1, service_pictureURL_2, service_pictureURL_3, service_status)
VALUES
(101, 100, N'Java Web應用開發', N'提供Java Web應用開發服務，包括後端API和資料庫設計', 5000, N'小時', 40.0, GETDATE(), GETDATE(), 'http://example.com/java1.jpg', 'http://example.com/java2.jpg', 'http://example.com/java3.jpg', 1),
(102, 101, N'網站UI設計', N'為您的網站提供現代化、用戶友好的UI設計', 4000, N'專案', 20.0, DATEADD(day, -10, GETDATE()), DATEADD(day, -10, GETDATE()), 'http://example.com/ui1.jpg', 'http://example.com/ui2.jpg', 'http://example.com/ui3.jpg', 1),
(103, 102, N'商業數據分析', N'使用先進的數據分析技術，為您的業務提供洞察', 6000, N'報告', 30.0, DATEADD(day, -20, GETDATE()), DATEADD(day, -15, GETDATE()), 'http://example.com/data1.jpg', 'http://example.com/data2.jpg', 'http://example.com/data3.jpg', 1),
(104, 103, N'社群媒體行銷策略', N'制定和執行有效的社群媒體行銷策略', 3500, N'月', 30.0, DATEADD(day, -30, GETDATE()), DATEADD(day, -25, GETDATE()), 'http://example.com/marketing1.jpg', 'http://example.com/marketing2.jpg', 'http://example.com/marketing3.jpg', 1),
(105, 104, N'敏捷專案管理諮詢', N'協助您的團隊實施敏捷專案管理方法', 7000, N'天', 5.0, DATEADD(day, -5, GETDATE()), DATEADD(day, -5, GETDATE()), 'http://example.com/agile1.jpg', 'http://example.com/agile2.jpg', 'http://example.com/agile3.jpg', 1);

-- 8. collection 表
INSERT INTO collection (user_id, major_id, collection_cover_img_id, collection_name)
VALUES
(101, 100, NULL, N'Java專案作品集'),
(102, 101, NULL, N'UI設計作品集'),
(103, 102, NULL, N'數據分析專案集'),
(104, 103, NULL, N'行銷案例集'),
(105, 104, NULL, N'專案管理經驗集');

-- 9. image 表
INSERT INTO image (collection_id, image_pictureURL)
VALUES
(1, 'http://example.com/java_project1.jpg'),
(1, 'http://example.com/java_project2.jpg'),
(2, 'http://example.com/ui_design1.jpg'),
(2, 'http://example.com/ui_design2.jpg'),
(3, 'http://example.com/data_analysis1.jpg');

-- 10. video 表
INSERT INTO video (collection_id, video_url, video_disc)
VALUES
(1, 'http://example.com/java_demo.mp4', N'Java專案演示'),
(2, 'http://example.com/ui_walkthrough.mp4', N'UI設計流程展示'),
(3, 'http://example.com/data_presentation.mp4', N'數據分析結果報告'),
(4, 'http://example.com/marketing_case.mp4', N'行銷活動回顧'),
(5, 'http://example.com/project_management.mp4', N'專案管理經驗分享');

-- 11. courses 表
INSERT INTO courses (course_id, course_name, course_create_user_id, course_category, course_information, course_description, course_enrollment_date, course_start_date, course_end_date, course_price, course_status)
VALUES
('C0100', N'Java 進階開發', 101, 100, N'適合有基礎Java經驗的開發者', N'學習Java高級特性和設計模式', GETDATE(), DATEADD(month, 1, GETDATE()), DATEADD(month, 3, GETDATE()), 15000, 'Active'),
('C0101', N'UI/UX設計原理', 102, 101, N'零基礎入門UI/UX設計', N'學習設計思維和工具使用', DATEADD(day, -10, GETDATE()), DATEADD(day, 20, GETDATE()), DATEADD(month, 2, GETDATE()), 12000, 'Active'),
('C0102', N'Python數據分析', 103, 102, N'適合有基礎Python經驗的學習者', N'使用Python進行數據處理和視覺化', DATEADD(day, -20, GETDATE()), DATEADD(day, 10, GETDATE()), DATEADD(month, 2, GETDATE()), 18000, 'Active'),
('C0103', N'數位行銷策略', 104, 103, N'適合行銷新手和有經驗者', N'學習現代數位行銷技巧和策略', DATEADD(day, -5, GETDATE()), DATEADD(month, 1, GETDATE()), DATEADD(month, 3, GETDATE()), 10000, 'Active'),
('C0104', N'敏捷Scrum實務', 105, 104, N'適合專案經理和團隊領導', N'學習Scrum框架和實施技巧', GETDATE(), DATEADD(month, 2, GETDATE()), DATEADD(month, 4, GETDATE()), 20000, 'Active');

-- 12. course_order 表
INSERT INTO course_order (course_order_id, course_id, student_id, course_order_price, course_order_create_date, course_order_remark, course_order_status)
VALUES
('CR100', 'C0100', 101, 15000, GETDATE(), N'期待學習Java進階知識', 'Completed'),
('CR101', 'C0101', 102, 12000, DATEADD(day, -1, GETDATE()), N'想提升UI設計能力', 'Completed'),
('CR102', 'C0102', 103, 18000, DATEADD(day, -2, GETDATE()), N'需要學習數據分析技能', 'Pending'),
('CR103', 'C0103', 104, 10000, DATEADD(day, -3, GETDATE()), N'提升數位行銷能力', 'Completed'),
('CR104', 'C0104', 105, 20000, GETDATE(), N'學習敏捷專案管理', 'Pending');

-- 13. course_grade_content 表
INSERT INTO course_grade_content (course_id, student_id, course_grade_score, course_grade_comment)
VALUES
('C0100', '101', 90, N'表現優秀，掌握了大部分進階概念'),
('C0101', '102', 85, N'設計作品有創意，需要加強用戶體驗考量'),
('C0102', '103', NULL, NULL),
('C0103', '104', 88, N'策略規劃能力強，需要更多實踐'),
('C0104', '105', NULL, NULL);

-- 14. course_module 表
INSERT INTO course_module (course_id, course_module_name)
VALUES
('C0100', N'Java 多執行緒程式設計'),
('C0100', N'設計模式在Java中的應用'),
('C0101', N'使用者研究方法'),
('C0101', N'介面設計原則'),
('C0102', N'Python數據處理基礎');

-- 15. course_lessons 表
INSERT INTO course_lessons (course_module_id, course_id, course_lesson_name, course_lesson_sort, lesson_media_url, lesson_media_type, lesson_media_duration)
VALUES
(1, 'C0100', N'理解Java執行緒生命週期', '1', 'http://example.com/java_thread_lifecycle.mp4', 'video', 45),
(1, 'C0100', N'同步與互斥', '2', 'http://example.com/java_synchronization.mp4', 'video', 50),
(2, 'C0101', N'用戶訪談技巧', '1', 'http://example.com/user_interview.mp4', 'video', 40),
(2, 'C0101', N'繪製用戶旅程地圖', '2', 'http://example.com/user','website',30);

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

-- 1. job_orders 表
INSERT INTO job_orders (job_orders_id, job_application_id, job_order_date, job_order_status, job_notes, job_amount)
VALUES
('J0100', 1, DATEADD(day, -5, GETDATE()), 'Processing', N'網站重構專案訂單', 50000),
('J0101', 2, DATEADD(day, -4, GETDATE()), 'Completed', N'手機APP UI設計訂單', 30000),
('J0102', 3, DATEADD(day, -3, GETDATE()), 'Processing', N'客戶數據分析報告訂單', 40000),
('J0103', 4, DATEADD(day, -2, GETDATE()), 'Canceled', N'品牌推廣活動訂單', 60000),
('J0104', 5, DATEADD(day, -1, GETDATE()), 'Processing', N'電商平台前端開發訂單', 45000);

-- 2. user_transactions 表
INSERT INTO user_transactions (transaction_id, user_id, transaction_type, transaction_amount, transaction_status, created_at, completion_at)
VALUES
('TR001', 101, 'payment', 50000, 'completed', DATEADD(day, -5, GETDATE()), DATEADD(day, -5, GETDATE())),
('TR002', 102, 'deposit', 100000, 'completed', DATEADD(day, -4, GETDATE()), DATEADD(day, -4, GETDATE())),
('TR003', 103, 'payment', 40000, 'pending', DATEADD(day, -3, GETDATE()), NULL),
('TR004', 104, 'withdrawal', 20000, 'completed', DATEADD(day, -2, GETDATE()), DATEADD(day, -2, GETDATE())),
('TR005', 105, 'refund', 15000, 'pending', DATEADD(day, -1, GETDATE()), NULL);

-- 3. invoices 表
INSERT INTO invoices (invoice_number, transaction_id, job_order_id, course_order_id, event_order_id, invoice_amount, issued_date, invoice_status)
VALUES
('INV001', 'TR001', 'J0100', NULL, NULL, 50000, DATEADD(day, -5, GETDATE()), 'open'),
('INV002', 'TR002', NULL, 'CR100', NULL, 15000, DATEADD(day, -4, GETDATE()), 'open'),
('INV003', 'TR003', 'J0101', NULL, NULL, 40000, DATEADD(day, -3, GETDATE()), 'open'),
('INV004', 'TR004', NULL, NULL, 'EO100', 500, DATEADD(day, -2, GETDATE()), 'open'),
('INV005', 'TR005', NULL, 'CR101', NULL, 12000, DATEADD(day, -1, GETDATE()), 'canceled');
