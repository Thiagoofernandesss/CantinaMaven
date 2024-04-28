/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.Fornecedor;
import view.BuscaFornecedor;

/**
 *
 * @author Thiago and Gabrieli
 */
public class ControllerBuscaFornecedor implements ActionListener {

    BuscaFornecedor buscaFornecedor;

    public ControllerBuscaFornecedor(BuscaFornecedor buscaFornecedor) {
        this.buscaFornecedor = buscaFornecedor;
        this.buscaFornecedor.getjButtonFiltrar().addActionListener(this);
        this.buscaFornecedor.getjButtonCarregar().addActionListener(this);
        this.buscaFornecedor.getjButtonSair().addActionListener(this);
        this.buscaFornecedor.getjComboBoxBuscaFornecedoresPor().addActionListener(this);
        this.buscaFornecedor.getjTextFieldFiltrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.buscaFornecedor.getjButtonFiltrar()) {
            DefaultTableModel tabela = (DefaultTableModel) this.buscaFornecedor.getjTableDados().getModel();
            tabela.setRowCount(0); // Limpa a tabela

            String filtro = this.buscaFornecedor.getjTextFieldFiltrar().getText().trim();

            if (filtro.isEmpty()) {
                List<Fornecedor> listaFornecedor = service.FornecedorService.carregar();

                for (Fornecedor fornecedorAtual : listaFornecedor) {
                    tabela.addRow(new Object[]{
                        fornecedorAtual.getId(),
                        fornecedorAtual.getCnpj(),
                        fornecedorAtual.getRazaoSocial(),
                        fornecedorAtual.getInscricaoEstadual(),
                        fornecedorAtual.getStatus()
                    });
                }
            } else {
                List<Fornecedor> listaFornecedor = new ArrayList<>();

                if (this.buscaFornecedor.getjComboBoxBuscaFornecedoresPor().getSelectedItem().toString().equalsIgnoreCase("id")) {
                    try {
                        int id = Integer.parseInt(filtro);
                        listaFornecedor.add(service.FornecedorService.carregar(id));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (this.buscaFornecedor.getjComboBoxBuscaFornecedoresPor().getSelectedItem().toString().equalsIgnoreCase("razão social")) {
                    listaFornecedor = service.FornecedorService.carregar("razaoSocial", this.buscaFornecedor.getjTextFieldFiltrar().getText());
                } else if (this.buscaFornecedor.getjComboBoxBuscaFornecedoresPor().getSelectedItem().toString().equalsIgnoreCase("inscrição estadual")) {
                    listaFornecedor = service.FornecedorService.carregar("inscricaoEstadual", this.buscaFornecedor.getjTextFieldFiltrar().getText());
                } else {
                    listaFornecedor = service.FornecedorService.carregar(this.buscaFornecedor.getjComboBoxBuscaFornecedoresPor()
                            .getSelectedItem().toString().trim().toLowerCase(), this.buscaFornecedor.getjTextFieldFiltrar().getText());
                }
                
                for (Fornecedor fornecedorAtual : listaFornecedor) {
                    tabela.addRow(new Object[]{
                        fornecedorAtual.getId(),
                        fornecedorAtual.getCnpj(),
                        fornecedorAtual.getRazaoSocial(),
                        fornecedorAtual.getInscricaoEstadual(),
                        fornecedorAtual.getStatus()
                    });
                }
            }
        } else if (e.getSource() == this.buscaFornecedor.getjButtonCarregar()) {
            controller.ControllerCadastroFornecedor.codigo = (int) this.buscaFornecedor.getjTableDados().
                    getValueAt(this.buscaFornecedor.getjTableDados().getSelectedRow(), 0);

            this.buscaFornecedor.dispose();

        } else if (e.getSource() == this.buscaFornecedor.getjButtonSair()) {
            this.buscaFornecedor.dispose();
        }

    }

}
