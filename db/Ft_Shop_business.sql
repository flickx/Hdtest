/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/10/25 15:16:05                          */
/*==============================================================*/


drop table if exists business;

drop table if exists business_bank;

drop table if exists business_base;

drop table if exists business_manage;

drop table if exists business_store;

drop table if exists business_store_classify;

drop table if exists business_store_login;

drop table if exists business_store_manage_category;

drop table if exists business_store_rank;

/*==============================================================*/
/* Table: business                                              */
/*==============================================================*/
create table business
(
   id                   int(50) not null comment '商家Id',
   base_id              varchar(100) binary comment '商家基本信息ID',
   manage_id            varchar(100) comment '商家经营ID',
   bank_id              varchar(100) comment '商家银行ID',
   operate_id           varchar(100) comment '操作人ID',
   operate_time         varchar(100) comment '操作时间',
   create_id            varchar(100) comment '创建人Id',
   create_time          varchar(50) comment '创建时间',
   state                int comment '用于数据表的逻辑操作',
   primary key (id)
);

alter table business comment '商家中间表';

/*==============================================================*/
/* Table: business_bank                                         */
/*==============================================================*/
create table business_bank
(
   id                   varchar(100) not null comment '商家银行ID',
   bank_account_name    varchar(100) not null comment '银行开户名',
   bank_account         varchar(50) not null comment '银行账户',
   bank_name            varchar(100) not null comment '开户行名称',
   bank_account_img     varchar(500) binary not null comment '银行开户证明扫描图',
   operate_id           varchar(100) comment '操作人ID，记录该数据的人员ID',
   operate_time         varchar(50) comment '操作时间',
   create_id            varchar(100) comment '创建人ID',
   create_time          varchar(50) comment '创建时间',
   state                varchar(1) comment '用于数据表的逻辑操作',
   primary key (id)
);

alter table business_bank comment '商家银行信息数据表';

/*==============================================================*/
/* Table: business_base                                         */
/*==============================================================*/
create table business_base
(
   id                   varchar(100) not null comment '商家基本信息ID',
   company_address      varchar(500) binary not null comment '公司地址',
   company_name         varchar(100) binary not null comment '公司名字',
   registered_capital   varchar(50) comment '注册资本',
   linkman_name         varchar(50) not null comment '联系人姓名',
   linkman_number       varchar(50) not null comment '联系人手机号码',
   email                varchar(50) not null comment '电子邮箱',
   operate_id           varchar(100) comment '操作人ID，记录录入数据的人员ID',
   operate_time         varchar(50) comment '操作人时间',
   create_id            varchar(100) comment 'create_id#创建人Id',
   create_time          varchar(50) comment '创建时间',
   state                varchar(1) comment '用于数据表的逻辑操作',
   primary key (id)
);

alter table business_base comment '商家基本信息数据表';

/*==============================================================*/
/* Table: business_manage                                       */
/*==============================================================*/
create table business_manage
(
   id                   varchar(100) not null comment '商家经营ID',
   legal_person         varchar(50) not null comment '法定代表人',
   id_card              varchar(50) not null comment '法定代表人身份证',
   Id_card_face_img     varchar(500) binary not null comment '身份证正面图',
   id_card_con_img      varchar(500) binary not null comment '身份证反面图',
   business_licence_number varchar(100) not null comment '营业执照号',
   establish_time       varchar(50) not null comment '成立日期',
   manage_scope         varchar(500) binary not null comment '经营范围',
   business_licence_img varchar(500) binary not null comment '营业执照扫描图',
   busines_slicence_date varchar(100) not null comment '营业执照有效期，存储方式为1900-01-01 ——1999-01-01',
   operate_id           varchar(100) comment '操作人ID，记录该项数据的人员ID',
   operate_time         varchar(50) comment '操作时间',
   create_id            varchar(100) comment '创建人Id',
   create_time          varchar(50) comment '创建时间',
   state                varchar(1) comment '用于数据表逻辑操作',
   primary key (id)
);

alter table business_manage comment '商家经营信息数据表';

/*==============================================================*/
/* Table: business_store                                        */
/*==============================================================*/
create table business_store
(
   id                   varchar(100) not null comment '商家店铺ID',
   business_id          int(50) comment '商家ID',
   pic                  varchar(500) binary not null comment '店铺头像',
   classify_id          varchar(100) comment '店铺分类Id',
   rank_id              varchar(100) comment '店铺等级ID',
   store_name           varchar(100) not null comment '店铺名称',
   store_duration       varchar(50) not null comment '开店时长',
   operate_id           varchar(100) not null comment '操作人ID，记录该数据的人员ID(外键)',
   operate_time         varchar(50) comment '操作时间',
   create_id            varchar(100) comment '创建人Id',
   create_time          varchar(50) comment '创建时间',
   verify_id            varchar(100) comment '审核人Id',
   verify_time          varchar(50) comment '审核通过时间',
   state                varchar(1) comment '用于数据表的逻辑操作',
   primary key (id)
);

alter table business_store comment '商家店铺信息数据表';

/*==============================================================*/
/* Table: business_store_classify                               */
/*==============================================================*/
create table business_store_classify
(
   id                   varchar(100) not null comment '店铺分类Id',
   store_type           varchar(50) not null comment '店铺种类',
   store_classify       varchar(50) not null comment '店铺类型',
   classify_comment     varchar(100) comment '类型说明',
   operate_id           varchar(100) comment '操作人ID',
   operate_time         varchar(50) comment '操作时间',
   create_id            varchar(100) comment '创建人Id',
   create_time          varchar(50) comment '创建时间',
   state                varchar(1) comment '用于数据表的逻辑操作',
   primary key (id)
);

alter table business_store_classify comment '店铺分类信息数据表';

/*==============================================================*/
/* Table: business_store_login                                  */
/*==============================================================*/
create table business_store_login
(
   id                   varchar(100) not null comment '店铺登录ID',
   store_id             varchar(100) comment '商家店铺ID',
   store_account        varchar(100) not null comment '店铺登陆账号',
   password             varchar(100) not null comment '登录密码',
   login_tIme           varchar(50) comment '最后登录时间',
   login_ip             varchar(50) comment '最后登录IP',
   operate_id           varchar(100) comment '操作人ID',
   operate_time         varchar(50) comment '操作时间',
   create_id            varchar(100),
   create_time          varchar(50) comment '创建时间',
   state                varchar(1) comment '用于数据表的逻辑操作',
   primary key (id)
);

alter table business_store_login comment '商家登录账号信息数据表';

/*==============================================================*/
/* Table: business_store_manage_category                        */
/*==============================================================*/
create table business_store_manage_category
(
   id                   varchar(100) not null comment '经营类目ID',
   store_id             varchar(100) comment '商家店铺ID',
   first_category       varchar(100) comment '一级类目',
   two_category         varchar(100) comment '二级类目',
   three_category       varchar(100) comment '三级类目',
   operate_id           varchar(100) comment '操作人ID',
   operate_time         varchar(50) comment '操作时间',
   create_id            varchar(100) comment '创建人Id',
   create_time          varchar(50) comment '创建时间',
   state                varchar(0) comment '用于数据表的逻辑操作',
   primary key (id)
);

alter table business_store_manage_category comment '商家经营类目信息数据表';

/*==============================================================*/
/* Table: business_store_rank                                   */
/*==============================================================*/
create table business_store_rank
(
   id                   varchar(100) not null comment '店铺等级ID',
   store_rank           varchar(50) not null comment '店铺等级',
   store_cost           varchar(50) not null comment '费用标准',
   server_comment       varchar(500) binary comment '服务说明',
   operate_id           varchar(100) comment '操作人ID',
   operate_time         varchar(50) comment '操作时间',
   create_id            varchar(100) comment '创建人Id',
   create_time          varchar(50) comment '创建时间',
   state                varchar(1) comment '用于逻辑操作',
   primary key (id)
);

alter table business_store_rank comment '店铺等级信息数据表';

alter table business add constraint FK_Reference_1 foreign key (base_id)
      references business_base (id) on delete restrict on update restrict;

alter table business add constraint FK_Reference_2 foreign key (manage_id)
      references business_manage (id) on delete restrict on update restrict;

alter table business add constraint FK_Reference_3 foreign key (bank_id)
      references business_bank (id) on delete restrict on update restrict;

alter table business_store add constraint FK_Reference_4 foreign key (business_id)
      references business (id) on delete restrict on update restrict;

alter table business_store add constraint FK_Reference_6 foreign key (classify_id)
      references business_store_classify (id) on delete restrict on update restrict;

alter table business_store add constraint FK_Reference_7 foreign key (rank_id)
      references business_store_rank (id) on delete restrict on update restrict;

alter table business_store_login add constraint FK_Reference_8 foreign key (store_id)
      references business_store (id) on delete restrict on update restrict;

alter table business_store_manage_category add constraint FK_Reference_5 foreign key (store_id)
      references business_store (id) on delete restrict on update restrict;

