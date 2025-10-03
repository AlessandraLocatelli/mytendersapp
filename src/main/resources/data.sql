-- RESPONSIBLES
INSERT INTO responsible (id, first_name, last_name, tax_code)
VALUES (1, 'Mario', 'Rossi', 'LGTCNI88D56G789R');

INSERT INTO responsible (id, first_name, last_name, tax_code)
VALUES (2, 'Valerio', 'Bianchi', 'AOTPNI90D56G789B');

-- VENDORS
INSERT INTO vendor (id, name, vat_number)
VALUES (1, 'Tech Solutions', 'IT12345678901');

INSERT INTO vendor (id, name, vat_number)
VALUES (2, 'Global Supplies', 'IT23349628781');

-- TENDERS
INSERT INTO tender (
    protocol_id, description, amount, is_threshold, public_visibility, doc_available_until_qualification, tender_type, responsible_id
)
VALUES
('gara_n_1', 'OpenG1', 20000.05, false, true, '2025-12-31', 'OPEN_PROCEDURE', 2);

INSERT INTO tender (
    protocol_id, description, amount, is_threshold, public_visibility, doc_available_until_qualification, tender_type, responsible_id
)
VALUES
('gara_n_2', 'RestG1', 6000.63, false, false, '2025-11-30', 'RESTRICTED_PROCEDURE', 1);

INSERT INTO tender (
    protocol_id, description, amount, is_threshold, public_visibility, doc_available_until_qualification, tender_type, responsible_id
)
VALUES
('gara_n_3', 'NegG1', 82900.29, true, true, '2025-10-31', 'NEGOTIATED_PROCEDURE', 2);

-- Join tender with vendor (for tender gara_n_2)
INSERT INTO tender_vendor (tender_id, vendor_id)
VALUES ('gara_n_2', 2);

-- LOTS
INSERT INTO lot (cig, description, amount, evaluation_type, tender_id)
VALUES
('1267895647', 'Laptops', 20000.05, 'MOST_ECONOMICALLY_ADVANTAGEOUS_OFFER_PRICE_TECH', 'gara_n_1'),
('2345678923', 'Stationery', 6000.63, 'MOST_ECONOMICALLY_ADVANTAGEOUS_OFFER_ONLY_PRICE', 'gara_n_2'),
('5345570933', 'High Tech', 78000.25, 'MOST_ECONOMICALLY_ADVANTAGEOUS_OFFER_ONLY_TECH', 'gara_n_3'),
('9264995627', 'E-friendly', 4900.04, 'MOST_ECONOMICALLY_ADVANTAGEOUS_OFFER_PRICE_TECH', 'gara_n_3');

