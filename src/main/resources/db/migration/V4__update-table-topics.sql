ALTER TABLE topics
ADD COLUMN user_id BIGINT;

ALTER TABLE topics
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id) REFERENCES users(id)
ON DELETE CASCADE;