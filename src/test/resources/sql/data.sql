INSERT INTO users (
    created_at, updated_at, user_id, email, username
)
VALUES
    (
        NOW(), NOW(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
        'user1@example.com', 'user1'
    ),
    (
        NOW(), NOW(), 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12',
        'user2@example.com', 'user2'
    ),
    (
        NOW(), NOW(), 'c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a13',
        'user3@example.com', 'user3'
    );
