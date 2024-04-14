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
import model.bo.Funcionario;
import view.BuscaFuncionario;

/**
 *
 * @author Thiago and Gabrieli
 */
public class ControllerBuscaFuncionario implements ActionListener {

    BuscaFuncionario buscaFuncionario;

    public ControllerBuscaFuncionario(BuscaFuncionario buscaFuncionario) {
        this.buscaFuncionario = buscaFuncionario;
        this.buscaFuncionario.getjButtonFiltrar().addActionListener(this);
        this.buscaFuncionario.getjButtonCarregar().addActionListener(this);
        this.buscaFuncionario.getjComboBoxBuscaFuncionariosPor().addActionListener(this);
        this.buscaFuncionario.getjButtonSair().addActionListener(this);
        this.buscaFuncionario.getjTextFieldFiltrar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buscaFuncionario.getjButtonFiltrar()) {
            DefaultTableModel tabela = (DefaultTableModel) this.buscaFuncionario.getjTableDados().getModel();
            tabela.setRowCount(0); // Limpa a tabela

            String filtro = this.buscaFuncionario.getjTextFieldFiltrar().getText().trim();

            if (filtro.isEmpty()) {
                // Se o campo de filtro estiver vazio, exibe todos os funcionários
                List<Funcionario> listaFuncionarios = service.FuncionarioService.carregar();

                for (Funcionario funcionarioAtual : listaFuncionarios) {
                    tabela.addRow(new Object[]{
                        funcionarioAtual.getId(),
                        funcionarioAtual.getNome(),
                        funcionarioAtual.getCpf(),
                        funcionarioAtual.getRg(),
                        funcionarioAtual.getUsuario(),
                        funcionarioAtual.getStatus()
                    });
                }
            } else {
                List<Funcionario> listaFuncionarios = new ArrayList<>();

                if (this.buscaFuncionario.getjComboBoxBuscaFuncionariosPor().getSelectedItem().toString().equalsIgnoreCase("id")) {
                    try {
                        int id = Integer.parseInt(filtro);
                        listaFuncionarios.add(service.FuncionarioService.carregar(id));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O ID deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (this.buscaFuncionario.getjComboBoxBuscaFuncionariosPor().getSelectedItem().toString().equalsIgnoreCase("usuário")) {
                    listaFuncionarios = service.FuncionarioService.carregar("usuario", this.buscaFuncionario.getjTextFieldFiltrar().getText());
                    
                } else{
                    listaFuncionarios = service.FuncionarioService.carregar(this.buscaFuncionario.getjComboBoxBuscaFuncionariosPor()
                            .getSelectedItem().toString().trim().toLowerCase(), this.buscaFuncionario.getjTextFieldFiltrar().getText());
                }

                for (Funcionario funcionarioAtual : listaFuncionarios) {
                    tabela.addRow(new Object[]{
                        funcionarioAtual.getId(),
                        funcionarioAtual.getNome(),
                        funcionarioAtual.getCpf(),
                        funcionarioAtual.getRg(),
                        funcionarioAtual.getUsuario(),
                        funcionarioAtual.getStatus()
                    });
                }
            }
        } else if (e.getSource() == this.buscaFuncionario.getjButtonCarregar()) {
            controller.ControllerCadastroFuncionario.codigo = (int) this.buscaFuncionario.getjTableDados().
                    getValueAt(this.buscaFuncionario.getjTableDados().getSelectedRow(), 0);

            this.buscaFuncionario.dispose();

        } else if (e.getSource() == this.buscaFuncionario.getjButtonSair()) {
            this.buscaFuncionario.dispose();
        }
    }

}
