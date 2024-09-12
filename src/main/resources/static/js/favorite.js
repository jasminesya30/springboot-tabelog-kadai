fetch(`/houses/${houseId}/favorites/add`, {
	method: 'POST',
	headers: {
		'Content-Type': 'application/json',
		'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
	},
	body: JSON.stringify({ houseId: houseId })
})
	.then(response => {
		if (response.ok) {
			element.classList.add('active');
		} else {
			return response.json().then(data => {
				console.error('Failed to add favorite:', data.message || 'Unknown error');
			});
		}
	})
	.catch(error => {
		console.error('Error:', error);
	});
