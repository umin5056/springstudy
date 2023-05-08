-- 목록(pagination, scroll)
-- 계층

-- 첨부
-- 댓글
-- 사용자

-- 1게시글 - 1첨부 - 1테이블
-- 1게시글 - 다중첨부 - 2테이블

-- 다중 첨부 게시판

-- 첨부 파일 정보 테이블
drop table attach;
create table attach(
    attach_no       number              not null, -- pk
    path            varchar2(300 byte)  not null, -- 첨부 파일 경로
    origin_name     varchar2(300 byte)  not null, -- 첨부 파일의 원래 이름
    filesystem_name varchar2(50 byte)   not null, -- 첨부 파일의 저장 이름 
    download_count  number,                       -- 다운로드 횟수
    has_thumbnail   number,                       -- 썸네일이 있으면 1, 없으면 0  
    upload_no       number                        -- 게시글 fk
);

-- 게시글 정보 테이블   
drop table upload;
create table upload(
    upload_no       number              not null, -- pk
    upload_title    varchar2(1000 byte) not null, -- 제목
    upload_content  clob,                         -- 내용
    created_at      timestamp,                    -- 작성일
    modified_at     timestamp                     -- 수정일
       
);

-- 기본키
alter table attach
    add constraint pk_attach
        primary key(attach_no);

alter table upload
    add constraint pk_upload
        primary key(upload_no);
        
-- 외래키
alter table attach
    add constraint fk_attach_upload
        foreign key(upload_no) REFERENCES upload(upload_no)
            on delete cascade;
        
-- 시퀀스
drop sequence attach_seq;
create sequence attach_seq nocache;
drop sequence upload_seq;
create sequence upload_seq nocache;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        