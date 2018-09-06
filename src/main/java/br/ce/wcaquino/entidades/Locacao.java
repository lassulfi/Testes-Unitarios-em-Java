package br.ce.wcaquino.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Locacao {

    private Usuario usuario;
    private Filme filme;
    private List<Filme> filmes;
    private Date dataLocacao;
    private Date dataRetorno;
    private Double valor;

    public Locacao() {
        this.filmes = new ArrayList<Filme>();
        this.valor = 0.0;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Double getValor() {

        return valor;
    }

    public void setValor() {
        valor = 0.0;
        
        for (Filme filme : filmes) {
            valor += filme.getPrecoLocacao();
        }
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
        filmes.add(filme);
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public Filme getFilme(int pos) {
        return filmes.get(pos);
    }
}
