/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import view.PontoVenda;

@Entity

/**
 *
 * @author Thiago
 */
public class ItemVenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private int qtdProduto;
    
    @Column
    private float valorUnitario;
    
    @Column
    private char status;
    
    @JoinColumn
    @ManyToOne
    private Venda venda;
    
    @JoinColumn
    @ManyToOne
    private Produto produto;
    

    public ItemVenda() {
    }

    public ItemVenda(int id, int qtdProduto, float valorUnitario, char status, Venda venda, Produto produto) {
        this.id = id;
        this.qtdProduto = qtdProduto;
        this.status = status;
        this.venda = venda;
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }



    public int getId() {
        return id;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public char getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.getId() + ", " 
                + this.getQtdProduto() + ", " 
                + this.getStatus() + ", "
                + this.venda.getId() + ", "
                + this.produto.getId() + ", "
                + this.produto.getDescricao();

    }

}
