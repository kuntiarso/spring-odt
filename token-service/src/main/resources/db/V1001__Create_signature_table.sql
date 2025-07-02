CREATE TABLE IF NOT EXISTS `signature` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `api_type` VARCHAR(10) NOT NULL,
    `signature_type` VARCHAR(10) DEFAULT NULL,
    `request_id` VARCHAR(50) NOT NULL UNIQUE,
    `http_method` VARCHAR(6) DEFAULT NULL,
    `target_endpoint` VARCHAR(255) DEFAULT NULL,
    `digest` TEXT DEFAULT NULL,
    `string_to_sign` TEXT DEFAULT NULL,
    `signature` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(100) NOT NULL,
    `updated_at` TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(100) NOT NULL,
    `deleted` BIT(1) NOT NULL DEFAULT 0,
    `deleted_by` TIMESTAMP(6) DEFAULT NULL,
    `deleted_at` VARCHAR(100) DEFAULT NULL,
    `version` BIGINT NOT NULL DEFAULT 0
);