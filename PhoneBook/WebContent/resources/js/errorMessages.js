function hideMessages() {
	var elements = document.getElementsByName('errorMessage');
	for(var i=0; i < elements.length; i++) {
		alert(elements[i].innerText);
		if(elements[i].innerText == '') {
			elements[i].style.display = 'none';
		}
	}
}