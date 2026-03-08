-- 1. 이벤트 생성
INSERT INTO event (id, title, description, thumbnail_image_url, status, started_at, ended_at, created_at, updated_at)
VALUES (1, '소울 푸드 운세', '사주와 심리로 알아보는 당신의 메뉴', 'https://img.com/main.png', 'OPEN', now(), now() + interval '1 year', now(), now());

-- 2. 질문 생성
INSERT INTO question (id, event_id, content, order_no) VALUES (1, 1, '오늘 기분은?', 1);
INSERT INTO question (id, event_id, content, order_no) VALUES (2, 1, '좋아하는 계절은?', 2);

-- 3. 선택지 생성
INSERT INTO question_option (id, question_id, option_text, score) VALUES (1, 1, '매우 좋음', 50);
INSERT INTO question_option (id, question_id, option_text, score) VALUES (2, 1, '그저 그럼', 20);

-- 4. 결과지 생성 (generateMatchCode의 모든 경우의 수 대비)
INSERT INTO test_results (id, result_code, title, description, image_url)
VALUES (1, 'FIRE_HIGH', '열정의 마라탕', '당신은 뜨거운 에너지를 가진 사람입니다.', 'https://img.com/fire_high.png');

INSERT INTO test_results (id, result_code, title, description, image_url)
VALUES (2, 'WATER_LOW', '차분한 평양냉면', '깊고 고요한 지혜를 가진 사람입니다.', 'https://img.com/water_low.png');