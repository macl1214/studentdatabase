window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    let table = new DataTable('#datatablesSimple', {
    	// options
		rowReorder: true,
        columnDefs: [
            { orderable: false, targets: 6 },
            { orderable: true, className: 'reorder', targets: '_all' }
        ]
	});
});
