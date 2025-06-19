CREATE TABLE Departamento (
    id_departamento INTEGER PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE Professor (
    id_professor INTEGER PRIMARY KEY,
    nome VARCHAR(100),
    titulacao VARCHAR(50),
    id_departamento INTEGER,
    FOREIGN KEY(id_departamento) REFERENCES Departamento (id_departamento)
);

CREATE TABLE Disciplina (
    id_disciplina INTEGER PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE Curso (
    id_curso INTEGER PRIMARY KEY,
    nome VARCHAR(100),
    duracao INTEGER,
    id_departamento INTEGER,
    FOREIGN KEY(id_departamento) REFERENCES Departamento (id_departamento)
);

CREATE TABLE Turma (
    id_turma INTEGER PRIMARY KEY,
    id_disciplina INTEGER,
    id_professor INTEGER,
    FOREIGN KEY(id_disciplina) REFERENCES Disciplina (id_disciplina),
    FOREIGN KEY(id_professor) REFERENCES Professor (id_professor)
);

CREATE TABLE Matricula (
    id_matricula INTEGER PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(11),
    status VARCHAR(20),
    id_curso INTEGER,
    FOREIGN KEY(id_curso) REFERENCES Curso (id_curso)
);

CREATE TABLE GradeCurricular (
    id_grade INTEGER PRIMARY KEY,
    id_curso INTEGER,
    id_disciplina INTEGER,
    semestre_ideal INTEGER,
    FOREIGN KEY(id_curso) REFERENCES Curso (id_curso),
    FOREIGN KEY(id_disciplina) REFERENCES Disciplina (id_disciplina)
);

CREATE TABLE MatriculaTurma (
    id_matricula_turma INTEGER PRIMARY KEY,
    id_matricula INTEGER,
    id_turma INTEGER,
    FOREIGN KEY(id_matricula) REFERENCES Matricula (id_matricula),
    FOREIGN KEY(id_turma) REFERENCES Turma (id_turma)
);
