ALTER TABLE `signature` ADD INDEX `idx_signature_req_id`(`request_id`);

ALTER TABLE `va_payment_detail` DROP INDEX `idx_inq_req_id`;
ALTER TABLE `va_payment_detail` DROP INDEX `idx_va_no`;
ALTER TABLE `va_payment_detail` ADD INDEX `idx_va_inq_id`(`inquiry_id`);
ALTER TABLE `va_payment_detail` ADD INDEX `idx_va_va_no`(`va_no`);
ALTER TABLE `va_payment_detail` ADD INDEX `idx_va_req_id`(`request_id`);

ALTER TABLE `payment` DROP INDEX `idx_ord_id`;
ALTER TABLE `payment` DROP INDEX `idx_usr_id`;
ALTER TABLE `payment` ADD INDEX `idx_payment_ord_id`(`order_id`);
ALTER TABLE `payment` ADD INDEX `idx_payment_usr_id`(`user_id`);
ALTER TABLE `payment` ADD INDEX `idx_payment_req_id`(`request_id`);