$ = jQuery;
function hideIfEmpty(data) {
	
	$("div[name='errorMessage']").each(function() {
		if(data.status == "success") {
			if($(this).text().trim() == "") {
				$(this).hide();
			} else {
				$(this).show();
			}
		}
	});
}
