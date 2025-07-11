CREATE TABLE tbl_users{
    user_id UUID PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
};

CREATE INDEX idx_users_username ON tbl_users(username);
CREATE INDEX idx_users_email ON tbl_users(email);