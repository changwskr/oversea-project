-- SKCC Oversea Banking System Test Data
-- This file contains test data insertion scripts

-- Insert Common Data
INSERT INTO common (common_code, common_name, common_type, common_value, description, is_active, effective_date, register_date, register_by) VALUES
('BANK_001', 'SKCC Bank', 'BANK', '001', 'SKCC Main Bank', true, '20240101', '20240101', 'SYSTEM'),
('BANK_002', 'SKCC Overseas', 'BANK', '002', 'SKCC Overseas Branch', true, '20240101', '20240101', 'SYSTEM'),
('BRANCH_001', 'Seoul Main Branch', 'BRANCH', '001', 'Seoul Main Branch', true, '20240101', '20240101', 'SYSTEM'),
('BRANCH_002', 'Busan Branch', 'BRANCH', '002', 'Busan Branch', true, '20240101', '20240101', 'SYSTEM'),
('CURRENCY_KRW', 'Korean Won', 'CURRENCY', 'KRW', 'Korean Won Currency', true, '20240101', '20240101', 'SYSTEM'),
('CURRENCY_USD', 'US Dollar', 'CURRENCY', 'USD', 'US Dollar Currency', true, '20240101', '20240101', 'SYSTEM'),
('CARD_STATUS_ACTIVE', 'Active Card', 'CARD_STATUS', 'ACTIVE', 'Active Cash Card Status', true, '20240101', '20240101', 'SYSTEM'),
('CARD_STATUS_BLOCKED', 'Blocked Card', 'CARD_STATUS', 'BLOCKED', 'Blocked Cash Card Status', true, '20240101', '20240101', 'SYSTEM'),
('CARD_STATUS_EXPIRED', 'Expired Card', 'CARD_STATUS', 'EXPIRED', 'Expired Cash Card Status', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_STATUS_ACTIVE', 'Active Account', 'ACCOUNT_STATUS', 'ACTIVE', 'Active Account Status', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_STATUS_CLOSED', 'Closed Account', 'ACCOUNT_STATUS', 'CLOSED', 'Closed Account Status', true, '20240101', '20240101', 'SYSTEM'),
('USER_STATUS_ACTIVE', 'Active User', 'USER_STATUS', 'ACTIVE', 'Active User Status', true, '20240101', '20240101', 'SYSTEM'),
('USER_STATUS_INACTIVE', 'Inactive User', 'USER_STATUS', 'INACTIVE', 'Inactive User Status', true, '20240101', '20240101', 'SYSTEM');

-- Insert User Data
INSERT INTO user (user_id, user_name, email, phone, status, register_date, register_by) VALUES
('USER001', '김철수', 'kim.cs@skcc.com', '010-1234-5678', 'ACTIVE', '20240101', 'SYSTEM'),
('USER002', '이영희', 'lee.yh@skcc.com', '010-2345-6789', 'ACTIVE', '20240101', 'SYSTEM'),
('USER003', '박민수', 'park.ms@skcc.com', '010-3456-7890', 'ACTIVE', '20240101', 'SYSTEM'),
('ADMIN001', '관리자', 'admin@skcc.com', '010-9999-9999', 'ACTIVE', '20240101', 'SYSTEM');

-- Insert Teller Data
INSERT INTO teller (teller_id, teller_name, branch_code, bank_code, status, register_date, register_by) VALUES
('TELLER001', '김텔러', '001', '001', 'ACTIVE', '20240101', 'SYSTEM'),
('TELLER002', '이텔러', '001', '001', 'ACTIVE', '20240101', 'SYSTEM'),
('TELLER003', '박텔러', '002', '001', 'ACTIVE', '20240101', 'SYSTEM'),
('TELLER004', '최텔러', '002', '001', 'ACTIVE', '20240101', 'SYSTEM');

-- Insert Cash Card Data
INSERT INTO cash_card (card_number, primary_account_no, bank_code, branch_code, cif_no, cif_name, currency, daily_limit_amount, daily_accum_amount, status, effective_date, expiry_date, register_date, register_by) VALUES
('1234567890123456', '110123456789', '001', '001', 'CIF001', '김철수', 'KRW', 1000000.00, 0.00, 'ACTIVE', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123457', '110123456790', '001', '001', 'CIF002', '이영희', 'KRW', 2000000.00, 0.00, 'ACTIVE', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123458', '110123456791', '001', '002', 'CIF003', '박민수', 'KRW', 1500000.00, 0.00, 'ACTIVE', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123459', '110123456792', '001', '002', 'CIF004', '최영수', 'KRW', 3000000.00, 0.00, 'BLOCKED', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123460', '110123456793', '001', '001', 'CIF005', '정미영', 'USD', 50000.00, 0.00, 'ACTIVE', '20240101', '20261231', '20240101', 'SYSTEM');

-- Insert Hot Card Data
INSERT INTO hot_card (card_number, sequence_no, primary_account_no, cif_no, cif_name, status, register_date, register_by, remark) VALUES
('1234567890123459', 1, '110123456792', 'CIF004', '최영수', 'BLOCKED', '20240101', 'SYSTEM', 'Fraudulent activity detected'),
('1234567890123461', 1, '110123456794', 'CIF006', '한지민', 'BLOCKED', '20240101', 'SYSTEM', 'Lost card reported'),
('1234567890123462', 1, '110123456795', 'CIF007', '송혜교', 'BLOCKED', '20240101', 'SYSTEM', 'Stolen card reported');

-- Insert Deposit Data
INSERT INTO deposit (account_number, bank_code, branch_code, cif_no, cif_name, currency, balance, interest_rate, status, open_date, maturity_date, register_date, register_by) VALUES
('110123456789', '001', '001', 'CIF001', '김철수', 'KRW', 5000000.00, 2.50, 'ACTIVE', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456790', '001', '001', 'CIF002', '이영희', 'KRW', 7500000.00, 3.00, 'ACTIVE', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456791', '001', '002', 'CIF003', '박민수', 'KRW', 3000000.00, 2.75, 'ACTIVE', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456792', '001', '002', 'CIF004', '최영수', 'KRW', 0.00, 0.00, 'CLOSED', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456793', '001', '001', 'CIF005', '정미영', 'USD', 50000.00, 1.50, 'ACTIVE', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456794', '001', '001', 'CIF006', '한지민', 'KRW', 12000000.00, 3.25, 'ACTIVE', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456795', '001', '002', 'CIF007', '송혜교', 'KRW', 8000000.00, 2.80, 'ACTIVE', '20240101', '20241231', '20240101', 'SYSTEM');

-- Insert Transaction Log Data
INSERT INTO transaction_log (transaction_id, transaction_no, host_name, system_name, method_name, bank_code, branch_code, user_id, channel_type, business_date, register_date, in_time, out_time, response_time, error_code, event_no, ip_address) VALUES
('TXN001', 'TXN20240101001', 'HOST001', 'CASH_CARD', 'createCashCard', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '090000', '090015', 15000, 'I0000', 'EVT001', '192.168.1.100'),
('TXN002', 'TXN20240101002', 'HOST001', 'DEPOSIT', 'createDeposit', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '090100', '090110', 10000, 'I0000', 'EVT002', '192.168.1.100'),
('TXN003', 'TXN20240101003', 'HOST001', 'CASH_CARD', 'getCashCardById', '001', '001', 'USER002', 'WEB', '20240101', '20240101', '090200', '090205', 5000, 'I0000', 'EVT003', '192.168.1.101'),
('TXN004', 'TXN20240101004', 'HOST001', 'DEPOSIT', 'getDepositById', '001', '002', 'USER002', 'WEB', '20240101', '20240101', '090300', '090308', 8000, 'I0000', 'EVT004', '192.168.1.101'),
('TXN005', 'TXN20240101005', 'HOST001', 'COMMON', 'getCommonInfo', '001', '001', 'USER003', 'WEB', '20240101', '20240101', '090400', '090402', 2000, 'I0000', 'EVT005', '192.168.1.102'),
('TXN006', 'TXN20240101006', 'HOST001', 'TELLER', 'createTeller', '001', '001', 'ADMIN001', 'WEB', '20240101', '20240101', '090500', '090512', 12000, 'I0000', 'EVT006', '192.168.1.103'),
('TXN007', 'TXN20240101007', 'HOST001', 'CASH_CARD', 'updateCashCard', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '090600', '090615', 15000, 'I0000', 'EVT007', '192.168.1.100'),
('TXN008', 'TXN20240101008', 'HOST001', 'DEPOSIT', 'updateDeposit', '001', '002', 'USER002', 'WEB', '20240101', '20240101', '090700', '090710', 10000, 'I0000', 'EVT008', '192.168.1.101'),
('TXN009', 'TXN20240101009', 'HOST001', 'CASH_CARD', 'deleteCashCard', '001', '001', 'ADMIN001', 'WEB', '20240101', '20240101', '090800', '090805', 5000, 'I0000', 'EVT009', '192.168.1.103'),
('TXN010', 'TXN20240101010', 'HOST001', 'TRANSACTION_LOG', 'getTransactionLogs', '001', '001', 'ADMIN001', 'WEB', '20240101', '20240101', '090900', '090905', 5000, 'I0000', 'EVT010', '192.168.1.103'); 