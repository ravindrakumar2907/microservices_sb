INSERT INTO roles(role_name) VALUES ('ROLE_USER');
INSERT INTO roles(role_name) VALUES ('ROLE_ADMIN');



-- Insert two demo users
INSERT INTO users (name, email, password, enabled, email_verified, accept_term_condition) VALUES
                                                                                              ('User One','user1@example.com','$2b$12$D4R2mLRPlSbmJyQwwP/EjuYHGVLotH8TKLtT7j6CfqE9FTTp1.ldK', TRUE, TRUE, 'yes'),
                                                                                              ('User Two','user2@example.com','$2b$12$UGxCKJlCNes6NZw2PJ7lVutThFZw6XS3Udi136m2TbGuUYKAnPec2', TRUE, TRUE, 'yes');
