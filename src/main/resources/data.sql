DROP TABLE IF EXISTS testdb;

INSERT INTO produto (id, nome, descricao, categoria, marca, valor_venda, quantidade_estoque) VALUES
  (1, 'Iphone', 'Iphone X', 'Celular' , 'Apple', 1000.0, 1),
  (2, 'MotoG8', '128GB, Tela Ultra', 'Celular' , 'Motorola', 750.99, 10),
  (3, 'Galaxy', 'Faz Chamadas por voz', 'Celular' , 'Samsung', 1000.0, 5);
  
 INSERT INTO funcionario (id, nome, salario_bruto, cargo) VALUES
  (1, 'Ana', 1800, 'Caixa'),
  (2, 'Eliza', 1550, 'Estoquista'),
  (3, 'Carlos', 1300, 'Atendente');
  
 	