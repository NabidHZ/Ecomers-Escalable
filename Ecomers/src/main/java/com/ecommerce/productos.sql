-- 1. Crear la tabla products
DROP TABLE IF EXISTS products;
CREATE TABLE products (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(150) NOT NULL,
                          description TEXT,
                          price DECIMAL(10,2) NOT NULL,
                          category VARCHAR(50) NOT NULL,
                          image_url VARCHAR(255) NOT NULL
);

-- 2. Insertar productos de moda

INSERT INTO products (name, description, price, category, image_url) VALUES
-- Sudaderas
('Sudadera con capucha deportiva', 'Sudadera unisex con capucha y bolsillo canguro, ideal para deporte.', 39.99, 'sudaderas', 'https://i.dummyjson.com/data/products/9/1.jpg'),
('Sudadera oversize con estampado', 'Sudadera oversize de algodón con estampado gráfico frontal.', 49.99, 'sudaderas', 'https://i.dummyjson.com/data/products/10/1.jpg'),

-- Abrigos
('Abrigo largo de paño', 'Abrigo largo de paño con solapa amplia y cinturón.', 129.99, 'abrigos', 'https://i.dummyjson.com/data/products/4/1.jpg'),
('Chaqueta acolchada con capucha', 'Chaqueta acolchada impermeable con capucha desmontable.', 89.99, 'abrigos', 'https://i.dummyjson.com/data/products/7/1.jpg'),

-- Pantalones
('Pantalón vaquero slim fit', 'Jeans slim fit con lavado oscuro y cinco bolsillos.', 59.99, 'pantalones', 'https://i.dummyjson.com/data/products/20/1.jpg'),
('Chándal de jogging', 'Pantalón de chándal cómodo con puños elásticos.', 29.99, 'pantalones', 'https://i.dummyjson.com/data/products/3/1.jpg'),

-- Vestidos
('Vestido midi estampado', 'Vestido midi estampado floral, corte evasé y tirantes finos.', 69.99, 'vestidos', 'https://i.dummyjson.com/data/products/12/1.jpg'),
('Vestido corto de fiesta', 'Vestido corto de lentejuelas ideal para noches de fiesta.', 79.99, 'vestidos', 'https://i.dummyjson.com/data/products/13/1.jpg'),

-- Camisetas
('Camiseta básica de algodón', 'Camiseta unisex básica de algodón orgánico.', 19.99, 'camisetas', 'https://i.dummyjson.com/data/products/14/1.jpg'),
('Camiseta oversize con logo', 'Camiseta oversize con estampado de logo en pecho.', 24.99, 'camisetas', 'https://i.dummyjson.com/data/products/15/1.jpg'),

-- Calzado
('Zapatillas running ligeras', 'Zapatillas de running con suela de goma antideslizante.', 59.99, 'calzado', 'https://i.dummyjson.com/data/products/21/1.jpg'),
('Botines de piel con tacón', 'Botines de piel auténtica con tacón medio.', 89.99, 'calzado', 'https://i.dummyjson.com/data/products/22/1.jpg'),

-- Accesorios
('Bolso bandolera de piel', 'Bolso bandolera de piel sintética con cierre de cremallera.', 49.99, 'accesorios', 'https://i.dummyjson.com/data/products/23/1.jpg'),
('Gafas de sol unisex', 'Gafas de sol con montura de metal y lentes polarizadas.', 29.99, 'accesorios', 'https://i.dummyjson.com/data/products/24/1.jpg'),

-- Joyería
('Collar minimalista en acero', 'Collar de acero inoxidable con colgante minimalista.', 19.99, 'joyeria', 'https://i.dummyjson.com/data/products/25/1.jpg'),
('Pendientes de aro dorados', 'Pendientes de aro dorados de 3 cm de diámetro.', 14.99, 'joyeria', 'https://i.dummyjson.com/data/products/26/1.jpg');

-- Confirmar la cantidad
SELECT COUNT(*) AS total_products FROM products;
