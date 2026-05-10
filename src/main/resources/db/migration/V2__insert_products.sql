INSERT INTO supermarket.product (product_name, product_type, product_quality, product_base_price, product_expiry_date)
VALUES
    ('Gouda',        'CHEESE',  80, 2.00,  CURRENT_DATE + INTERVAL '60 days'),
    ('Brie',         'CHEESE',  32, 3.00,  CURRENT_DATE + INTERVAL '55 days'),
    ('Rotwein 2020', 'WINE',    20, 15.00, CURRENT_DATE - INTERVAL '30 days'),
    ('Vollkornbrot', 'BREAD',   60, 1.50,  CURRENT_DATE + INTERVAL '3 days'),
    ('Apfel',        'DEFAULT', 70, 0.50,  NULL);