ALTER TABLE product_reviews DROP CONSTRAINT uk_71kr315nedgkucd6345i14co7;

DROP VIEW pg_stat_statements;

DROP TABLE product_reviews CASCADE;

DROP TABLE user_product_order CASCADE;

ALTER TABLE review DROP COLUMN review_title;

ALTER TABLE order_product ALTER COLUMN  order_id DROP NOT NULL;

ALTER TABLE orders ALTER COLUMN  order_sum DROP NOT NULL;

ALTER TABLE product ALTER COLUMN  price DROP NOT NULL;

ALTER TABLE order_product ALTER COLUMN  quantity DROP NOT NULL;

ALTER TABLE review ALTER COLUMN  rating DROP NOT NULL;