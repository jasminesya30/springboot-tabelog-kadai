document.addEventListener('DOMContentLoaded', function() {
	document.querySelectorAll('.favorite-button').forEach(button => {
		button.addEventListener('click', function() {
			const houseId = this.getAttribute('data-house-id');
			const isActive = this.classList.contains('active');
			const action = isActive ? 'delete' : 'add';  // ボタンがアクティブかどうかによってアクションを決定

			fetch(`/houses/${houseId}/favorites/${action}`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
				},
				body: JSON.stringify({ houseId: houseId })
			})
				.then(response => {
					if (response.ok) {
						// サーバーからのレスポンスによってボタンの状態を更新
						if (action === 'add') {
							this.classList.add('active');
						} else {
							this.classList.remove('active');
						}
					} else {
						return response.json().then(data => {
							console.error('Failed to update favorite:', data.message || 'Unknown error');
						});
					}
				})
				.catch(error => {
					console.error('Error:', error);
				});
		});
	});
});
