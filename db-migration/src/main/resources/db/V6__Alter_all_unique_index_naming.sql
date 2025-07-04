ALTER TABLE `signature` DROP INDEX `request_id`;
ALTER TABLE `signature` DROP INDEX `request_id_2`;
ALTER TABLE `signature` DROP INDEX `idx_signature_req_id`, ADD CONSTRAINT `idx_signature_req_id` UNIQUE (`request_id`);

ALTER TABLE `va_payment_detail` DROP INDEX `request_id`;
ALTER TABLE `va_payment_detail` DROP INDEX `idx_va_req_id`, ADD CONSTRAINT `idx_va_req_id` UNIQUE (`request_id`);

ALTER TABLE `payment` DROP INDEX `request_id`;
ALTER TABLE `payment` DROP INDEX `idx_payment_req_id`, ADD CONSTRAINT `idx_payment_req_id` UNIQUE (`request_id`);