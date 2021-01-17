DROP TABLE IF EXISTS testdb;

INSERT INTO produto (id, nome, descricao, categoria, marca, valor_venda, quantidade_estoque, nome_imagem) VALUES
  (100000, 'Iphone', 'Iphone X', 'Celular' , 'Apple', 1000.0, 1, '100000prime_music.png'),
  (200000, 'MotoG8', '128GB, Tela Ultra', 'Celular' , 'Motorola', 750.99, 10, '100000prime_music.png'),
  (300000, 'Galaxy', 'Faz Chamadas por voz', 'Celular' , 'Samsung', 1000.0, 5, '100000prime_music.png'),
    (400000, 'Redmi', 'Faz Chamadas por voz', 'Celular' , 'Xiaomi', 980.76, 5, '100000prime_music.png'),
      (500000, 'Tv LG 40', 'Televisão LG 40 polegadas, Smartv', 'Televisor' , 'LG', 2390.0, 5, '100000prime_music.png');
  
 INSERT INTO funcionario (id, nome, salario_bruto, cargo, email, senha) VALUES
  (100000, 'Ana', 1800, 'gerente', 'g@mail.com', '123'),
  (200000, 'Eliza', 1550, 'vendedor', 'a@mail.com','12345'),
  (300000, 'Carlos', 1300, 'vendedor', 'b@mail.com','12345');
  
 INSERT INTO papeis (id, nome) VALUES
  (100000, 'gerente'),
  (200000, 'vendedor');
