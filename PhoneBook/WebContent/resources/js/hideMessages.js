$ = jQuery;
$(document).ready(function() {
	$("div[name='errorMessage']").each(function() {
		if($(this).text().trim() == "") {
 			$(this).hide();
 		}
	});
});