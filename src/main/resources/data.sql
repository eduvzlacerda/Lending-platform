
-- Users
INSERT INTO users (id, name, email, password) VALUES (100, 'Admin', 'admin@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')
INSERT INTO users (id, name, email, password) VALUES (200, 'User2', 'user2@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')
INSERT INTO users (id, name, email, password) VALUES (300, 'User3', 'user3@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')
INSERT INTO users (id, name, email, password) VALUES (400, 'User4', 'user4@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')

-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ADMIN')
INSERT INTO roles (id, name) VALUES (2, 'MODERATOR')
INSERT INTO roles (id, name) VALUES (3, 'USER')

-- User Roles mapping
INSERT INTO users_roles (user_id, role_id) VALUES (100, 1)
INSERT INTO users_roles (user_id, role_id) VALUES (200, 3)
INSERT INTO users_roles (user_id, role_id) VALUES (300, 3)
INSERT INTO users_roles (user_id, role_id) VALUES (400, 3)

-- Articles
INSERT INTO articles (id,description, title, article_status, owner_id) VALUES (100,'Article1', 'Content of article 1', 'AVAILABLE', 200)
INSERT INTO articles (id,description, title, article_status, owner_id) VALUES (200, 'Article2', 'Content of article 2', 'AVAILABLE', 300)
INSERT INTO articles (id,description, title, article_status, owner_id) VALUES (300,'Article3', 'Content of article 3', 'AVAILABLE', 400)
INSERT INTO articles (id,description, title, article_status, owner_id) VALUES (400, 'Article4','Content of article 4', 'HIDDEN', 200)

