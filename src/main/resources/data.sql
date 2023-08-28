insert into member (email, created_at, modified_at, member_no, password, birthday, name, nickname)
values('dawon@gmail.com',now()-1,now(),1,'12345678','2000-01-01','test','nick test');


insert into post (created_at, modified_at, content, img_path, title, member_email)
VALUES('2000-01-01',now(),'test-content','test-img-path','testtitle','dawon@gmail.com');