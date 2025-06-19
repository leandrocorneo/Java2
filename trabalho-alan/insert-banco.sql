INSERT INTO Departamento (nome) VALUES
('Ciência da Computação'),
('Matemática'),
('Engenharia');

INSERT INTO Professor (nome, titulacao, id_departamento) VALUES
('Dr. João Silva', 'Doutor', 1),
('Maria Oliveira', 'Mestre', 2),
('Carlos Souza', 'Especialista', 3);

INSERT INTO Disciplina (nome) VALUES
('Estrutura de Dados'),
('Cálculo I'),
('Física Geral');

INSERT INTO Curso (nome, duracao, id_departamento) VALUES
('Sistemas de Informação', 8, 1),
('Engenharia Civil', 10, 3);

INSERT INTO Turma (id_disciplina, id_professor) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO Matricula (nome, cpf, status, id_curso) VALUES
('Ana Lima', '12345678900', 'Ativa', 1),
('Pedro Martins', '98765432100', 'Trancada', 2);

INSERT INTO GradeCurricular (id_curso, id_disciplina, semestre_ideal) VALUES
(1, 1, 2),
(2, 2, 1),
(2, 3, 2);

INSERT INTO MatriculaTurma (id_matricula, id_turma) VALUES
(1, 1),
(2, 2);
