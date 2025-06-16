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
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` VARCHAR(50) NOT NULL,
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by` VARCHAR(50) NOT NULL,
    `deleted` BOOLEAN NOT NULL DEFAULT 0,
    `deleted_by` DATETIME DEFAULT NULL,
    `deleted_at` VARCHAR(50) DEFAULT NULL,
    `version` BIGINT NOT NULL DEFAULT 0
);