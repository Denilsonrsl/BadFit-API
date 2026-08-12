INSERT INTO tb_usuario (id, nome, email, senha, role) VALUES
('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Admin Master', 'admin@badfit.com', '123456', 'ADMIN'),
('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 'Instrutor Carlos', 'carlos@badfit.com', '123456', 'INSTRUTOR'),
('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 'Aluno João', 'joao@badfit.com', '123456', 'ALUNO'),
('b2eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 'Instrutora Ana', 'ana@badfit.com', '123456', 'INSTRUTOR'),
('c3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', 'Aluna Maria', 'maria@badfit.com', '123456', 'ALUNO'),
('c4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55', 'Aluno Pedro', 'pedro@badfit.com', '123456', 'ALUNO')
ON CONFLICT (email) DO NOTHING;

INSERT INTO tb_exercicio (nome, grupo_muscular, descricao) VALUES
('Supino Reto', 'Peito', 'Exercício com barra no banco plano'),
('Agachamento Livre', 'Pernas', 'Agachamento completo com barra'),
('Puxada Frontal', 'Costas', 'Puxada na polia alta'),
('Leg Press 45', 'Pernas', 'Flexão e extensão de joelhos no aparelho'),
('Rosca Direta', 'Bíceps', 'Exercício com barra reta para bíceps'),
('Tríceps Corda', 'Tríceps', 'Extensão de cotovelos na polia alta com corda'),
('Desenvolvimento com Halteres', 'Ombros', 'Elevação vertical para deltoides');

INSERT INTO tb_treino (aluno_id, nome, observacao) VALUES
('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 'Treino A - Peito e Tríceps', 'Intervalo de 60 segundos entre séries'),
('c3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', 'Treino A - Pernas e Glúteos', 'Manter cadência 2x2'),
('c4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55', 'Treino ABC - Ombros e Braços', 'Carga moderada');

INSERT INTO tb_item_treino (treino_id, exercicio_id, series, repeticoes, carga_kg) VALUES
(1, 1, 4, 10, 30.00),
(2, 4, 4, 12, 80.00),
(3, 5, 3, 12, 10.00),
(3, 6, 3, 15, 25.00);

INSERT INTO tb_ficha_treino (aluno_id, data_inicio, data_fim) VALUES
('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', '2026-08-01', '2026-09-01'),
('c3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', '2026-08-01', '2026-10-01'),
('c4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55', '2026-08-10', '2026-09-10');

INSERT INTO tb_avaliacao_fisica (aluno_id, instrutor_id, peso_kg, altura_m) VALUES
('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 75.50, 1.78),
('c3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', 'b2eebc99-9c0b-4ef8-bb6d-6bb9bd380a23', 62.00, 1.65),
('c4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22', 82.30, 1.81);

INSERT INTO tb_agendamento (aluno_id, data_hora, status) VALUES
('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', '2026-08-15 10:00:00', 'PENDENTE'),
('c3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', '2026-08-16 14:00:00', 'CONFIRMADO'),
('c4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55', '2026-08-17 09:00:00', 'PENDENTE');

INSERT INTO tb_mensalidade (aluno_id, valor, data_vencimento, paga) VALUES
('c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33', 120.00, '2026-08-30', false),
('c3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44', 120.00, '2026-08-25', true),
('c4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55', 120.00, '2026-08-20', false);