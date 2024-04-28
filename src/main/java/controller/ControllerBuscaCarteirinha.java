/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.Carteirinha;
import view.BuscaCarteirinha;

/**
 *
 * @author Thiago and Gabrieli
 */
public class ControllerBuscaCarteirinha implements ActionListener{
    
    BuscaCarteirinha buscaCarteirinha;

    public ControllerBuscaCarteirinha(BuscaCarteirinha buscaCarteirinha) {
        
        this.buscaCarteirinha = buscaCarteirinha;
        
        this.buscaCarteirinha.getjButtonFiltrar().addActionListener(this);
        this.buscaCarteirinha.getjButtonCarregar().addActionListener(this);
        this.buscaCarteirinha.getjButtonSair().addActionListener(this);
        this.buscaCarteirinha.getjComboBoxBuscaCarteirinhaPor().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buscaCarteirinha.getjButtonFiltrar()) {
            DefaultTableModel tabela = (DefaultTableModel) this.buscaCarteirinha.getjTableDados().getModel();
            tabela.setRowCount(0); // Limpa a tabela
    
            String filtro = this.buscaCarteirinha.getjTextFieldFiltrar().getText().trim();
    
            if (filtro.isEmpty()) {
                List<Carteirinha> listaCarteirinha = service.CarteirinhaService.carregar();
        
                for (Carteirinha carteirinhaAtual : listaCarteirinha) {
                    tabela.addRow(new Object[]{
                        carteirinhaAtual.getId(),
                        carteirinhaAtual.getCliente().getNome(),
                        carteirinhaAtual.getCodigoBarra(),
                        carteirinhaAtual.getDataCancelamento()
                    });
                }
                
            } else {
                List<Carteirinha> listaCarteirinha = new ArrayList<>();
        
                if (this.buscaCarteirinha.getjComboBoxBuscaCarteirinhaPor().getSelectedItem().toString().equalsIgnoreCase("id")) {
                    try {
                       int id = Integer.parseInt(filtro);
                       listaCarteirinha.add(service.CarteirinhaService.carregar(id));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (this.buscaCarteirinha.getjComboBoxBuscaCarteirinhaPor().getSelectedItem().toString().equalsIgnoreCase("código barra")) {
                    listaCarteirinha = service.CarteirinhaService.carregar("codigoBarra", this.buscaCarteirinha.getjTextFieldFiltrar().getText());
                } else if (this.buscaCarteirinha.getjComboBoxBuscaCarteirinhaPor().getSelectedItem().toString().equalsIgnoreCase("nome")) {
                    listaCarteirinha = service.CarteirinhaService.carregar("cliente.nome", this.buscaCarteirinha.getjTextFieldFiltrar().getText());
                }else if (this.buscaCarteirinha.getjComboBoxBuscaCarteirinhaPor().getSelectedItem().toString().equalsIgnoreCase("data geração")) {
                    listaCarteirinha = service.CarteirinhaService.carregar("dataGeracao", filtro);
                }else{
                    listaCarteirinha = service.CarteirinhaService.carregar(this.buscaCarteirinha.getjComboBoxBuscaCarteirinhaPor()
                            .getSelectedItem().toString().trim().toLowerCase(), this.buscaCarteirinha.getjTextFieldFiltrar().getText());
                }
               
                for (Carteirinha carteirinhaAtual : listaCarteirinha) {
                    tabela.addRow(new Object[]{
                    carteirinhaAtual.getId(),
                    carteirinhaAtual.getCliente().getNome(),
                    carteirinhaAtual.getCodigoBarra(),
                    carteirinhaAtual.getDataCancelamento()       
                });
            
        }
        }}else if(e.getSource() == this.buscaCarteirinha.getjButtonCarregar()){
            controller.ControllerCadastroCarteirinha.codigo = (int) this.buscaCarteirinha.getjTableDados().
            getValueAt(this.buscaCarteirinha.getjTableDados().getSelectedRow(), 0);
            
            this.buscaCarteirinha.dispose();
            
        }else if(e.getSource() == this.buscaCarteirinha.getjButtonSair()){
            this.buscaCarteirinha.dispose();
            
        }
    }   
}
