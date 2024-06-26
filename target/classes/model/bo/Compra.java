/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity

/**
 *
 * @author Thiago
 */
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private int numeroNF;
    
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraCompra;
    
    @Column
    private float valorDesconto;
    
    @Column
    private char flagTipoDesconto;
    
    @Column
    private char status;
    
    @Column
    private String observacao;
    
    @JoinColumn
    @ManyToOne
    private Fornecedor fornecedor;

    public Compra() {
    }

    public Compra(int id, int numeroNF, Date dataHoraCompra, float valorDesconto, char flagTipoDesconto, char status, String observacao, Fornecedor fornecedor) {
        this.id = id;
        this.numeroNF = numeroNF;
        this.dataHoraCompra = dataHoraCompra;
        this.valorDesconto = valorDesconto;
        this.flagTipoDesconto = flagTipoDesconto;
        this.status = status;
        this.observacao = observacao;
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }



    public int getId() {
        return id;
    }

    public int getNumeroNF() {
        return numeroNF;
    }

    public Date getDataHoraCompra() {
        return dataHoraCompra;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public char getFlagTipoDesconto() {
        return flagTipoDesconto;
    }

    public char getStatus() {
        return status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumeroNF(int numeroNF) {
        this.numeroNF = numeroNF;
    }

    public void setDataHoraCompra(Date dataHoraCompra) {
        this.dataHoraCompra = dataHoraCompra;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public void setFlagTipoDesconto(char flagTipoDesconto) {
        this.flagTipoDesconto = flagTipoDesconto;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return this.getId() + ", " 
                + this.getNumeroNF() + ", " 
                + this.getDataHoraCompra() + ", " 
                + this.getValorDesconto() + ", " 
                + this.getFlagTipoDesconto() + ", " 
                + this.getStatus() + ", " 
                + this.getObservacao() + ", "
                + this.fornecedor.getCnpj() + ", "
                + this.fornecedor.getRazaoSocial();

    }

}
