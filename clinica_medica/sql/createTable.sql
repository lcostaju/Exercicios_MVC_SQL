CREATE DATABASE clinica_medica_1;
USE clinica_medica_1;

-- Tabela Consulta
CREATE TABLE Consulta (
    codConsulta INT AUTO_INCREMENT PRIMARY KEY,
    nomeMedico VARCHAR(255) NOT NULL,
    dataConsulta DATE NOT NULL,
    observacoes TEXT,
    tipoConsulta VARCHAR(255) NOT NULL,
    nomePaciente VARCHAR(255) NOT NULL
);

-- Tabela RegistroPagamento
CREATE TABLE RegistroPagamento (
    codPagamento INT AUTO_INCREMENT PRIMARY KEY,
    tipoPagamento VARCHAR(255) NOT NULL,
    valorPagamento DOUBLE NOT NULL,
    dataPagamento DATE NOT NULL,
    consulta_id INT,
    observacao TEXT,
    FOREIGN KEY (consulta_id) REFERENCES Consulta(codConsulta) ON DELETE CASCADE
);

-- Tabela PedidoExame
CREATE TABLE PedidoExame (
    codExame INT AUTO_INCREMENT PRIMARY KEY,
    nomeExame VARCHAR(255) NOT NULL,
    dataSolicitacao DATE NOT NULL,
    consulta_id INT,
    observacao TEXT,
    laboratorio VARCHAR(255) NOT NULL,
    FOREIGN KEY (consulta_id) REFERENCES Consulta(codConsulta) ON DELETE CASCADE
);

-- faça a inserção de dados nas tabelas, colocando pelo menos 5 registros em cada tabela

-- Tabela Consulta
INSERT INTO Consulta (nomeMedico, dataConsulta, observacoes, tipoConsulta, nomePaciente) VALUES
('Dr. João', '2021-10-01', 'Consulta de rotina', 'Consulta', 'Maria'),
('Dr. Maria', '2021-10-02', 'Consulta de rotina', 'Consulta', 'João'),
('Dr. José', '2021-10-03', 'Consulta de rotina', 'Consulta', 'Pedro'),
('Dr. Ana', '2021-10-04', 'Consulta de rotina', 'Consulta', 'Paula'),
('Dr. Carlos', '2021-10-05', 'Consulta de rotina', 'Consulta', 'Lucas');

-- Tabela RegistroPagamento
INSERT INTO RegistroPagamento (tipoPagamento, valorPagamento, dataPagamento, consulta_id, observacao) VALUES
('Dinheiro', 100.00, '2021-10-01', 1, 'Pagamento em dinheiro'),
('Cartão', 150.00, '2021-10-02', 2, 'Pagamento em cartão'),
('Pix', 200.00, '2021-10-03', 3, 'Pagamento em pix'),
('Boleto', 250.00, '2021-10-04', 4, 'Pagamento em boleto'),
('Transferência', 300.00, '2021-10-05', 5, 'Pagamento em transferência');

-- Tabela PedidoExame
INSERT INTO PedidoExame (nomeExame, dataSolicitacao, consulta_id, observacao, laboratorio) VALUES
('Exame de Sangue', '2021-10-01', 1, 'Exame de rotina', 'Laboratório A'),
('Exame de Urina', '2021-10-02', 2, 'Exame de rotina', 'Laboratório B'),
('Exame de Fezes', '2021-10-03', 3, 'Exame de rotina', 'Laboratório C'),
('Raio-X', '2021-10-04', 4, 'Exame de rotina', 'Laboratório D'),
('Ultrassom', '2021-10-05', 5, 'Exame de rotina', 'Laboratório E');