--2016-11-7 订单表新增三个字段
alter table orders add consignee varchar(100) DEFAULT NULL COMMENT '收货人';
alter table orders add consignee_tel varchar(100) DEFAULT NULL COMMENT '收货人电话';
alter table orders add address varchar(200) DEFAULT NULL COMMENT '收货地址';
--sql填充生产环境往期订单地址（新增的以上三个字段）
update orders o set consignee=(select consignee from user_address u where o.user_address_id=u.id);
update orders o set consignee_tel=(select tel from user_address u where o.user_address_id=u.id);
update orders o set address=(select CONCAT(name,address) from user_address u where o.user_address_id=u.id);