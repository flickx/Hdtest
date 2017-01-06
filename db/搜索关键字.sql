drop table if exists search_key_name;

/*==============================================================*/
/* Table: search_key_name                                       */
/*==============================================================*/
create table search_key_name
(
   id                   varchar(32) not null,
   key_name             varchar(50) comment '�ؼ���',
   font_color           varchar(50) comment '������ɫ',
   state                varchar(1) comment '������ҳ������ҳ�ؼ���',
   primary key (id)
);

alter table search_key_name comment '�����ؼ���';
/*==============================================================*/
/* search_key_name    �����ؼ��ֳ�ʼ��                          */
/*==============================================================*/
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("1","�㽶","0","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("2","ƻ��","1","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("3","����","1","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("4","����","0","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("5","����","0","0");

INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("6","�㽶","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("7","ƻ��","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("8","����","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("9","����","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("10","����","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("11","�Ϲ�","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("12","Ҭ��","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("13","����","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("14","����","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("15","����","0","1");