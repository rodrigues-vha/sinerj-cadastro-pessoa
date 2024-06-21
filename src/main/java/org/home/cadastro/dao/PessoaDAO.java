package org.home.cadastro.dao;

import java.util.List;
import org.home.cadastro.model.PessoaModel;

public interface PessoaDAO {
    public void Salvar(PessoaModel p);
    public List<PessoaModel> ListarTodasPessoas();
    public PessoaModel GetPessoaByID(long id);
    public void Excluir(PessoaModel p);
}
