const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");

const api = require("../routes");
const { ErrorHandler } = require("../middleware");

module.exports = app => {
    // define any middlewares that need to run befoure our routes
    var corsOptions = {
        origin: "http://localhost:3000"
    };
    app.use(cors(corsOptions));

    app.use(bodyParser.json());
    
    app.use(express.json());
    app.use(express.urlencoded({ extended: false }));

    // define ALL routes here
    app.use(api);

    // for any other middlewares that need to run after our routes
    app.use(ErrorHandler);
};