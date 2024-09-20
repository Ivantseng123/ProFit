$(function() {
	var jqAjax = $.ajax;
	$.ajax = function(options) {
		var jqXHR = jqAjax.apply(this, arguments);
		if (!/slow.html/.test(options && options.url)) {
			return jqXHR;
		}
		return jqXHR
			.then(function(result) {
				return $.Deferred(function(d) {
					setTimeout(function() {
						d.resolve(result);
					}, 1000);
				});
			})
			.promise({ abort: jqXHR.abort });
	}
	$("#tabs").tabs({
		beforeLoad: function(event, ui) {
			ui.jqXHR.fail(function(e) {
				ui.panel.html(
					(e && e.statusText ? "Error " + e.status + ": " + e.statusText + ". \n" : "") +
					"Couldn't load this tab. We'd try to fix this as soon as possible " +
					"if this weren't a demo.");
			});
		}
	});
});


