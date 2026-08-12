CREATE TABLE IF NOT EXISTS tb_usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'ALUNO'
);

CREATE TABLE IF NOT EXISTS tb_exercicio (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    grupo_muscular VARCHAR(100),
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS tb_treino (
    id BIGSERIAL PRIMARY KEY,
    aluno_id UUID REFERENCES tb_usuario(id),
    nome VARCHAR(100) NOT NULL,
    observacao TEXT
);

CREATE TABLE IF NOT EXISTS tb_item_treino (
    id BIGSERIAL PRIMARY KEY,
    treino_id BIGINT REFERENCES tb_treino(id),
    exercicio_id BIGINT REFERENCES tb_exercicio(id),
    series INT,
    repeticoes INT,
    carga_kg NUMERIC(5,2)
);

CREATE TABLE IF NOT EXISTS tb_ficha_treino (
    id BIGSERIAL PRIMARY KEY,
    aluno_id UUID REFERENCES tb_usuario(id),
    data_inicio DATE,
    data_fim DATE
);

CREATE TABLE IF NOT EXISTS tb_avaliacao_fisica (
    id BIGSERIAL PRIMARY KEY,
    aluno_id UUID REFERENCES tb_usuario(id),
    instrutor_id UUID REFERENCES tb_usuario(id),
    peso_kg NUMERIC(5,2),
    altura_m NUMERIC(3,2),
    data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_agendamento (
    id BIGSERIAL PRIMARY KEY,
    aluno_id UUID REFERENCES tb_usuario(id),
    data_hora TIMESTAMP NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDENTE'
);

CREATE TABLE IF NOT EXISTS tb_mensalidade (
    id BIGSERIAL PRIMARY KEY,
    aluno_id UUID REFERENCES tb_usuario(id),
    valor NUMERIC(10,2) NOT NULL,
    data_vencimento DATE NOT NULL,
    paga BOOLEAN DEFAULT FALSE
);