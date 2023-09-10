insert into member (email, created_at, modified_at, member_no, password, birthday, name, nickname)
values('d@gmail.com',now()-1,now(),1,'asdf1234','2000-01-01','test','nick test');
insert into post (created_at, modified_at, content, title, member_email)
VALUES('2000-01-01',now(),'test-content','test-title','d@gmail.com');

# -- post 테이블에서 부모 테이블(`member`)의 외래 키를 참조하는 레코드를 삭제
# DELETE FROM post WHERE member_email = 'dawon@gmail.com';
#
# -- 이제 부모 테이블(`member`)의 레코드를 삭제할 수 있습니다.
# DELETE FROM member WHERE email = 'dawon@gmail.com';


