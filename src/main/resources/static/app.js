function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	var id_token = googleUser.getAuthResponse().id_token;
	console.log('ID token: ' + id_token);
	console.log('ID: ' + profile.getId());
	console.log('Name: ' + profile.getName());
	console.log('Image URL: ' + profile.getImageUrl());
	console.log('Email: ' + profile.getEmail());
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'https://demo.meta.golf/tokensignin');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onload = function() {
		console.log('Signed in as: ' + xhr.responseText);
	};
	xhr.send('idtoken=' + id_token);
}

function errFunc(err) {
    console.log("Post error!");
    console.log(err);
}

function connectSuccess(data){
	console.log('success'+data);
//document.getElementById("transmit").disabled = false	
//document.getElementById("nsfwTransmit").disabled = false	
//location.reload()
}

function send() {
	
	var m = '{ "userId" : "' + $('#userid').val() +
				'", "passwd":"' + $('#passwd').val() + '"}'

	$.ajax({
		type : 'POST',
		async : true,
		error : errFunc,
		url : "/auth",
		contentType: "application/json; charset=utf-8",
		data : m,
		success : connectSuccess
	})
}

$(document).on("click", "#button", function () {
    send()
})