-- 병원 데이터
INSERT INTO hospital(hid, hpwd, apino ) VALUES ('hospital1', '1234', 3901); -- 의료법인 길의료재단 길병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital2', '1234', 2310); -- 인하대학교의과대학부속병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital3', '1234', 6234); -- 가톨릭관동대학교인천성모병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital4', '1234', 2532); -- 가톨릭관동대학교국제성모병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital5', '1234', 0366); -- 검단탑병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital6', '1234', 8135); -- 인천세종병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital7', '1234', 9396); -- 의료법인 나사렛의료재단 나사렛국제병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital8', '1234', 7782); -- 의료법인 루가의료재단 나은병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital9', '1234', 2924); -- 인천사랑병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital10', '1234', 5460); -- 안은의료재단부평세림병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital11', '1234', 8670); -- 한림병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital12', '1234', 0008); -- 비에스종합병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital13', '1234', 5555); -- 온누리병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital14', '1234', 1072); -- 의료법인 성세의료재단 뉴 성민병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital15', '1234', 6561); -- 인천광역시의료원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital16', '1234', 8410); -- 인천기독병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital17', '1234', 5698); -- 현대유비스병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital18', '1234', 1733); -- 인천광역시의료원백령병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital19', '1234', 3169); -- 인천힘찬종합병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital20', '1234', 4009); -- 인천적십자병원
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital21', '1234', 8118); -- 인천백병원
-- 사용자 데이터
INSERT INTO user(uid, upwd, uname, ustate, uphone) VALUES ('admin', 'admin', '관리자', 1, '010-2222-2222');
INSERT INTO user(uid, upwd, uname, ustate, uphone) VALUES ('abc123', '1234', '이민수', 0, '010-2222-2223');
INSERT INTO user(uid, upwd, uname, ustate, uphone) VALUES ('1111', '1111', '장민우', 0, '010-2222-2225');
INSERT INTO user(uid, upwd, uname, ustate, uphone) VALUES ('jeon', 'jeon', '전은서', 0, '010-2222-2226');
INSERT INTO user(uid, upwd, uname, ustate, uphone) VALUES ('bear', '0305', '류예나', 0, '010-2222-2227');

-- 1. category (카테고리 샘플 데이터)
insert into category (cname) values
('공지사항'),
('AED 건의사항');

-- 2. board (uno=1~2번 회원이 작성, cno는 위에서 만든 카테고리)
insert into board (btitle, bcontent, bview, cno, uno,create_at) values
('첫 번째 공지입니다.', '길병원 관련 안내입니다.', 3, 1, 1,NOW()),
('문의드립니다', 'AED 더 설치해주세요', 5, 2, 2,NOW()),
('AED 설치 요망', '인천 남동구에 AED 더 설치해주세요', 7, 2, 3,NOW());
-- 3. ireply (uno=1이 댓글 작성, bno는 위 게시글 번호로 가정)
insert into ireply (rcontent, uno, bno,create_at) values
('설치 완료', 1, 2,NOW()),
('설치 완료', 1, 3,NOW());

-- 4. hlog (병원 방문 기록, uno=1,2,3,4번 사용자, hno=1~4로 가정)
insert into hlog (llat, llong, lstate, uno, hno,create_at) values
('37.448894', '126.702515', 1, 1, 1,NOW()),  -- 길병원
('37.456486', '126.633448', 1, 2, 2,NOW()),  -- 인하대병원
('37.474467', '126.642024', 1, 3, 3,NOW()),  -- 인천성모병원
('37.518291', '126.719215', 1, 4, 4,NOW());  -- 국제성모병원
