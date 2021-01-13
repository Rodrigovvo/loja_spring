DROP TABLE IF EXISTS testdb;

INSERT INTO produto (id, nome, descricao, categoria, marca, valor_venda, quantidade_estoque) VALUES
  (100000, 'Iphone', 'Iphone X', 'Celular' , 'Apple', 1000.0, 1),
  (200000, 'MotoG8', '128GB, Tela Ultra', 'Celular' , 'Motorola', 750.99, 10),
  (300000, 'Galaxy', 'Faz Chamadas por voz', 'Celular' , 'Samsung', 1000.0, 5);
  
 INSERT INTO funcionario (id, nome, salario_bruto, cargo, email, senha) VALUES
  (100000, 'Ana', 1800, 'gerente', 'g@mail.com', '123'),
  (200000, 'Eliza', 1550, 'vendedor', 'a@mail.com','12345'),
  (300000, 'Carlos', 1300, 'vendedor', 'b@mail.com','12345');
  
 INSERT INTO papeis (id, nome) VALUES
  (100000, 'gerente'),
  (200000, 'vendedor');
