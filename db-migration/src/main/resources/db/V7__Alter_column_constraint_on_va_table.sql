ALTER TABLE `va_payment_detail`
    MODIFY COLUMN `billed_amount_value` DECIMAL (19, 2) NOT NULL,
    MODIFY COLUMN `billed_amount_currency` VARCHAR (3) NOT NULL,
    MODIFY COLUMN `paid_amount_value` DECIMAL (19, 2) DEFAULT NULL,
    MODIFY COLUMN `paid_amount_currency` VARCHAR (3) DEFAULT NULL,
    MODIFY COLUMN `conf_reusable` BIT (1) DEFAULT 0,
    MODIFY COLUMN `conf_min_amount` DECIMAL (19, 2) DEFAULT NULL,
    MODIFY COLUMN `conf_max_amount` DECIMAL (19, 2) DEFAULT NULL,
    MODIFY COLUMN `status` VARCHAR (16) DEFAULT 'INITIATED',
    MODIFY COLUMN `created_at` TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP (6),
    MODIFY COLUMN `deleted` BIT (1) DEFAULT 0,
    MODIFY COLUMN `version` BIGINT DEFAULT 0;