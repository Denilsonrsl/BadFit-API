ALTER TABLE tb_usuario ENABLE ROW LEVEL SECURITY;
ALTER TABLE tb_exercicio ENABLE ROW LEVEL SECURITY;
ALTER TABLE tb_treino ENABLE ROW LEVEL SECURITY;
ALTER TABLE tb_item_treino ENABLE ROW LEVEL SECURITY;
ALTER TABLE tb_ficha_treino ENABLE ROW LEVEL SECURITY;
ALTER TABLE tb_avaliacao_fisica ENABLE ROW LEVEL SECURITY;
ALTER TABLE tb_agendamento ENABLE ROW LEVEL SECURITY;
ALTER TABLE tb_mensalidade ENABLE ROW LEVEL SECURITY;

-- USUÁRIOS
DROP POLICY IF EXISTS "Permitir usuario visualizar seu proprio perfil" ON tb_usuario;
CREATE POLICY "Permitir usuario visualizar seu proprio perfil"
ON tb_usuario FOR SELECT TO authenticated
USING (auth.uid() = id OR auth.jwt() ->> 'role' IN ('ADMIN', 'INSTRUTOR'));

DROP POLICY IF EXISTS "Permitir usuario atualizar seu proprio perfil" ON tb_usuario;
CREATE POLICY "Permitir usuario atualizar seu proprio perfil"
ON tb_usuario FOR UPDATE TO authenticated
USING (auth.uid() = id);

-- EXERCÍCIOS
DROP POLICY IF EXISTS "Leitura de exercicios para todos os autenticados" ON tb_exercicio;
CREATE POLICY "Leitura de exercicios para todos os autenticados"
ON tb_exercicio FOR SELECT TO authenticated
USING (true);

DROP POLICY IF EXISTS "Gerenciamento de exercicios apenas para instrutores/admins" ON tb_exercicio;
CREATE POLICY "Gerenciamento de exercicios apenas para instrutores/admins"
ON tb_exercicio FOR ALL TO authenticated
USING (auth.jwt() ->> 'role' IN ('ADMIN', 'INSTRUTOR'));

-- TREINOS
DROP POLICY IF EXISTS "Alunos veem apenas seus treinos" ON tb_treino;
CREATE POLICY "Alunos veem apenas seus treinos"
ON tb_treino FOR SELECT TO authenticated
USING (aluno_id = auth.uid() OR auth.jwt() ->> 'role' IN ('ADMIN', 'INSTRUTOR'));

DROP POLICY IF EXISTS "Criacao e alteracao de treinos" ON tb_treino;
CREATE POLICY "Criacao e alteracao de treinos"
ON tb_treino FOR ALL TO authenticated
USING (aluno_id = auth.uid() OR auth.jwt() ->> 'role' IN ('ADMIN', 'INSTRUTOR'))
WITH CHECK (aluno_id = auth.uid() OR auth.jwt() ->> 'role' IN ('ADMIN', 'INSTRUTOR'));

-- AVALIAÇÃO FÍSICA
DROP POLICY IF EXISTS "Visualizacao de avaliacaofisica" ON tb_avaliacao_fisica;
CREATE POLICY "Visualizacao de avaliacaofisica"
ON tb_avaliacao_fisica FOR SELECT TO authenticated
USING (aluno_id = auth.uid() OR auth.jwt() ->> 'role' IN ('ADMIN', 'INSTRUTOR'));

DROP POLICY IF EXISTS "Criacao de avaliacao por instrutores" ON tb_avaliacao_fisica;
CREATE POLICY "Criacao de avaliacao por instrutores"
ON tb_avaliacao_fisica FOR INSERT TO authenticated
WITH CHECK (auth.jwt() ->> 'role' IN ('ADMIN', 'INSTRUTOR'));

-- MENSALIDADE
DROP POLICY IF EXISTS "Alunos veem suas mensalidades" ON tb_mensalidade;
CREATE POLICY "Alunos veem suas mensalidades"
ON tb_mensalidade FOR SELECT TO authenticated
USING (aluno_id = auth.uid() OR auth.jwt() ->> 'role' = 'ADMIN');

DROP POLICY IF EXISTS "Apenas admin gerencia mensalidades" ON tb_mensalidade;
CREATE POLICY "Apenas admin gerencia mensalidades"
ON tb_mensalidade FOR ALL TO authenticated
USING (auth.jwt() ->> 'role' = 'ADMIN');