drop table if exists search_key_name;

/*==============================================================*/
/* Table: search_key_name                                       */
/*==============================================================*/
create table search_key_name
(
   id                   varchar(32) not null,
   key_name             varchar(50) comment '关键字',
   font_color           varchar(50) comment '字体颜色',
   state                varchar(1) comment '区别首页与搜索页关键字',
   primary key (id)
);

alter table search_key_name comment '搜索关键字';