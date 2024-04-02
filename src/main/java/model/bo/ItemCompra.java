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

@Entity

/**
 *
 * @author Thiago
 */
public class ItemCompra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private float qtdProduto;
    
    @Column
    private float valorUnitario;
    
    @Column
    private char status;

    @JoinColumn
    @ManyToOne
    private Produto produto;
    
    @JoinColumn
    @ManyToOne
    private Compra compra;

    public ItemCompra() {
    }

    public ItemCompra(int id, float qtdProduto, float valorUnitario, char status, Produto produto, Compra compra) {
        this.id = id;
        this.qtdProduto = qtdProduto;
        this.valorUnitario = valorUnitario;
        this.status = status;
        this.produto = produto;
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public int getId() {
        return id;
    }

    public float getQtdProduto() {
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

    public void setQtdProduto(float qtdProduto) {
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
                + this.getValorUnitario() + ", "
                + this.getStatus() + ", "
                + this.compra.getId() + ", "
                + this.produto.getId() + ", "
                + this.produto.getDescricao();

    }

}
