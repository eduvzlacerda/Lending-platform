-- Users
INSERT INTO users (id, name, email, password) VALUES (1, 'Admin', 'admin@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')
INSERT INTO users (id, name, email, password) VALUES (2, 'User2', 'user2@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')
INSERT INTO users (id, name, email, password) VALUES (3, 'User3', 'user3@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')
INSERT INTO users (id, name, email, password) VALUES (4, 'User4', 'user4@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6')

-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ADMIN')
INSERT INTO roles (id, name) VALUES (2, 'MODERATOR')
INSERT INTO roles (id, name) VALUES (3, 'USER')

-- User Roles mapping
INSERT INTO users_roles (users_id, roles_id) VALUES (1, 1)
INSERT INTO users_roles (users_id, roles_id) VALUES (2, 3)
INSERT INTO users_roles (users_id, roles_id) VALUES (3, 3)
INSERT INTO users_roles (users_id, roles_id) VALUES (4, 3)

-- Articles
INSERT INTO articles (id, description, title, article_status, owner_id) VALUES (1, 'Article1', 'Content of article 1', 'AVAILABLE', 2)
INSERT INTO articles (id, description, title, article_status, owner_id) VALUES (2, 'Article2', 'Content of article 2', 'AVAILABLE', 3)
INSERT INTO articles (id, description, title, article_status, owner_id) VALUES (3, 'Article3', 'Content of article 3', 'AVAILABLE', 4)
INSERT INTO articles (id, description, title, article_status, owner_id) VALUES (4, 'Article4', 'Content of article 4', 'AVAILABLE', 2)