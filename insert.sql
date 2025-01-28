SET foreign_key_checks=1;

USE team2411b;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES ('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('野球', '野球の商品です'),
('サッカー', 'サッカーの商品です'),
('アメフト', 'アメフトの商品です');

INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company)VALUES 
('グローブ','ぐろーぶ','野球用グローブです',1,12000,'/img/baseball/glove.jpg','2020/01/05','Shiratori Sports'),
('バット','ばっと','野球用バットです',1,20000,'/img/baseball/bat.jpg','2020/01/15','Komuro Sports'),
('ホームベース','ほーむべーす','野球用ホームベースです',1,5000,'/img/baseball/homebase.jpg','2020/01/25','Yoshida Sports'),
('サッカーボール','さっかーぼーる','サッカーボールです',2,8000,'/img/soccer/ball.jpg','2020/02/05','Nakazato Sports'),
('スパイク','すぱいく','サッカー用スパイクです',2,10000,'/img/soccer/spike.jpg','2020/02/15','Yoshida Sports'),
('ゴールネット','ごーるねっと','サッカー用ゴールネットです',2,15000,'/img/soccer/goal.jpg','2020/02/25','Miyagi Sports'),
('ヘルメット','へるめっと','アメフト用ヘルメットです',3,32000,'/img/americanfootball/helmet.jpg','2020/03/05','Miyagi Sports'),
('アメフトボール','あめふとぼーる','アメフトボールです',3,7000,'/img/americanfootball/ball.jpg','2020/03/15','Shiratori Sports'),
('ショルダーパッド','しょるだーぱっど','アメフト用ショルダーパッドです',3,35000,'/img/americanfootball/shoulderpad.jpg','2020/03/25','Komuro Sports');
