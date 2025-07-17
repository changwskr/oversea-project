-- SKCC Oversea Banking System Comprehensive Test Data
-- This file contains comprehensive test data for all tables
-- Generated for testing purposes with various scenarios

-- ========================================
-- 1. COMMON TABLE DATA (공통 코드 데이터)
-- ========================================
INSERT INTO common (common_code, common_name, common_type, common_value, description, is_active, effective_date, register_date, register_by) VALUES
-- Bank Codes (은행 코드)
('BANK_001', 'SKCC Main Bank', 'BANK', '001', 'SKCC Main Bank Code', true, '20240101', '20240101', 'SYSTEM'),
('BANK_002', 'SKCC Overseas', 'BANK', '002', 'SKCC Overseas Branch Code', true, '20240101', '20240101', 'SYSTEM'),
('BANK_003', 'SKCC Investment', 'BANK', '003', 'SKCC Investment Bank Code', true, '20240101', '20240101', 'SYSTEM'),

-- Branch Codes (지점 코드)
('BRANCH_001', 'Seoul Main Branch', 'BRANCH', '001', 'Seoul Main Branch Code', true, '20240101', '20240101', 'SYSTEM'),
('BRANCH_002', 'Busan Branch', 'BRANCH', '002', 'Busan Branch Code', true, '20240101', '20240101', 'SYSTEM'),
('BRANCH_003', 'Incheon Branch', 'BRANCH', '003', 'Incheon Branch Code', true, '20240101', '20240101', 'SYSTEM'),
('BRANCH_004', 'Daegu Branch', 'BRANCH', '004', 'Daegu Branch Code', true, '20240101', '20240101', 'SYSTEM'),
('BRANCH_005', 'Daejeon Branch', 'BRANCH', '005', 'Daejeon Branch Code', true, '20240101', '20240101', 'SYSTEM'),

-- Currency Codes (통화 코드)
('CURRENCY_KRW', 'Korean Won', 'CURRENCY', 'KRW', 'Korean Won Currency', true, '20240101', '20240101', 'SYSTEM'),
('CURRENCY_USD', 'US Dollar', 'CURRENCY', 'USD', 'US Dollar Currency', true, '20240101', '20240101', 'SYSTEM'),
('CURRENCY_EUR', 'Euro', 'CURRENCY', 'EUR', 'Euro Currency', true, '20240101', '20240101', 'SYSTEM'),
('CURRENCY_JPY', 'Japanese Yen', 'CURRENCY', 'JPY', 'Japanese Yen Currency', true, '20240101', '20240101', 'SYSTEM'),
('CURRENCY_CNY', 'Chinese Yuan', 'CURRENCY', 'CNY', 'Chinese Yuan Currency', true, '20240101', '20240101', 'SYSTEM'),

-- Card Status Codes (카드 상태 코드)
('CARD_STATUS_A', 'Active', 'CARD_STATUS', 'A', 'Active Card Status', true, '20240101', '20240101', 'SYSTEM'),
('CARD_STATUS_I', 'Inactive', 'CARD_STATUS', 'I', 'Inactive Card Status', true, '20240101', '20240101', 'SYSTEM'),
('CARD_STATUS_H', 'Hot Card', 'CARD_STATUS', 'H', 'Hot Card Status (Blocked)', true, '20240101', '20240101', 'SYSTEM'),
('CARD_STATUS_S', 'Suspended', 'CARD_STATUS', 'S', 'Suspended Card Status', true, '20240101', '20240101', 'SYSTEM'),
('CARD_STATUS_P', 'Pending', 'CARD_STATUS', 'P', 'Pending Approval Status', true, '20240101', '20240101', 'SYSTEM'),

-- Account Status Codes (계좌 상태 코드)
('ACCOUNT_STATUS_A', 'Active', 'ACCOUNT_STATUS', 'A', 'Active Account Status', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_STATUS_C', 'Closed', 'ACCOUNT_STATUS', 'C', 'Closed Account Status', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_STATUS_F', 'Frozen', 'ACCOUNT_STATUS', 'F', 'Frozen Account Status', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_STATUS_D', 'Dormant', 'ACCOUNT_STATUS', 'D', 'Dormant Account Status', true, '20240101', '20240101', 'SYSTEM'),

-- User Status Codes (사용자 상태 코드)
('USER_STATUS_A', 'Active', 'USER_STATUS', 'A', 'Active User Status', true, '20240101', '20240101', 'SYSTEM'),
('USER_STATUS_I', 'Inactive', 'USER_STATUS', 'I', 'Inactive User Status', true, '20240101', '20240101', 'SYSTEM'),
('USER_STATUS_L', 'Locked', 'USER_STATUS', 'L', 'Locked User Status', true, '20240101', '20240101', 'SYSTEM'),

-- Teller Status Codes (텔러 상태 코드)
('TELLER_STATUS_A', 'Active', 'TELLER_STATUS', 'A', 'Active Teller Status', true, '20240101', '20240101', 'SYSTEM'),
('TELLER_STATUS_I', 'Inactive', 'TELLER_STATUS', 'I', 'Inactive Teller Status', true, '20240101', '20240101', 'SYSTEM'),
('TELLER_STATUS_V', 'Vacation', 'TELLER_STATUS', 'V', 'Vacation Teller Status', true, '20240101', '20240101', 'SYSTEM'),

-- Card Types (카드 종류)
('CARD_TYPE_DEBIT', 'Debit Card', 'CARD_TYPE', 'DEBIT', 'Debit Card Type', true, '20240101', '20240101', 'SYSTEM'),
('CARD_TYPE_CREDIT', 'Credit Card', 'CARD_TYPE', 'CREDIT', 'Credit Card Type', true, '20240101', '20240101', 'SYSTEM'),
('CARD_TYPE_PREPAID', 'Prepaid Card', 'CARD_TYPE', 'PREPAID', 'Prepaid Card Type', true, '20240101', '20240101', 'SYSTEM'),

-- Account Types (계좌 종류)
('ACCOUNT_TYPE_SAVINGS', 'Savings Account', 'ACCOUNT_TYPE', 'SAVINGS', 'Savings Account Type', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_TYPE_CHECKING', 'Checking Account', 'ACCOUNT_TYPE', 'CHECKING', 'Checking Account Type', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_TYPE_FIXED', 'Fixed Deposit', 'ACCOUNT_TYPE', 'FIXED', 'Fixed Deposit Account Type', true, '20240101', '20240101', 'SYSTEM'),
('ACCOUNT_TYPE_CURRENT', 'Current Account', 'ACCOUNT_TYPE', 'CURRENT', 'Current Account Type', true, '20240101', '20240101', 'SYSTEM');

-- ========================================
-- 2. USER TABLE DATA (사용자 데이터)
-- ========================================
INSERT INTO users (user_id, user_name, email, phone, status, register_date, register_by) VALUES
-- Regular Users (일반 사용자)
('USER001', '김철수', 'kim.cs@skcc.com', '010-1234-5678', 'A', '20240101', 'SYSTEM'),
('USER002', '이영희', 'lee.yh@skcc.com', '010-2345-6789', 'A', '20240101', 'SYSTEM'),
('USER003', '박민수', 'park.ms@skcc.com', '010-3456-7890', 'A', '20240101', 'SYSTEM'),
('USER004', '최영수', 'choi.ys@skcc.com', '010-4567-8901', 'A', '20240101', 'SYSTEM'),
('USER005', '정미영', 'jung.my@skcc.com', '010-5678-9012', 'A', '20240101', 'SYSTEM'),
('USER006', '한지민', 'han.jm@skcc.com', '010-6789-0123', 'A', '20240101', 'SYSTEM'),
('USER007', '송혜교', 'song.hg@skcc.com', '010-7890-1234', 'A', '20240101', 'SYSTEM'),
('USER008', '강동원', 'kang.dw@skcc.com', '010-8901-2345', 'A', '20240101', 'SYSTEM'),
('USER009', '배두나', 'bae.dn@skcc.com', '010-9012-3456', 'A', '20240101', 'SYSTEM'),
('USER010', '원빈', 'won.b@skcc.com', '010-0123-4567', 'A', '20240101', 'SYSTEM'),

-- Inactive Users (비활성 사용자)
('USER011', '김비활성', 'kim.inactive@skcc.com', '010-1111-1111', 'I', '20240101', 'SYSTEM'),
('USER012', '이잠금', 'lee.locked@skcc.com', '010-2222-2222', 'L', '20240101', 'SYSTEM'),

-- Admin Users (관리자)
('ADMIN001', '시스템관리자', 'admin@skcc.com', '010-9999-9999', 'A', '20240101', 'SYSTEM'),
('ADMIN002', '운영관리자', 'operator@skcc.com', '010-8888-8888', 'A', '20240101', 'SYSTEM'),
('ADMIN003', '보안관리자', 'security@skcc.com', '010-7777-7777', 'A', '20240101', 'SYSTEM');

-- ========================================
-- 3. TELLER TABLE DATA (텔러 데이터)
-- ========================================
INSERT INTO teller (teller_id, teller_name, branch_code, bank_code, status, register_date, register_by) VALUES
-- Seoul Main Branch Tellers (서울본점 텔러)
('TELLER001', '김텔러', '001', '001', 'A', '20240101', 'SYSTEM'),
('TELLER002', '이텔러', '001', '001', 'A', '20240101', 'SYSTEM'),
('TELLER003', '박텔러', '001', '001', 'A', '20240101', 'SYSTEM'),
('TELLER004', '최텔러', '001', '001', 'V', '20240101', 'SYSTEM'),

-- Busan Branch Tellers (부산지점 텔러)
('TELLER005', '정텔러', '002', '001', 'A', '20240101', 'SYSTEM'),
('TELLER006', '한텔러', '002', '001', 'A', '20240101', 'SYSTEM'),
('TELLER007', '송텔러', '002', '001', 'A', '20240101', 'SYSTEM'),

-- Incheon Branch Tellers (인천지점 텔러)
('TELLER008', '강텔러', '003', '001', 'A', '20240101', 'SYSTEM'),
('TELLER009', '배텔러', '003', '001', 'A', '20240101', 'SYSTEM'),

-- Daegu Branch Tellers (대구지점 텔러)
('TELLER010', '원텔러', '004', '001', 'A', '20240101', 'SYSTEM'),
('TELLER011', '윤텔러', '004', '001', 'I', '20240101', 'SYSTEM'),

-- Daejeon Branch Tellers (대전지점 텔러)
('TELLER012', '임텔러', '005', '001', 'A', '20240101', 'SYSTEM'),
('TELLER013', '조텔러', '005', '001', 'A', '20240101', 'SYSTEM');

-- ========================================
-- 4. DEPOSIT TABLE DATA (예금 데이터)
-- ========================================
INSERT INTO deposit (account_number, bank_code, branch_code, cif_no, cif_name, currency, balance, interest_rate, status, open_date, maturity_date, register_date, register_by) VALUES
-- Active Savings Accounts (활성 저축계좌)
('110123456789', '001', '001', 'CIF001', '김철수', 'KRW', 5000000.00, 2.50, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456790', '001', '001', 'CIF002', '이영희', 'KRW', 7500000.00, 3.00, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456791', '001', '002', 'CIF003', '박민수', 'KRW', 3000000.00, 2.75, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456792', '001', '002', 'CIF004', '최영수', 'KRW', 12000000.00, 3.25, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456793', '001', '001', 'CIF005', '정미영', 'USD', 50000.00, 1.50, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456794', '001', '001', 'CIF006', '한지민', 'KRW', 12000000.00, 3.25, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456795', '001', '002', 'CIF007', '송혜교', 'KRW', 8000000.00, 2.80, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456796', '001', '003', 'CIF008', '강동원', 'KRW', 15000000.00, 3.50, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456797', '001', '003', 'CIF009', '배두나', 'EUR', 25000.00, 1.75, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456798', '001', '004', 'CIF010', '원빈', 'KRW', 25000000.00, 4.00, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),

-- Foreign Currency Accounts (외화계좌)
('110123456799', '001', '001', 'CIF011', '김외화', 'USD', 100000.00, 2.00, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456800', '001', '002', 'CIF012', '이외화', 'EUR', 50000.00, 1.80, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456801', '001', '003', 'CIF013', '박외화', 'JPY', 5000000.00, 0.50, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456802', '001', '004', 'CIF014', '최외화', 'CNY', 200000.00, 2.20, 'A', '20240101', '20241231', '20240101', 'SYSTEM'),

-- Closed Accounts (해지계좌)
('110123456803', '001', '001', 'CIF015', '김해지', 'KRW', 0.00, 0.00, 'C', '20240101', '20241231', '20240101', 'SYSTEM'),
('110123456804', '001', '002', 'CIF016', '이해지', 'KRW', 0.00, 0.00, 'C', '20240101', '20241231', '20240101', 'SYSTEM'),

-- Frozen Accounts (동결계좌)
('110123456805', '001', '003', 'CIF017', '김동결', 'KRW', 5000000.00, 2.50, 'F', '20240101', '20241231', '20240101', 'SYSTEM'),

-- Dormant Accounts (휴면계좌)
('110123456806', '001', '004', 'CIF018', '김휴면', 'KRW', 1000000.00, 2.50, 'D', '20240101', '20241231', '20240101', 'SYSTEM');

-- ========================================
-- 5. CASH CARD TABLE DATA (현금카드 데이터)
-- ========================================
INSERT INTO cash_card (card_number, primary_account_no, bank_code, branch_code, cif_no, cif_name, currency, daily_limit_amount, daily_accum_amount, status, effective_date, expiry_date, register_date, register_by) VALUES
-- Active Cards (활성 카드)
('1234567890123456', '110123456789', '001', '001', 'CIF001', '김철수', 'KRW', 1000000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123457', '110123456790', '001', '001', 'CIF002', '이영희', 'KRW', 2000000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123458', '110123456791', '001', '002', 'CIF003', '박민수', 'KRW', 1500000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123459', '110123456792', '001', '002', 'CIF004', '최영수', 'KRW', 3000000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123460', '110123456793', '001', '001', 'CIF005', '정미영', 'USD', 50000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123461', '110123456794', '001', '001', 'CIF006', '한지민', 'KRW', 5000000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123462', '110123456795', '001', '002', 'CIF007', '송혜교', 'KRW', 2500000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123463', '110123456796', '001', '003', 'CIF008', '강동원', 'KRW', 8000000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123464', '110123456797', '001', '003', 'CIF009', '배두나', 'EUR', 10000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123465', '110123456798', '001', '004', 'CIF010', '원빈', 'KRW', 10000000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),

-- Foreign Currency Cards (외화 카드)
('1234567890123466', '110123456799', '001', '001', 'CIF011', '김외화', 'USD', 10000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123467', '110123456800', '001', '002', 'CIF012', '이외화', 'EUR', 5000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123468', '110123456801', '001', '003', 'CIF013', '박외화', 'JPY', 500000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123469', '110123456802', '001', '004', 'CIF014', '최외화', 'CNY', 20000.00, 0.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),

-- Inactive Cards (비활성 카드)
('1234567890123470', '110123456803', '001', '001', 'CIF015', '김해지', 'KRW', 1000000.00, 0.00, 'I', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123471', '110123456804', '001', '002', 'CIF016', '이해지', 'KRW', 1500000.00, 0.00, 'I', '20240101', '20261231', '20240101', 'SYSTEM'),

-- Suspended Cards (정지 카드)
('1234567890123472', '110123456805', '001', '003', 'CIF017', '김동결', 'KRW', 2000000.00, 0.00, 'S', '20240101', '20261231', '20240101', 'SYSTEM'),

-- Pending Cards (승인대기 카드)
('1234567890123473', '110123456806', '001', '004', 'CIF018', '김휴면', 'KRW', 1000000.00, 0.00, 'P', '20240101', '20261231', '20240101', 'SYSTEM'),

-- Cards with Daily Accumulation (일일 누적이 있는 카드)
('1234567890123474', '110123456789', '001', '001', 'CIF001', '김철수', 'KRW', 1000000.00, 250000.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123475', '110123456790', '001', '001', 'CIF002', '이영희', 'KRW', 2000000.00, 1800000.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM'),
('1234567890123476', '110123456791', '001', '002', 'CIF003', '박민수', 'KRW', 1500000.00, 1200000.00, 'A', '20240101', '20261231', '20240101', 'SYSTEM');

-- ========================================
-- 6. HOT CARD TABLE DATA (핫카드 데이터)
-- ========================================
INSERT INTO hot_card (card_number, sequence_no, primary_account_no, cif_no, cif_name, status, register_date, register_by, remark) VALUES
-- Lost Cards (분실 카드)
('1234567890123470', 1, '110123456803', 'CIF015', '김해지', 'H', '20240101', 'SYSTEM', 'Lost card reported by customer'),
('1234567890123471', 1, '110123456804', 'CIF016', '이해지', 'H', '20240101', 'SYSTEM', 'Lost card reported by customer'),

-- Stolen Cards (도난 카드)
('1234567890123472', 1, '110123456805', 'CIF017', '김동결', 'H', '20240101', 'SYSTEM', 'Stolen card reported by customer'),

-- Fraudulent Activity (사기 의심)
('1234567890123473', 1, '110123456806', 'CIF018', '김휴면', 'H', '20240101', 'SYSTEM', 'Fraudulent activity detected by system'),

-- Multiple Hot Card Entries (다중 핫카드 등록)
('1234567890123470', 2, '110123456803', 'CIF015', '김해지', 'H', '20240102', 'SYSTEM', 'Second hot card registration - suspicious activity'),
('1234567890123471', 2, '110123456804', 'CIF016', '이해지', 'H', '20240102', 'SYSTEM', 'Second hot card registration - unauthorized usage');

-- ========================================
-- 7. TRANSACTION LOG TABLE DATA (거래 로그 데이터)
-- ========================================
INSERT INTO transaction_log (transaction_id, transaction_no, host_name, system_name, method_name, bank_code, branch_code, user_id, channel_type, business_date, register_date, in_time, out_time, response_time, error_code, event_no, ip_address) VALUES
-- Cash Card Transactions (현금카드 거래)
('TXN001', 'TXN20240101001', 'HOST001', 'CASH_CARD', 'createCashCard', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '090000', '090015', 15000, 'I0000', 'EVT001', '192.168.1.100'),
('TXN002', 'TXN20240101002', 'HOST001', 'CASH_CARD', 'getCardInfo', '001', '001', 'USER002', 'WEB', '20240101', '20240101', '090100', '090105', 5000, 'I0000', 'EVT002', '192.168.1.101'),
('TXN003', 'TXN20240101003', 'HOST001', 'CASH_CARD', 'updateCardInfo', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '090200', '090215', 15000, 'I0000', 'EVT003', '192.168.1.100'),
('TXN004', 'TXN20240101004', 'HOST001', 'CASH_CARD', 'issueCard', '001', '002', 'USER003', 'WEB', '20240101', '20240101', '090300', '090320', 20000, 'I0000', 'EVT004', '192.168.1.102'),
('TXN005', 'TXN20240101005', 'HOST001', 'CASH_CARD', 'registerHotCard', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '090400', '090410', 10000, 'I0000', 'EVT005', '192.168.1.100'),

-- Deposit Transactions (예금 거래)
('TXN006', 'TXN20240101006', 'HOST001', 'DEPOSIT', 'createDeposit', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '090500', '090510', 10000, 'I0000', 'EVT006', '192.168.1.100'),
('TXN007', 'TXN20240101007', 'HOST001', 'DEPOSIT', 'getDepositInfo', '001', '002', 'USER002', 'WEB', '20240101', '20240101', '090600', '090605', 5000, 'I0000', 'EVT007', '192.168.1.101'),
('TXN008', 'TXN20240101008', 'HOST001', 'DEPOSIT', 'updateDeposit', '001', '002', 'USER002', 'WEB', '20240101', '20240101', '090700', '090710', 10000, 'I0000', 'EVT008', '192.168.1.101'),

-- Common Code Transactions (공통코드 거래)
('TXN009', 'TXN20240101009', 'HOST001', 'COMMON', 'getCommonInfo', '001', '001', 'USER003', 'WEB', '20240101', '20240101', '090800', '090802', 2000, 'I0000', 'EVT009', '192.168.1.102'),
('TXN010', 'TXN20240101010', 'HOST001', 'COMMON', 'getCommonByType', '001', '001', 'USER003', 'WEB', '20240101', '20240101', '090900', '090903', 3000, 'I0000', 'EVT010', '192.168.1.102'),

-- Teller Transactions (텔러 거래)
('TXN011', 'TXN20240101011', 'HOST001', 'TELLER', 'createTeller', '001', '001', 'ADMIN001', 'WEB', '20240101', '20240101', '091000', '091012', 12000, 'I0000', 'EVT011', '192.168.1.103'),
('TXN012', 'TXN20240101012', 'HOST001', 'TELLER', 'getTellerInfo', '001', '002', 'ADMIN002', 'WEB', '20240101', '20240101', '091100', '091105', 5000, 'I0000', 'EVT012', '192.168.1.104'),

-- User Transactions (사용자 거래)
('TXN013', 'TXN20240101013', 'HOST001', 'USER', 'createUser', '001', '001', 'ADMIN001', 'WEB', '20240101', '20240101', '091200', '091210', 10000, 'I0000', 'EVT013', '192.168.1.103'),
('TXN014', 'TXN20240101014', 'HOST001', 'USER', 'getUserInfo', '001', '001', 'ADMIN002', 'WEB', '20240101', '20240101', '091300', '091305', 5000, 'I0000', 'EVT014', '192.168.1.104'),

-- Error Transactions (에러 거래)
('TXN015', 'TXN20240101015', 'HOST001', 'CASH_CARD', 'getCardInfo', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '091400', '091405', 5000, 'E0001', 'EVT015', '192.168.1.100'),
('TXN016', 'TXN20240101016', 'HOST001', 'DEPOSIT', 'updateDeposit', '001', '002', 'USER002', 'WEB', '20240101', '20240101', '091500', '091510', 10000, 'E0002', 'EVT016', '192.168.1.101'),

-- Mobile Channel Transactions (모바일 채널 거래)
('TXN017', 'TXN20240101017', 'HOST001', 'CASH_CARD', 'getCardInfo', '001', '001', 'USER001', 'MOBILE', '20240101', '20240101', '091600', '091605', 5000, 'I0000', 'EVT017', '192.168.1.100'),
('TXN018', 'TXN20240101018', 'HOST001', 'DEPOSIT', 'getDepositInfo', '001', '002', 'USER002', 'MOBILE', '20240101', '20240101', '091700', '091705', 5000, 'I0000', 'EVT018', '192.168.1.101'),

-- ATM Channel Transactions (ATM 채널 거래)
('TXN019', 'TXN20240101019', 'HOST001', 'CASH_CARD', 'withdraw', '001', '001', 'USER001', 'ATM', '20240101', '20240101', '091800', '091820', 20000, 'I0000', 'EVT019', '192.168.1.100'),
('TXN020', 'TXN20240101020', 'HOST001', 'DEPOSIT', 'deposit', '001', '002', 'USER002', 'ATM', '20240101', '20240101', '091900', '091915', 15000, 'I0000', 'EVT020', '192.168.1.101'),

-- High Value Transactions (고액 거래)
('TXN021', 'TXN20240101021', 'HOST001', 'CASH_CARD', 'transfer', '001', '001', 'USER001', 'WEB', '20240101', '20240101', '092000', '092030', 30000, 'I0000', 'EVT021', '192.168.1.100'),
('TXN022', 'TXN20240101022', 'HOST001', 'DEPOSIT', 'transfer', '001', '002', 'USER002', 'WEB', '20240101', '20240101', '092100', '092130', 30000, 'I0000', 'EVT022', '192.168.1.101'),

-- System Maintenance Transactions (시스템 유지보수 거래)
('TXN023', 'TXN20240101023', 'HOST001', 'SYSTEM', 'backup', '001', '001', 'ADMIN001', 'SYSTEM', '20240101', '20240101', '230000', '230500', 300000, 'I0000', 'EVT023', '192.168.1.103'),
('TXN024', 'TXN20240101024', 'HOST001', 'SYSTEM', 'cleanup', '001', '001', 'ADMIN002', 'SYSTEM', '20240101', '20240101', '235000', '235100', 60000, 'I0000', 'EVT024', '192.168.1.104'),

-- Batch Job Transactions (배치 작업 거래)
('TXN025', 'TXN20240101025', 'HOST001', 'BATCH', 'dailySettlement', '001', '001', 'SYSTEM', 'BATCH', '20240101', '20240101', '000000', '000500', 300000, 'I0000', 'EVT025', '192.168.1.105'),
('TXN026', 'TXN20240101026', 'HOST001', 'BATCH', 'monthlyReport', '001', '001', 'SYSTEM', 'BATCH', '20240101', '20240101', '010000', '010300', 180000, 'I0000', 'EVT026', '192.168.1.105');

-- ========================================
--8EW JPA TABLES DATA (새로운 JPA 테이블 데이터)
-- ========================================

-- Users JPA Table Data (사용자 JPA 테이블 데이터)
INSERT INTO users_jpa (email, password, username, user_id, address, job, age, company, status, name, phone, department, position, user_type, created_date, last_modified_date) VALUES
-- Regular Users (일반 사용자)
('kim.cs@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '김철수', 'USER001', '서울시 강남구', '개발자', 30, 'SKCC', 'ACTIVE', '김철수', '010-1234-5678', 'IT', '사원', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('lee.yh@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '이영희', 'USER002', '서울시 서초구', '디자이너', 28, 'SKCC', 'ACTIVE', '이영희', '010-2345-6789', '디자인', '사원', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('park.ms@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '박민수', 'USER003', '부산시 해운대구', '기획자', 32, 'SKCC', 'ACTIVE', '박민수', '010-3456-7890', '기획', '대리', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('choi.ys@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '최영수', 'USER004', '인천시 연수구', '테스터', 29, 'SKCC', 'ACTIVE', '최영수', '010-4567-8901', 'QA', '사원', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('jung.my@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '정미영', 'USER005', '대구시 수성구', '운영자', 31, 'SKCC', 'ACTIVE', '정미영', '010-5678-9012', '운영', '대리', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('han.jm@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '한지민', 'USER006', '대전시 유성구', '보안관리자', 33, 'SKCC', 'ACTIVE', '한지민', '010-6789-0123', '보안', '과장', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('song.hg@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '송혜교', 'USER007', '광주시 서구', '데이터분석가', 27, 'SKCC', 'ACTIVE', '송혜교', '010-7890-1234', '데이터', '사원', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('kang.dw@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '강동원', 'USER008', '울산시 남구', '인프라관리자', 34, 'SKCC', 'ACTIVE', '강동원', '010-8901-2345', '인프라', '과장', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('bae.dn@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '배두나', 'USER009', '세종시 조치원읍', '프로젝트매니저', 35, 'SKCC', 'ACTIVE', '배두나', '010-9012-3456', 'PM', '팀장', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('won.b@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '원빈', 'USER010', '제주시 아라동', '아키텍트', 36, 'SKCC', 'ACTIVE', '원빈', '010-0123-4567', '아키텍처', '수석', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),

-- Inactive Users (비활성 사용자)
('kim.inactive@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '김비활성', 'USER011', '서울시 강북구', '개발자', 30, 'SKCC', 'INACTIVE', '김비활성', '010-1111-1111', 'IT', '사원', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('lee.locked@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '이잠금', 'USER012', '서울시 마포구', '디자이너', 28, 'SKCC', 'LOCKED', '이잠금', '010-2222-2222', '디자인', '사원', 'EMPLOYEE', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),

-- Admin Users (관리자)
('admin@example.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '관리자', 'admin', '서울시 강남구', '시스템관리자', 40, 'SKCC', 'ACTIVE', '관리자', '010-0000-0000', 'IT', '팀장', 'ADMIN', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('admin@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '시스템관리자', 'ADMIN001', '서울시 강남구', '시스템관리자', 40, 'SKCC', 'ACTIVE', '시스템관리자', '010-9999-9999', 'IT', '팀장', 'ADMIN', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('operator@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '운영관리자', 'ADMIN002', '서울시 서초구', '운영관리자', 38, 'SKCC', 'ACTIVE', '운영관리자', '010-8888-8888', '운영', '팀장', 'ADMIN', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
('security@skcc.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '보안관리자', 'ADMIN003', '서울시 마포구', '보안관리자', 42, 'SKCC', 'ACTIVE', '보안관리자', '010-7777-7777', '보안', '팀장', 'ADMIN', '2024-01-01 00:00:00', '2024-01-01 00:00:00');

-- Roles JPA Table Data (역할 JPA 테이블 데이터)
INSERT INTO roles_jpa (role_id, role_name, description, is_active, register_date, register_by) VALUES
('ROLE_USER', '일반사용자', '일반 사용자 역할', true, '202411', 'SYSTEM'),
('ROLE_ADMIN', '관리자', '시스템 관리자 역할', true, '202411', 'SYSTEM'),
('ROLE_OPERATOR', '운영자', '시스템 운영자 역할', true, '202411', 'SYSTEM'),
('ROLE_SECURITY', '보안관리자', '보안 관리자 역할', true, '20240101', 'SYSTEM');

-- User Roles JPA Table Data (사용자 역할 JPA 테이블 데이터)
INSERT INTO user_roles_jpa (user_id, role_id, register_date, register_by) VALUES
-- Regular Users with USER role
('USER001', 'ROLE_USER', '202411', 'SYSTEM'),
('USER002', 'ROLE_USER', '202411', 'SYSTEM'),
('USER003', 'ROLE_USER', '202411', 'SYSTEM'),
('USER004', 'ROLE_USER', '202411', 'SYSTEM'),
('USER005', 'ROLE_USER', '202411', 'SYSTEM'),
('USER006', 'ROLE_USER', '202411', 'SYSTEM'),
('USER007', 'ROLE_USER', '202411', 'SYSTEM'),
('USER008', 'ROLE_USER', '202411', 'SYSTEM'),
('USER009', 'ROLE_USER', '202411', 'SYSTEM'),
('USER010', 'ROLE_USER', '202411', 'SYSTEM'),
('USER011', 'ROLE_USER', '202411', 'SYSTEM'),
('USER012', 'ROLE_USER', '20240101', 'SYSTEM'),

-- Admin Users with ADMIN role
('ADMIN001', 'ROLE_ADMIN', '202411', 'SYSTEM'),
('ADMIN002', 'ROLE_OPERATOR', '202411', 'SYSTEM'),
('ADMIN003', 'ROLE_SECURITY', '20240101'); 