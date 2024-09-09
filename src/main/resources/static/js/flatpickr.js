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
	let minTimeFormatted = (now.getHours() < 10) ? "10:00" : nowFormatted;
	let disableToday = now.getHours() >= 21;

	flatpickr('#reservationDateTime', {
		enableTime: true, // 時間の選択を有効にする
		enableSeconds: false, // 秒の選択を無効にする
		dateFormat: "Y-m-d H:i", // 日付フォーマットを指定
		time_24hr: true, // 24時間表示にする（任意の設定）
		locale: 'ja',
		minuteIncrement: 30, // 分単位でのインクリメントを設定する
		minDate: 'today',
		maxDate: maxDate,
		minTime: "10:00", // 初期最小時間を設定
		maxTime: "21:00", // 最大時間
		disable: disableToday ? [new Date()] : [], // 今日が無効化されるかどうかを設定
		onReady: function(selectedDates, dateStr, instance) {
			// 初期表示時に空欄に近い状態にするために、デフォルトの時刻を設定しない
			instance.setDate(null, false); // 初期状態をクリアする
		},
		onChange: function(selectedDates, dateStr, instance) {
			if (selectedDates.length > 0) {
				let selectedDate = selectedDates[0];
				let isToday = selectedDate.toDateString() === now.toDateString();
				let minTime = isToday ? minTimeFormatted : "10:00";

				instance.set('minTime', minTime);
				if (isToday && selectedDate < nextAvailableTime) {
					instance.setDate(nextAvailableTime, true);
				}
			}
		},
		onOpen: function(selectedDates, dateStr, instance) {
			let selectedDate = instance.selectedDates[0];
			if (selectedDate) {
				let isToday = selectedDate.toDateString() === now.toDateString();
				let minTime = isToday ? minTimeFormatted : "10:00";
				instance.set('minTime', minTime);
			}
		}
	});
});