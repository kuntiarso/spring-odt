CREATE TABLE IF NOT EXISTS `payment` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `order_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `gateway` VARCHAR(10) NOT NULL,
    `amount_value` DECIMAL(19, 2) NOT NULL DEFAULT 0,
    `amount_currency` VARCHAR(3) NOT NULL DEFAULT 'IDR',
    `status` VARCHAR(16) NOT NULL DEFAULT 'INITIATED',
    `error_code` VARCHAR(10) DEFAULT NULL,
    `error_message` TEXT DEFAULT NULL,
    `paid_at` TIMESTAMP(6) DEFAULT NULL,
    `created_at` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `created_by` VARCHAR(100) NOT NULL,
    `updated_at` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `updated_by` VARCHAR(100) NOT NULL,
    `deleted` BIT(1) NOT NULL DEFAULT 0,
    `deleted_at` TIMESTAMP(6) DEFAULT NULL,
    `deleted_by` VARCHAR(100) DEFAULT NULL,
    `version` BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX `idx_ord_id` ON `payment`(`order_id`);
CREATE INDEX `idx_usr_id` ON `payment`(`user_id`);