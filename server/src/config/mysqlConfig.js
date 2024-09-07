module.exports = {
    client: "mysql2",
    useNullasDefault: true,
    connection: {
        host: process.env.DB_HOST,
        user: process.env.DB_USER,
        password: process.env.DB_PASSWORD,
        database: process.env.DB_NAME,
        port: process.env.DB_PORT || 3306
    },
    
    pool: {
        min: 10,
        max: 20
    }
};