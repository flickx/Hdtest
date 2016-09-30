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
   name                 varchar(32) comment '权限名',
   buckup               varchar(200) comment '备注',
   create_person        varchar(32) comment '创建人',
   create_time          varchar(32) comment '创建时间',
   modify_person        varchar(32) comment '修改人',
   modify_time          varchar(32) comment '修改时间',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table base_author comment '权限表';

/*==============================================================*/
/* Table: base_author_resource                                  */
/*==============================================================*/
create table base_author_resource
(
   id                   varchar(32) not null comment 'ID',
   rid                  varchar(32) comment '资源ID',
   aid                  varchar(32) comment '权限ID',
   buckup               varchar(200) comment '备注',
   create_person        varchar(32) comment '创建人',
   create_time          varchar(32) comment '创建时间',
   modify_person        varchar(32) comment '修改人',
   modify_time          varchar(32) comment '修改时间',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table base_author_resource comment '资源与权限分配表';

/*==============================================================*/
/* Table: base_person_roler                                     */
/*==============================================================*/
create table base_person_roler
(
   id                   varchar(32) not null comment 'ID',
   rid                  varchar(32) comment '角色ID',
   login_user_id        varchar(32) comment '管理员ID',
   create_person        varchar(32) comment '创建人',
   create_time          varchar(32) comment '创建时间',
   modify_person        varchar(32) comment '修改人',
   modify_time          varchar(32) comment '修改时间',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table base_person_roler comment '人员与角色分配表';

/*==============================================================*/
/* Table: base_resource                                         */
/*==============================================================*/
create table base_resource
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(32) comment '资源名称',
   type                 varchar(32) comment '资源类型',
   content              varchar(100) comment '资源内容',
   buckup               varchar(200) comment '备注',
   create_person        varchar(32) comment '创建人',
   create_time          varchar(32) comment '创建时间',
   modify_person        varchar(32) comment '修改人',
   modify_time          varchar(32) comment '修改时间',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table base_resource comment '资源表';

/*==============================================================*/
/* Table: base_role                                             */
/*==============================================================*/
create table base_role
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(32) comment '角色名',
   create_person        varchar(32) comment '创建人',
   create_time          varchar(32) comment '创建时间',
   modify_person        varchar(32) comment '修改人',
   modify_time          varchar(32) comment '修改时间',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table base_role comment '角色表';

/*==============================================================*/
/* Table: base_role_author                                      */
/*==============================================================*/
create table base_role_author
(
   id                   varchar(32) not null comment 'ID',
   rid                  varchar(32) comment '角色ID',
   aid                  varchar(32) comment '权限ID',
   create_person        varchar(32) comment '创建人',
   create_time          varchar(32) comment '创建时间',
   modify_person        varchar(32) comment '修改人',
   modify_time          varchar(32) comment '修改时间',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table base_role_author comment '权限与角色分配表';

/*==============================================================*/
/* Table: gift                                                  */
/*==============================================================*/
create table gift
(
   id                   varchar(32) not null comment 'id',
   shop_id              varchar(32) comment '店铺ID',
   name                 varchar(32) comment '名称',
   stock                varchar(32) comment '库存',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table gift comment '赠品表';

/*==============================================================*/
/* Table: gift_purchase                                         */
/*==============================================================*/
create table gift_purchase
(
   id                   varchar(32) not null,
   gift_id              varchar(32) comment '赠品ID',
   num                  varchar(32) comment '数量',
   buckup               varchar(200) comment '备注',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table gift_purchase comment '赠品采购单';

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   id                   varchar(32) not null,
   goods_type           varchar(32) comment '分类ID',
   goods_brand_id       varchar(32) comment '品牌ID',
   goods_prop_type_id   varchar(32) comment '商品属性分类ID',
   one_indiana          varchar(32) comment '是否为1元夺宝商品(1:一元夺宝;2:普通商品)',
   title                varchar(100) comment '标题',
   price                varchar(10) comment '默认价格',
   place                varchar(100) comment '采购地',
   give_score           varchar(10) comment '赠送积分',
   give_level           varchar(10) comment '赠送等级经验',
   max_score_price      varchar(10) comment '积分最大购买金额',
   pic_src              varchar(100) comment '商品图片',
   code                 varchar(32) comment '编码',
   pc_info              varchar(1000) comment '电脑端描述',
   mobil_info           varchar(1000) comment '手机端描述',
   invoice              varchar(1) comment '是否有发票',
   guarantee            varchar(1) comment '是否保修',
   volume               varchar(32) comment '体积',
   weight               varchar(32) comment '重量',
   freight_template_id  varchar(32) comment '运费模板ID',
   lottery_number       varchar(32) comment '1元夺宝中奖号码',
   lottery_user_id      varchar(32) comment '1元夺宝中奖人',
   trem                 varchar(32) comment '有效期',
   begin_time           varchar(32) comment '开始时间',
   recommend            varchar(1) comment '是否推荐',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods comment '商品信息表';

/*==============================================================*/
/* Table: goods_brand                                           */
/*==============================================================*/
create table goods_brand
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(32) comment '品牌名称',
   goods_type_id        varchar(32) comment '分类ID',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_brand comment '商品品牌表';

/*==============================================================*/
/* Table: goods_event                                           */
/*==============================================================*/
create table goods_event
(
   id                   varchar(32) not null,
   event_type           varchar(32) comment '活动类型(超实惠、秒杀、折扣、9.9)',
   goods_id             varchar(32) comment '商品ID',
   event_begen          varchar(32) comment '活动开始时间',
   event_end            varchar(32) comment '活动结束时间',
   discount             varchar(32) comment '折扣/秒杀价格',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_event comment '商品活动表';

/*==============================================================*/
/* Table: goods_gift                                            */
/*==============================================================*/
create table goods_gift
(
   id                   varchar(32) not null comment 'id',
   goods_id             varchar(32) comment '商品ID',
   gift_id              varchar(32) comment '赠品ID',
   total                varchar(10) comment '数量',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_gift comment '商品关联赠品表';

/*==============================================================*/
/* Table: goods_param                                           */
/*==============================================================*/
create table goods_param
(
   id                   varchar(32) not null comment 'id',
   goods_id             varchar(32) comment '商品ID',
   param_name           varchar(50) comment '参数名',
   price                varchar(10) comment '价格',
   defalut              varchar(1) comment '是否默认',
   stock                varchar(10) comment '库存',
   sale_number          varchar(10) comment '已销售数量',
   market_price         varchar(10) comment '市场价',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_param comment '商品信息参数表';

/*==============================================================*/
/* Table: goods_prop                                            */
/*==============================================================*/
create table goods_prop
(
   id                   varchar(32) not null,
   goods_id             varchar(32) comment '商品ID',
   goods_property_type_info_id varchar(32) comment '商品属性分类详情ID',
   content              varchar(32) comment '内容',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_prop comment '商品属性内容表';

/*==============================================================*/
/* Table: goods_prop_type                                       */
/*==============================================================*/
create table goods_prop_type
(
   id                   varchar(32) not null,
   name                 varchar(32) comment '分类名',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_prop_type comment '商品属性分类表';

/*==============================================================*/
/* Table: goods_property_type_info                              */
/*==============================================================*/
create table goods_property_type_info
(
   id                   varchar(32) not null,
   good_prop_type_id    varchar(32) comment '商品属性分类ID',
   name                 varchar(32) comment '属性名',
   text_or_select       varchar(1) comment '手填还是可选(1:手填;2可选)',
   content              varchar(200) comment 'text_or_select为2是生效,通过半角逗号隔开可选内容',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_property_type_info comment '商品属性分类详情表';

/*==============================================================*/
/* Table: goods_purchase                                        */
/*==============================================================*/
create table goods_purchase
(
   id                   varchar(32) not null,
   goods_pram_id        varchar(32) comment '商品参数ID',
   num                  varchar(32) comment '数量',
   buckup               varchar(200) comment '备注',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_purchase comment '商品采购单';

/*==============================================================*/
/* Table: goods_return                                          */
/*==============================================================*/
create table goods_return
(
   id                   varchar(32) not null,
   orders_detail_id     varchar(32) comment '订单详情ID',
   return_type          varchar(1) comment '类型(1:退货;2:换货)',
   return_time          varchar(32) comment '获取商品时间',
   result               varchar(1) comment '处理结果(1:同意;2:不同意)',
   kd_code              varchar(32) comment '快递代码',
   kd_number            varchar(32) comment '快递单号',
   delivery             varchar(32) comment '重新发货时间',
   buck_up              varchar(200) comment '备注',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_return comment '订单退换货表';

/*==============================================================*/
/* Table: goods_type                                            */
/*==============================================================*/
create table goods_type
(
   id                   varchar(32) not null comment 'id',
   name                 varchar(32) comment '分类名称',
   pid                  varchar(32) comment '上级分类ID',
   level                varchar(10) comment '级别',
   info                 varchar(200) comment 'info#',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_type comment '商品分类表';

/*==============================================================*/
/* Table: goods_uploadpic                                       */
/*==============================================================*/
create table goods_uploadpic
(
   id                   varchar(32) not null comment 'id',
   goods_id             varchar(32) comment '商品ID',
   number               varchar(32) comment '数量',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table goods_uploadpic comment '商品上传图片表';

/*==============================================================*/
/* Table: index_article                                         */
/*==============================================================*/
create table index_article
(
   id                   varchar(32) not null,
   title                varchar(100) comment '标题',
   content              varchar(1000) comment '内容',
   index_show           varchar(1) comment '是否首页显示',
   show_begen           varchar(32) comment '开始显示时间',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   show_end             varchar(32) comment '截止显示时间',
   primary key (id)
);

alter table index_article comment '文章表';

/*==============================================================*/
/* Table: index_carousel_pic                                    */
/*==============================================================*/
create table index_carousel_pic
(
   id                   varchar(32) not null,
   goods_id             varchar(32) comment '商品ID',
   carousel_type        varchar(1) comment '轮播类型',
   srot                 varchar(32) comment '序号',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table index_carousel_pic comment '首页轮播图表';

/*==============================================================*/
/* Table: index_links                                           */
/*==============================================================*/
create table index_links
(
   id                   varchar(32) not null,
   site_name            varchar(32) comment '友情链接网站名',
   site_url             varchar(200) comment '友情链接地址',
   state                varchar(1) comment '删除标记',
   site_pic             varchar(200) comment '友情链接图标',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   primary key (id)
);

alter table index_links comment '友情链接表';

/*==============================================================*/
/* Table: login_user                                            */
/*==============================================================*/
create table login_user
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   shop_id              varchar(32) comment '商铺ID',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table login_user comment '后台用户登陆表';

/*==============================================================*/
/* Table: login_user_log                                        */
/*==============================================================*/
create table login_user_log
(
   id                   varchar(32) not null,
   login_user_id        varchar(32) comment '管理员ID',
   operation            varchar(100) comment '操作',
   prams                varchar(500) comment '参数',
   operation_time       varchar(32) comment '时间',
   primary key (id)
);

alter table login_user_log comment '管理员操作记录表';

/*==============================================================*/
/* Table: message_send                                          */
/*==============================================================*/
create table message_send
(
   id                   varchar(32) not null,
   msg                  varchar(200) comment '短信内容',
   user_id              varchar(32) comment '接受用户ID',
   send_time            varchar(32) comment '发送时间',
   primary key (id)
);

alter table message_send comment '短信发送表';

/*==============================================================*/
/* Table: message_verification                                  */
/*==============================================================*/
create table message_verification
(
   id                   varchar(32) not null,
   user_id              varchar(32) comment '用户ID',
   message_type         varchar(32) comment '短信类型',
   content              varchar(100) comment '内容',
   verification_code    varchar(32) comment '验证码',
   valid_time           varchar(32) comment '有效时间',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table message_verification comment '短信验证表';

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   order_number         varchar(32) comment '订单号',
   pay_type             varchar(50) comment '支付方式',
   confirm_time         varchar(32) comment '`确认时间',
   order_static         varchar(1) comment '订单状态',
   deliver_static       varchar(1) comment '发货状态',
   pay_static           varchar(32) comment '支付时间',
   confirm_static       varchar(1) comment '确认收货状态',
   odd                  varchar(10) comment '快递单号',
   order_time           varchar(32) comment '订单创建时间',
   pay_time             varchar(32) comment '支付时间',
   deliver_time         varchar(32) comment '发货时间',
   feedback             varchar(200) comment '客户留言',
   user_address_id      varchar(32) comment '收货地址ID',
   payable              varchar(32) comment '应付金额',
   order_price          varchar(32) comment '实付金额',
   shop_message         varchar(200) comment '商家留言',
   out_of_stock         varchar(32) comment '缺货处理',
   bee_coins            varchar(32) comment '蜂币抵扣',
   invoice_type         varchar(32) comment '发票类型',
   invoice_head         varchar(32) comment '发票抬头',
   invoice_content      varchar(100) comment '发票内容',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   one_indiana          varchar(32) comment '是否为1元夺宝订单(1:一元夺宝;2:普通商品)',
   primary key (id)
);

alter table orders comment '商品订单表';

/*==============================================================*/
/* Table: orders_detail                                         */
/*==============================================================*/
create table orders_detail
(
   id                   varchar(32) not null,
   orders_id            varchar(32) comment '订单ID',
   good_param_id        varchar(32) comment '商品参数ID',
   number               varchar(32) comment '数量',
   event_begen          varchar(32) comment '活动开始时间',
   event_end            varchar(32) comment '活动结束时间',
   gift                 varchar(1) comment '是否带赠品',
   gift_number          varchar(10) comment '赠品数量',
   discount             varchar(32) comment '折扣/秒杀价格',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table orders_detail comment '订单详情表';

/*==============================================================*/
/* Table: orders_pay                                            */
/*==============================================================*/
create table orders_pay
(
   id                   varchar(32) not null,
   orders_id            varchar(32) comment '订单ID',
   pay_static           varchar(32) comment '支付状态',
   pay_type             varchar(32) comment '支付类型',
   pay_price            varchar(32) comment '支付金额',
   pay_card             varchar(32) comment '支付卡号/支付宝号',
   serial_number        varchar(32) comment '流水号',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table orders_pay comment '订单支付表';

/*==============================================================*/
/* Table: shop                                                  */
/*==============================================================*/
create table shop
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   name                 varchar(32) comment '商铺名称',
   pic                  varchar(100) comment '商铺图标',
   bond                 varchar(32) comment '保证金',
   level                varchar(32) comment '商铺等级',
   score                varbinary(32) comment '积分',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table shop comment '商铺表';

/*==============================================================*/
/* Table: shop_car                                              */
/*==============================================================*/
create table shop_car
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   goods_param_id       varchar(32) comment '商品参数ID',
   number               varchar(32) comment '数量',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table shop_car comment '购物车';

/*==============================================================*/
/* Table: shop_coupon                                           */
/*==============================================================*/
create table shop_coupon
(
   id                   varchar(32) not null,
   shop_id              varchar(32) comment '店铺ID',
   name                 varchar(32) comment '名称',
   price                varchar(32) comment '金额',
   valid_begen          varchar(32) comment '有效期起',
   valid_end            varchar(32) comment '有效期止',
   min_order_price      varchar(32) comment '最小订单使用金额',
   stock                varchar(32) comment '库存',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table shop_coupon comment '优惠券表';

/*==============================================================*/
/* Table: shop_coupon_send                                      */
/*==============================================================*/
create table shop_coupon_send
(
   id                   varchar(32) not null,
   shop_id              varchar(32) comment '店铺ID',
   name                 varchar(32) comment '名称',
   orders_id            varchar(32) comment '使用订单ID',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table shop_coupon_send comment '优惠券发放表';

/*==============================================================*/
/* Table: shop_freight_template                                 */
/*==============================================================*/
create table shop_freight_template
(
   id                   varchar(32) not null,
   shop_id              varchar(32) comment '商铺ID',
   shop_address         varchar(32) comment '发货地址',
   shop_time            varchar(32) comment '发货时间',
   free_shop            varchar(32) comment '是否包邮',
   price_type           varchar(32) comment '计价方式',
   default_freight      varchar(32) comment '默认运费',
   default_price        varchar(32) comment '默认价格',
   increase_unit        varchar(32) comment '每次增加单位',
   increase_price       varchar(32) comment '每次增加价格',
   kd_id                varchar(32) comment '快递公司代码',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table shop_freight_template comment '运费模板表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   varchar(32) not null comment 'id',
   p2p_id               varchar(32) comment 'p2p用户ID',
   username             varchar(32) comment '用户名',
   score                varchar(32) comment '当前积分',
   xp                   varchar(32) comment '经验值',
   email                varchar(32) comment '邮箱',
   level                varchar(32) comment '会员等级',
   pic                  varchar(100) comment '会员头像',
   name                 varchar(32) comment '真实姓名',
   sex                  varchar(10) comment '性别',
   mobil                varchar(32) comment '手机号码',
   card_id              varchar(32) comment '身份证号',
   pay_password         varchar(32) comment '支付密码',
   password_version     varchar(32) comment '密码版本号',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table user comment '用户表';

/*==============================================================*/
/* Table: user_address                                          */
/*==============================================================*/
create table user_address
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   province             varchar(32) comment '省',
   city                 varchar(32) comment '市',
   county               varchar(32) comment '县/区',
   town                 varchar(32) comment '镇/街道',
   village              varchar(32) comment '村/社区',
   consignee            varchar(32) comment '收货人',
   tel                  varchar(32) comment '电话',
   address              varchar(100) comment '详细地址',
   defulat              varchar(1) comment '是否默认',
   landmarks            varchar(100) comment '标志建筑',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table user_address comment '用户收货地址表';

/*==============================================================*/
/* Table: user_browse                                           */
/*==============================================================*/
create table user_browse
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   ip_address           varchar(32) comment 'ip地址',
   source               varchar(200) comment '来源',
   goods_id             varchar(32) comment '商品ID',
   browse_time          varchar(32) comment '浏览时间',
   primary key (id)
);

alter table user_browse comment '用户浏览记录表';

/*==============================================================*/
/* Table: user_collection                                       */
/*==============================================================*/
create table user_collection
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   shop_id              varchar(32) comment '商铺ID',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table user_collection comment '用户收藏表';

/*==============================================================*/
/* Table: user_comment                                          */
/*==============================================================*/
create table user_comment
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   good_id              varchar(32) comment '商品ID',
   comment_level        varchar(1) comment '评分等级(1-5:1-5星;0:追评)',
   content              varchar(200) comment '内容',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table user_comment comment '用户评论表';

/*==============================================================*/
/* Table: user_follow                                           */
/*==============================================================*/
create table user_follow
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   goods_id             varchar(32) comment '商品ID',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table user_follow comment '会员关注表';

/*==============================================================*/
/* Table: user_message                                          */
/*==============================================================*/
create table user_message
(
   id                   varchar(32) not null,
   user_id              varchar(32) comment '用户ID',
   shop_id              varchar(32) comment '商铺ID',
   message_type         varchar(32) comment '留言类型',
   content              varchar(500) comment '内容',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table user_message comment '用户留言表';

/*==============================================================*/
/* Table: user_operation                                        */
/*==============================================================*/
create table user_operation
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   ip_address           varchar(32) comment 'ip地址',
   goods_id             varchar(32) comment '商品ID',
   browse_time          varchar(32) comment '浏览时间',
   primary key (id)
);

alter table user_operation comment '用户操作记录表';

/*==============================================================*/
/* Table: user_sroce                                            */
/*==============================================================*/
create table user_sroce
(
   id                   varchar(32) not null comment 'id',
   user_id              varchar(32) comment '用户ID',
   good_id              varchar(32) comment '商品ID',
   score                varchar(32) comment '积分(正数为赠送，负数为消费)',
   create_time          varchar(32) comment '创建时间',
   create_person        varchar(32) comment '创建人',
   modify_time          varchar(32) comment '修改时间',
   modify_person        varchar(32) comment '修改人',
   state                varchar(1) comment '删除标记',
   primary key (id)
);

alter table user_sroce comment '用户积分表';

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

