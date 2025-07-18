-- SKCC Oversea Banking System Database Schema
-- This file contains table creation scripts for testing purposes

-- Cash Card Tables
CREATE TABLE IF NOT EXISTS cash_card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_number VARCHAR(20) NOT NULL UNIQUE,
    card_no VARCHAR(20) NOT NULL UNIQUE,
    primary_account_no VARCHAR(20) NOT NULL,
    bank_code VARCHAR(10) NOT NULL,
    branch_code VARCHAR(10) NOT NULL,
    cif_no VARCHAR(20) NOT NULL,
    cif_name VARCHAR(100) NOT NULL,
    currency VARCHAR(3) DEFAULT 'KRW',
    daily_limit_amount DECIMAL(15,2) DEFAULT 0.00,
    daily_accum_amount DECIMAL(15,2) DEFAULT 0.00,
    status VARCHAR(10) DEFAULT 'ACTIVE',
    effective_date VARCHAR(8),
    expiry_date VARCHAR(8),
    register_date VARCHAR(8),
    register_time VARCHAR(6),
    register_by VARCHAR(20),
    last_update_date VARCHAR(8),
    last_update_time VARCHAR(6),
    last_update_user_id VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Hot Card Tables
CREATE TABLE IF NOT EXISTS hot_card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_number VARCHAR(20) NOT NULL,
    sequence_no INT NOT NULL,
    primary_account_no VARCHAR(20) NOT NULL,
    cif_no VARCHAR(20) NOT NULL,
    cif_name VARCHAR(100) NOT NULL,
    status VARCHAR(10) DEFAULT 'BLOCKED',
    register_date VARCHAR(8),
    register_time VARCHAR(6),
    register_by VARCHAR(20),
    released_date VARCHAR(8),
    released_time VARCHAR(6),
    released_by VARCHAR(20),
    remark VARCHAR(200),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_hot_card (card_number, sequence_no)
);

-- Deposit Tables
CREATE TABLE IF NOT EXISTS deposit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    bank_code VARCHAR(10) NOT NULL,
    branch_code VARCHAR(10) NOT NULL,
    cif_no VARCHAR(20) NOT NULL,
    cif_name VARCHAR(100) NOT NULL,
    currency VARCHAR(3) DEFAULT 'KRW',
    balance DECIMAL(15,2) DEFAULT 0.00,
    interest_rate DECIMAL(5,2) DEFAULT 0.00,
    status VARCHAR(10) DEFAULT 'ACTIVE',
    open_date VARCHAR(8),
    maturity_date VARCHAR(8),
    register_date VARCHAR(8),
    register_time VARCHAR(6),
    register_by VARCHAR(20),
    last_update_date VARCHAR(8),
    last_update_time VARCHAR(6),
    last_update_user_id VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Common Tables
CREATE TABLE IF NOT EXISTS common (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    common_code VARCHAR(50) NOT NULL UNIQUE,
    common_name VARCHAR(100) NOT NULL,
    common_type VARCHAR(20) NOT NULL,
    common_value VARCHAR(200),
    description VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    effective_date VARCHAR(8),
    expiry_date VARCHAR(8),
    register_date VARCHAR(8),
    register_time VARCHAR(6),
    register_by VARCHAR(20),
    last_update_date VARCHAR(8),
    last_update_time VARCHAR(6),
    last_update_user_id VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- EPlaton Common Tables (for eplatonframework package)
CREATE TABLE IF NOT EXISTS eplaton_common (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    common_code VARCHAR(20) NOT NULL UNIQUE,
    common_name VARCHAR(100),
    common_type VARCHAR(10),
    common_value VARCHAR(200),
    description VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    effective_date DATE,
    expiry_date DATE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP,
    version BIGINT DEFAULT 0,
    created_by VARCHAR(50),
    modified_by VARCHAR(50),
    is_deleted BOOLEAN DEFAULT FALSE
);

-- Teller Tables
CREATE TABLE IF NOT EXISTS teller (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    teller_id VARCHAR(20) NOT NULL UNIQUE,
    teller_name VARCHAR(100) NOT NULL,
    branch_code VARCHAR(10) NOT NULL,
    bank_code VARCHAR(10) NOT NULL,
    status VARCHAR(10) DEFAULT 'ACTIVE',
    register_date VARCHAR(8),
    register_time VARCHAR(6),
    register_by VARCHAR(20),
    last_update_date VARCHAR(8),
    last_update_time VARCHAR(6),
    last_update_user_id VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- EPlaton Teller Tables (for eplatonframework package)
CREATE TABLE IF NOT EXISTS eplaton_teller (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    teller_id VARCHAR(20) NOT NULL UNIQUE,
    teller_name VARCHAR(100),
    branch_code VARCHAR(10),
    teller_type VARCHAR(10),
    teller_status VARCHAR(2),
    hire_date DATE,
    termination_date DATE,
    daily_limit DECIMAL(15,2),
    monthly_limit DECIMAL(15,2),
    currency_code VARCHAR(3),
    email VARCHAR(100),
    phone VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP,
    version BIGINT DEFAULT 0,
    created_by VARCHAR(50),
    modified_by VARCHAR(50),
    is_deleted BOOLEAN DEFAULT FALSE
);

-- User Tables
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL UNIQUE,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    status VARCHAR(10) DEFAULT 'ACTIVE',
    register_date VARCHAR(8),
    register_time VARCHAR(6),
    register_by VARCHAR(20),
    last_update_date VARCHAR(8),
    last_update_time VARCHAR(6),
    last_update_user_id VARCHAR(20),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Users JPA Table (JPA 엔티티용 테이블)
CREATE TABLE IF NOT EXISTS users_jpa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    user_id VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(500),
    job VARCHAR(100),
    age INT,
    company VARCHAR(200),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    name VARCHAR(100),
    phone VARCHAR(20),
    department VARCHAR(100),
    position VARCHAR(100),
    user_type VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Transaction Log Tables
CREATE TABLE IF NOT EXISTS transaction_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_id VARCHAR(50) NOT NULL UNIQUE,
    transaction_no VARCHAR(50),
    host_name VARCHAR(100),
    system_name VARCHAR(50),
    method_name VARCHAR(100),
    bank_code VARCHAR(10),
    branch_code VARCHAR(10),
    user_id VARCHAR(50),
    channel_type VARCHAR(20),
    business_date VARCHAR(8),
    register_date VARCHAR(8),
    in_time VARCHAR(6),
    out_time VARCHAR(6),
    response_time BIGINT,
    error_code VARCHAR(10),
    event_no VARCHAR(20),
    ip_address VARCHAR(15),
    update_date TIMESTAMP,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for better performance
CREATE INDEX IF NOT EXISTS idx_cash_card_card_number ON cash_card(card_number);
CREATE INDEX IF NOT EXISTS idx_cash_card_account_no ON cash_card(primary_account_no);
CREATE INDEX IF NOT EXISTS idx_cash_card_cif_no ON cash_card(cif_no);
CREATE INDEX IF NOT EXISTS idx_hot_card_card_number ON hot_card(card_number);
CREATE INDEX IF NOT EXISTS idx_deposit_account_number ON deposit(account_number);
CREATE INDEX IF NOT EXISTS idx_deposit_cif_no ON deposit(cif_no);
CREATE INDEX IF NOT EXISTS idx_common_code ON common(common_code);
CREATE INDEX IF NOT EXISTS idx_common_type ON common(common_type);
CREATE INDEX IF NOT EXISTS idx_teller_id ON teller(teller_id);
CREATE INDEX IF NOT EXISTS idx_user_id ON users(user_id);
CREATE INDEX IF NOT EXISTS idx_users_jpa_user_id ON users_jpa(user_id);
CREATE INDEX IF NOT EXISTS idx_users_jpa_email ON users_jpa(email);
CREATE INDEX IF NOT EXISTS idx_transaction_log_transaction_id ON transaction_log(transaction_id);
CREATE INDEX IF NOT EXISTS idx_transaction_log_user_id ON transaction_log(user_id);
CREATE INDEX IF NOT EXISTS idx_transaction_log_system_name ON transaction_log(system_name);

-- Tech Spec Tables (기술 스펙 테이블)
CREATE TABLE IF NOT EXISTS tech_spec (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    sub_item VARCHAR(200) NOT NULL,
    technology_name VARCHAR(200) NOT NULL,
    version VARCHAR(50),
    description TEXT,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tech Spec Indexes
CREATE INDEX IF NOT EXISTS idx_tech_spec_category ON tech_spec(category);
CREATE INDEX IF NOT EXISTS idx_tech_spec_sub_item ON tech_spec(sub_item);
CREATE INDEX IF NOT EXISTS idx_tech_spec_technology_name ON tech_spec(technology_name);
CREATE INDEX IF NOT EXISTS idx_tech_spec_is_active ON tech_spec(is_active); 