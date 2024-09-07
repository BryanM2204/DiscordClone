const mysql = require('mysql2')
const { Model } = require('objection')

const mysqlConfig = require('../config/mysqlConfig')
const logger = require('../utils/logger')

module.exports = async () => {
    logger.debug("Starting Database connection");
    const connection = mysql.createConnection(mysqlConfig.connection);
    connection.connect((err) => {
        if (err) {
            logger.error(`Error: ${err}`);
            return process.exit(1);
        }
        logger.info("Connected to MySQL");
    });
    
}