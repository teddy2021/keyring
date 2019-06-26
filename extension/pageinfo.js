
var url = require URL;

var page = url.hostname(document.Location);

$onReady () => {
	$.ajax({
		data: page
		url: "localhost:8080"
		success: () => {

		}
	})
}
