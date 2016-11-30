/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/10/25 15:43:12                          */
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
   id                   varchar(100) not null comment '�̼�Id',
   base_id              varchar(100) comment '�̼һ���ϢID',
   manage_id            varchar(100) comment '�̼Ҿ�ӪID',
   bank_id              varchar(100) comment '�̼�����ID',
   operate_id           varchar(100) comment '������ID',
   operate_time         varchar(100) comment '����ʱ��',
   create_id            varchar(100) comment '������Id',
   create_time          varchar(50) comment '����ʱ��',
   state                int comment '������ݱ���߼�����',
   primary key (id)
);

alter table business comment '�̼��м��';

/*==============================================================*/
/* Table: business_bank                                         */
/*==============================================================*/
create table business_bank
(
   id                   varchar(100) not null comment '�̼�����ID',
   bank_account_name    varchar(100) not null comment '���п�����',
   bank_account         varchar(50) not null comment '�����˻�',
   bank_name            varchar(100) not null comment '���������',
   bank_account_img     varchar(500) binary not null comment '���п���֤��ɨ��ͼ',
   operate_id           varchar(100) comment '������ID����¼����ݵ���ԱID',
   operate_time         varchar(50) comment '����ʱ��',
   create_id            varchar(100) comment '������ID',
   create_time          varchar(50) comment '����ʱ��',
   state                varchar(1) comment '������ݱ���߼�����',
   primary key (id)
);

alter table business_bank comment '�̼�������Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_base                                         */
/*==============================================================*/
create table business_base
(
   id                   varchar(100) not null comment '�̼һ���ϢID',
   company_address      varchar(500) binary not null comment '��˾��ַ',
   company_name         varchar(100) binary not null comment '��˾����',
   registered_capital   varchar(50) comment 'ע���ʱ�',
   linkman_name         varchar(50) not null comment '��ϵ������',
   linkman_number       varchar(50) not null comment '��ϵ���ֻ����',
   email                varchar(50) not null comment '��������',
   operate_id           varchar(100) comment '������ID����¼¼����ݵ���ԱID',
   operate_time         varchar(50) comment '������ʱ��',
   create_id            varchar(100) comment 'create_id#������Id',
   create_time          varchar(50) comment '����ʱ��',
   state                varchar(1) comment '������ݱ���߼�����',
   primary key (id)
);

alter table business_base comment '�̼һ���Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_manage                                       */
/*==============================================================*/
create table business_manage
(
   id                   varchar(100) not null comment '�̼Ҿ�ӪID',
   legal_person         varchar(50) not null comment '���������',
   id_card              varchar(50) not null comment '������������֤',
   Id_card_face_img     varchar(500) binary not null comment '���֤����ͼ',
   id_card_con_img      varchar(500) binary not null comment '���֤����ͼ',
   business_licence_number varchar(100) not null comment 'Ӫҵִ�պ�',
   establish_time       varchar(50) not null comment '��������',
   manage_scope         varchar(500) binary not null comment '��Ӫ��Χ',
   business_licence_img varchar(500) binary not null comment 'Ӫҵִ��ɨ��ͼ',
   busines_slicence_date varchar(100) not null comment 'Ӫҵִ����Ч�ڣ��洢��ʽΪ1900-01-01 ����1999-01-01',
   operate_id           varchar(100) comment '������ID����¼������ݵ���ԱID',
   operate_time         varchar(50) comment '����ʱ��',
   create_id            varchar(100) comment '������Id',
   create_time          varchar(50) comment '����ʱ��',
   state                varchar(1) comment '������ݱ��߼�����',
   primary key (id)
);

alter table business_manage comment '�̼Ҿ�Ӫ��Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store                                        */
/*==============================================================*/
create table business_store
(
   id                   varchar(100) not null comment '�̼ҵ���ID',
   business_id          varchar(100) comment '�̼�ID',
   pic                  varchar(500) binary not null comment '����ͷ��',
   classify_id          varchar(100) comment '���̷���Id',
   rank_id              varchar(100) comment '���̵ȼ�ID',
   store_name           varchar(100) not null comment '�������',
   store_duration       varchar(50) not null comment '����ʱ��',
   operate_id           varchar(100) not null comment '������ID����¼����ݵ���ԱID(���)',
   operate_time         varchar(50) comment '����ʱ��',
   create_id            varchar(100) comment '������Id',
   create_time          varchar(50) comment '����ʱ��',
   verify_id            varchar(100) comment '�����Id',
   verify_time          varchar(50) comment '���ͨ��ʱ��',
   state                varchar(1) comment '������ݱ���߼�����',
   primary key (id)
);

alter table business_store comment '�̼ҵ�����Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_classify                               */
/*==============================================================*/
create table business_store_classify
(
   id                   varchar(100) not null comment '���̷���Id',
   store_type           varchar(50) not null comment '��������',
   store_classify       varchar(50) not null comment '��������',
   classify_comment     varchar(100) comment '����˵��',
   operate_id           varchar(100) comment '������ID',
   operate_time         varchar(50) comment '����ʱ��',
   create_id            varchar(100) comment '������Id',
   create_time          varchar(50) comment '����ʱ��',
   state                varchar(1) comment '������ݱ���߼�����',
   primary key (id)
);

alter table business_store_classify comment '���̷�����Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_login                                  */
/*==============================================================*/
create table business_store_login
(
   id                   varchar(100) not null comment '���̵�¼ID',
   store_id             varchar(100) comment '�̼ҵ���ID',
   store_account        varchar(100) not null comment '���̵�½�˺�',
   password             varchar(100) not null comment '��¼����',
   login_tIme           varchar(50) comment '����¼ʱ��',
   login_ip             varchar(50) comment '����¼IP',
   operate_id           varchar(100) comment '������ID',
   operate_time         varchar(50) comment '����ʱ��',
   create_id            varchar(100),
   create_time          varchar(50) comment '����ʱ��',
   state                varchar(1) comment '������ݱ���߼�����',
   primary key (id)
);

alter table business_store_login comment '�̼ҵ�¼�˺���Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_manage_category                        */
/*==============================================================*/
create table business_store_manage_category
(
   id                   varchar(100) not null comment '��Ӫ��ĿID',
   store_id             varchar(100) comment '�̼ҵ���ID',
   first_category       varchar(100) comment 'һ����Ŀ',
   two_category         varchar(100) comment '������Ŀ',
   three_category       varchar(100) comment '����Ŀ',
   operate_id           varchar(100) comment '������ID',
   operate_time         varchar(50) comment '����ʱ��',
   create_id            varchar(100) comment '������Id',
   create_time          varchar(50) comment '����ʱ��',
   state                varchar(1) comment '������ݱ���߼�����',
   primary key (id)
);

alter table business_store_manage_category comment '�̼Ҿ�Ӫ��Ŀ��Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_rank                                   */
/*==============================================================*/
create table business_store_rank
(
   id                   varchar(100) not null comment '���̵ȼ�ID',
   store_rank           varchar(50) not null comment '���̵ȼ�',
   store_cost           varchar(50) not null comment '���ñ�׼',
   server_comment       varchar(500) binary comment '����˵��',
   operate_id           varchar(100) comment '������ID',
   operate_time         varchar(50) comment '����ʱ��',
   create_id            varchar(100) comment '������Id',
   create_time          varchar(50) comment '����ʱ��',
   state                varchar(1) comment '�����߼�����',
   primary key (id)
);

alter table business_store_rank comment '���̵ȼ���Ϣ��ݱ�';

alter table business add constraint FK_business_base_1 foreign key (base_id)
      references business_base (id) on delete restrict on update restrict;

alter table business add constraint FK_business_manage_2 foreign key (manage_id)
      references business_manage (id) on delete restrict on update restrict;

alter table business add constraint FK_business_bank_3 foreign key (bank_id)
      references business_bank (id) on delete restrict on update restrict;

alter table business_store add constraint FK_business_4 foreign key (business_id)
      references business (id) on delete restrict on update restrict;

alter table business_store add constraint FK_business_store_classify_6 foreign key (classify_id)
      references business_store_classify (id) on delete restrict on update restrict;

alter table business_store add constraint FK_business_store_rank_7 foreign key (rank_id)
      references business_store_rank (id) on delete restrict on update restrict;

alter table business_store_login add constraint FK_business_store_8 foreign key (store_id)
      references business_store (id) on delete restrict on update restrict;

alter table business_store_manage_category add constraint FK_business_store_5 foreign key (store_id)
      references business_store (id) on delete restrict on update restrict;


drop table if exists business_store_login_log;

/*==============================================================*/
/* Table: business_store_login_log                              */
/*==============================================================*/
create table business_store_login_log
(
   id                   varchar(100) not null comment '商家店铺登录日志ID',
   login_id             varchar(100) comment '店铺登录ID',
   operation            varchar(20) comment '操作类型',
   method_package       varchar(20) comment '包名',
   method_name          varchar(20) comment '调用方法',
   prams                varchar(100) comment '提交参数',
   operation_time       varchar(20) comment '操作时间',
   ip_address           varchar(20) comment '操作IP地址',
   res_static           varchar(10) comment '返回状态码',
   res_text             varchar(100) comment '返回结果',
   primary key (id)
);

alter table business_store_login_log comment '商家店铺登录日志表';

alter table business_store_login_log add constraint FK_business_store_login_9 foreign key (login_id)
      references business_store_login (id) on delete restrict on update restrict;
drop table if exists business_store_summary;

/*==============================================================*/
/* Table: business_store_summary                                */
/*==============================================================*/
create table business_store_summary
(
   id                   varchar(100) not null comment '店铺简介信息表',
   store_id             varchar(100) comment '店铺ID',
   summary              varchar(1000) comment '简介',
   operate_id           varchar(100) comment '操作人ID',
   operate_time         varchar(20) comment '操作时间',
   create_id            varchar(100) comment '创建人ID',
   create_time          varchar(20) comment '创建时间',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table business_store_summary comment '商家简介信息表';

alter table business_store_summary add constraint FK_business_store_10 foreign key (store_id)
      references business_store (id) on delete restrict on update restrict;
/*==============================================================*/
/* Table: goods     去除与店铺的外键，改为字段shop_id  2016.11.16        */
/*==============================================================*/
/*==============================================================*/
/* Table: business_store_manage   business_store  2016.11.17    */
/*==============================================================*/
alter table business_manage  add business_licence_address varchar(100);
alter table business_store add store_id varchar(100);
