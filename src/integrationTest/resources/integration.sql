-- Users
INSERT INTO users (id, name, email, password) VALUES ('afe19162-047b-473a-9552-0c254cac753a', 'Admin', 'admin@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('afe19162-047b-473a-9552-0c254cac753b', 'Tester', 'tester@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('afe19162-047b-473a-9552-0c254cac753c', 'Tester2', 'tester2@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');

-- Articles
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7','Article1', 'Description of article 1', 'AVAILABLE', 'afe19162-047b-473a-9552-0c254cac753a');

-- Lending Process --
INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 'PENDING', '933606a4-506b-4749-ac53-3f07a958a8a7', 'afe19162-047b-473a-9552-0c254cac753a', 'afe19162-047b-473a-9552-0c254cac753b');
INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a8', 'ACTIVE', '933606a4-506b-4749-ac53-3f07a958a8a7', 'afe19162-047b-473a-9552-0c254cac753a', 'afe19162-047b-473a-9552-0c254cac753c');
