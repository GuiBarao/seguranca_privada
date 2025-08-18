CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_usuario VARCHAR(100) NOT NULL,
    status ENUM('ATIVO', 'INATIVO') DEFAULT 'ATIVO',
    nome_completo VARCHAR(200),
    senha VARCHAR(250),
    tipo ENUM('ADM', 'CLIENTE', 'SEGURANCA'));

CREATE TABLE cobranca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    status ENUM('VENCIDA', 'PAGA', 'PENDENTE', 'CANCELADA') DEFAULT 'PENDENTE',
    data_vencimento DATE NOT NULL,

    FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);

CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    rua  VARCHAR(100) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    complemento VARCHAR(250),
    cep VARCHAR(20) NOT NULL,
    numero VARCHAR(20) NOT NULL,

    FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);

CREATE TABLE plano (
   id INT AUTO_INCREMENT PRIMARY KEY,
   nome VARCHAR(100) NOT NULL,
   max_horas INT NOT NULL,
   max_segurancas INT NOT NULL,
   valor_mensal DECIMAL(10,2) NOT NULL,
   max_localizacoes INT NOT NULL,
   status ENUM('ATIVO', 'INATIVO') DEFAULT 'ATIVO'
);

CREATE TABLE plano_cliente (
   id_plano INT NOT NULL,
   id_cliente INT PRIMARY KEY,
   FOREIGN KEY (id_plano) REFERENCES plano(id),
   FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);

CREATE TABLE sem_disponibilidade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_seguranca INT NOT NULL,
    data_inicial DATE NOT NULL,
    data_final DATE NOT NULL,
    FOREIGN KEY (id_seguranca) REFERENCES usuario(id)
);

CREATE TABLE item_estoque (
   id INT AUTO_INCREMENT PRIMARY KEY,
   status ENUM('ATIVO','INATIVO') DEFAULT 'ATIVO',
   telefone_fornecedor VARCHAR(50),
   nome VARCHAR(100) NOT NULL,
   quantidade INT DEFAULT 0
);

CREATE TABLE equipe (
  id INT AUTO_INCREMENT PRIMARY KEY,
  status ENUM('ATIVO','INATIVO') NOT NULL DEFAULT 'ATIVO',
  id_cliente INT NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);

CREATE TABLE seguranca_participa_equipe (
   id_seguranca INT NOT NULL,
   id_equipe INT NOT NULL,
   PRIMARY KEY (id_seguranca, id_equipe),
   FOREIGN KEY (id_seguranca) REFERENCES usuario(id),
   FOREIGN KEY (id_equipe) REFERENCES equipe(id)
);

CREATE TABLE equipe_usa_item (
  id_item INT NOT NULL,
  id_equipe INT NOT NULL,
  quantidade INT NOT NULL,
  PRIMARY KEY (id_item, id_equipe),
  FOREIGN KEY (id_item) REFERENCES item_estoque(id),
  FOREIGN KEY (id_equipe) REFERENCES equipe(id)
);

CREATE TABLE solicitacao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  descricao TEXT NOT NULL,
  id_cliente INT NOT NULL,
  id_atendente INT NOT NULL,
  status ENUM('PENDENTE', 'ANALISE', 'ACEITA', 'NEGADA') NOT NULL DEFAULT 'PENDENTE',

  FOREIGN KEY (id_cliente) REFERENCES usuario(id),
  FOREIGN KEY(id_atendente) REFERENCES usuario(id)
);

CREATE TABLE turno (
   id INT AUTO_INCREMENT PRIMARY KEY,
   id_seguranca INT NOT NULL,
   id_equipe INT NOT NULL,
   horarioInicial DATETIME NOT NULL,
   horarioFinal DATETIME NOT NULL,

   FOREIGN KEY (id_seguranca) REFERENCES usuario(id),
   FOREIGN KEY (id_equipe) REFERENCES equipe(id)
);