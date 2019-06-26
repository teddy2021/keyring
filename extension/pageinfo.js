

var url = require(url);

var page = url.hostname(document.Location);

$document.onReady() =>{
	$.ajax({
		data: page,
		url: "localhost:8080",
		success: () => {
			$("#Username").val(this.username);
			$("#Password").val(this.password);
		}
	});
};


