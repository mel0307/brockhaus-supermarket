INSERT INTO supermarket.product (product_name, product_type, product_quality, product_base_price, product_expiry_date)
VALUES
    ('Gouda',        'CHEESE',  30, 2.00,  CURRENT_DATE + INTERVAL '60 days'),
    ('Brie',         'CHEESE',  30, 3.00,  CURRENT_DATE + INTERVAL '55 days'),
    ('Asbach', 'WINE',    1, 15.00, CURRENT_DATE - INTERVAL '30 days'),
    ('Vollkornbrot', 'BREAD',   5, 1.50,  CURRENT_DATE + INTERVAL '5 days'),