/*==============================================================*/
/* DBMS name:      MySQL 5.0     22                               */
/* Created on:     2016/10/25 14:58:55                          */
/*==============================================================*/


drop table if exists business#�̼ұ�;

drop table if exists business_bank#�̼�������Ϣ��;

drop table if exists business_base#�̼һ���Ϣ��;

drop table if exists business_manage#�̼Ҿ�Ӫ��Ϣ��;

drop table if exists business_store#�̼ҵ�����Ϣ��;

drop table if exists business_store_classify#�̼ҵ��̷�����Ϣ��;

drop table if exists business_store_login#�̼ҵ��̵�¼��Ϣ��;

drop table if exists business_store_manage_category#�̼ҵ��̾�Ӫ��Ŀ��Ϣ��;

drop table if exists business_store_rank#�̼ҵ��̵ȼ���Ϣ��;

/*==============================================================*/
/* Table: business#�̼ұ�                                          */
/*==============================================================*/
create table business#�̼ұ�
(
   id                   int(50) not null comment '�̼�Id',
   base_id              varchar(100) binary comment '�̼һ���ϢID',
   manage_id            varchar(100) comment '�̼Ҿ�ӪID',
   bank_id              varchar(100) comment '�̼�����ID',
   operate_id           varchar(100) comment '������ID',
   operate_time         varchar(100) comment '����ʱ��',
   create_id            varchar(100) comment '������Id',
   create_time          varchar(50) comment '����ʱ��',
   state                int comment '������ݱ���߼�����',
   primary key (id)
);

alter table business#�̼ұ� comment '�̼��м��';

/*==============================================================*/
/* Table: business_bank#�̼�������Ϣ��                                 */
/*==============================================================*/
create table business_bank#�̼�������Ϣ��
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

alter table business_bank#�̼�������Ϣ�� comment '�̼�������Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_base#�̼һ���Ϣ��                                 */
/*==============================================================*/
create table business_base#�̼һ���Ϣ��
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

alter table business_base#�̼һ���Ϣ�� comment '�̼һ���Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_manage#�̼Ҿ�Ӫ��Ϣ��                               */
/*==============================================================*/
create table business_manage#�̼Ҿ�Ӫ��Ϣ��
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

alter table business_manage#�̼Ҿ�Ӫ��Ϣ�� comment '�̼Ҿ�Ӫ��Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store#�̼ҵ�����Ϣ��                                */
/*==============================================================*/
create table business_store#�̼ҵ�����Ϣ��
(
   id                   varchar(100) not null comment '�̼ҵ���ID',
   business_id          int(50) comment '�̼�ID',
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

alter table business_store#�̼ҵ�����Ϣ�� comment '�̼ҵ�����Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_classify#�̼ҵ��̷�����Ϣ��                     */
/*==============================================================*/
create table business_store_classify#�̼ҵ��̷�����Ϣ��
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

alter table business_store_classify#�̼ҵ��̷�����Ϣ�� comment '���̷�����Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_login#�̼ҵ��̵�¼��Ϣ��                        */
/*==============================================================*/
create table business_store_login#�̼ҵ��̵�¼��Ϣ��
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

alter table business_store_login#�̼ҵ��̵�¼��Ϣ�� comment '�̼ҵ�¼�˺���Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_manage_category#�̼ҵ��̾�Ӫ��Ŀ��Ϣ��            */
/*==============================================================*/
create table business_store_manage_category#�̼ҵ��̾�Ӫ��Ŀ��Ϣ��
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
   state                varchar(0) comment '������ݱ���߼�����',
   primary key (id)
);

alter table business_store_manage_category#�̼ҵ��̾�Ӫ��Ŀ��Ϣ�� comment '�̼Ҿ�Ӫ��Ŀ��Ϣ��ݱ�';

/*==============================================================*/
/* Table: business_store_rank#�̼ҵ��̵ȼ���Ϣ��                         */
/*==============================================================*/
create table business_store_rank#�̼ҵ��̵ȼ���Ϣ��
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

alter table business_store_rank#�̼ҵ��̵ȼ���Ϣ�� comment '���̵ȼ���Ϣ��ݱ�';

alter table business#�̼ұ� add constraint FK_Reference_1 foreign key (base_id)
      references business_base#�̼һ���Ϣ�� (id) on delete restrict on update restrict;

alter table business#�̼ұ� add constraint FK_Reference_2 foreign key (manage_id)
      references business_manage#�̼Ҿ�Ӫ��Ϣ�� (id) on delete restrict on update restrict;

alter table business#�̼ұ� add constraint FK_Reference_3 foreign key (bank_id)
      references business_bank#�̼�������Ϣ�� (id) on delete restrict on update restrict;

alter table business_store#�̼ҵ�����Ϣ�� add constraint FK_Reference_4 foreign key (business_id)
      references business#�̼ұ� (id) on delete restrict on update restrict;

alter table business_store#�̼ҵ�����Ϣ�� add constraint FK_Reference_6 foreign key (classify_id)
      references business_store_classify#�̼ҵ��̷�����Ϣ�� (id) on delete restrict on update restrict;

alter table business_store#�̼ҵ�����Ϣ�� add constraint FK_Reference_7 foreign key (rank_id)
      references business_store_rank#�̼ҵ��̵ȼ���Ϣ�� (id) on delete restrict on update restrict;

alter table business_store_login#�̼ҵ��̵�¼��Ϣ�� add constraint FK_Reference_8 foreign key (store_id)
      references business_store#�̼ҵ�����Ϣ�� (id) on delete restrict on update restrict;

alter table business_store_manage_category#�̼ҵ��̾�Ӫ��Ŀ��Ϣ�� add constraint FK_Reference_5 foreign key (store_id)
      references business_store#�̼ҵ�����Ϣ�� (id) on delete restrict on update restrict;

