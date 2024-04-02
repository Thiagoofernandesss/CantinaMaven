/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass

/**
 *
 * @author Thiago
 */
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String nome;
    @Column
    private String fone1;
    @Column
    private String fone2;
    @Column
    private String email;
    @Column
    private char status;
    @Column
    private String complementoEndereco;
    
    @JoinColumn
    @ManyToOne
    private Endereco endereco;
    
    

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String fone1, String fone2, String email, char status, String complementoEndereco, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.fone1 = fone1;
        this.fone2 = fone2;
        this.email = email;
        this.status = status;
        this.complementoEndereco = complementoEndereco;
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }



    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFone1() {
        return fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public String getEmail() {
        return email;
    }

    public char getStatus() {
        return status;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
    public void setStatus(boolean statusInformado) {
        if(statusInformado == false){
            this.status = 'A';
        } else{
            this.status = 'I';
        }
        
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    @Override
    public String toString() {
        return this.getId() + ", " 
                + this.getNome() + ", " 
                + this.getFone1() + ", " 
                + this.getFone2() + ", " 
                + this.getEmail() + ", " 
                + this.getStatus() + ", " 
                + this.getComplementoEndereco() + ", "
                + this.getEndereco().toString();
    }

}
