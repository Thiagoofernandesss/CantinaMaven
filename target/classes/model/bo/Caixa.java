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
public class Caixa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraAbertura;
    
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHoraFechamento;
    
    @Column
    private float valorAbertura;
    
    @Column
    private float valorFechamento;
    
    @Column
    private String observacao;
    
    @Column
    private char status;
    
    @JoinColumn
    @ManyToOne
    private Funcionario funcionario;

    public Caixa() {
    }

    public Caixa(int id, Date dataHoraAbertura, Date dataHoraFechamento, float valorAbertura, float valorFechamento, String observaccao, char status, Funcionario funcionario) {
        this.id = id;
        this.dataHoraAbertura = dataHoraAbertura;
        this.dataHoraFechamento = dataHoraFechamento;
        this.valorAbertura = valorAbertura;
        this.valorFechamento = valorFechamento;
        this.observacao = observaccao;
        this.status = status;
        this.funcionario = funcionario;
    }

    public Date getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public void setDataHoraAbertura(Date dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }



    public int getId() {
        return id;
    }


    public Date getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    public float getValorAbertura() {
        return valorAbertura;
    }

    public float getValorFechamento() {
        return valorFechamento;
    }

    public String getObservaccao() {
        return observacao;
    }

    public char getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataHoraFechamento(Date dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    public void setValorAbertura(float valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public void setValorFechamento(float valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public void setObservaccao(String observaccao) {
        this.observacao = observaccao;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
    public void setStatu(boolean statusInformado) {
        if(statusInformado == false){
            this.status = 'A';
        } else{
            this.status = 'I';
    }
        
   }

    @Override
    public String toString() {
        return this.getId() + ", " 
                + this.getDataHoraAbertura() + ", " 
                + this.getDataHoraFechamento() + ", " 
                + this.getValorAbertura() + ", " 
                + this.getValorFechamento() + ", " 
                + this.getObservaccao() + ", " 
                + this.getStatus() + ", "
                + this.funcionario.getUsuario();

    }

}
