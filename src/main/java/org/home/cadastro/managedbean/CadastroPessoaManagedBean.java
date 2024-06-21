package org.home.cadastro.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import org.home.cadastro.dao.PessoaDAO;
import javax.enterprise.inject.Model;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.home.cadastro.dao.EnderecoDAO;
import org.home.cadastro.model.EnderecoModel;
import org.home.cadastro.model.PessoaModel;

@Model
@ViewScoped
public class CadastroPessoaManagedBean implements Serializable {
    
    @Inject
    private PessoaDAO pessoaDAO;
    
    @Inject
    private EnderecoDAO enderecoDAO;
    
    private PessoaModel pessoaSelecionado = new PessoaModel();
    
    private List<PessoaModel> pessoas;
    
    private String voltar;

    private List<String> sexos = new ArrayList<>(Arrays.asList("M", "F", "I"));
    
    @SessionScoped
    private EnderecoModel enderecoSelecionado;
    
    public EnderecoModel getEnderecoSelecionado() {
        if(enderecoSelecionado == null){
            enderecoSelecionado = new EnderecoModel();
        }
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(EnderecoModel enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public String getVoltar() {
        return voltar;
    }

    public void setVoltar(String voltar) {
        this.voltar = voltar;
    }
    
    public List<PessoaModel> getPessoas() {
        if(this.pessoas == null){
            this.pessoas = pessoaDAO.ListarTodasPessoas();
        }
        
        return pessoas;
    }

    public void setPessoas(List<PessoaModel> pessoas) {
        this.pessoas = pessoas;
    }
    
    public List<String> getSexos() {
        return sexos;
    }

    public void setSexos(List<String> sexos) {
        this.sexos = sexos;
    }

    public PessoaModel getPessoaSelecionado() {
        return pessoaSelecionado;
    }

    public void setPessoaSelecionado(PessoaModel pessoaSelecionado) {
        this.pessoaSelecionado = pessoaSelecionado;
    }
    
    public PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public PessoaModel getNovaPessoa() {
        return pessoaSelecionado;
    }

    public void setNovaPessoa(PessoaModel novaPessoa) {
        this.pessoaSelecionado = novaPessoa;
    }
    
    public String Salvar(){
        pessoaDAO.Salvar(pessoaSelecionado);
        this.pessoaSelecionado = new PessoaModel();
        return "ListaPessoa";
    }
    
    public String ListarPessoas(){
        return "ListaPessoa";
    }
    
    public String ListarEnderecos(PessoaModel p){
        voltar = "ListaPessoa";
        this.pessoaSelecionado = p;
        return "ListaEndereco";
    }
    
    public String EditarPessoa(PessoaModel p){
        voltar = "ListaPessoa";
        this.pessoaSelecionado = p;
        return "CadastroNovaPessoa";
    }
    
    public void ExcluirPessoa(PessoaModel p){
        pessoaDAO.Excluir(p);
        pessoas.remove(p);
    }
    
    public String CadastrarNovaPessoa(){
        voltar = "inicio";
        return "CadastroNovaPessoa";
    }
    
    
    public String CadastrarNovoEndereco(){
        PessoaModel p = pessoaDAO.GetPessoaByID(pessoaSelecionado.getId());
        pessoaSelecionado = p;
        
        return "CadEndereco";
    }
    
    public String VoltarInicio(){
        return "inicio";
    }
    
    public String Voltar(){
        System.out.println("VOLTAR SEM PARAMETRO");
        return voltar;
    }
    
    public String Voltar(String pagina){
        return pagina;
    }
    
    public String VoltarListaEnderecosFromCadEndereco(long IdPessoa){
        
        PessoaModel p = pessoaDAO.GetPessoaByID(pessoaSelecionado.getId());
        pessoaSelecionado = p;
        
        return "ListaEndereco";
    }
    
    public String SalvarEndereco(){        
        PessoaModel p = pessoaDAO.GetPessoaByID(pessoaSelecionado.getId());
        enderecoSelecionado.setPessoa(p);
        enderecoDAO.Salvar(enderecoSelecionado);
        
        pessoaSelecionado = pessoaDAO.GetPessoaByID(p.getId());
        
        return "ListaEndereco";
    }
    
    public void ExcluirEndereco(EnderecoModel end){
        //enderecoDAO.Excluir(end);
        System.out.println("EXCLUIR ENDEREÇO");
        //System.out.println("ID PESSOA SELECIONADA: " + pessoaSelecionado.getId());
        
        //PessoaModel p = end.getPessoa();
        //p.getEnderecos().remove(end);
        
        //pessoas.remove(end);
    }
    
}
