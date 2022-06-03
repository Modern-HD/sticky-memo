drop table if exists memos cascade;

create table if not exists memos (
	id int(5) not null AUTO_INCREMENT,
	types varchar(10) not null,
	color varchar(10) not null,
	content text not null,
	created datetime not null,
	modified timestamp not null default CURRENT_TIMESTAMP,
	primary key(id)
);

insert into memos(types, color, content, created, modified) values
('post', 'pink', 'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Corrupti vero perferendis tempore doloribus excepturi voluptates laborum!', now(), now()),
('post', 'yellow', 'Lorem ipsum dolor sit, amet consectetur adipisicing elit. Corrupti vero perferendis tempore doloribus excepturi voluptates laborum!', now(), now());
