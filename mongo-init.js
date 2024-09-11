db.createUser(
    {
        user: "bookstore",
        pwd: "book",
        roles: [
            {
                role: "readWrite",
                db: "bookstore"
            }
        ]
    }
);

db.createCollection('bookstore');