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
/*==============================================================*/
/* search_key_name    搜索关键字初始化                          */
/*==============================================================*/
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("1","香蕉","0","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("2","苹果","1","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("3","橘子","1","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("4","西瓜","0","0");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("5","冬瓜","0","0");

INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("6","香蕉","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("7","苹果","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("8","橘子","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("9","西瓜","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("10","冬瓜","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("11","南瓜","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("12","椰子","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("13","桃子","1","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("14","梨子","0","1");
INSERT INTO search_key_name(id,key_name,font_color,state) VALUES("15","栗子","0","1");