ALTER TABLE `user_collection`
DROP COLUMN `goods_id`,
ADD COLUMN `goods_id`  varchar(32) NULL COMMENT '������Ʒid' AFTER `shop_id`;
ALTER TABLE `goods`
DROP COLUMN `subtitle`,
ADD COLUMN `subtitle`  varchar(100) NULL COMMENT '������' AFTER `title`;