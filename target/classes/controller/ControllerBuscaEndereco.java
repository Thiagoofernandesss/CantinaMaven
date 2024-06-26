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
import model.bo.Endereco;
import sun.print.resources.serviceui;
import view.BuscaEndereco;

/**
 *
 * @author Thiago
 */
public class ControllerBuscaEndereco implements ActionListener {

    BuscaEndereco buscaEndereco;

    public ControllerBuscaEndereco(BuscaEndereco buscaEndereco) {
        this.buscaEndereco = buscaEndereco;
        this.buscaEndereco.getjButtonFiltrar().addActionListener(this);
        this.buscaEndereco.getjButtonCarregar().addActionListener(this);
        this.buscaEndereco.getjButtonSair().addActionListener(this);
        this.buscaEndereco.getjComboBoxBuscaEnderecoPor().addActionListener(this);
        this.buscaEndereco.getjTextFieldFiltrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buscaEndereco.getjButtonFiltrar()) {
            DefaultTableModel tabela = (DefaultTableModel) this.buscaEndereco.getjTableDados().getModel();
            tabela.setRowCount(0);

            String filtro = this.buscaEndereco.getjTextFieldFiltrar().getText().trim();

            if (filtro.isEmpty()) {
                List<Endereco> listaEnderecos = service.EnderecoService.carregar();

                for (Endereco enderecoAtual : listaEnderecos) {
                    tabela.addRow(new Object[]{enderecoAtual.getId(),
                        enderecoAtual.getCep(),
                        enderecoAtual.getLogradouro(),
                        enderecoAtual.getStatus(),
                        enderecoAtual.getCidade().getDescricao(),
                        enderecoAtual.getBairro().getDescricao()});
                }
            } else {
                List<Endereco> listaEnderecos = new ArrayList<Endereco>();

                if (this.buscaEndereco.getjComboBoxBuscaEnderecoPor().getSelectedItem().toString().equalsIgnoreCase("id")) {
                    try {
                        int id = Integer.parseInt(filtro);
                        listaEnderecos.add(service.EnderecoService.carregar(id));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                } else if (this.buscaEndereco.getjComboBoxBuscaEnderecoPor().getSelectedItem().toString().equalsIgnoreCase("cidade")) {
                    listaEnderecos = service.EnderecoService.carregar("cidade.descricao", this.buscaEndereco.getjTextFieldFiltrar().getText());
                } else if (this.buscaEndereco.getjComboBoxBuscaEnderecoPor().getSelectedItem().toString().equalsIgnoreCase("bairro")) {
                    listaEnderecos = service.EnderecoService.carregar("bairro.descricao", this.buscaEndereco.getjTextFieldFiltrar().getText());
                } else {
                    listaEnderecos = service.EnderecoService.carregar(this.buscaEndereco.getjComboBoxBuscaEnderecoPor().getSelectedItem().toString().trim().toLowerCase(),
                            this.buscaEndereco.getjTextFieldFiltrar().getText());
                }
                
                 for (Endereco enderecoAtual : listaEnderecos) {
                    tabela.addRow(new Object[]{enderecoAtual.getId(),
                        enderecoAtual.getCep(),
                        enderecoAtual.getLogradouro(),
                        enderecoAtual.getStatus(),
                        enderecoAtual.getCidade().getDescricao(),
                        enderecoAtual.getBairro().getDescricao()});
                }
                

            }

        } else if (e.getSource() == this.buscaEndereco.getjButtonCarregar()) {
            controller.ControllerCadastroEndereco.codigo = (int) this.buscaEndereco.getjTableDados().
                    getValueAt(this.buscaEndereco.getjTableDados().getSelectedRow(), 0);
            this.buscaEndereco.dispose();

        } else if (e.getSource() == this.buscaEndereco.getjButtonSair()) {
            this.buscaEndereco.dispose();
        }
    }

}
