ALTER TABLE `signature`
MODIFY COLUMN `request_id` VARCHAR(14) NOT NULL UNIQUE;

ALTER TABLE `va_payment_detail`
ADD COLUMN `request_id` VARCHAR(14) NOT NULL UNIQUE
AFTER `inquiry_id`;

ALTER TABLE `payment`
ADD COLUMN `request_id` VARCHAR(14) NOT NULL UNIQUE
AFTER `user_id`;