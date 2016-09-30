/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/7/15 15:46:39                           */
/*==============================================================*/


drop table if exists base_author;

drop table if exists base_author_resource;

drop table if exists base_person_roler;

drop table if exists base_resource;

drop table if exists base_role;

drop table if exists base_role_author;

drop table if exists gift;

drop table if exists gift_purchase;

drop table if exists goods;

drop table if exists goods_brand;

drop table if exists goods_event;

drop table if exists goods_gift;

drop table if exists goods_param;

drop table if exists goods_prop;

drop table if exists goods_prop_type;

drop table if exists goods_property_type_info;

drop table if exists goods_purchase;

drop table if exists goods_return;

drop table if exists goods_type;

drop table if exists goods_uploadpic;

drop table if exists index_article;

drop table if exists index_carousel_pic;

drop table if exists index_links;

drop table if exists login_user;

drop table if exists login_user_log;

drop table if exists message_send;

drop table if exists message_verification;

drop table if exists orders;

drop table if exists orders_detail;

drop table if exists orders_pay;

drop table if exists shop;

drop table if exists shop_car;

drop table if exists shop_coupon;

drop table if exists shop_coupon_send;

drop table if exists shop_freight_template;

drop table if exists user;

drop table if exists user_address;

drop table if exists user_browse;

drop table if exists user_collection;

drop table if exists user_comment;

drop table if exists user_follow;

drop table if exists user_message;

drop table if exists user_operation;

drop table if exists user_sroce;

/*==============================================================*/
/* Table: base_author                                           */
/*==============================================================*/
create table base_author
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(32) comment 'Ȩ����',
   buckup               varchar(200) comment '��ע',
   create_person        varchar(32) comment '������',
   create_time          varchar(32) comment '����ʱ��',
   modify_person        varchar(32) comment '�޸���',
   modify_time          varchar(32) comment '�޸�ʱ��',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table base_author comment 'Ȩ�ޱ�';

/*==============================================================*/
/* Table: base_author_resource                                  */
/*==============================================================*/
create table base_author_resource
(
   id                   varchar(32) not null comment 'ID',
   rid                  varchar(32) comment '��ԴID',
   aid                  varchar(32) comment 'Ȩ��ID',
   buckup               varchar(200) comment '��ע',
   create_person        varchar(32) comment '������',
   create_time          varchar(32) comment '����ʱ��',
   modify_person        varchar(32) comment '�޸���',
   modify_time          varchar(32) comment '�޸�ʱ��',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table base_author_resource comment '��Դ��Ȩ�޷����';

/*==============================================================*/
/* Table: base_person_roler                                     */
/*==============================================================*/
create table base_person_roler
(
   id                   varchar(32) not null comment 'ID',
   rid                  varchar(32) comment '��ɫID',
   login_user_id        varchar(32) comment '����ԱID',
   create_person        varchar(32) comment '������',
   create_time          varchar(32) comment '����ʱ��',
   modify_person        varchar(32) comment '�޸���',
   modify_time          varchar(32) comment '�޸�ʱ��',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table base_person_roler comment '��Ա���ɫ�����';

/*==============================================================*/
/* Table: base_resource                                         */
/*==============================================================*/
create table base_resource
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(32) comment '��Դ����',
   type                 varchar(32) comment '��Դ����',
   content              varchar(100) comment '��Դ����',
   buckup               varchar(200) comment '��ע',
   create_person        varchar(32) comment '������',
   create_time          varchar(32) comment '����ʱ��',
   modify_person        varchar(32) comment '�޸���',
   modify_time          varchar(32) comment '�޸�ʱ��',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table base_resource comment '��Դ��';

/*==============================================================*/
/* Table: base_role                                             */
/*==============================================================*/
create table base_role
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(32) comment '��ɫ��',
   create_person        varchar(32) comment '������',
   create_time          varchar(32) comment '����ʱ��',
   modify_person        varchar(32) comment '�޸���',
   modify_time          varchar(32) comment '�޸�ʱ��',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table base_role comment '��ɫ��';

/*==============================================================*/
/* Table: base_role_author                                      */
/*==============================================================*/
create table base_role_author
(
   id                   varchar(32) not null comment 'ID',
   rid                  varchar(32) comment '��ɫID',
   aid                  varchar(32) comment 'Ȩ��ID',
   create_person        varchar(32) comment '������',
   create_time          varchar(32) comment '����ʱ��',
   modify_person        varchar(32) comment '�޸���',
   modify_time          varchar(32) comment '�޸�ʱ��',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table base_role_author comment 'Ȩ�����ɫ�����';

/*==============================================================*/
/* Table: gift                                                  */
/*==============================================================*/
create table gift
(
   id                   varchar(32) not null comment 'id',
   shop_id              varchar(32) comment '����ID',
   name                 varchar(32) comment '����',
   stock                varchar(32) comment '���',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table gift comment '��Ʒ��';

/*==============================================================*/
/* Table: gift_purchase                                         */
/*==============================================================*/
create table gift_purchase
(
   id                   varchar(32) not null,
   gift_id              varchar(32) comment '��ƷID',
   num                  varchar(32) comment '����',
   buckup               varchar(200) comment '��ע',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table gift_purchase comment '��Ʒ�ɹ���';

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   id                   varchar(32) not null,
   goods_type           varchar(32) comment '����ID',
   goods_brand_id       varchar(32) comment 'Ʒ��ID',
   goods_prop_type_id   varchar(32) comment '��Ʒ���Է���ID',
   one_indiana          varchar(32) comment '�Ƿ�Ϊ1Ԫ�ᱦ��Ʒ(1:һԪ�ᱦ;2:��ͨ��Ʒ)',
   title                varchar(100) comment '����',
   price                varchar(10) comment 'Ĭ�ϼ۸�',
   place                varchar(100) comment '�ɹ���',
   give_score           varchar(10) comment '���ͻ���',
   give_level           varchar(10) comment '���͵ȼ�����',
   max_score_price      varchar(10) comment '�����������',
   pic_src              varchar(100) comment '��ƷͼƬ',
   code                 varchar(32) comment '����',
   pc_info              varchar(1000) comment '���Զ�����',
   mobil_info           varchar(1000) comment '�ֻ�������',
   invoice              varchar(1) comment '�Ƿ��з�Ʊ',
   guarantee            varchar(1) comment '�Ƿ���',
   volume               varchar(32) comment '���',
   weight               varchar(32) comment '����',
   freight_template_id  varchar(32) comment '�˷�ģ��ID',
   lottery_number       varchar(32) comment '1Ԫ�ᱦ�н�����',
   lottery_user_id      varchar(32) comment '1Ԫ�ᱦ�н���',
   trem                 varchar(32) comment '��Ч��',
   begin_time           varchar(32) comment '��ʼʱ��',
   recommend            varchar(1) comment '�Ƿ��Ƽ�',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods comment '��Ʒ��Ϣ��';

/*==============================================================*/
/* Table: goods_brand                                           */
/*==============================================================*/
create table goods_brand
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(32) comment 'Ʒ������',
   goods_type_id        varchar(32) comment '����ID',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_brand comment '��ƷƷ�Ʊ�';

/*==============================================================*/
/* Table: goods_event                                           */
/*==============================================================*/
create table goods_event
(
   id                   varchar(32) not null,
   event_type           varchar(32) comment '�����(��ʵ�ݡ���ɱ���ۿۡ�9.9)',
   goods_id             varchar(32) comment '��ƷID',
   event_begen          varchar(32) comment '���ʼʱ��',
   event_end            varchar(32) comment '�����ʱ��',
   discount             varchar(32) comment '�ۿ�/��ɱ�۸�',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_event comment '��Ʒ���';

/*==============================================================*/
/* Table: goods_gift                                            */
/*==============================================================*/
create table goods_gift
(
   id                   varchar(32) not null comment 'id',
   goods_id             varchar(32) comment '��ƷID',
   gift_id              varchar(32) comment '��ƷID',
   total                varchar(10) comment '����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_gift comment '��Ʒ������Ʒ��';

/*==============================================================*/
/* Table: goods_param                                           */
/*==============================================================*/
create table goods_param
(
   id                   varchar(32) not null comment 'id',
   goods_id             varchar(32) comment '��ƷID',
   param_name           varchar(50) comment '������',
   price                varchar(10) comment '�۸�',
   defalut              varchar(1) comment '�Ƿ�Ĭ��',
   stock                varchar(10) comment '���',
   sale_number          varchar(10) comment '����������',
   market_price         varchar(10) comment '�г���',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_param comment '��Ʒ��Ϣ������';

/*==============================================================*/
/* Table: goods_prop                                            */
/*==============================================================*/
create table goods_prop
(
   id                   varchar(32) not null,
   goods_id             varchar(32) comment '��ƷID',
   goods_property_type_info_id varchar(32) comment '��Ʒ���Է�������ID',
   content              varchar(32) comment '����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_prop comment '��Ʒ�������ݱ�';

/*==============================================================*/
/* Table: goods_prop_type                                       */
/*==============================================================*/
create table goods_prop_type
(
   id                   varchar(32) not null,
   name                 varchar(32) comment '������',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_prop_type comment '��Ʒ���Է����';

/*==============================================================*/
/* Table: goods_property_type_info                              */
/*==============================================================*/
create table goods_property_type_info
(
   id                   varchar(32) not null,
   good_prop_type_id    varchar(32) comment '��Ʒ���Է���ID',
   name                 varchar(32) comment '������',
   text_or_select       varchar(1) comment '����ǿ�ѡ(1:����;2��ѡ)',
   content              varchar(200) comment 'text_or_selectΪ2����Ч,ͨ����Ƕ��Ÿ�����ѡ����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_property_type_info comment '��Ʒ���Է��������';

/*==============================================================*/
/* Table: goods_purchase                                        */
/*==============================================================*/
create table goods_purchase
(
   id                   varchar(32) not null,
   goods_pram_id        varchar(32) comment '��Ʒ����ID',
   num                  varchar(32) comment '����',
   buckup               varchar(200) comment '��ע',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_purchase comment '��Ʒ�ɹ���';

/*==============================================================*/
/* Table: goods_return                                          */
/*==============================================================*/
create table goods_return
(
   id                   varchar(32) not null,
   orders_detail_id     varchar(32) comment '��������ID',
   return_type          varchar(1) comment '����(1:�˻�;2:����)',
   return_time          varchar(32) comment '��ȡ��Ʒʱ��',
   result               varchar(1) comment '������(1:ͬ��;2:��ͬ��)',
   kd_code              varchar(32) comment '��ݴ���',
   kd_number            varchar(32) comment '��ݵ���',
   delivery             varchar(32) comment '���·���ʱ��',
   buck_up              varchar(200) comment '��ע',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_return comment '�����˻�����';

/*==============================================================*/
/* Table: goods_type                                            */
/*==============================================================*/
create table goods_type
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(32) comment '��������',
   pid                  varchar(32) comment '�ϼ�����ID',
   level                varchar(10) comment '����',
   info                 varchar(200) comment 'info#',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_type comment '��Ʒ�����';

/*==============================================================*/
/* Table: goods_uploadpic                                       */
/*==============================================================*/
create table goods_uploadpic
(
   id                   varchar(32) not null comment 'id',
   goods_id             varchar(32) comment '��ƷID',
   number               varchar(32) comment '����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table goods_uploadpic comment '��Ʒ�ϴ�ͼƬ��';

/*==============================================================*/
/* Table: index_article                                         */
/*==============================================================*/
create table index_article
(
   id                   varchar(32) not null,
   title                varchar(100) comment '����',
   content              varchar(1000) comment '����',
   index_show           varchar(1) comment '�Ƿ���ҳ��ʾ',
   show_begen           varchar(32) comment '��ʼ��ʾʱ��',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   show_end             varchar(32) comment '��ֹ��ʾʱ��',
   primary key (id)
);

alter table index_article comment '���±�';

/*==============================================================*/
/* Table: index_carousel_pic                                    */
/*==============================================================*/
create table index_carousel_pic
(
   id                   varchar(32) not null,
   goods_id             varchar(32) comment '��ƷID',
   carousel_type        varchar(1) comment '�ֲ�����',
   srot                 varchar(32) comment '���',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table index_carousel_pic comment '��ҳ�ֲ�ͼ��';

/*==============================================================*/
/* Table: index_links                                           */
/*==============================================================*/
create table index_links
(
   id                   varchar(32) not null,
   site_name            varchar(32) comment '����������վ��',
   site_url             varchar(200) comment '�������ӵ�ַ',
   state                varchar(1) comment 'ɾ�����',
   site_pic             varchar(200) comment '��������ͼ��',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   primary key (id)
);

alter table index_links comment '�������ӱ�';

/*==============================================================*/
/* Table: login_user                                            */
/*==============================================================*/
create table login_user
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   shop_id              varchar(32) comment '����ID',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table login_user comment '��̨�û���½��';

/*==============================================================*/
/* Table: login_user_log                                        */
/*==============================================================*/
create table login_user_log
(
   id                   varchar(32) not null,
   login_user_id        varchar(32) comment '����ԱID',
   operation            varchar(100) comment '����',
   prams                varchar(500) comment '����',
   operation_time       varchar(32) comment 'ʱ��',
   primary key (id)
);

alter table login_user_log comment '����Ա������¼��';

/*==============================================================*/
/* Table: message_send                                          */
/*==============================================================*/
create table message_send
(
   id                   varchar(32) not null,
   msg                  varchar(200) comment '��������',
   user_id              varchar(32) comment '�����û�ID',
   send_time            varchar(32) comment '����ʱ��',
   primary key (id)
);

alter table message_send comment '���ŷ��ͱ�';

/*==============================================================*/
/* Table: message_verification                                  */
/*==============================================================*/
create table message_verification
(
   id                   varchar(32) not null,
   user_id              varchar(32) comment '�û�ID',
   message_type         varchar(32) comment '��������',
   content              varchar(100) comment '����',
   verification_code    varchar(32) comment '��֤��',
   valid_time           varchar(32) comment '��Чʱ��',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table message_verification comment '������֤��';

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   order_number         varchar(32) comment '������',
   pay_type             varchar(50) comment '֧����ʽ',
   confirm_time         varchar(32) comment '`ȷ��ʱ��',
   order_static         varchar(1) comment '����״̬',
   deliver_static       varchar(1) comment '����״̬',
   pay_static           varchar(32) comment '֧��ʱ��',
   confirm_static       varchar(1) comment 'ȷ���ջ�״̬',
   odd                  varchar(10) comment '��ݵ���',
   order_time           varchar(32) comment '��������ʱ��',
   pay_time             varchar(32) comment '֧��ʱ��',
   deliver_time         varchar(32) comment '����ʱ��',
   feedback             varchar(200) comment '�ͻ�����',
   user_address_id      varchar(32) comment '�ջ���ַID',
   payable              varchar(32) comment 'Ӧ�����',
   order_price          varchar(32) comment 'ʵ�����',
   shop_message         varchar(200) comment '�̼�����',
   out_of_stock         varchar(32) comment 'ȱ������',
   bee_coins            varchar(32) comment '��ҵֿ�',
   invoice_type         varchar(32) comment '��Ʊ����',
   invoice_head         varchar(32) comment '��Ʊ̧ͷ',
   invoice_content      varchar(100) comment '��Ʊ����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   one_indiana          varchar(32) comment '�Ƿ�Ϊ1Ԫ�ᱦ����(1:һԪ�ᱦ;2:��ͨ��Ʒ)',
   primary key (id)
);

alter table orders comment '��Ʒ������';

/*==============================================================*/
/* Table: orders_detail                                         */
/*==============================================================*/
create table orders_detail
(
   id                   varchar(32) not null,
   orders_id            varchar(32) comment '����ID',
   good_param_id        varchar(32) comment '��Ʒ����ID',
   number               varchar(32) comment '����',
   event_begen          varchar(32) comment '���ʼʱ��',
   event_end            varchar(32) comment '�����ʱ��',
   gift                 varchar(1) comment '�Ƿ����Ʒ',
   gift_number          varchar(10) comment '��Ʒ����',
   discount             varchar(32) comment '�ۿ�/��ɱ�۸�',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table orders_detail comment '���������';

/*==============================================================*/
/* Table: orders_pay                                            */
/*==============================================================*/
create table orders_pay
(
   id                   varchar(32) not null,
   orders_id            varchar(32) comment '����ID',
   pay_static           varchar(32) comment '֧��״̬',
   pay_type             varchar(32) comment '֧������',
   pay_price            varchar(32) comment '֧�����',
   pay_card             varchar(32) comment '֧������/֧������',
   serial_number        varchar(32) comment '��ˮ��',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table orders_pay comment '����֧����';

/*==============================================================*/
/* Table: shop                                                  */
/*==============================================================*/
create table shop
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   name                 varchar(32) comment '��������',
   pic                  varchar(100) comment '����ͼ��',
   bond                 varchar(32) comment '��֤��',
   level                varchar(32) comment '���̵ȼ�',
   score                varbinary(32) comment '����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table shop comment '���̱�';

/*==============================================================*/
/* Table: shop_car                                              */
/*==============================================================*/
create table shop_car
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   goods_param_id       varchar(32) comment '��Ʒ����ID',
   number               varchar(32) comment '����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table shop_car comment '���ﳵ';

/*==============================================================*/
/* Table: shop_coupon                                           */
/*==============================================================*/
create table shop_coupon
(
   id                   varchar(32) not null,
   shop_id              varchar(32) comment '����ID',
   name                 varchar(32) comment '����',
   price                varchar(32) comment '���',
   valid_begen          varchar(32) comment '��Ч����',
   valid_end            varchar(32) comment '��Ч��ֹ',
   min_order_price      varchar(32) comment '��С����ʹ�ý��',
   stock                varchar(32) comment '���',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table shop_coupon comment '�Ż�ȯ��';

/*==============================================================*/
/* Table: shop_coupon_send                                      */
/*==============================================================*/
create table shop_coupon_send
(
   id                   varchar(32) not null,
   shop_id              varchar(32) comment '����ID',
   name                 varchar(32) comment '����',
   orders_id            varchar(32) comment 'ʹ�ö���ID',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table shop_coupon_send comment '�Ż�ȯ���ű�';

/*==============================================================*/
/* Table: shop_freight_template                                 */
/*==============================================================*/
create table shop_freight_template
(
   id                   varchar(32) not null,
   shop_id              varchar(32) comment '����ID',
   shop_address         varchar(32) comment '������ַ',
   shop_time            varchar(32) comment '����ʱ��',
   free_shop            varchar(32) comment '�Ƿ����',
   price_type           varchar(32) comment '�Ƽ۷�ʽ',
   default_freight      varchar(32) comment 'Ĭ���˷�',
   default_price        varchar(32) comment 'Ĭ�ϼ۸�',
   increase_unit        varchar(32) comment 'ÿ�����ӵ�λ',
   increase_price       varchar(32) comment 'ÿ�����Ӽ۸�',
   kd_id                varchar(32) comment '��ݹ�˾����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table shop_freight_template comment '�˷�ģ���';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   varchar(32) not null comment 'id',
   p2p_id               varchar(32) comment 'p2p�û�ID',
   username             varchar(32) comment '�û���',
   score                varchar(32) comment '��ǰ����',
   xp                   varchar(32) comment '����ֵ',
   email                varchar(32) comment '����',
   level                varchar(32) comment '��Ա�ȼ�',
   pic                  varchar(100) comment '��Աͷ��',
   name                 varchar(32) comment '��ʵ����',
   sex                  varchar(10) comment '�Ա�',
   mobil                varchar(32) comment '�ֻ�����',
   card_id              varchar(32) comment '���֤��',
   pay_password         varchar(32) comment '֧������',
   password_version     varchar(32) comment '����汾��',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table user comment '�û���';

/*==============================================================*/
/* Table: user_address                                          */
/*==============================================================*/
create table user_address
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   province             varchar(32) comment 'ʡ',
   city                 varchar(32) comment '��',
   county               varchar(32) comment '��/��',
   town                 varchar(32) comment '��/�ֵ�',
   village              varchar(32) comment '��/����',
   consignee            varchar(32) comment '�ջ���',
   tel                  varchar(32) comment '�绰',
   address              varchar(100) comment '��ϸ��ַ',
   defulat              varchar(1) comment '�Ƿ�Ĭ��',
   landmarks            varchar(100) comment '��־����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table user_address comment '�û��ջ���ַ��';

/*==============================================================*/
/* Table: user_browse                                           */
/*==============================================================*/
create table user_browse
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   ip_address           varchar(32) comment 'ip��ַ',
   source               varchar(200) comment '��Դ',
   goods_id             varchar(32) comment '��ƷID',
   browse_time          varchar(32) comment '���ʱ��',
   primary key (id)
);

alter table user_browse comment '�û������¼��';

/*==============================================================*/
/* Table: user_collection                                       */
/*==============================================================*/
create table user_collection
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   shop_id              varchar(32) comment '����ID',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table user_collection comment '�û��ղر�';

/*==============================================================*/
/* Table: user_comment                                          */
/*==============================================================*/
create table user_comment
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   good_id              varchar(32) comment '��ƷID',
   comment_level        varchar(1) comment '���ֵȼ�(1-5:1-5��;0:׷��)',
   content              varchar(200) comment '����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table user_comment comment '�û����۱�';

/*==============================================================*/
/* Table: user_follow                                           */
/*==============================================================*/
create table user_follow
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   goods_id             varchar(32) comment '��ƷID',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table user_follow comment '��Ա��ע��';

/*==============================================================*/
/* Table: user_message                                          */
/*==============================================================*/
create table user_message
(
   id                   varchar(32) not null,
   user_id              varchar(32) comment '�û�ID',
   shop_id              varchar(32) comment '����ID',
   message_type         varchar(32) comment '��������',
   content              varchar(500) comment '����',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table user_message comment '�û����Ա�';

/*==============================================================*/
/* Table: user_operation                                        */
/*==============================================================*/
create table user_operation
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   ip_address           varchar(32) comment 'ip��ַ',
   goods_id             varchar(32) comment '��ƷID',
   browse_time          varchar(32) comment '���ʱ��',
   primary key (id)
);

alter table user_operation comment '�û�������¼��';

/*==============================================================*/
/* Table: user_sroce                                            */
/*==============================================================*/
create table user_sroce
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '�û�ID',
   good_id              varchar(32) comment '��ƷID',
   score                varchar(32) comment '����(����Ϊ���ͣ�����Ϊ����)',
   create_time          varchar(32) comment '����ʱ��',
   create_person        varchar(32) comment '������',
   modify_time          varchar(32) comment '�޸�ʱ��',
   modify_person        varchar(32) comment '�޸���',
   state                varchar(1) comment 'ɾ�����',
   primary key (id)
);

alter table user_sroce comment '�û����ֱ�';

alter table base_author_resource add constraint FK_fk37 foreign key (aid)
      references base_author (id) on delete restrict on update restrict;

alter table base_author_resource add constraint FK_fk38 foreign key (rid)
      references base_resource (id) on delete restrict on update restrict;

alter table base_person_roler add constraint FK_Reference_51 foreign key (login_user_id)
      references login_user (id) on delete restrict on update restrict;

alter table base_person_roler add constraint FK_fk6 foreign key (rid)
      references base_role (id) on delete restrict on update restrict;

alter table base_role_author add constraint FK_fk1 foreign key (aid)
      references base_author (id) on delete restrict on update restrict;

alter table base_role_author add constraint FK_fk2 foreign key (rid)
      references base_role (id) on delete restrict on update restrict;

alter table gift add constraint FK_Reference_20 foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table gift_purchase add constraint FK_Reference_39 foreign key (gift_id)
      references gift (id) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_10 foreign key (goods_type)
      references goods_type (id) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_11 foreign key (goods_brand_id)
      references goods_brand (id) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_23 foreign key (goods_prop_type_id)
      references goods_prop_type (id) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_30 foreign key (lottery_user_id)
      references user (id) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_9 foreign key (freight_template_id)
      references shop_freight_template (id) on delete restrict on update restrict;

alter table goods_event add constraint FK_Reference_29 foreign key (goods_id)
      references goods (id) on delete restrict on update restrict;

alter table goods_gift add constraint FK_Reference_13 foreign key (gift_id)
      references gift (id) on delete restrict on update restrict;

alter table goods_gift add constraint FK_Reference_14 foreign key (goods_id)
      references goods (id) on delete restrict on update restrict;

alter table goods_param add constraint FK_Reference_12 foreign key (goods_id)
      references goods (id) on delete restrict on update restrict;

alter table goods_prop add constraint FK_Reference_24 foreign key (goods_id)
      references goods (id) on delete restrict on update restrict;

alter table goods_prop add constraint FK_Reference_25 foreign key (goods_property_type_info_id)
      references goods_property_type_info (id) on delete restrict on update restrict;

alter table goods_property_type_info add constraint FK_Reference_22 foreign key (good_prop_type_id)
      references goods_prop_type (id) on delete restrict on update restrict;

alter table goods_purchase add constraint FK_Reference_41 foreign key (goods_pram_id)
      references goods_param (id) on delete restrict on update restrict;

alter table goods_return add constraint FK_Reference_40 foreign key (orders_detail_id)
      references orders_detail (id) on delete restrict on update restrict;

alter table goods_uploadpic add constraint FK_Reference_2 foreign key (goods_id)
      references goods (id) on delete restrict on update restrict;

alter table index_carousel_pic add constraint FK_Reference_44 foreign key (goods_id)
      references goods (id) on delete restrict on update restrict;

alter table login_user_log add constraint FK_Reference_42 foreign key (login_user_id)
      references login_user (id) on delete restrict on update restrict;

alter table message_send add constraint FK_Reference_43 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table message_verification add constraint FK_Reference_38 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_31 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table orders add constraint FK_Reference_33 foreign key (user_address_id)
      references user_address (id) on delete restrict on update restrict;

alter table orders_detail add constraint FK_Reference_35 foreign key (orders_id)
      references orders (id) on delete restrict on update restrict;

alter table orders_detail add constraint FK_Reference_36 foreign key (good_param_id)
      references goods_param (id) on delete restrict on update restrict;

alter table orders_pay add constraint FK_Reference_37 foreign key (orders_id)
      references orders (id) on delete restrict on update restrict;

alter table shop_car add constraint FK_Reference_34 foreign key (goods_param_id)
      references goods_param (id) on delete restrict on update restrict;

alter table shop_car add constraint FK_Reference_4 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table shop_coupon add constraint FK_Reference_27 foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table shop_coupon_send add constraint FK_Reference_26 foreign key (name)
      references shop_coupon (id) on delete restrict on update restrict;

alter table shop_coupon_send add constraint FK_Reference_28 foreign key (shop_id)
      references user (id) on delete restrict on update restrict;

alter table shop_coupon_send add constraint FK_Reference_32 foreign key (orders_id)
      references orders (id) on delete restrict on update restrict;

alter table shop_freight_template add constraint FK_Reference_8 foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table user_address add constraint FK_Reference_19 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_browse add constraint FK_Reference_1 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_collection add constraint FK_Reference_5 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_collection add constraint FK_Reference_7 foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table user_comment add constraint FK_Reference_17 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_comment add constraint FK_Reference_18 foreign key (good_id)
      references goods (id) on delete restrict on update restrict;

alter table user_follow add constraint FK_Reference_6 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_message add constraint FK_Reference_45 foreign key (shop_id)
      references shop (id) on delete restrict on update restrict;

alter table user_message add constraint FK_Reference_46 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_operation add constraint FK_Reference_21 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_sroce add constraint FK_Reference_15 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_sroce add constraint FK_Reference_16 foreign key (user_id)
      references goods (id) on delete restrict on update restrict;

