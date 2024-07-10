let maxDate = new Date();
maxDate.setMonth(maxDate.getMonth() + 3);

flatpickr('#reservationDateTime', {
	enableTime: true, // 時間の選択を有効にする
	enableSeconds: false, // 秒の選択を無効にする
	dateFormat: "Y-m-d H:i", // 日付フォーマットを指定
	time_24hr: true, // 24時間表示にする（任意の設定）
	locale: 'ja',
	minuteIncrement: 30, // 分単位でのインクリメントを設定する
	minDate: 'today',
	maxDate: maxDate
});
