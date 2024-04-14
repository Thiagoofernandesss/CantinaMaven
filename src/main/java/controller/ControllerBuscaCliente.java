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
import model.bo.Cliente;
import view.BuscaCliente;

/**
 *
 * @author Thiago
 */
public class ControllerBuscaCliente implements ActionListener {

    BuscaCliente buscaCliente;

    public ControllerBuscaCliente(BuscaCliente buscaCliente) {
        this.buscaCliente = buscaCliente;
        this.buscaCliente.getjButtonFiltrar().addActionListener(this);
        this.buscaCliente.getjButtonCarregar().addActionListener(this);
        this.buscaCliente.getjButtonSair().addActionListener(this);
        this.buscaCliente.getjComboBoxBuscaClientesPor().addActionListener(this);
        this.buscaCliente.getjTextFieldFiltrar().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buscaCliente.getjButtonFiltrar()) {
            DefaultTableModel tabela = (DefaultTableModel) this.buscaCliente.getjTableDados().getModel();
            tabela.setRowCount(0); // Limpa a tabela

            String filtro = this.buscaCliente.getjTextFieldFiltrar().getText().trim();

            if (filtro.isEmpty()) {
                // Se o campo de filtro estiver vazio, exibe todos os clientes
                List<Cliente> listaClientes = service.ClienteService.carregar();
                for (Cliente clienteAtual : listaClientes) {
                    tabela.addRow(new Object[]{
                        clienteAtual.getId(),
                        clienteAtual.getNome(),
                        clienteAtual.getCpf(),
                        clienteAtual.getMatricula(),
                        clienteAtual.getStatus()});
                }
            } else {
                List<Cliente> listaClientes = new ArrayList<>();
                
                if(this.buscaCliente.getjComboBoxBuscaClientesPor().getSelectedItem().toString().equalsIgnoreCase("id")){
                    try {
                        int id = Integer.parseInt(filtro);
                        listaClientes.add(service.ClienteService.carregar(id));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }else if(this.buscaCliente.getjComboBoxBuscaClientesPor().getSelectedItem().toString().equalsIgnoreCase("matrícula")){
                    listaClientes = service.ClienteService.carregar("matricula", this.buscaCliente.getjTextFieldFiltrar().getText());
                
                } else{
                    listaClientes = service.ClienteService.carregar(this.buscaCliente.getjComboBoxBuscaClientesPor()
                            .getSelectedItem().toString().trim().toLowerCase(), this.buscaCliente.getjTextFieldFiltrar().getText());
                }

                for (Cliente clienteAtual : listaClientes) {
                    tabela.addRow(new Object[]{
                        clienteAtual.getId(),
                        clienteAtual.getNome(),
                        clienteAtual.getCpf(),
                        clienteAtual.getMatricula(),
                        clienteAtual.getStatus()
                    });
                }
            }
        } else if (e.getSource() == this.buscaCliente.getjButtonCarregar()) {
            controller.ControllerCadastroCliente.codigo = (int) this.buscaCliente.getjTableDados().
                    getValueAt(this.buscaCliente.getjTableDados().getSelectedRow(), 0);

            this.buscaCliente.dispose();

        } else if (e.getSource() == this.buscaCliente.getjButtonSair()) {
            this.buscaCliente.dispose();

        }

    }

}
