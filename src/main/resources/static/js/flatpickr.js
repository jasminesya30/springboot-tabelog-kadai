document.addEventListener('DOMContentLoaded', function() {
	let maxDate = new Date();
	maxDate.setMonth(maxDate.getMonth() + 3);

	let now = new Date(); // 現在の日時
	let currentMinutes = now.getMinutes();
	let nextAvailableTime = new Date(now);

	if (currentMinutes < 30) {
		nextAvailableTime.setMinutes(30);
	} else {
		nextAvailableTime.setHours(nextAvailableTime.getHours() + 1);
		nextAvailableTime.setMinutes(0);
	}

	let nowFormatted = nextAvailableTime.getHours().toString().padStart(2, '0') + ':' + nextAvailableTime.getMinutes().toString().padStart(2, '0');

	flatpickr('#reservationDateTime', {
		enableTime: true, // 時間の選択を有効にする
		enableSeconds: false, // 秒の選択を無効にする
		dateFormat: "Y-m-d H:i", // 日付フォーマットを指定
		time_24hr: true, // 24時間表示にする（任意の設定）
		locale: 'ja',
		minuteIncrement: 30, // 分単位でのインクリメントを設定する
		minDate: 'today',
		maxDate: maxDate,
		minTime: nowFormatted, // 最小時間
		maxTime: "21:00", // 最大時間
		onChange: function(selectedDates, dateStr, instance) {
			if (selectedDates.length > 0) {
				let selectedDate = selectedDates[0];
				if (selectedDate.toDateString() === now.toDateString()) {
					// 今日の日付が選択された場合は、現在の時刻以降を選択可能にする
					instance.set('minTime', nowFormatted);
				} else {
					// 他の日付が選択された場合は、10:00以降を選択可能にする
					instance.set('minTime', "10:00");
				}
			}
		}
	});
});