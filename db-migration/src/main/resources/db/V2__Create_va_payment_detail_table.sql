CREATE TABLE IF NOT EXISTS `va_payment_detail` (
    `payment_id` BIGINT PRIMARY KEY,
    `partner_id` VARCHAR(8) NOT NULL,
    `inquiry_id` VARCHAR(30) DEFAULT NULL,
    `customer_no` VARCHAR(20) NOT NULL,
    `va_no` VARCHAR(20) NOT NULL,
    `va_name` VARCHAR(255) NOT NULL,
    `billed_amount_value` DECIMAL(19, 2) NOT NULL DEFAULT 0,
    `billed_amount_currency` VARCHAR(3) NOT NULL DEFAULT 'IDR',
    `paid_amount_value` DECIMAL(19, 2) NOT NULL DEFAULT 0,
    `paid_amount_currency` VARCHAR(3) NOT NULL DEFAULT 'IDR',
    `channel` VARCHAR(30) NOT NULL,
    `how_to_pay_page` VARCHAR(255) DEFAULT NULL,
    `how_to_pay_api` VARCHAR(255) DEFAULT NULL,
    `conf_reusable` BIT(1) NOT NULL DEFAULT 0,
    `conf_min_amount` DECIMAL(19, 2) NOT NULL DEFAULT 0,
    `conf_max_amount` DECIMAL(19, 2) NOT NULL DEFAULT 0,
    `transaction_type` CHAR(1) NOT NULL,
    `expired_at` TIMESTAMP(6) DEFAULT NULL,
    `status` VARCHAR(16) NOT NULL DEFAULT 'INITIATED',
    `created_at` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `created_by` VARCHAR(100) NOT NULL,
    `updated_at` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `updated_by` VARCHAR(100) NOT NULL,
    `deleted` BIT(1) NOT NULL DEFAULT 0,
    `deleted_at` TIMESTAMP(6) DEFAULT NULL,
    `deleted_by` VARCHAR(100) DEFAULT NULL,
    `version` BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX `idx_inq_req_id` ON `va_payment_detail`(`inquiry_id`);
CREATE INDEX `idx_va_no` ON `va_payment_detail`(`va_no`);