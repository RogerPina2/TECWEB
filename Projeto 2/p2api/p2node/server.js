var unirest = require("unirest");

var req = unirest("GET", "https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi");

req.query({
	"q": "get:exp:US",
	"t": "ns",
	"st": "adv",
	"p": "1"
});

req.headers({
	"x-rapidapi-host": "unogs-unogs-v1.p.rapidapi.com",
	"x-rapidapi-key": "881e8db56fmsh5a7397d04f7f773p1cc60ejsn9ff8b2b5357d"
});


req.end(function (res) {
	if (res.error) throw new Error(res.error);

	console.log(res.body);
});

const express = require("express");
const app = express();

app.use("/", require("./src/routes"));

app.listen(3001);