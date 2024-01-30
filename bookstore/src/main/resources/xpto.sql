INSERT INTO autores (nome, email, descricao, criado_em) VALUES('Renato', 'email@email.com.br', 'Apenas um novo escritor...', NOW())

INSERT INTO categorias (id, nome) VALUES(1L, 'programacao')

INSERT INTO paises (id, nome) VALUES(1L, 'brasil')
INSERT INTO paises (id, nome) VALUES(2L, 'china')

INSERT INTO estados (id, nome, pais_id) VALUES(1L, 'são paulo', 1L)
INSERT INTO estados (id, nome, pais_id) VALUES(2L, 'hunan', 2L)

INSERT INTO livros (id, titulo, resumo, sumario, preco, quantidade_de_paginas, isbn, data_publicacao, categoria_id, autor_id) VALUES(1L, 'O abominavel homem das neves 1', 'livro bacaninha, conta a historia de um tiozinho maneiro', 'Um tiozinho maneiro que vivia sozinho nas neves e só queria paz, até a chegada do homem branco opressor que julgou o tiozinho como um monstro por causa dos seus pés grandes ;)', 20.00, 257, 'XPTO1234BD', NOW(), 1L, 1L)
INSERT INTO livros (id, titulo, resumo, sumario, preco, quantidade_de_paginas, isbn, data_publicacao, categoria_id, autor_id) VALUES(2L, 'O abominavel homem das neves 2', 'livro bacana, conta sobra a vingança do tio', 'Tiozinho que era maneiro, agora vive cheio de odio do homem opressor e trama a sua vingança', 30.00, 401, 'XPTO1235CY', NOW(), 1L, 1L)