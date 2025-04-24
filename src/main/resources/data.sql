
-- 병원 데이터
INSERT INTO hospital(hid, hpwd, apino) VALUES ('hospital1', '1234', 3901); -- 의료법인 길의료재단 길병원
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

-- 공지사항 게시판
INSERT INTO adboard(adbtitle, adbcontent,create_at, uno) VALUES ('AED 가이드', '동영상 참고',now(), 1);
INSERT INTO adboard(adbtitle, adbcontent,create_at, uno) VALUES ('CPR 가이드', '사진 참고',now(), 1);

-- AED 설치 건의 게시판
INSERT INTO install(ititle, icontent,create_at, uno) VALUES
('설치 건의', '인천 부평구 부평1동에 설치 해주세요',now(), 5),
('설치 건의', '인천 부평구 부평1동에 설치 해주세요',now(), 2),
('설치 건의', '인천 부평구 부평1동에 설치 해주세요',now(), 3),
('설치 건의', '인천 부평구 부평1동에 설치 해주세요',now(), 4);

-- AED 답글
INSERT INTO ireply(rcontent,create_at, uno, ino) VALUES
('수용하도록',now(), 1, 1),
('수용',now(), 1, 2),
('하도록',now(), 1, 3),
('설치하겠습니다',now(), 1, 4);

-- 호출 로그
INSERT INTO hlog(lloc, uno, hno, create_at) VALUES
('인천시 남동구', 3, 1, now()),
('인천시 부평구', 4, 2, now()),
('인천시 서구', 5, 3, now());

-- 일반 사용자(uno=3)가 답글 작성
INSERT INTO ireply (rcontent,create_at, uno, ino) VALUES ('저도 찬성입니다',now(), 3, 2);
-- 상태 확인 → 그대로 0
SELECT ino, istate FROM install WHERE ino = 2;
-- 관리자(uno=1)가 답글 작성
INSERT INTO ireply (rcontent,create_at, uno, ino) VALUES ('설치하겠습니다',now(), 1, 2);
-- 상태 확인 → 이제 1로 바뀜
SELECT ino, istate FROM install WHERE ino = 2;