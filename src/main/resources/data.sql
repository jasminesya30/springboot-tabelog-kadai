-- housesテーブル
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   1,
   'To Go Samurai',
   'house01.jpg',
   'ソフトクリーム屋さん',
   1000,
   70,
   '451-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   2,
   '串専門 さむらい',
   'house02.jpg',
   'どて煮屋さん',
   2000,
   50,
   '452-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   3,
   'サムライサンド',
   'house03.jpg',
   'サンドイッチ屋さん',
   1000,
   80,
   '453-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   4,
   'コーヒーハウス SAMURAI',
   'house04.jpg',
   '小倉トーストのコーヒー屋さん',
   2000,
   70,
   '454-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   5,
   '天むす 侍屋',
   'house05.jpg',
   '天むす屋さん',
   1000,
   50,
   '455-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   6,
   'うなぎ家 侍や',
   'house06.jpg',
   'うなぎ・ひつまぶし屋さん',
   3000,
   80,
   '456-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   7,
   '味処 さむらい',
   'house07.jpg',
   '味噌カツ屋さん',
   2000,
   70,
   '457-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   8,
   'SAMURAI DINING',
   'house08.jpg',
   '創作料理店',
   5000,
   50,
   '458-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   9,
   '酉侍',
   'house09.jpg',
   '手羽先屋さん',
   2000,
   80,
   '459-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   10,
   'Samurai Terrace',
   'house10.jpg',
   '海鮮料理店',
   3000,
   70,
   '460-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   11,
   '侍きしめん',
   'house11.jpg',
   'きしめん屋さん',
   1000,
   50,
   '461-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   12,
   '喫茶 サムライ',
   'house12.jpg',
   '鉄板ナポリタンの喫茶店',
   2000,
   80,
   '462-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   13,
   'とんかつ 侍',
   'house13.jpg',
   '味噌カツ屋さん',
   2000,
   70,
   '463-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
INSERT IGNORE INTO houses
(
   id,
   name,
   image_name,
   description,
   price,
   capacity,
   postal_code,
   address,
   phone_number
)
VALUES
(
   14,
   '肉亭 さむらい',
   'house14.jpg',
   'やきにく屋さん',
   5000,
   50,
   '464-XXXX',
   '愛知県名古屋市X-XX-XX',
   '821-414-629'
);
-- rolesテーブル
INSERT IGNORE INTO roles
(
   id,
   name
)
VALUES
(
   1,
   'ROLE_GENERAL'
);
INSERT IGNORE INTO roles
(
   id,
   name
)
VALUES
(
   2,
   'ROLE_ADMIN'
);
-- usersテーブル
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   1,
   '侍 太郎',
   'サムライ タロウ',
   '101-0022',
   '東京都千代田区神田練塀町300番地',
   '090-1234-5678',
   'taro.samurai@example.com',
   '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO',
   1,
   true
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   2,
   '侍 花子',
   'サムライ ハナコ',
   '101-0022',
   '東京都千代田区神田練塀町300番地',
   '090-1234-5678',
   'hanako.samurai@example.com',
   '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO',
   2,
   true
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   3,
   '侍 義勝',
   'サムライ ヨシカツ',
   '638-0644',
   '奈良県五條市西吉野町湯川X-XX-XX',
   '090-1234-5678',
   'yoshikatsu.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   4,
   '侍 幸美',
   'サムライ サチミ',
   '342-0006',
   '埼玉県吉川市南広島X-XX-XX',
   '090-1234-5678',
   'sachimi.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   5,
   '侍 雅',
   'サムライ ミヤビ',
   '527-0209',
   '滋賀県東近江市佐目町X-XX-XX',
   '090-1234-5678',
   'miyabi.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   6,
   '侍 正保',
   'サムライ マサヤス',
   '989-1203',
   '宮城県柴田郡大河原町旭町X-XX-XX',
   '090-1234-5678',
   'masayasu.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   7,
   '侍 真由美',
   'サムライ マユミ',
   '951-8015',
   '新潟県新潟市松岡町X-XX-XX',
   '090-1234-5678',
   'mayumi.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   8,
   '侍 安民',
   'サムライ ヤスタミ',
   '241-0033',
   '神奈川県横浜市旭区今川町X-XX-XX',
   '090-1234-5678',
   'yasutami.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   9,
   '侍 章緒',
   'サムライ アキオ',
   '739-2103',
   '広島県東広島市高屋町宮領X-XX-XX',
   '090-1234-5678',
   'akio.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   10,
   '侍 祐子',
   'サムライ ユウコ',
   '601-0761',
   '京都府南丹市美山町高野X-XX-XX',
   '090-1234-5678',
   'yuko.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   11,
   '侍 秋美',
   'サムライ アキミ',
   '606-8235',
   '京都府京都市左京区田中西春菜町X-XX-XX',
   '090-1234-5678',
   'akimi.samurai@example.com',
   'password',
   1,
   false
);
INSERT IGNORE INTO users
(
   id,
   name,
   furigana,
   postal_code,
   address,
   phone_number,
   email,
   password,
   role_id,
   enabled
)
VALUES
(
   12,
   '侍 信平',
   'サムライ シンペイ',
   '673-1324',
   '兵庫県加東市新定X-XX-XX',
   '090-1234-5678',
   'shinpei.samurai@example.com',
   'password',
   1,
   false
);
-- reviewsテーブル
INSERT INTO reviews
(
   house_id,
   user_id,
   content,
   rating,
   created_at
)
VALUES
(
   1,
   1,
   '素晴らしいソフトクリームでした！また行きたいです。',
   5,
   '2024-04-01 10:00'
),

(
   2,
   2,
   'どて煮がとても美味しかったです。',
   5,
   '2024-04-02 11:00'
),

(
   3,
   3,
   'サンドイッチがとても美味しかったです。',
   5,
   '2024-04-03 12:00'
),

(
   4,
   4,
   'コーヒーが香り高くて良かったです。',
   5,
   '2024-04-04 13:00'
),

(
   5,
   5,
   '天むすがとても美味しかったです。',
   5,
   '2024-04-05 14:00'
),

(
   6,
   6,
   'うなぎが絶品でした。また来ます。',
   5,
   '2024-04-06 15:00'
),

(
   7,
   7,
   '味噌カツが最高でした！',
   5,
   '2024-04-07 16:00'
),

(
   8,
   8,
   '創作料理が独創的で美味しかったです。',
   5,
   '2024-04-08 17:00'
),

(
   9,
   9,
   '手羽先がとても美味しかったです。',
   5,
   '2024-04-09 18:00'
),

(
   10,
   10,
   '海鮮料理が新鮮で美味しかったです。',
   5,
   '2024-04-10 19:00'
),

(
   11,
   11,
   'きしめんがもちもちしていて、だしの味が絶品でした。また行きたいです。',
   5,
   '2024-04-11 20:00'
),

(
   12,
   12,
   '鉄板ナポリタンがとても美味しく、レトロな雰囲気も良かったです。',
   5,
   '2024-04-12 21:00'
),

(
   13,
   1,
   '味噌カツがとても美味しく、ボリュームも満点でした。また行きたいです。',
   5,
   '2024-04-13 10:00'
),

(
   14,
   2,
   '焼肉が新鮮で、質の高い肉が楽しめました。雰囲気も良く、また行きたいです。',
   5,
   '2024-04-14 11:00'
),

(
   1,
   3,
   'カラフルなトッピングが映えて、写真に収めるのが楽しかったです。味も期待以上でした！',
   4,
   '2024-04-15 12:00'
);
-- favoritesテーブル
INSERT IGNORE INTO favorites
(
   house_id,
   image_name,
   name,
   description,
   price,
   postal_code,
   address
)
VALUES
(
   1,
   'house01.jpg',
   'To Go Samurai',
   'ソフトクリーム屋さん',
   1000,
   '451-XXXX',
   '愛知県名古屋市X-XX-XX'
),

(
   14,
   'house14.jpg',
   '肉亭 さむらい',
   'やきにく屋さん',
   5000,
   '464-XXXX',
   '愛知県名古屋市X-XX-XX'
);