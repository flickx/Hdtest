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