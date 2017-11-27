var express = require('express');
var path = require('path');
var app = express();
var RateLimit = require('express-rate-limit');

app.use(express.static(path.join(__dirname, 'www')));


app.all('/', function(req, res) {



    res.sendFile(path.join(__dirname, 'www', 'webapplication', 'resources', 'static', 'index.html'));
});

app.listen(8081, function () {
    console.log('App listening on port 8081');
});