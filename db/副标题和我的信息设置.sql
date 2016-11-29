ALTER TABLE `user_collection`
DROP COLUMN `goods_id`,
ADD COLUMN `goods_id`  varchar(32) NULL COMMENT '关联商品id' AFTER `shop_id`;
ALTER TABLE `goods`
DROP COLUMN `subtitle`,
ADD COLUMN `subtitle`  varchar(100) NULL COMMENT '副标题' AFTER `title`;
ALTER TABLE `user`
DROP COLUMN `nickname`,
DROP COLUMN `birth`,
ADD COLUMN `nickname`  varchar(32) NULL AFTER `source`,
ADD COLUMN `birth`  varchar(32) NULL AFTER `name`;

