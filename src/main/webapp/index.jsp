function validate_url(){
	var str = document.getElementsByName("urlLink")[0].value;
	 var pattern = new RegExp('^((https|http):\\/\\/){1}'+ 		// protocol
    '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ 		// domain name
    '((\\d{1,3}\\.){3}\\d{1,3}))'+ 								// OR ip (v4) address
    '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ 							// port and path
    '(\\?[;&a-z\\d%_.~+=-]*)?'+ 								// query string
    '(\\#[-a-z\\d_]*)?$','i'); 									// fragment locator
  var result = !!pattern.test(str);
  console.log(result);
  return result;
}

function validateURL(){
	const btn = document.getElementById("btnrun");
	if(validate_url()) {
		btn.disabled=false;
	}
	else {
		alert("Enter the URL in the format : \'http://URL or https://URL\'");
		btn.disabled=true;
	}
}

function validateFile(){
	var myfile = document.getElementsByName("myfile")[0].value;
	console.log(myfile);
}
